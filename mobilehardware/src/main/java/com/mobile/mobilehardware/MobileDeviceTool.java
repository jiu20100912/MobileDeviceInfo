package com.mobile.mobilehardware;

import android.os.SystemClock;
import android.text.TextUtils;

import com.mobile.mobilehardware.band.BandHelper;
import com.mobile.mobilehardware.battery.BatteryHelper;
import com.mobile.mobilehardware.build.BuildHelper;
import com.mobile.mobilehardware.cpu.CpuHelper;
import com.mobile.mobilehardware.debug.DebugHelper;
import com.mobile.mobilehardware.emulator.EmulatorHelper;
import com.mobile.mobilehardware.local.LocalHelper;
import com.mobile.mobilehardware.memory.MemoryHelper;
import com.mobile.mobilehardware.network.NetWorkHelper;
import com.mobile.mobilehardware.base.BaseData;
import com.mobile.mobilehardware.root.RootHelper;
import com.mobile.mobilehardware.screen.ScreenHelper;
import com.mobile.mobilehardware.signal.SignalHelper;
import com.mobile.mobilehardware.simcard.SimCardHelper;
import com.mobile.mobilehardware.wifilist.WifiHelper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class MobileDeviceTool {

    public static void getDeviceInfo(DeviceResultListener resultListener) {

        Map<String, Object>       deviceBaseMap = new HashMap<>();
        Map<String, Object>       simMap        = new HashMap<>();
        Map<String, Object>       batteryMap    = new HashMap<>();
        Map<String, Object>       cpuMap        = new HashMap<>();
        List<Map<String, Object>> cellList      = new ArrayList<>();

        try {

            JSONObject netWork       = NetWorkHelper.mobGetMobNetWork();
            JSONObject debuggingData = DebugHelper.getDebuggingData();
            JSONObject emulator      = EmulatorHelper.mobCheckEmulator();
            JSONObject screen        = ScreenHelper.mobGetMobScreen(null);
            JSONObject buildInfo     = BuildHelper.mobGetBuildInfo();
            JSONObject bandInfo      = BandHelper.mobGetBandInfo();
            JSONObject battery       = BatteryHelper.mobGetBattery();
            JSONObject signalInfo    = SignalHelper.mobGetNetRssi();
            JSONObject simInfo       = SimCardHelper.mobileSimInfo();
            JSONObject cpuInfo       = CpuHelper.mobGetCpuInfo();
            JSONObject memoryInfo    = MemoryHelper.getMemoryInfo();
            JSONObject local         = LocalHelper.mobGetMobLocal();

            WifiHelper.wifiList(jsonObject -> {
                List<Map<String, Object>> wifiList = new ArrayList<>();
                try {
                    String    curBSSID       = signalInfo.optString(BaseData.Signal.BSSID);
                    String    mac            = signalInfo.optString(BaseData.Signal.MAC_ADDRESS);
                    JSONArray wifiScanResult = jsonObject.optJSONArray("wifiScanResult");
                    if (wifiScanResult != null && wifiScanResult.length() > 0) {
                        int length = wifiScanResult.length();
                        for (int i = 0; i < length; i++) {
                            JSONObject          jb    = wifiScanResult.getJSONObject(i);
                            String              bssid = jb.optString("BSSID");
                            Map<String, Object> map   = new HashMap<>();
                            map.put("ssid", jb.optString("SSID"));
                            map.put("bssid", bssid);
                            map.put("current", bssid.equals(curBSSID));
                            if (!TextUtils.isEmpty(mac))
                                map.put("mac", mac);
                            wifiList.add(map);
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    resultListener.onWifiResult(wifiList);
                }


            });


            try {

                deviceBaseMap.put("vpnFlag", netWork.optBoolean(BaseData.NetWork.VPN));
                deviceBaseMap.put("debugFlag", debuggingData.optBoolean(BaseData.Debug.IS_OPEN_DEBUG));
                deviceBaseMap.put("emulatorFlag", emulator.optBoolean(BaseData.Emulator.CHECK_BUILD));
                deviceBaseMap.put("rootFlag", RootHelper.mobileRoot());

                deviceBaseMap.put("serial", buildInfo.optString(BaseData.Build.SERIAL));
                deviceBaseMap.put("product", buildInfo.optString(BaseData.Build.PRODUCT));
                deviceBaseMap.put("brand", buildInfo.optString(BaseData.Build.BRAND));
                deviceBaseMap.put("hardware", buildInfo.optString(BaseData.Build.HARDWARE));
                deviceBaseMap.put("device", buildInfo.optString(BaseData.Build.DEVICE));
                deviceBaseMap.put("buildType", buildInfo.optString(BaseData.Build.TYPE));
                deviceBaseMap.put("buildTags", buildInfo.optString(BaseData.Build.TAGS));
                deviceBaseMap.put("sdkInt", buildInfo.optInt(BaseData.Build.SDK_INT));
                deviceBaseMap.put("buildUser", buildInfo.optString(BaseData.Build.USER));
                deviceBaseMap.put("board", buildInfo.optString(BaseData.Build.BOARD));
                deviceBaseMap.put("display", buildInfo.optString(BaseData.Build.DISPLAY));
                deviceBaseMap.put("hardwareId", buildInfo.optString(BaseData.Build.ID));
                deviceBaseMap.put("bootloader", buildInfo.optString(BaseData.Build.BOOTLOADER));
                deviceBaseMap.put("fingerPrint", buildInfo.optString(BaseData.Build.FINGERPRINT));
                deviceBaseMap.put("buildHost", buildInfo.optString(BaseData.Build.HOST));
                deviceBaseMap.put("radio", buildInfo.optString(BaseData.Build.RADIO));
                deviceBaseMap.put("buildTime", buildInfo.optLong(BaseData.Build.TIME));
                deviceBaseMap.put("model", buildInfo.optString(BaseData.Build.MODEL));
                deviceBaseMap.put("manufacturer", buildInfo.optString(BaseData.Build.MANUFACTURER));


                deviceBaseMap.put("ram", memoryInfo.optString(BaseData.Memory.RAM_MEMORY));
                deviceBaseMap.put("rom", memoryInfo.optString(BaseData.Memory.SDCARD_REAL_MEMORY_TOTAL));
                deviceBaseMap.put("canUseRom", memoryInfo.optString(BaseData.Memory.RAM_AVAIL_MEMORY));
                deviceBaseMap.put("canUseRam", memoryInfo.optString(BaseData.Memory.ROM_MEMORY_AVAILABLE));


                deviceBaseMap.put("mac", signalInfo.optString(BaseData.Signal.MAC_ADDRESS));

                deviceBaseMap.put("language", local.optString(BaseData.Local.LANGUAGE));

                deviceBaseMap.put("baseBand", bandInfo.optString(BaseData.Band.BASE_BAND));


                int    sHeight      = screen.optInt(BaseData.Screen.HEIGHT);
                int    sWidth       = screen.optInt(BaseData.Screen.WIDTH);
                double density      = screen.optDouble(BaseData.Screen.DENSITY_SCALE);
                double physicalSize = Math.sqrt(Math.pow(sWidth, 2) + Math.pow(sHeight, 2)) / (160 * density);
                deviceBaseMap.put("resolution", sHeight + "*" + sWidth);
                deviceBaseMap.put("screenDensity", density);
                deviceBaseMap.put("screenDensityDpi", screen.optString(BaseData.Screen.DENSITY_DPI));
                deviceBaseMap.put("screenBrightness", screen.optInt(BaseData.Screen.SCREEN_BRIGHTNESS));
                deviceBaseMap.put("physicalSize", physicalSize);

                TimeZone timeZone = TimeZone.getDefault();
                deviceBaseMap.put("timeZone", timeZone.getID() + "_" + timeZone.getDisplayName(false, TimeZone.SHORT));

            } catch (Exception e) {
                e.printStackTrace();
            }


            try {

                batteryMap.put("batteryPercentage", battery.optString(BaseData.Battery.BR));
                batteryMap.put("batteryStatus", battery.optInt(BaseData.Battery.STATUS));
                batteryMap.put("chargingMode", battery.optInt(BaseData.Battery.PLUG_STATE));
                batteryMap.put("activeTime", SystemClock.uptimeMillis());
                batteryMap.put("upTime", SystemClock.elapsedRealtime());
                batteryMap.put("bootTime", System.currentTimeMillis() - SystemClock.elapsedRealtime());

            } catch (Exception e) {
                e.printStackTrace();
            }

            try {

                cpuMap.put("cpuName", cpuInfo.optString(BaseData.Cpu.CPU_NAME));
                cpuMap.put("cpuFreq", cpuInfo.optString(BaseData.Cpu.CPU_FREQ));
                cpuMap.put("cpuMinFreq", cpuInfo.optString(BaseData.Cpu.CPU_MIN_FREQ));
                cpuMap.put("cpuMaxFreq", cpuInfo.optString(BaseData.Cpu.CPU_MAX_FREQ));
                cpuMap.put("cpuHardware", cpuInfo.optString(BaseData.Cpu.CPU_HARDWARE));
                cpuMap.put("cpuCores", cpuInfo.optString(BaseData.Cpu.CPU_CORES));
                cpuMap.put("cpuTemp", cpuInfo.optString(BaseData.Cpu.CPU_TEMP));
                cpuMap.put("cpuAbi", cpuInfo.optString(BaseData.Cpu.CPU_ABI));

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                deviceBaseMap.putAll(cpuMap);
            }

            try {
                boolean twoCard  = simInfo.optBoolean(BaseData.SimCard.IS_TWO_CARD);
                boolean haveCard = simInfo.optBoolean(BaseData.SimCard.IS_HAVE_CARD);
                simMap.put("simCount", twoCard ? 2 : (haveCard ? 1 : 0));
                simMap.put("simState1", simInfo.optInt(BaseData.SimCard.SIM1_STATE));
                simMap.put("simState2", simInfo.optInt(BaseData.SimCard.SIM2_STATE));
                simMap.put("meid", simInfo.optString(BaseData.SimCard.MEID));
                simMap.put("simOperator1", simInfo.optString(BaseData.SimCard.SIM1_CARRIER_NAME));
                simMap.put("simOperator2", simInfo.optString(BaseData.SimCard.SIM2_CARRIER_NAME));
                simMap.put("imsi1", simInfo.optString(BaseData.SimCard.SIM1_IMSI));
                simMap.put("imsi2", simInfo.optString(BaseData.SimCard.SIM2_IMSI));
                simMap.put("simNetType", simInfo.optString(BaseData.SimCard.SIM_NETWORK_TYPE));

                JSONArray jsonArray = simInfo.optJSONArray(BaseData.SimCard.CELL_IDENTITY);

                if (jsonArray != null && jsonArray.length() > 0) {
                    int length = jsonArray.length();

                    for (int i = 0; i < length; i++) {
                        JSONObject          jsonObject = jsonArray.getJSONObject(i);
                        Map<String, Object> map        = new HashMap<>();

                        String cid  = jsonObject.optString(BaseData.CELL.CID);
                        String lac  = jsonObject.optString(BaseData.CELL.LAC);
                        String mcc  = jsonObject.optString(BaseData.CELL.MCC);
                        String mnc  = jsonObject.optString(BaseData.CELL.MNC);
                        String type = jsonObject.optString(BaseData.CELL.CELL_TYPE);

                        if (!TextUtils.isEmpty(cid))
                            map.put("cid", cid);
                        if (!TextUtils.isEmpty(lac))
                            map.put("lac", lac);
                        if (!TextUtils.isEmpty(mcc))
                            map.put("mcc", mcc);
                        if (!TextUtils.isEmpty(mnc))
                            map.put("mnc", mnc);
                        if (!TextUtils.isEmpty(type))
                            map.put("type", type);
                        map.put("dbm", jsonObject.optInt(BaseData.CELL.DBM));

                        cellList.add(map);

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


            Iterator<Map.Entry<String, Object>> iterator = deviceBaseMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> next = iterator.next();
                if (next.getValue() instanceof String) {
                    if (TextUtils.isEmpty((CharSequence) next.getValue())) {
                        iterator.remove();
                    }
                }
            }

            iterator = batteryMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> next = iterator.next();
                if (next.getValue() instanceof String) {
                    if (TextUtils.isEmpty((CharSequence) next.getValue())) {
                        iterator.remove();
                    }
                }
            }

            iterator = simMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> next = iterator.next();
                if (next.getValue() instanceof String) {
                    if (TextUtils.isEmpty((CharSequence) next.getValue())) {
                        iterator.remove();
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            resultListener.onDeviceResult(deviceBaseMap, batteryMap, simMap, cellList);
        }

    }


}
