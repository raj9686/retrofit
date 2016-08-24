package com.example.raj.mytest.Model;

import com.example.raj.mytest.R;

/**
 * Created by Raj on 8/23/2016.
 */
public enum CustomPagerEnum {
    CONTACT(R.string.contact, R.layout.fragment_contact),
    BLUE(R.string.webservice, R.layout.fragment_web_service);

    private int mTitleResId;
    private int mLayoutResId;

    CustomPagerEnum(int titleResId, int layoutResId) {
        mTitleResId = titleResId;
        mLayoutResId = layoutResId;
    }

    public int getTitleResId() {
        return mTitleResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }

}
