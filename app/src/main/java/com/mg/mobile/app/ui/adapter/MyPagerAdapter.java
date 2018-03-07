package com.mg.mobile.app.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by chenaxing on 2018/3/6.
 */

public class MyPagerAdapter extends FragmentStatePagerAdapter
{
    private List<Fragment> viewList;
    private List<String> titleList;

    public MyPagerAdapter(FragmentManager fm,List<String> titles,List<Fragment> views)
    {
        super(fm);
        viewList = views;
        titleList = titles;
    }

    @Override
    public int getCount()
    {
        return viewList.size();//页卡数
    }


    @Override
    public Fragment getItem(int position)
    {
        return viewList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        String title = "";
        if(titleList != null && titleList.size() > 0){
            title = titleList.get(position);
        }
        return title;
    }
}
