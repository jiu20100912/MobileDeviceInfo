package com.mobile.mobilehardware.band;


import org.json.JSONObject;


/**
 * @author guxiaonian
 */
public class BandHelper extends BandInfo {

    /**
     * bandInfo
     *
     * @return
     */
    public static JSONObject mobGetBandInfo() {
        return mobGetBandInfoBean().toJSONObject();
    }

    public static BandBean mobGetBandInfoBean() {
        return getBandInfo();
    }
}
