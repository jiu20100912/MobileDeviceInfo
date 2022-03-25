package com.mobile.mobilehardware.simcard;

import android.util.Log;

import com.mobile.mobilehardware.base.BaseBean;
import com.mobile.mobilehardware.base.BaseData;

import org.json.JSONObject;

public class CellIdentityBean extends BaseBean {
    private String cid;
    private String lac;
    private String mcc;
    private String mnc;
    private String type;
    private int dbm;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getLac() {
        return lac;
    }

    public void setLac(String lac) {
        this.lac = lac;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getMnc() {
        return mnc;
    }

    public void setMnc(String mnc) {
        this.mnc = mnc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDbm() {
        return dbm;
    }

    public void setDbm(int dbm) {
        this.dbm = dbm;
    }

    @Override
    protected JSONObject toJSONObject() {
        try {
            jsonObject.put(BaseData.CELL.CID, isEmpty(cid));
            jsonObject.put(BaseData.CELL.LAC, isEmpty(lac));
            jsonObject.put(BaseData.CELL.MCC, isEmpty(mcc));
            jsonObject.put(BaseData.CELL.MNC, isEmpty(mnc));
            jsonObject.put(BaseData.CELL.CELL_TYPE, isEmpty(type));
            jsonObject.put(BaseData.CELL.DBM, dbm);
        } catch (Exception e) {

        }
        return super.toJSONObject();
    }
}
