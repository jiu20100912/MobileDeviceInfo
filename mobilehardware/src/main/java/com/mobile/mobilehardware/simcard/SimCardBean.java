package com.mobile.mobilehardware.simcard;

import android.util.Log;

import com.mobile.mobilehardware.base.BaseBean;
import com.mobile.mobilehardware.base.BaseData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * @author guxiaonian
 */
public class SimCardBean extends BaseBean {
    private static final String TAG = SimCardBean.class.getSimpleName();

    /**
     * imei  for sim1
     */
    private String sim1Imei;

    /**
     * imei for sim2
     */
    private String sim2Imei;

    /**
     * imsi for sim1
     */
    private String sim1Imsi;

    /**
     * imsi for sim2
     */
    private String sim2Imsi;

    /**
     * 有流量的卡的卡槽id
     */
    private int simSlotIndex = -1;

    /**
     * meid
     */
    private String meid;

    /**
     * 卡1运营商
     */
    private String sim1ImsiOperator;

    /**
     * 卡2运营商
     */
    private String sim2ImsiOperator;

    /**
     * 卡1是否激活
     */
    private boolean sim1Ready;

    /**
     * 卡1状态
     */
    private int sim1State;

    /**
     * 卡2状态
     */
    private int sim2State;

    /**
     * 卡2是否激活
     */
    private boolean sim2Ready;

    /**
     * 是否有两张卡
     */
    private boolean isTwoCard;

    /**
     * 是否有卡
     */
    private boolean isHaveCard;

    /**
     * 流量卡运营商
     */
    private String operator;

    /**
     * sim卡网络类型
     */
    private String simNetworkType;

    private String sim1IccId;
    private String sim2IccId;
    private int sim1SimId = -1;
    private int sim2SimId = -1;
    private int sim1subId = -1;
    private int sim2subId = -1;
    private String sim1mcc;
    private String sim2mcc;
    private String sim1mnc;
    private String sim2mnc;
    private String sim1carrierName;
    private String sim2carrierName;
    private List<CellIdentityBean> cellIdentityList;

    public String getSim1Imei() {
        return sim1Imei;
    }

    public void setSim1Imei(String sim1Imei) {
        this.sim1Imei = sim1Imei;
    }

    public String getSim2Imei() {
        return sim2Imei;
    }

    public void setSim2Imei(String sim2Imei) {
        this.sim2Imei = sim2Imei;
    }

    public String getSim1Imsi() {
        return sim1Imsi;
    }

    public void setSim1Imsi(String sim1Imsi) {
        this.sim1Imsi = sim1Imsi;
    }

    public String getSim2Imsi() {
        return sim2Imsi;
    }

    public void setSim2Imsi(String sim2Imsi) {
        this.sim2Imsi = sim2Imsi;
    }

    public int getSimSlotIndex() {
        return simSlotIndex;
    }

    public void setSimSlotIndex(int simSlotIndex) {
        this.simSlotIndex = simSlotIndex;
    }

    public String getMeid() {
        return meid;
    }

    public void setMeid(String meid) {
        this.meid = meid;
    }

    public String getSim1ImsiOperator() {
        return sim1ImsiOperator;
    }

    public void setSim1ImsiOperator(String sim1ImsiOperator) {
        this.sim1ImsiOperator = sim1ImsiOperator;
    }

    public String getSim2ImsiOperator() {
        return sim2ImsiOperator;
    }

    public void setSim2ImsiOperator(String sim2ImsiOperator) {
        this.sim2ImsiOperator = sim2ImsiOperator;
    }

    public boolean isSim1Ready() {
        return sim1Ready;
    }

    public void setSim1Ready(boolean sim1Ready) {
        this.sim1Ready = sim1Ready;
    }

    public boolean isSim2Ready() {
        return sim2Ready;
    }

    public void setSim2Ready(boolean sim2Ready) {
        this.sim2Ready = sim2Ready;
    }

    public boolean isTwoCard() {
        return isTwoCard;
    }

    public void setTwoCard(boolean twoCard) {
        isTwoCard = twoCard;
    }

    public boolean isHaveCard() {
        return isHaveCard;
    }

    public void setHaveCard(boolean haveCard) {
        isHaveCard = haveCard;
    }

    public String getSim1IccId() {
        return sim1IccId;
    }

    public void setSim1IccId(String sim1IccId) {
        this.sim1IccId = sim1IccId;
    }

    public String getSim2IccId() {
        return sim2IccId;
    }

    public void setSim2IccId(String sim2IccId) {
        this.sim2IccId = sim2IccId;
    }

    public int getSim1SimId() {
        return sim1SimId;
    }

    public void setSim1SimId(int sim1SimId) {
        this.sim1SimId = sim1SimId;
    }

    public int getSim2SimId() {
        return sim2SimId;
    }

    public void setSim2SimId(int sim2SimId) {
        this.sim2SimId = sim2SimId;
    }

    public int getSim1subId() {
        return sim1subId;
    }

    public void setSim1subId(int sim1subId) {
        this.sim1subId = sim1subId;
    }

    public int getSim2subId() {
        return sim2subId;
    }

    public void setSim2subId(int sim2subId) {
        this.sim2subId = sim2subId;
    }

    public String getSim1mcc() {
        return sim1mcc;
    }

    public void setSim1mcc(String sim1mcc) {
        this.sim1mcc = sim1mcc;
    }

    public String getSim2mcc() {
        return sim2mcc;
    }

    public void setSim2mcc(String sim2mcc) {
        this.sim2mcc = sim2mcc;
    }

    public String getSim1mnc() {
        return sim1mnc;
    }

    public void setSim1mnc(String sim1mnc) {
        this.sim1mnc = sim1mnc;
    }

    public String getSim2mnc() {
        return sim2mnc;
    }

    public void setSim2mnc(String sim2mnc) {
        this.sim2mnc = sim2mnc;
    }

    public String getSim1carrierName() {
        return sim1carrierName;
    }

    public void setSim1carrierName(String sim1carrierName) {
        this.sim1carrierName = sim1carrierName;
    }

    public String getSim2carrierName() {
        return sim2carrierName;
    }

    public void setSim2carrierName(String sim2carrierName) {
        this.sim2carrierName = sim2carrierName;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getSimNetworkType() {
        return simNetworkType;
    }

    public void setSimNetworkType(String simNetworkType) {
        this.simNetworkType = simNetworkType;
    }

    public int getSim1State() {
        return sim1State;
    }

    public void setSim1State(int sim1State) {
        this.sim1State = sim1State;
    }

    public int getSim2State() {
        return sim2State;
    }

    public void setSim2State(int sim2State) {
        this.sim2State = sim2State;
    }

    public List<CellIdentityBean> getCellIdentityList() {
        return cellIdentityList;
    }

    public void setCellIdentityList(List<CellIdentityBean> cellIdentityList) {
        this.cellIdentityList = cellIdentityList;
    }

}
