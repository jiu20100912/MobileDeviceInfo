package com.mobile.mobilehardware.wifilist;


import com.mobile.mobilehardware.base.BaseBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * @author guxiaonian
 */
public class WifiBean extends BaseBean {
    private boolean wifiScanStatus;
    private List<WifiResultBean> wifiScanResult;
    private long time;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isWifiScanStatus() {
        return wifiScanStatus;
    }

    public void setWifiScanStatus(boolean wifiScanStatus) {
        this.wifiScanStatus = wifiScanStatus;
    }

    public List<WifiResultBean> getWifiScanResult() {
        return wifiScanResult;
    }

    public void setWifiScanResult(List<WifiResultBean> wifiScanResult) {
        this.wifiScanResult = wifiScanResult;
    }

    public static class WifiResultBean extends BaseBean {
        private String SSID;
        private String BSSID;
        private String capabilities;
        private int level;

        public String getSSID() {
            return SSID;
        }

        public void setSSID(String SSID) {
            this.SSID = SSID;
        }

        public String getBSSID() {
            return BSSID;
        }

        public void setBSSID(String BSSID) {
            this.BSSID = BSSID;
        }

        public String getCapabilities() {
            return capabilities;
        }

        public void setCapabilities(String capabilities) {
            this.capabilities = capabilities;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }
    }


}
