package com.mobile.mobilehardware.build;


import org.json.JSONObject;

/**
 * @author guxiaonian
 */
public class BuildHelper extends BuildInfo {

    /**
     * build信息
     *
     * @return
     */
    public static JSONObject mobGetBuildInfo() {
        return putNativeJson(mobGetBuildInfoBean().toJSONObject());
    }

    public static BuildBean mobGetBuildInfoBean() {
        return getBuildInfo();
    }
}
