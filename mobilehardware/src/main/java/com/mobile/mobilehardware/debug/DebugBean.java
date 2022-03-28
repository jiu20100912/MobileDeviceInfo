package com.mobile.mobilehardware.debug;

import android.util.Log;

import com.mobile.mobilehardware.base.BaseBean;
import com.mobile.mobilehardware.base.BaseData;

import org.json.JSONObject;

/**
 * @author guxiaonian
 */
public class DebugBean extends BaseBean {
    private static final String TAG = DebugBean.class.getSimpleName();

    /**
     * 是否开启了调试模式
     */
    private boolean isOpenDebug;

    /**
     * 是否是Debug版本
     */
    private boolean isDebugVersion;

    /**
     * 是否正在调试
     */
    private boolean isDebugging;

    /**
     * 读取id判断是否在调试
     */
    private boolean isReadProcStatus;



    public boolean isOpenDebug() {
        return isOpenDebug;
    }

    public void setOpenDebug(boolean openDebug) {
        isOpenDebug = openDebug;
    }

    public boolean isDebugVersion() {
        return isDebugVersion;
    }

    public void setDebugVersion(boolean debugVersion) {
        isDebugVersion = debugVersion;
    }

    public boolean isDebugging() {
        return isDebugging;
    }

    public void setDebugging(boolean debugging) {
        isDebugging = debugging;
    }

    public boolean isReadProcStatus() {
        return isReadProcStatus;
    }

    public void setReadProcStatus(boolean readProcStatus) {
        isReadProcStatus = readProcStatus;
    }


}
