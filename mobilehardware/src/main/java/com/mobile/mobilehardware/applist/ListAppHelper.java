package com.mobile.mobilehardware.applist;


import com.mobile.mobilehardware.MobileHardWareHelper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guxiaonian
 */
public class ListAppHelper extends ListAppInfo {

    /**
     * 获取安装软件列表
     *
     * @return 应用列表
     */
    public static List<JSONObject> mobListApp() {
        List<JSONObject>  list         = new ArrayList<>();
        List<ListAppBean> listAppBeans = mobListAppBean();
        for (int i = 0; i < listAppBeans.size(); i++) {
            list.add(listAppBeans.get(i).toJSONObject());
        }
        return list;
    }

    public static List<ListAppBean> mobListAppBean() {
        return getMobListApp(MobileHardWareHelper.getContext());
    }
}
