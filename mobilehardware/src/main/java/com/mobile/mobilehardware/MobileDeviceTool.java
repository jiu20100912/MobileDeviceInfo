package com.mobile.mobilehardware;

import android.os.SystemClock;

import com.mobile.mobilehardware.band.BandBean;
import com.mobile.mobilehardware.band.BandHelper;
import com.mobile.mobilehardware.battery.BatteryBean;
import com.mobile.mobilehardware.battery.BatteryHelper;
import com.mobile.mobilehardware.build.BuildBean;
import com.mobile.mobilehardware.build.BuildHelper;
import com.mobile.mobilehardware.cpu.CpuBean;
import com.mobile.mobilehardware.cpu.CpuHelper;
import com.mobile.mobilehardware.debug.DebugBean;
import com.mobile.mobilehardware.debug.DebugHelper;
import com.mobile.mobilehardware.emulator.EmulatorBean;
import com.mobile.mobilehardware.emulator.EmulatorHelper;
import com.mobile.mobilehardware.local.LocalBean;
import com.mobile.mobilehardware.local.LocalHelper;
import com.mobile.mobilehardware.memory.MemoryBean;
import com.mobile.mobilehardware.memory.MemoryHelper;
import com.mobile.mobilehardware.network.NetWorkBean;
import com.mobile.mobilehardware.network.NetWorkHelper;
import com.mobile.mobilehardware.root.RootHelper;
import com.mobile.mobilehardware.screen.ScreenBean;
import com.mobile.mobilehardware.screen.ScreenHelper;
import com.mobile.mobilehardware.signal.SignalBean;
import com.mobile.mobilehardware.signal.SignalHelper;
import com.mobile.mobilehardware.simcard.CellIdentityBean;
import com.mobile.mobilehardware.simcard.SimCardBean;
import com.mobile.mobilehardware.simcard.SimCardHelper;
import com.mobile.mobilehardware.wifilist.WifiBean;
import com.mobile.mobilehardware.wifilist.WifiHelper;

import java.util.ArrayList;
import java.util.HashMap;
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

            NetWorkBean  netWork       = NetWorkHelper.mobGetMobNetWorkBean();
            DebugBean    debuggingData = DebugHelper.getDebuggingDataBean();
            EmulatorBean emulator      = EmulatorHelper.mobCheckEmulatorBean();
            ScreenBean   screen        = ScreenHelper.mobGetMobScreenBean(null);
            BuildBean    buildInfo     = BuildHelper.mobGetBuildInfoBean();
            BandBean     bandInfo      = BandHelper.mobGetBandInfoBean();
            BatteryBean  battery       = BatteryHelper.mobGetBatteryBean();
            SignalBean   signalInfo    = SignalHelper.mobGetNetRssiBean();
            SimCardBean  simInfo       = SimCardHelper.mobileSimInfoBean();
            CpuBean      cpuInfo       = CpuHelper.mobGetCpuInfoBean();
            MemoryBean   memoryInfo    = MemoryHelper.getMemoryInfoBean();
            LocalBean    local         = LocalHelper.mobGetMobLocalBean();

            WifiHelper.wifiList(jsonObject -> {
                List<Map<String, Object>> wifiList = new ArrayList<>();
                try {
                    String                        curBSSID       = signalInfo.isEmpty(signalInfo.getBssid());
                    String                        mac            = signalInfo.isEmpty(signalInfo.getMacAddress());
                    List<WifiBean.WifiResultBean> wifiScanResult = jsonObject.getWifiScanResult();
                    if (wifiScanResult != null && wifiScanResult.size() > 0) {
                        int length = wifiScanResult.size();
                        for (int i = 0; i < length; i++) {
                            WifiBean.WifiResultBean jb    = wifiScanResult.get(i);
                            String                  bssid = jb.isEmpty(jb.getBSSID());
                            Map<String, Object>     map   = new HashMap<>();
                            map.put("ssid", jb.isEmpty(jb.getSSID()));
                            map.put("bssid", bssid);
                            map.put("current", bssid.equals(curBSSID));
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

                deviceBaseMap.put("vpnFlag", netWork.isVpn());
                deviceBaseMap.put("debugFlag", debuggingData.isOpenDebug());
                deviceBaseMap.put("emulatorFlag", emulator.isCheckBuild());
                deviceBaseMap.put("rootFlag", RootHelper.mobileRoot());

                deviceBaseMap.put("serial", buildInfo.isEmpty(buildInfo.getSerial()));
                deviceBaseMap.put("product", buildInfo.isEmpty(buildInfo.getProduct()));
                deviceBaseMap.put("brand", buildInfo.isEmpty(buildInfo.getBrand()));
                deviceBaseMap.put("hardware", buildInfo.isEmpty(buildInfo.getHardware()));
                deviceBaseMap.put("device", buildInfo.isEmpty(buildInfo.getDevice()));
                deviceBaseMap.put("buildType", buildInfo.isEmpty(buildInfo.getType()));
                deviceBaseMap.put("buildTags", buildInfo.isEmpty(buildInfo.getTags()));
                deviceBaseMap.put("sdkInt", buildInfo.getSdkInt());
                deviceBaseMap.put("buildUser", buildInfo.isEmpty(buildInfo.getUser()));
                deviceBaseMap.put("board", buildInfo.isEmpty(buildInfo.getBoard()));
                deviceBaseMap.put("display", buildInfo.isEmpty(buildInfo.getDisplay()));
                deviceBaseMap.put("hardwareId", buildInfo.isEmpty(buildInfo.getId()));
                deviceBaseMap.put("bootloader", buildInfo.isEmpty(buildInfo.getBootloader()));
                deviceBaseMap.put("fingerPrint", buildInfo.isEmpty(buildInfo.getFingerprint()));
                deviceBaseMap.put("buildHost", buildInfo.isEmpty(buildInfo.getHost()));
                deviceBaseMap.put("radio", buildInfo.isEmpty(buildInfo.getRadio()));
                deviceBaseMap.put("buildTime", buildInfo.getTime());
                deviceBaseMap.put("model", buildInfo.isEmpty(buildInfo.getModel()));
                deviceBaseMap.put("manufacturer", buildInfo.isEmpty(buildInfo.getManufacturer()));


                deviceBaseMap.put("ram", memoryInfo.isEmpty(memoryInfo.getRamMemory()));
                deviceBaseMap.put("rom", memoryInfo.isEmpty(memoryInfo.getSdCardRealMemoryTotal()));
                deviceBaseMap.put("canUseRom", memoryInfo.isEmpty(memoryInfo.getRomMemoryAvailable()));
                deviceBaseMap.put("canUseRam", memoryInfo.isEmpty(memoryInfo.getRamAvailMemory()));


                deviceBaseMap.put("mac", signalInfo.isEmpty(signalInfo.getMacAddress()));

                deviceBaseMap.put("language", local.isEmpty(local.getLanguage()));

                deviceBaseMap.put("baseBand", bandInfo.isEmpty(bandInfo.getBaseBand()));


                int    sHeight      = screen.getHeight();
                int    sWidth       = screen.getWidth();
                double density      = screen.getDensityScale();
                double physicalSize = Math.sqrt(Math.pow(sWidth, 2) + Math.pow(sHeight, 2)) / (160 * density);
                deviceBaseMap.put("resolution", sHeight + "*" + sWidth);
                deviceBaseMap.put("screenDensity", density);
                deviceBaseMap.put("screenDensityDpi", screen.getDensityDpi());
                deviceBaseMap.put("screenBrightness", screen.getScreenBrightness());
                deviceBaseMap.put("physicalSize", physicalSize);

                TimeZone timeZone = TimeZone.getDefault();
                deviceBaseMap.put("timeZone", timeZone.getID() + "_" + timeZone.getDisplayName(false, TimeZone.SHORT));

            } catch (Exception e) {
                e.printStackTrace();
            }


            try {

                batteryMap.put("batteryPercentage", battery.isEmpty(battery.getBr()));
                batteryMap.put("batteryStatus", battery.getStatus());
                batteryMap.put("chargingMode", battery.getPlugState());
                batteryMap.put("activeTime", SystemClock.uptimeMillis());
                batteryMap.put("upTime", SystemClock.elapsedRealtime());
                batteryMap.put("bootTime", System.currentTimeMillis() - SystemClock.elapsedRealtime());

            } catch (Exception e) {
                e.printStackTrace();
            }

            try {

                cpuMap.put("cpuName", cpuInfo.isEmpty(cpuInfo.getCpuName()));
                cpuMap.put("cpuFreq", cpuInfo.isEmpty(cpuInfo.getCpuFreq()));
                cpuMap.put("cpuMinFreq", cpuInfo.isEmpty(cpuInfo.getCpuMinFreq()));
                cpuMap.put("cpuMaxFreq", cpuInfo.isEmpty(cpuInfo.getCpuMaxFreq()));
                cpuMap.put("cpuHardware", cpuInfo.isEmpty(cpuInfo.getCpuHardware()));
                cpuMap.put("cpuCores", cpuInfo.getCpuCores());
                cpuMap.put("cpuTemp", cpuInfo.isEmpty(cpuInfo.getCpuTemp()));
                cpuMap.put("cpuAbi", cpuInfo.isEmpty(cpuInfo.getCpuAbi()));

            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                boolean twoCard  = simInfo.isTwoCard();
                boolean haveCard = simInfo.isHaveCard();
                simMap.put("simCount", twoCard ? 2 : (haveCard ? 1 : 0));
                simMap.put("simState1", simInfo.getSim1State());
                simMap.put("simState2", simInfo.getSim2State());
                simMap.put("meid", simInfo.isEmpty(simInfo.getMeid()));
                simMap.put("simOperator1", simInfo.isEmpty(simInfo.getSim1carrierName()));
                simMap.put("simOperator2", simInfo.isEmpty(simInfo.getSim2carrierName()));
                simMap.put("imsi1", simInfo.isEmpty(simInfo.getSim1Imsi()));
                simMap.put("imsi2", simInfo.isEmpty(simInfo.getSim2Imsi()));
                simMap.put("simNetType", simInfo.isEmpty(simInfo.getSimNetworkType()));

                List<CellIdentityBean> cellIdentityList = simInfo.getCellIdentityList();
                if (cellIdentityList != null && cellIdentityList.size() > 0) {
                    int length = cellIdentityList.size();

                    for (int i = 0; i < length; i++) {
                        CellIdentityBean    cell = cellIdentityList.get(i);
                        Map<String, Object> map  = new HashMap<>();
                        map.put("cid", cell.isEmpty(cell.getCid()));
                        map.put("lac", cell.isEmpty(cell.getLac()));
                        map.put("mcc", cell.isEmpty(cell.getMcc()));
                        map.put("mnc", cell.isEmpty(cell.getMnc()));
                        map.put("type", cell.isEmpty(cell.getType()));
                        map.put("dbm", cell.getDbm());

                        cellList.add(map);

                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            deviceBaseMap.putAll(cpuMap);
            resultListener.onDeviceResult(deviceBaseMap, batteryMap, simMap, cellList);
        }

    }


}
