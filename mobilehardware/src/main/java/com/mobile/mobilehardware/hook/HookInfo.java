package com.mobile.mobilehardware.hook;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;

import com.mobile.mobilehardware.MobileNativeHelper;
import com.mobile.mobilehardware.utils.CommandUtil;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author guxiaonian
 */
class HookInfo {
    private static final String TAG = HookInfo.class.getSimpleName();

    /**
     * 判断是否有xposed等hook工具
     *
     * @param context
     * @return
     */
    static HookBean getXposedHook(Context context) {
        HookBean hookBean = new HookBean();
        HookBean.XposedBean xposedBean = new HookBean.XposedBean();
        HookBean.SubstrateBean substrateBean = new HookBean.SubstrateBean();
        HookBean.FridaBean fridaBean = new HookBean.FridaBean();
        try {
            chargeXposedPackage(context, xposedBean, substrateBean);
            chargeXposedHookMethod(xposedBean, substrateBean);
            chargeXposedJars(xposedBean, substrateBean, fridaBean);
            fridaBean.setCheckRunningProcesses(checkRunningProcesses(context));
            addMethod(xposedBean);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }

        substrateBean.setcSo(MobileNativeHelper.checkSubstrateBySo() == 1);
        String mapData = MobileNativeHelper.checkHookByMap();
        String packageData = MobileNativeHelper.checkHookByPackage();
        if (!TextUtils.isEmpty(mapData)) {
            if (mapData.contains("xposed")) {
                xposedBean.setcMap(true);
            }
            if (mapData.contains("frida")) {
                fridaBean.setcMap(true);
            }
            if (mapData.contains("substrate")) {
                substrateBean.setcMap(true);
            }
        }
        if (!TextUtils.isEmpty(packageData)) {
            if (packageData.contains("xposed")) {
                xposedBean.setcPackage(true);
            }
            if (packageData.contains("substrate")) {
                substrateBean.setcPackage(true);
            }
        }

        hookBean.setIsHaveXposed(xposedBean);
        hookBean.setIsHaveSubstrate(substrateBean);
        hookBean.setIsHaveFrida(fridaBean);
        return hookBean;
    }

    private static boolean checkRunningProcesses(Context context) {
        boolean returnValue = false;
        // Get currently running application processes
        ActivityManager activityManager = (ActivityManager) (context.getSystemService(Context.ACTIVITY_SERVICE));

        List<ActivityManager.RunningServiceInfo> list = activityManager.getRunningServices(300);
        if (list != null) {
            String tempName;
            for (int i = 0; i < list.size(); ++i) {
                tempName = list.get(i).process;
                if (tempName.contains("fridaserver")) {
                    returnValue = true;
                }
            }
        }
        return returnValue;
    }

    /**
     * 新增方法
     *
     * 是否安装了Xposed
     */
    private static void addMethod( HookBean.XposedBean xposedBean) {
        xposedBean.setCheckClassLoader(testClassLoader());
        xposedBean.setCheckNativeMethod(checkNativeMethod());
        xposedBean.setCheckSystem(checkSystem());
        xposedBean.setCheckExecLib(checkExecLib());
        xposedBean.setCheckXposedBridge(checkXposedBridge());

    }

    /**
     * 新增检测载入Xposed工具类
     * 方法参考自<url>https://github.com/w568w/XposedChecker/</url>
     *
     * @return 是否安装了Xposed
     */
    private static boolean testClassLoader() {
        try {
            ClassLoader.getSystemClassLoader()
                    .loadClass("de.robv.android.xposed.XposedHelpers");

            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 新增判断系统方法调用钩子
     * 方法参考自<url>https://github.com/w568w/XposedChecker/</url>
     *
     * @return 是否安装了Xposed
     */
    private static boolean checkNativeMethod() {
        try {
            Method method = Throwable.class.getDeclaredMethod("getStackTrace");
            return Modifier.isNative(method.getModifiers());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();


        }
        return false;
    }

    /**
     * 新增虚拟检测Xposed环境
     * 方法参考自<url>https://github.com/w568w/XposedChecker/</url>
     *
     * @return 是否安装了Xposed
     */
    private static boolean checkSystem() {
        return System.getProperty("vxp") != null;
    }

    /**
     * 寻找Xposed运行库文件
     * 方法参考自<url>https://github.com/w568w/XposedChecker/</url>
     *
     * @return 是否安装了Xposed
     */
    private static boolean checkExecLib() {
        String result = CommandUtil.getSingleInstance().exec("ls /system/lib");
        if (TextUtils.isEmpty(result)) {
            return false;
        }
        return result.contains("xposed");
    }


    /**
     * 环境变量特征字判断
     * 方法参考自<url>https://github.com/w568w/XposedChecker/</url>
     *
     * @return 是否安装了Xposed
     */
    private static boolean checkXposedBridge() {
        String result = System.getenv("CLASSPATH");
        if (TextUtils.isEmpty(result)) {
            return false;
        }
        return result.contains("XposedBridge");
    }


    /**
     * 检查包名是否存在
     *
     * @param context
     * @return
     */
    private static void chargeXposedPackage(Context context, HookBean.XposedBean xposedBean, HookBean.SubstrateBean substrateBean) {
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        List<ApplicationInfo> appliacationInfoList = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
        if (appliacationInfoList == null) {
            return;
        }
        for (ApplicationInfo item : appliacationInfoList) {
            //新增包名检测 方法参考自<url>https://github.com/w568w/XposedChecker/</url>
            if ("de.robv.android.xposed.installer".equals(item.packageName) || "io.va.exposed".equals(item.packageName)) {
                xposedBean.setCheckXposedPackage(true);
            }

            if ("com.saurik.substrate".equals(item.packageName)) {
                substrateBean.setCheckSubstratePackage(true);
            }
        }
    }

    /**
     * 检测调用栈中的可疑方法
     */
    private static void chargeXposedHookMethod(HookBean.XposedBean xposedBean, HookBean.SubstrateBean substrateBean) {

        try {

            throw new Exception("Deteck hook");

        } catch (Exception e) {

            int zygoteInitCallCount = 0;
            for (StackTraceElement item : e.getStackTrace()) {
                // 检测"com.android.internal.os.ZygoteInit"是否出现两次，如果出现两次，则表明Substrate框架已经安装
                if ("com.android.internal.os.ZygoteInit".equals(item.getClassName())) {
                    zygoteInitCallCount++;
                    if (zygoteInitCallCount == 2) {
                        substrateBean.setCheckSubstrateHookMethod(true);
                    }
                }

                if ("com.saurik.substrate.MS$2".equals(item.getClassName()) && "invoke".equals(item.getMethodName())) {
                    substrateBean.setCheckSubstrateHookMethod(true);
                }

                if ("de.robv.android.xposed.XposedBridge".equals(item.getClassName())
                        && "main".equals(item.getMethodName())) {
                    xposedBean.setCheckXposedHookMethod(true);
                }
                if ("de.robv.android.xposed.XposedBridge".equals(item.getClassName())
                        && "handleHookedMethod".equals(item.getMethodName())) {
                    xposedBean.setCheckXposedHookMethod(true);
                }

            }

        }
    }

    /**
     * 检测内存中可疑的jars
     */
    private static void chargeXposedJars(HookBean.XposedBean xposedBean, HookBean.SubstrateBean substrateBean, HookBean.FridaBean fridaBean) {
        Set<String> libraries = new HashSet<String>();
        String mapsFilename = "/proc/" + android.os.Process.myPid() + "/maps";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(mapsFilename));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.toLowerCase().contains("frida")) {
                    fridaBean.setCheckFridaJars(true);
                }
                if (line.endsWith(".so") || line.endsWith(".jar")) {
                    int n = line.lastIndexOf(" ");
                    libraries.add(line.substring(n + 1));
                }


            }
            for (String library : libraries) {
                if (library.contains("com.saurik.substrate")) {
                    substrateBean.setCheckSubstrateJars(true);
                }
                if (library.contains("XposedBridge.jar")) {
                    xposedBean.setCheckXposedJars(true);
                }
            }

            reader.close();
        } catch (Exception e) {
        }
    }
}
