package com.mobile.mobilehardware.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

public class GsonTool {
    private static Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().create();

    public static String toJson(final Object object) {
        return gson.toJson(object);
    }
}
