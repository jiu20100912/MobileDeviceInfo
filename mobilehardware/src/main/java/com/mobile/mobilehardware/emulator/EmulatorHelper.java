package com.mobile.mobilehardware.emulator;


import com.mobile.mobilehardware.MobileHardWareHelper;

import org.json.JSONObject;


/**
 * @author guxiaonian
 */
public class EmulatorHelper extends EmulatorInfo {


    /**
     * 判断是否是模拟器
     * 通过静态资源，设备特征参数来判断
     *
     * @return true为模拟器
     */

    public static JSONObject mobCheckEmulator() {
        return mobCheckEmulatorBean().toJSONObject();

    }

    public static EmulatorBean mobCheckEmulatorBean() {
        return checkEmulator(MobileHardWareHelper.getContext());

    }

}
