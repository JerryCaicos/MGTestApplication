package com.mg.mobile.app;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.mg.mobile.app.ui.CartFragment;
import com.mg.mobile.app.ui.MainShopFragment;
import com.mg.mobile.app.ui.MineFragment;
import com.mg.mobile.app.ui.adapter.MyPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    protected TabLayout mTabLayout;
    private ViewPager mViewPager;

    private List<String> mTitleList = new ArrayList<>();//页卡标题集合
    private List<Fragment> mViewList = new ArrayList<>();//页卡视图集合

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.vp_view);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        mTabLayout.addTab(mTabLayout.newTab());//添加tab选项卡
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());

        mTitleList.add(getResources().getText(R.string.app_shop).toString());
        mTitleList.add(getResources().getText(R.string.app_mine).toString());
        mTitleList.add(getResources().getText(R.string.app_cart).toString());

        MainShopFragment shopFragment = new MainShopFragment();
        MineFragment mineFragment = new MineFragment();
        CartFragment cartFragment = new CartFragment();

        mViewList.add(shopFragment);
        mViewList.add(mineFragment);
        mViewList.add(cartFragment);

        MyPagerAdapter mAdapter = new MyPagerAdapter(getSupportFragmentManager(), mTitleList, mViewList);
        mViewPager.setAdapter(mAdapter);//给ViewPager设置适配器
        mViewPager.setCurrentItem(0);

        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        ((MainApplication)getApplication()).exit();
    }
}
