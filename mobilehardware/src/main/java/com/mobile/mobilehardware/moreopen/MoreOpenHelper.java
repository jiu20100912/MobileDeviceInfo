package com.mobile.mobilehardware.moreopen;




import com.mobile.mobilehardware.MobileHardWareHelper;

import org.json.JSONObject;


/**
 * @author guxiaonian
 */
public class MoreOpenHelper extends MoreOpenInfo {

    public static JSONObject checkVirtual() {
        return checkVirtualBean().toJSONObject();
    }

    public static MoreOpenBean checkVirtualBean() {
        return checkVirtualInfo(MobileHardWareHelper.getContext());
    }

}
