package com.example.raj.mytest.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.raj.mytest.Fragment.ContactFragment;
import com.example.raj.mytest.Fragment.WebServiceFragment;
import com.example.raj.mytest.Model.CustomPagerEnum;

/**
 * Created by Raj on 8/23/2016.
 */
public class CustomPagerAdapter extends FragmentPagerAdapter {
    private final int pagerSize;
    private String title[] = {"CURRICULA", "GROUPS"};
    private Context mContext;

    public CustomPagerAdapter(FragmentManager fm, int size) {
        super(fm);
        pagerSize = size;
    }

    @Override
    public Fragment getItem(int index) {
        switch (index) {
            case 0:
                return new WebServiceFragment();
            case 1:
                return new ContactFragment();
            default:
                return new WebServiceFragment();
        }

    }

    @Override
    public int getCount() {
        return pagerSize;
    }


    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
