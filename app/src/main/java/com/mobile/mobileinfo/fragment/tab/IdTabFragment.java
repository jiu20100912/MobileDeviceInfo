package com.mobile.mobileinfo.fragment.tab;


import android.os.Bundle;

import com.mobile.mobileinfo.fragment.IDFragment;


public class IdTabFragment extends BaseFragment {

    public static IdTabFragment newInstance() {
        Bundle args = new Bundle();
        IdTabFragment fragment = new IdTabFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void addParam() {
        mList.add(IDFragment.newInstance());

    }

    @Override
    protected void getIntentUrl() {
        intentUrl.add("https://github.com/guxiaonian/MobileInfo/wiki/PhoneId");

    }

    @Override
    protected void getTitleList() {
        titleList.add("phoneId");
    }
}
