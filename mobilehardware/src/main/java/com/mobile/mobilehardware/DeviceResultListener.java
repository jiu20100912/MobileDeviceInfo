package com.mobile.mobilehardware;

import java.util.List;
import java.util.Map;

public interface DeviceResultListener {

    void onWifiResult(List<Map<String, Object>> list);

    void onDeviceResult(Map<String, Object> deviceMap, Map<String, Object> batteryMap, Map<String, Object> simMap, Map<String, Object> cpuMap);
}
