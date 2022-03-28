package com.mobile.mobilehardware.base;

import android.text.TextUtils;

import com.mobile.mobilehardware.utils.GsonTool;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * @author guxiaonian
 */
public class BaseBean implements Serializable {

    public JSONObject toJSONObject() {
        try {
            return new JSONObject(GsonTool.toJson(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    protected BaseBean() {

    }

    public String isEmpty(String value) {
        if (TextUtils.isEmpty(value) || value.equals("null")) {
            return BaseData.UNKNOWN_PARAM;
        }
        return value;
    }

    protected String isEmpty(CharSequence value) {
        if (value == null) {
            return BaseData.UNKNOWN_PARAM;
        }
        return value.toString();
    }

}
