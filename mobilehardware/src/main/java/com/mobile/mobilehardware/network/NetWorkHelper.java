package com.mobile.mobilehardware.network;


import com.mobile.mobilehardware.MobileHardWareHelper;

import org.json.JSONObject;

/**
 * @author guxiaonian
 */
public class NetWorkHelper extends NetWorkInfo {

    public static JSONObject mobGetMobNetWork() {
        return mobGetMobNetWorkBean().toJSONObject();
    }

    public static NetWorkBean mobGetMobNetWorkBean() {
        return getMobNetWork(MobileHardWareHelper.getContext());
    }

}
