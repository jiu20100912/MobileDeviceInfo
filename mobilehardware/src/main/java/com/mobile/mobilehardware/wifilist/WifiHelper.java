package com.mobile.mobilehardware.wifilist;


import com.mobile.mobilehardware.MobileHardWareHelper;

/**
 * @author guxiaonian
 */
public class WifiHelper extends WifiInfo {

    public static void wifiList(WifiScanListener wifiScanListener) {
        getWifiList(MobileHardWareHelper.getContext(), wifiScanListener);
    }

    public static void wifiListBean(WifiScanListener wifiScanListener) {
        getWifiList(MobileHardWareHelper.getContext(), wifiScanListener);
    }

}
