package com.mg.mobile.app.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.mg.mobile.app.service.DBHelper;

/**
 * Created by chenaxing on 2018/3/7.
 */

public class BasicFragment extends Fragment
{
    public DBHelper dbHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        dbHelper = DBHelper.getInstance(getContext());
    }
}
