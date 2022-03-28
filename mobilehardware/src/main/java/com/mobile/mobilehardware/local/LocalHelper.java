package com.mobile.mobilehardware.local;


import org.json.JSONObject;


/**
 * @author guxiaonian
 */
public class LocalHelper extends LocalInfo {

    public static JSONObject mobGetMobLocal() {
        return mobGetMobLocalBean().toJSONObject();
    }

    public static LocalBean mobGetMobLocalBean() {
        return getMobLocal();
    }

}
