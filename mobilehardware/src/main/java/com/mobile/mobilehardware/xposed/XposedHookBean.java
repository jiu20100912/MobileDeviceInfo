package com.mobile.mobilehardware.xposed;

import android.util.Log;

import com.mobile.mobilehardware.base.BaseBean;
import com.mobile.mobilehardware.base.BaseData;

import org.json.JSONObject;

/**
 * @author guxiaonian
 */
public class XposedHookBean extends BaseBean {
    private static final String TAG = XposedHookBean.class.getSimpleName();

    private boolean xposedApp;
    private boolean xposedImei;
    private boolean xposedSerial;
    private boolean xposedSsid;
    private boolean xposedMac;
    private boolean xposedAddress;
    private boolean xposedAndroidId;
    private boolean xposedImsi;
    private boolean xposedLatitude;
    private boolean xposedLongitude;

    public XposedHookBean() {
    }

    public boolean isXposedApp() {
        return xposedApp;
    }

    public void setXposedApp(boolean xposedApp) {
        this.xposedApp = xposedApp;
    }

    public boolean isXposedImei() {
        return xposedImei;
    }

    public void setXposedImei(boolean xposedImei) {
        this.xposedImei = xposedImei;
    }

    public boolean isXposedSerial() {
        return xposedSerial;
    }

    public void setXposedSerial(boolean xposedSerial) {
        this.xposedSerial = xposedSerial;
    }

    public boolean isXposedSsid() {
        return xposedSsid;
    }

    public void setXposedSsid(boolean xposedSsid) {
        this.xposedSsid = xposedSsid;
    }

    public boolean isXposedMac() {
        return xposedMac;
    }

    public void setXposedMac(boolean xposedMac) {
        this.xposedMac = xposedMac;
    }

    public boolean isXposedAddress() {
        return xposedAddress;
    }

    public void setXposedAddress(boolean xposedAddress) {
        this.xposedAddress = xposedAddress;
    }

    public boolean isXposedAndroidId() {
        return xposedAndroidId;
    }

    public void setXposedAndroidId(boolean xposedAndroidId) {
        this.xposedAndroidId = xposedAndroidId;
    }

    public boolean isXposedImsi() {
        return xposedImsi;
    }

    public void setXposedImsi(boolean xposedImsi) {
        this.xposedImsi = xposedImsi;
    }

    public boolean isXposedLatitude() {
        return xposedLatitude;
    }

    public void setXposedLatitude(boolean xposedLatitude) {
        this.xposedLatitude = xposedLatitude;
    }

    public boolean isXposedLongitude() {
        return xposedLongitude;
    }

    public void setXposedLongitude(boolean xposedLongitude) {
        this.xposedLongitude = xposedLongitude;
    }

}
