package com.mobile.mobilehardware.simcard;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;


import org.json.JSONObject;

/**
 * @author guxiaonian
 */
class SimCardInfo {
    private static final String TAG = SimCardInfo.class.getSimpleName();


    static SimCardBean getMobSimInfo(Context context) {
        SimCardBean simCardBean = new SimCardBean();
        try {
            simCardBean.setHaveCard(hasSimCard(context));
//            SimCardUtils.SimCardInfo simCardInfo = SimCardUtils.instance().getmSimCardInfo(context.getApplicationContext());
//            simCardBean.setSim1Imsi(simCardInfo.getSim1Imsi());
//            simCardBean.setSim2Imsi(simCardInfo.getSim2Imsi());
//            simCardBean.setOperator(getOperators(simCardInfo.getOperator(simSlotIndex)));
//            simCardBean.setSim1ImsiOperator(getOperators(simCardInfo.getSim1Imsi()));
//            simCardBean.setSim2ImsiOperator(getOperators(simCardInfo.getSim2Imsi()));

            MobCardUtils.mobGetCardInfo(context, simCardBean);


        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return simCardBean;

    }

    /**
     * 判断是否包含SIM卡
     *
     * @param context 上下文
     * @return 状态 是否包含SIM卡
     */
    private static boolean hasSimCard(Context context) {
        boolean result = true;
        try {
            TelephonyManager telMgr = (TelephonyManager)
                    context.getSystemService(Context.TELEPHONY_SERVICE);
            int simState = telMgr.getSimState();
            switch (simState) {
                case TelephonyManager.SIM_STATE_ABSENT:
                    result = false;
                    break;
                case TelephonyManager.SIM_STATE_UNKNOWN:
                    result = false;
                    break;
                default:
                    break;
            }
        } catch (Exception e) {

        }
        return result;
    }
}
