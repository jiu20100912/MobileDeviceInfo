package com.mobile.mobilehardware.moreopen;

import android.util.Log;

import com.mobile.mobilehardware.base.BaseBean;
import com.mobile.mobilehardware.base.BaseData;

import org.json.JSONObject;

/**
 * @author guxiaonian
 */
public class MoreOpenBean extends BaseBean {
    private static final String TAG = MoreOpenBean.class.getSimpleName();

    /**
     * 检测私有路径判断是否有多开
     */
    private boolean checkByPrivateFilePath;

    /**
     * maps检测
     */
    private boolean checkByMultiApkPackageName;

    /**
     * ps检测
     */
    private boolean checkByHasSameUid;

    private boolean checkLs;

    public boolean isCheckLs() {
        return checkLs;
    }

    public void setCheckLs(boolean checkLs) {
        this.checkLs = checkLs;
    }

    public boolean isCheckByPrivateFilePath() {
        return checkByPrivateFilePath;
    }

    public void setCheckByPrivateFilePath(boolean checkByPrivateFilePath) {
        this.checkByPrivateFilePath = checkByPrivateFilePath;
    }

    public boolean isCheckByMultiApkPackageName() {
        return checkByMultiApkPackageName;
    }

    public void setCheckByMultiApkPackageName(boolean checkByMultiApkPackageName) {
        this.checkByMultiApkPackageName = checkByMultiApkPackageName;
    }

    public boolean isCheckByHasSameUid() {
        return checkByHasSameUid;
    }

    public void setCheckByHasSameUid(boolean checkByHasSameUid) {
        this.checkByHasSameUid = checkByHasSameUid;
    }

}
