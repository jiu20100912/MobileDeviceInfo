package com.mobile.mobilehardware.cpu;

import org.json.JSONObject;


/**
 * @author guxiaonian
 */
public class CpuHelper extends CpuInfo {

    /**
     * CPU
     * @return
     */
    public static JSONObject mobGetCpuInfo() {
        return mobGetCpuInfoBean().toJSONObject();
    }

    public static CpuBean mobGetCpuInfoBean() {
        return getCpuInfo();
    }


}
