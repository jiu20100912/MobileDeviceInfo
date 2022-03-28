package com.mobile.mobilehardware.simcard;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityNr;
import android.telephony.CellIdentityTdscdma;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoNr;
import android.telephony.CellInfoTdscdma;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrength;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.CellSignalStrengthLte;
import android.telephony.CellSignalStrengthNr;
import android.telephony.CellSignalStrengthTdscdma;
import android.telephony.CellSignalStrengthWcdma;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;


import com.mobile.mobilehardware.base.BaseData;
import com.mobile.mobilehardware.exceptions.MobException;


import org.json.JSONArray;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/**
 * @author guxiaonian
 */
public class MobCardUtils {

    private static final String TAG = MobCardUtils.class.getSimpleName();

    private static final String CM_MOBILE1 = "46000";
    private static final String CM_MOBILE2 = "46002";
    private static final String CM_MOBILE3 = "46004";
    private static final String CM_MOBILE4 = "46007";
    private static final String CU_MOBILE1 = "46001";
    private static final String CU_MOBILE2 = "46006";
    private static final String CU_MOBILE3 = "46009";
    private static final String CT_MOBILE1 = "46003";
    private static final String CT_MOBILE2 = "46005";
    private static final String CT_MOBILE3 = "46011";

    /**
     * 获取网络运营商，CM是移动，CU是联通，CT是电信
     *
     * @param data str
     * @return str
     */
    private static String getOperators(String data) {
        if (!TextUtils.isEmpty(data)) {
            if (data.startsWith(CM_MOBILE1) || data.startsWith(CM_MOBILE2) || data.startsWith(CM_MOBILE3) || data.startsWith(CM_MOBILE4)) {
                return "CM";
            } else if (data.startsWith(CU_MOBILE1) || data.startsWith(CU_MOBILE2) || data.startsWith(CU_MOBILE3)) {
                return "CU";
            } else if (data.startsWith(CT_MOBILE1) || data.startsWith(CT_MOBILE2) || data.startsWith(CT_MOBILE3)) {
                return "CT";
            } else {
                return null;
            }
        } else {
            return null;
        }

    }

    /**
     * mobile info
     *
     * @param context
     * @return
     */
    @SuppressLint({"HardwareIds", "MissingPermission"})
    public static void mobGetCardInfo(Context context, SimCardBean simCardBean) {
        TelephonyManager telephonyManager = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE));
        if (telephonyManager == null) {
            return;
        }
        int simStub = getDefaultDataSub(context);
        int sim1    = 0;
        int sim2    = 1;
        if (simStub == 2) {
            sim1 = 1;
            sim2 = 2;
        }
        simCardBean.setSimSlotIndex(simStub);
        boolean sim1Ready = telephonyManager.getSimState() == TelephonyManager.SIM_STATE_READY;
        boolean sim2Ready = false;
        try {
            simCardBean.setSim1State(getSIMStateBySlot(telephonyManager, "getSimStateGemini", sim1));
            simCardBean.setSim2State(getSIMStateBySlot(telephonyManager, "getSimStateGemini", sim2));
            sim1Ready = simCardBean.getSim1State() == TelephonyManager.SIM_STATE_READY;
            sim2Ready = simCardBean.getSim2State() == TelephonyManager.SIM_STATE_READY;
        } catch (MobException e) {
            try {
                simCardBean.setSim1State(getSIMStateBySlot(telephonyManager, "getSimState", sim1));
                simCardBean.setSim2State(getSIMStateBySlot(telephonyManager, "getSimState", sim2));
                sim1Ready = simCardBean.getSim1State() == TelephonyManager.SIM_STATE_READY;
                sim2Ready = simCardBean.getSim2State() == TelephonyManager.SIM_STATE_READY;
            } catch (MobException e1) {
                Log.i(TAG, e1.toString());
            }

        }
        simCardBean.setSim1Ready(sim1Ready);
        simCardBean.setSim2Ready(sim2Ready);
        simCardBean.setTwoCard((sim1Ready && sim2Ready));

        String sim1Imei = null;
        String sim2Imei = null;
        try {
            sim1Imei = getSIMOperator(telephonyManager, "getDeviceIdGemini", sim1);
            sim2Imei = getSIMOperator(telephonyManager, "getDeviceIdGemini", sim2);
        } catch (MobException e) {
            try {
                sim1Imei = getSIMOperator(telephonyManager, "getDeviceId", sim1);
                sim2Imei = getSIMOperator(telephonyManager, "getDeviceId", sim2);

            } catch (MobException e1) {
                Log.i(TAG, e1.toString());
            }
        }
        if (!TextUtils.isEmpty(sim1Imei)) {
            simCardBean.setSim1Imei(MidInfo.isNumeric(sim1Imei) ? sim1Imei : null);
        }
        if (!TextUtils.isEmpty(sim2Imei)) {
            simCardBean.setSim2Imei(MidInfo.isNumeric(sim2Imei) ? sim2Imei : null);
        }

        String sim1Imsi = null;
        String sim2Imsi = null;
        try {
            sim1Imsi = getSIMOperator(telephonyManager, "getSubscriberIdGemini", sim1);
            sim2Imsi = getSIMOperator(telephonyManager, "getSubscriberIdGemini", sim2);
        } catch (MobException e) {
            try {
                sim1Imsi = getSIMOperator(telephonyManager, "getSubscriberId", sim1);
                sim2Imsi = getSIMOperator(telephonyManager, "getSubscriberId", sim2);

            } catch (MobException e1) {
                Log.i(TAG, e1.toString());
            }
        }
        simCardBean.setSim1Imsi(sim1Imsi);
        simCardBean.setSim2Imsi(sim2Imsi);

        String sim1Operator = null;
        String sim2Operator = null;
        try {
            sim1Operator = getOperators(getSIMOperator(telephonyManager, "getSimOperatorGemini", sim1));
            sim2Operator = getOperators(getSIMOperator(telephonyManager, "getSimOperatorGemini", sim2));
        } catch (MobException e) {
            try {
                sim1Operator = getOperators(getSIMOperator(telephonyManager, "getSimOperator", sim1));
                sim2Operator = getOperators(getSIMOperator(telephonyManager, "getSimOperator", sim2));

            } catch (MobException e1) {
                Log.i(TAG, e1.toString());
            }
        }

        simCardBean.setMeid(MidInfo.getMeid(context));
        simInfoQuery(context, simCardBean);
        if (TextUtils.isEmpty(sim1Operator)) {
            if (TextUtils.isEmpty(simCardBean.getSim1carrierName())) {
                simCardBean.setSim1ImsiOperator(getIccidOperators(simCardBean.getSim1IccId()));
            } else {
                simCardBean.setSim1ImsiOperator(getCarrierOperators(simCardBean.getSim1carrierName()));
            }
        } else {
            simCardBean.setSim1ImsiOperator(sim1Operator);
        }


        if (TextUtils.isEmpty(sim2Operator)) {
            if (TextUtils.isEmpty(simCardBean.getSim2carrierName())) {
                simCardBean.setSim2ImsiOperator(getIccidOperators(simCardBean.getSim2IccId()));
            } else {
                simCardBean.setSim2ImsiOperator(getCarrierOperators(simCardBean.getSim2carrierName()));
            }
        } else {
            simCardBean.setSim2ImsiOperator(sim2Operator);
        }

        simCardBean.setOperator(simCardBean.getSimSlotIndex() == 0 ? simCardBean.getSim1ImsiOperator() : simCardBean.getSim2ImsiOperator());
        if (TextUtils.isEmpty(simCardBean.getOperator())) {
            String operator = telephonyManager.getSimOperator();
            if (TextUtils.isEmpty(operator) && Build.VERSION.SDK_INT <= 28 && checkPermission(context, Manifest.permission.READ_PHONE_STATE)) {
                String thisOperator = null;
                try {
                    thisOperator = telephonyManager.getSubscriberId();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (!TextUtils.isEmpty(thisOperator) && thisOperator.length() >= 5) {
                    operator = thisOperator.substring(0, 5);

                }
            }
            simCardBean.setOperator(getOperators(operator));
        }

        if (simCardBean.isHaveCard()) {
            simCardBean.setSimNetworkType(networkTypeMobile(context));
        }


        List<CellIdentityBean> cellIdentityBeanList = new ArrayList<>();

        TelephonyManager tm    = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        List<CellInfo>   infos = tm.getAllCellInfo();
        for (CellInfo info : infos) {
            String mcc    = "0";
            String mnc    = "0";
            String lac    = "0";
            String cellId = "0";
            int    rssi   = 0;
            String type   = "";
            if (info instanceof CellInfoCdma) {
                CellInfoCdma     cellInfoCdma     = (CellInfoCdma) info;
                CellIdentityCdma cellIdentityCdma = cellInfoCdma.getCellIdentity();
                mnc = cellIdentityCdma.getSystemId() + "";
                mcc = cellIdentityCdma.getBasestationId() + "";
                lac = cellIdentityCdma.getNetworkId() + "";
                cellId = cellIdentityCdma.getBasestationId() + "";
                CellSignalStrengthCdma cellSignalStrengthCdma = cellInfoCdma.getCellSignalStrength();
                rssi = cellSignalStrengthCdma.getCdmaDbm();
                type = "CDMA";
            } else if (info instanceof CellInfoGsm) {
                CellInfoGsm     cellInfoGsm     = (CellInfoGsm) info;
                CellIdentityGsm cellIdentityGsm = cellInfoGsm.getCellIdentity();
                mnc = cellIdentityGsm.getMnc() + "";
                mcc = cellIdentityGsm.getMcc() + "";
                lac = cellIdentityGsm.getLac() + "";
                cellId = cellIdentityGsm.getCid() + "";
                CellSignalStrengthGsm cellSignalStrengthGsm = cellInfoGsm.getCellSignalStrength();
                rssi = cellSignalStrengthGsm.getDbm();
                type = "GSM";
            } else if (info instanceof CellInfoLte) {
                CellInfoLte     cellInfoLte     = (CellInfoLte) info;
                CellIdentityLte cellIdentityLte = cellInfoLte.getCellIdentity();
                mnc = cellIdentityLte.getMnc() + "";
                mcc = cellIdentityLte.getMcc() + "";
                lac = cellIdentityLte.getTac() + "";
                cellId = cellIdentityLte.getCi() + "";
                CellSignalStrengthLte cellSignalStrengthLte = cellInfoLte.getCellSignalStrength();
                rssi = cellSignalStrengthLte.getDbm();
                type = "LTE";
            } else if (info instanceof CellInfoWcdma) {
                CellInfoWcdma     cellInfoWcdma     = (CellInfoWcdma) info;
                CellIdentityWcdma cellIdentityWcdma = cellInfoWcdma.getCellIdentity();
                mnc = cellIdentityWcdma.getMnc() + "";
                mcc = cellIdentityWcdma.getMcc() + "";
                lac = cellIdentityWcdma.getLac() + "";
                cellId = cellIdentityWcdma.getCid() + "";
                CellSignalStrengthWcdma cellSignalStrength = cellInfoWcdma.getCellSignalStrength();
                rssi = cellSignalStrength.getDbm();
                type = "WCDMA";
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                if (info instanceof CellInfoTdscdma) {
                    CellInfoTdscdma           cellInfoWcdma           = (CellInfoTdscdma) info;
                    CellIdentityTdscdma       cellIdentityWcdma       = null;
                    CellSignalStrengthTdscdma cellSignalStrengthWcdma = null;
                    cellIdentityWcdma = cellInfoWcdma.getCellIdentity();
                    mnc = cellIdentityWcdma.getMncString();
                    mcc = cellIdentityWcdma.getMncString();
                    lac = cellIdentityWcdma.getLac() + "";
                    cellId = cellIdentityWcdma.getCid() + "";
                    cellSignalStrengthWcdma = cellInfoWcdma.getCellSignalStrength();
                    rssi = cellSignalStrengthWcdma.getDbm();
                    type = "TDSCDMA";
                } else if (info instanceof CellInfoNr) {
                    CellInfoNr     cellInfo     = (CellInfoNr) info;
                    CellIdentityNr cellIdentity = null;
                    cellIdentity = (CellIdentityNr) cellInfo.getCellIdentity();
                    mnc = cellIdentity.getMncString();
                    mcc = cellIdentity.getMccString();
                    lac = cellIdentity.getTac() + "";
                    cellId = cellIdentity.getPci() + "";
                    CellSignalStrength cellSignalStrength = cellInfo.getCellSignalStrength();
                    rssi = cellSignalStrength.getDbm();
                    type = "NR";
                }
            }

            if (!type.isEmpty()) {
                CellIdentityBean bean = new CellIdentityBean();
                bean.setMcc(mcc);
                bean.setMnc(mnc);
                bean.setCid(cellId);
                bean.setLac(lac);
                bean.setType(type);
                bean.setDbm(rssi);
                cellIdentityBeanList.add(bean);
            }

            Log.d("card", "" + mcc + ":" + mnc + ":" + cellId + ":" + lac + ":" + type + ":" + rssi);
        }

        simCardBean.setCellIdentityList(cellIdentityBeanList);
    }

    private static boolean checkPermission(Context context, String permission) {
        PackageManager packageManager = context.getPackageManager();
        return packageManager != null && PackageManager.PERMISSION_GRANTED == packageManager.checkPermission(permission, context.getPackageName());
    }

    private static String getIccidOperators(String data) {
        if (!TextUtils.isEmpty(data)) {
            if (data.startsWith("898600") || data.startsWith("898602") || data.startsWith("898604") || data.startsWith("898607")) {
                return "CM";
            } else if (data.startsWith("898601") || data.startsWith("898606") || data.startsWith("898609")) {
                return "CU";
            } else if (data.startsWith("898603") || data.startsWith("898611")) {
                return "CT";
            } else {
                return null;
            }
        } else {
            return null;
        }

    }

    private static String getCarrierOperators(String data) {
        if (!TextUtils.isEmpty(data)) {
            if (data.startsWith("中国移动")) {
                return "CM";
            } else if (data.startsWith("中国联通")) {
                return "CU";
            } else if (data.startsWith("中国电信")) {
                return "CT";
            } else {
                return null;
            }
        } else {
            return null;
        }

    }

    private static void simInfoQuery(Context context, SimCardBean simCardBean) {
        Uri             uri  = Uri.parse("content://telephony/siminfo");
        ContentResolver var4 = context.getContentResolver();
        try (Cursor cursor = var4.query(uri, new String[]{"_id", "icc_id", "sim_id", "mcc", "mnc", "carrier_name", "number"}, "sim_id>=?", new String[]{"0"}, (String) null)) {
            if (null != cursor) {
                while (cursor.moveToNext()) {
                    String iccId       = cursor.getString(cursor.getColumnIndex("icc_id"));
                    int    simId       = cursor.getInt(cursor.getColumnIndex("sim_id"));
                    int    subId       = cursor.getInt(cursor.getColumnIndex("_id"));
                    String mcc         = cursor.getString(cursor.getColumnIndex("mcc"));
                    String mnc         = cursor.getString(cursor.getColumnIndex("mnc"));
                    String carrierName = cursor.getString(cursor.getColumnIndex("carrier_name"));
                    if (simId == 0) {
                        simCardBean.setSim1IccId(iccId);
                        simCardBean.setSim1SimId(simId);
                        simCardBean.setSim1subId(subId);
                        simCardBean.setSim1mcc(mcc);
                        simCardBean.setSim1mnc(mnc);
                        simCardBean.setSim1carrierName(carrierName);
                    } else if (simId == 1) {
                        simCardBean.setSim2IccId(iccId);
                        simCardBean.setSim2SimId(simId);
                        simCardBean.setSim2subId(subId);
                        simCardBean.setSim2mcc(mcc);
                        simCardBean.setSim2mnc(mnc);
                        simCardBean.setSim2carrierName(carrierName);
                    }
                }
            }
        } catch (Exception var15) {
            var15.printStackTrace();
        }
    }

    /**
     * 卡状态
     *
     * @param predictedMethodName
     * @param slotID
     * @return
     */
    private static int getSIMStateBySlot(TelephonyManager telephony, String predictedMethodName, int slotID) throws MobException {

        try {

            Class<?> telephonyClass = Class.forName(telephony.getClass().getName());

            Class<?>[] parameter = new Class[1];
            parameter[0] = int.class;
            Method getSimStateGemini = telephonyClass.getMethod(predictedMethodName, parameter);

            Object[] obParameter = new Object[1];
            obParameter[0] = slotID;
            Object ob_phone = getSimStateGemini.invoke(telephony, obParameter);

            if (ob_phone != null) {
                return Integer.parseInt(ob_phone.toString());
            }
        } catch (Exception e) {
            throw new MobException(predictedMethodName);
        }

        return 0;
    }


    /**
     * 获取哪张卡开启的运营商
     *
     * @param context con
     * @return int
     */
    private static int getDefaultDataSub(Context context) {
        int num = -1;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
            SubscriptionManager sm = SubscriptionManager.from(context);
            try {
                Method getSubId = sm.getClass().getDeclaredMethod("getDefaultDataSubscriptionId");
                if (getSubId != null) {
                    try {
                        num = (int) getSubId.invoke(sm);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            } catch (NoSuchMethodException e) {
                try {
                    @SuppressLint("PrivateApi") Method getSubId = sm.getClass().getDeclaredMethod("getDefaultDataSubId");
                    if (getSubId != null) {
                        try {
                            num = (int) getSubId.invoke(sm);
                        } catch (IllegalAccessException | InvocationTargetException e1) {
                            e1.printStackTrace();
                        }
                    }
                } catch (NoSuchMethodException e1) {
                    /**
                     * 新加一个方案，此方案也是用于拿到获取的运营商，跟getDefaultDataSubscriptionId平级
                     */
                    try {
                        Method slot = sm.getClass().getMethod("getDefaultDataSubscriptionInfo");
                        try {
                            SubscriptionInfo subscriptionInfo = (SubscriptionInfo) slot.invoke(sm);
                            num = subscriptionInfo.getSimSlotIndex();
                        } catch (IllegalAccessException | InvocationTargetException e2) {
                            e2.printStackTrace();
                        }
                    } catch (NoSuchMethodException e3) {
                        e3.printStackTrace();
                    }
                }

            }
        }

        return num;
    }

    /**
     * 获取卡的imei信息
     */
    private static String getSIMOperator(TelephonyManager telephony, String predictedMethodName, int slotID) throws MobException {

        String imei = "";


        try {

            Class<?> telephonyClass = Class.forName(telephony.getClass().getName());

            Class<?>[] parameter = new Class[1];
            parameter[0] = int.class;
            Method getSimStateGemini = telephonyClass.getMethod(predictedMethodName, parameter);

            Object[] obParameter = new Object[1];
            obParameter[0] = slotID;
            Object phone = getSimStateGemini.invoke(telephony, obParameter);

            if (phone != null) {
                imei = phone.toString();
            }
        } catch (Exception e) {
            throw new MobException(predictedMethodName);
        }

        return imei;
    }

    public static String networkTypeMobile(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context
                .TELEPHONY_SERVICE);
        if (telephonyManager == null) {
            return "";
        }
        @SuppressLint("MissingPermission") int networkType = telephonyManager.getNetworkType();
        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_GSM:
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return "2G";
            case TelephonyManager.NETWORK_TYPE_TD_SCDMA:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return "3G";
            case TelephonyManager.NETWORK_TYPE_IWLAN:
            case TelephonyManager.NETWORK_TYPE_LTE:
                return "4G";
            case TelephonyManager.NETWORK_TYPE_NR:
                return "5G";
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
            default:
                return BaseData.UNKNOWN_PARAM;
        }
    }

}
