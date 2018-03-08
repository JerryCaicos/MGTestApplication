package com.mg.mobile.app;

import android.app.Application;

import com.mg.mobile.app.service.DataBaseService;

/**
 * Created by chenaxing on 2018/3/6.
 */

public class MainApplication extends Application
{
    DataBaseService dataBaseService;
    @Override
    public void onCreate()
    {
        super.onCreate();
        dataBaseService = new DataBaseService();
        dataBaseService.onApplicationCreated(getApplicationContext());
    }

    public void exit()
    {
        if(dataBaseService != null)
        {
            dataBaseService.onApplicationDestory(getApplicationContext());
        }

        System.exit(0);
    }
}
