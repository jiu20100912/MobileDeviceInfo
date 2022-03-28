package com.mobile.mobilehardware.setting;


import com.mobile.mobilehardware.MobileHardWareHelper;

import org.json.JSONObject;

/**
 * @author guxiaonian
 */
public class SettingsHelper extends SettingsInfo {

    /**
     * 获取设置信息
     *
     * @return
     */
    public static JSONObject mobGetMobSettings() {
        return mobGetMobSettingsBean().toJSONObject();
    }

    public static SettingsBean mobGetMobSettingsBean() {
        return getMobSettings(MobileHardWareHelper.getContext());
    }

}
