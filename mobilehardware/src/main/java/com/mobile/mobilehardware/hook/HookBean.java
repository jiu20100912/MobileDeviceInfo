package com.mobile.mobilehardware.hook;

import android.util.Log;

import com.mobile.mobilehardware.base.BaseBean;
import com.mobile.mobilehardware.base.BaseData;

import org.json.JSONObject;

/**
 * @author guxiaonian
 */
public class HookBean extends BaseBean {
    private static final String TAG = HookBean.class.getSimpleName();

    /**
     * Xposed详细信息
     */
    private XposedBean isHaveXposed;

    /**
     * Substrate详细信息
     */
    private SubstrateBean isHaveSubstrate;

    /**
     * Frida详细信息
     */
    private FridaBean isHaveFrida;

    public XposedBean getIsHaveXposed() {
        return isHaveXposed;
    }

    public void setIsHaveXposed(XposedBean isHaveXposed) {
        this.isHaveXposed = isHaveXposed;
    }

    public SubstrateBean getIsHaveSubstrate() {
        return isHaveSubstrate;
    }

    public void setIsHaveSubstrate(SubstrateBean isHaveSubstrate) {
        this.isHaveSubstrate = isHaveSubstrate;
    }

    public FridaBean getIsHaveFrida() {
        return isHaveFrida;
    }

    public void setIsHaveFrida(FridaBean isHaveFrida) {
        this.isHaveFrida = isHaveFrida;
    }

    public static class XposedBean extends BaseBean {

        /**
         * 包名检测
         */
        private boolean checkXposedPackage;

        /**
         * 检测调用栈中的可疑方法
         */
        private boolean checkXposedHookMethod;

        /**
         * 检测内存中可疑的jars
         */
        private boolean checkXposedJars;

        /**
         * 检测载入Xposed工具类
         */
        private boolean checkClassLoader;

        /**
         * 新增判断系统方法调用钩子
         */
        private boolean checkNativeMethod;

        /**
         * 虚拟检测Xposed环境
         */
        private boolean checkSystem;

        /**
         * 寻找Xposed运行库文件
         */
        private boolean checkExecLib;

        /**
         * 环境变量特征字判断
         */
        private boolean checkXposedBridge;

        private boolean cMap;

        private boolean cPackage;

        public boolean iscMap() {
            return cMap;
        }

        public void setcMap(boolean cMap) {
            this.cMap = cMap;
        }

        public boolean iscPackage() {
            return cPackage;
        }

        public void setcPackage(boolean cPackage) {
            this.cPackage = cPackage;
        }

        public boolean isCheckXposedPackage() {
            return checkXposedPackage;
        }

        public void setCheckXposedPackage(boolean checkXposedPackage) {
            this.checkXposedPackage = checkXposedPackage;
        }

        public boolean isCheckXposedHookMethod() {
            return checkXposedHookMethod;
        }

        public void setCheckXposedHookMethod(boolean checkXposedHookMethod) {
            this.checkXposedHookMethod = checkXposedHookMethod;
        }

        public boolean isCheckXposedJars() {
            return checkXposedJars;
        }

        public void setCheckXposedJars(boolean checkXposedJars) {
            this.checkXposedJars = checkXposedJars;
        }

        public boolean isCheckClassLoader() {
            return checkClassLoader;
        }

        public void setCheckClassLoader(boolean checkClassLoader) {
            this.checkClassLoader = checkClassLoader;
        }

        public boolean isCheckNativeMethod() {
            return checkNativeMethod;
        }

        public void setCheckNativeMethod(boolean checkNativeMethod) {
            this.checkNativeMethod = checkNativeMethod;
        }

        public boolean isCheckSystem() {
            return checkSystem;
        }

        public void setCheckSystem(boolean checkSystem) {
            this.checkSystem = checkSystem;
        }

        public boolean isCheckExecLib() {
            return checkExecLib;
        }

        public void setCheckExecLib(boolean checkExecLib) {
            this.checkExecLib = checkExecLib;
        }


        public boolean isCheckXposedBridge() {
            return checkXposedBridge;
        }

        public void setCheckXposedBridge(boolean checkXposedBridge) {
            this.checkXposedBridge = checkXposedBridge;
        }
    }


    public static class SubstrateBean extends BaseBean {

        /**
         * 包名检测
         */
        private boolean checkSubstratePackage;

        /**
         * 检测调用栈中的可疑方法
         */
        private boolean checkSubstrateHookMethod;

        /**
         * 检测内存中可疑的jars
         */
        private boolean checkSubstrateJars;

        private boolean cSo;
        private boolean cMap;
        private boolean cPackage;

        public boolean iscSo() {
            return cSo;
        }

        public void setcSo(boolean cSo) {
            this.cSo = cSo;
        }

        public boolean iscMap() {
            return cMap;
        }

        public void setcMap(boolean cMap) {
            this.cMap = cMap;
        }

        public boolean iscPackage() {
            return cPackage;
        }

        public void setcPackage(boolean cPackage) {
            this.cPackage = cPackage;
        }

        public boolean isCheckSubstratePackage() {
            return checkSubstratePackage;
        }

        public void setCheckSubstratePackage(boolean checkSubstratePackage) {
            this.checkSubstratePackage = checkSubstratePackage;
        }

        public boolean isCheckSubstrateHookMethod() {
            return checkSubstrateHookMethod;
        }

        public void setCheckSubstrateHookMethod(boolean checkSubstrateHookMethod) {
            this.checkSubstrateHookMethod = checkSubstrateHookMethod;
        }

        public boolean isCheckSubstrateJars() {
            return checkSubstrateJars;
        }

        public void setCheckSubstrateJars(boolean checkSubstrateJars) {
            this.checkSubstrateJars = checkSubstrateJars;
        }
    }

    public static class FridaBean extends BaseBean {

        /**
         * 检测进程信息
         */
        private boolean checkRunningProcesses;

        /**
         * 检测内存中可疑的jars
         */
        private boolean checkFridaJars;

        private boolean cMap;

        public boolean iscMap() {
            return cMap;
        }

        public void setcMap(boolean cMap) {
            this.cMap = cMap;
        }

        public boolean isCheckRunningProcesses() {
            return checkRunningProcesses;
        }

        public void setCheckRunningProcesses(boolean checkRunningProcesses) {
            this.checkRunningProcesses = checkRunningProcesses;
        }

        public boolean isCheckFridaJars() {
            return checkFridaJars;
        }

        public void setCheckFridaJars(boolean checkFridaJars) {
            this.checkFridaJars = checkFridaJars;
        }
    }
}
