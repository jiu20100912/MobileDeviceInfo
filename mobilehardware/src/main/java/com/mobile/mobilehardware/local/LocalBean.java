package com.mobile.mobilehardware.local;

import android.util.Log;

import com.mobile.mobilehardware.base.BaseBean;
import com.mobile.mobilehardware.base.BaseData;

import org.json.JSONObject;

/**
 * @author guxiaonian
 */
public class LocalBean extends BaseBean {
    private static final String TAG = LocalBean.class.getSimpleName();

    /**
     * 当前国家
     */
    private String country;

    /**
     * 当前语言
     */
    private String language;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}
