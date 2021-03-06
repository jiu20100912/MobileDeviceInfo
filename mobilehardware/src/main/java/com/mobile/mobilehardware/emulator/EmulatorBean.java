package com.mobile.mobilehardware.emulator;

import android.util.Log;

import com.mobile.mobilehardware.base.BaseBean;
import com.mobile.mobilehardware.base.BaseData;

import org.json.JSONObject;

/**
 * @author guxiaonian
 */
public class EmulatorBean extends BaseBean {
    private static final String TAG = EmulatorBean.class.getSimpleName();

    /**
     * build
     */
    private boolean checkBuild;

    /**
     * 包名修改
     */
    private boolean checkPkg;

    /**
     * 管道检测
     */
    private boolean checkPipes;

    /**
     * 驱动程序检测
     */
    private boolean checkQEmuDriverFile;


    /**
     * cpu架构检测
     */
    private boolean checkCpuInfo;

    public boolean isCheckBuild() {
        return checkBuild;
    }

    public void setCheckBuild(boolean checkBuild) {
        this.checkBuild = checkBuild;
    }

    public boolean isCheckPkg() {
        return checkPkg;
    }

    public void setCheckPkg(boolean checkPkg) {
        this.checkPkg = checkPkg;
    }

    public boolean isCheckPipes() {
        return checkPipes;
    }

    public void setCheckPipes(boolean checkPipes) {
        this.checkPipes = checkPipes;
    }

    public boolean isCheckQEmuDriverFile() {
        return checkQEmuDriverFile;
    }

    public void setCheckQEmuDriverFile(boolean checkQEmuDriverFile) {
        this.checkQEmuDriverFile = checkQEmuDriverFile;
    }

    public boolean isCheckCpuInfo() {
        return checkCpuInfo;
    }

    public void setCheckCpuInfo(boolean checkCpuInfo) {
        this.checkCpuInfo = checkCpuInfo;
    }


}
