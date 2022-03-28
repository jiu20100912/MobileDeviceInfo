package com.mobile.mobilehardware.cpu;

import android.util.Log;

import com.mobile.mobilehardware.base.BaseBean;
import com.mobile.mobilehardware.base.BaseData;

import org.json.JSONObject;

/**
 * @author guxiaonian
 */
public class CpuBean extends BaseBean {
    private static final String TAG = CpuBean.class.getSimpleName();

    /**
     * CPU名字
     */
    private String cpuName;
    private String cpuPart;
    private String bogomips;
    private String features;
    private String cpuImplementer;
    private String cpuArchitecture;
    private String cpuVariant;

    public String getBogomips() {
        return bogomips;
    }

    public void setBogomips(String bogomips) {
        this.bogomips = bogomips;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getCpuImplementer() {
        return cpuImplementer;
    }

    public void setCpuImplementer(String cpuImplementer) {
        this.cpuImplementer = cpuImplementer;
    }

    public String getCpuArchitecture() {
        return cpuArchitecture;
    }

    public void setCpuArchitecture(String cpuArchitecture) {
        this.cpuArchitecture = cpuArchitecture;
    }

    public String getCpuVariant() {
        return cpuVariant;
    }

    public void setCpuVariant(String cpuVariant) {
        this.cpuVariant = cpuVariant;
    }

    /**
     * CPU频率
     */
    private String cpuFreq;

    /**
     * CPU最大频率
     */
    private String cpuMaxFreq;

    /**
     * CPU最小频率
     */
    private String cpuMinFreq;

    /**
     * CPU硬件名
     */
    private String cpuHardware;

    /**
     * CPU核数
     */
    private int cpuCores;

    /**
     * CPU温度
     */
    private String cpuTemp;

    /**
     * CPU架构
     */
    private String cpuAbi;

    public String getCpuName() {
        return cpuName;
    }

    public void setCpuName(String cpuName) {
        this.cpuName = cpuName;
    }

    public String getCpuFreq() {
        return cpuFreq;
    }

    public void setCpuFreq(String cpuFreq) {
        this.cpuFreq = cpuFreq;
    }

    public String getCpuMaxFreq() {
        return cpuMaxFreq;
    }

    public void setCpuMaxFreq(String cpuMaxFreq) {
        this.cpuMaxFreq = cpuMaxFreq;
    }

    public String getCpuMinFreq() {
        return cpuMinFreq;
    }

    public void setCpuMinFreq(String cpuMinFreq) {
        this.cpuMinFreq = cpuMinFreq;
    }

    public String getCpuHardware() {
        return cpuHardware;
    }

    public void setCpuHardware(String cpuHardware) {
        this.cpuHardware = cpuHardware;
    }

    public int getCpuCores() {
        return cpuCores;
    }

    public void setCpuCores(int cpuCores) {
        this.cpuCores = cpuCores;
    }

    public String getCpuTemp() {
        return cpuTemp;
    }

    public void setCpuTemp(String cpuTemp) {
        this.cpuTemp = cpuTemp;
    }

    public String getCpuAbi() {
        return cpuAbi;
    }

    public void setCpuAbi(String cpuAbi) {
        this.cpuAbi = cpuAbi;
    }

    public String getCpuPart() {
        return cpuPart;
    }

    public void setCpuPart(String cpuPart) {
        this.cpuPart = cpuPart;
    }

}
