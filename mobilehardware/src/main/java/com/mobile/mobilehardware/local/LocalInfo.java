package com.mobile.mobilehardware.local;


import android.util.Log;

import org.json.JSONObject;

import java.util.Locale;

/**
 * @author guxiaonian
 */
class LocalInfo {

    public static final String TAG = LocalInfo.class.getSimpleName();

    static LocalBean getMobLocal() {
        LocalBean localBean = new LocalBean();
        try {
            localBean.setCountry(Locale.getDefault().getCountry());
            localBean.setLanguage(Locale.getDefault().getLanguage());
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return localBean;
    }
}
