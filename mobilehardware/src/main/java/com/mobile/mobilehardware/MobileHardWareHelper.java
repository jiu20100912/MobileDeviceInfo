package com.mobile.mobilehardware;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Window;

import com.mobile.mobilehardware.applist.ListAppHelper;
import com.mobile.mobilehardware.audio.AudioHelper;
import com.mobile.mobilehardware.band.BandHelper;
import com.mobile.mobilehardware.bluetooth.BluetoothHelper;
import com.mobile.mobilehardware.build.BuildHelper;
import com.mobile.mobilehardware.complete.CompleteHelper;
import com.mobile.mobilehardware.sdcard.SDCardHelper;
import com.mobile.mobilehardware.simcard.SimCardHelper;
import com.mobile.mobilehardware.wifilist.WifiHelper;
import com.mobile.mobilehardware.wifilist.WifiScanListener;
import com.mobile.mobilehardware.xposed.XposedHookHelper;
import com.mobile.mobilehardware.root.RootHelper;
import com.mobile.mobilehardware.stack.StackSampler;
import com.mobile.mobilehardware.debug.DebugHelper;
import com.mobile.mobilehardware.moreopen.MoreOpenHelper;
import com.mobile.mobilehardware.hook.HookHelper;
import com.mobile.mobilehardware.battery.BatteryHelper;
import com.mobile.mobilehardware.camera.CameraHelper;
import com.mobile.mobilehardware.cpu.CpuHelper;
import com.mobile.mobilehardware.dns.DnsHelper;
import com.mobile.mobilehardware.emulator.EmulatorHelper;
import com.mobile.mobilehardware.local.LocalHelper;
import com.mobile.mobilehardware.memory.MemoryHelper;
import com.mobile.mobilehardware.network.NetWorkHelper;
import com.mobile.mobilehardware.app.PackageHelper;
import com.mobile.mobilehardware.signal.SignalHelper;
import com.mobile.mobilehardware.screen.ScreenHelper;
import com.mobile.mobilehardware.setting.SettingsHelper;
import com.mobile.mobilehardware.useragent.UserAgentHelper;
import com.mobile.mobilehardware.uniqueid.PhoneIdHelper;

import org.json.JSONObject;

import java.util.List;

/**
 * @author guxiaonian
 */
public class MobileHardWareHelper {

    /**
     * 全局上下文
     */
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    /**
     * 设置全局上下文 默认使用MobInitializer来进行初始化
     * 可以自行修改
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        mContext = context;
    }

    /**
     * 获取全局上下文
     *
     * @return 上下文
     */
    public static Context getContext() {
        return mContext;
    }


}
