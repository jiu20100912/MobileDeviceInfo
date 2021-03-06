package com.mobile.mobilehardware.sdcard;

import android.util.Log;

import com.mobile.mobilehardware.base.BaseBean;
import com.mobile.mobilehardware.base.BaseData;

import org.json.JSONObject;

/**
 * @author guxiaonian
 */
public class SDCardBean extends BaseBean {
    private static final String TAG = SDCardBean.class.getSimpleName();

    /**
     * sd card 是否可用
     */
    private boolean isSDCardEnable;

    /**
     * sd card路径
     */
    private String sDCardPath;

    private boolean isExtendedMemory;
    private String extendedMemoryPath;

    public boolean isExtendedMemory() {
        return isExtendedMemory;
    }

    public void setExtendedMemory(boolean extendedMemory) {
        isExtendedMemory = extendedMemory;
    }

    public String getExtendedMemoryPath() {
        return extendedMemoryPath;
    }

    public void setExtendedMemoryPath(String extendedMemoryPath) {
        this.extendedMemoryPath = extendedMemoryPath;
    }

    public boolean isSDCardEnable() {
        return isSDCardEnable;
    }

    public void setSDCardEnable(boolean SDCardEnable) {
        isSDCardEnable = SDCardEnable;
    }

    public String getsDCardPath() {
        return sDCardPath;
    }

    public void setsDCardPath(String sDCardPath) {
        this.sDCardPath = sDCardPath;
    }




}
