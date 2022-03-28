package com.mobile.mobilehardware.wifilist;


import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;


import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guxiaonian
 */
class WifiInfo {


    static void getWifiList(final Context context, final WifiScanListener wifiScanListener) {
        if (wifiScanListener == null) {
            throw new NullPointerException("the WifiScanListener is null");
        }
        final long        startTime   = System.currentTimeMillis();
        final WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        final WifiBean    wifiBean    = new WifiBean();
        if (wifiManager == null) {
            wifiScanListener.onResult(wifiBean);
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            wifiScanListener.onResult(wifiBean);
        }
        BroadcastReceiver wifiScanReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context c, Intent intent) {
                context.unregisterReceiver(this);
                scanSuccess(wifiManager, wifiBean, startTime, wifiScanListener);
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        context.registerReceiver(wifiScanReceiver, intentFilter);
        wifiManager.startScan();
    }

    private static void scanSuccess(WifiManager wifiManager, WifiBean wifiBean, long startTime, WifiScanListener wifiScanListener) {
        List<ScanResult> results = wifiManager.getScanResults();
        wifiBean.setWifiScanStatus(results.size() != 0);
        List<WifiBean.WifiResultBean> l = new ArrayList<>();
        for (ScanResult scanResult : results) {
            WifiBean.WifiResultBean wifiResultBean = new WifiBean.WifiResultBean();
            wifiResultBean.setBSSID(scanResult.BSSID);
            wifiResultBean.setSSID(scanResult.SSID);
            wifiResultBean.setCapabilities(scanResult.capabilities);
            wifiResultBean.setLevel(scanResult.level);
            l.add(wifiResultBean);
        }
        wifiBean.setWifiScanResult(l);
        wifiBean.setTime(System.currentTimeMillis() - startTime);
        wifiScanListener.onResult(wifiBean);

    }

}
