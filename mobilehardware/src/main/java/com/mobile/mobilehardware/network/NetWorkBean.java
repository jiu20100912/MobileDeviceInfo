package com.mobile.mobilehardware.network;

import android.util.Log;

import com.mobile.mobilehardware.base.BaseBean;
import com.mobile.mobilehardware.base.BaseData;

import org.json.JSONObject;

/**
 * @author guxiaonian
 */
public class NetWorkBean extends BaseBean {
    private static final String TAG = NetWorkBean.class.getSimpleName();

    /**
     * 网络类型
     */
    private String type;

    /**
     * 网络是否可用
     */
    private boolean networkAvailable;

    /**
     * 是否开启数据流量
     */
    private boolean haveIntent;

    /**
     * 是否是飞行模式
     */
    private boolean isFlightMode;

    /**
     * NFC功能是否开启
     */
    private boolean isNFCEnabled;

    /**
     * 是否开启热点
     */
    private boolean isHotspotEnabled;

    /**
     * 热点账号
     */
    private String hotspotSSID;

    /**
     * 热点密码
     */
    private String hotspotPwd;

    /**
     * 热点加密类型
     */
    private String encryptionType;

    private boolean isVpn;

    public boolean isVpn() {
        return isVpn;
    }

    public void setVpn(boolean vpn) {
        isVpn = vpn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isNetworkAvailable() {
        return networkAvailable;
    }

    public void setNetworkAvailable(boolean networkAvailable) {
        this.networkAvailable = networkAvailable;
    }

    public boolean isHaveIntent() {
        return haveIntent;
    }

    public void setHaveIntent(boolean haveIntent) {
        this.haveIntent = haveIntent;
    }

    public boolean isFlightMode() {
        return isFlightMode;
    }

    public void setFlightMode(boolean flightMode) {
        isFlightMode = flightMode;
    }

    public boolean isNFCEnabled() {
        return isNFCEnabled;
    }

    public void setNFCEnabled(boolean NFCEnabled) {
        isNFCEnabled = NFCEnabled;
    }

    public boolean isHotspotEnabled() {
        return isHotspotEnabled;
    }

    public void setHotspotEnabled(boolean hotspotEnabled) {
        isHotspotEnabled = hotspotEnabled;
    }

    public String getHotspotSSID() {
        return hotspotSSID;
    }

    public void setHotspotSSID(String hotspotSSID) {
        this.hotspotSSID = hotspotSSID;
    }

    public String getHotspotPwd() {
        return hotspotPwd;
    }

    public void setHotspotPwd(String hotspotPwd) {
        this.hotspotPwd = hotspotPwd;
    }

    public String getEncryptionType() {
        return encryptionType;
    }

    public void setEncryptionType(String encryptionType) {
        this.encryptionType = encryptionType;
    }

}
