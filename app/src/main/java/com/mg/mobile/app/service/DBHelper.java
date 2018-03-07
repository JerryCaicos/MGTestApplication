package com.mg.mobile.app.service;

import android.content.Context;

import com.mg.mobile.app.model.TableConstants;
import com.mg.mobile.app.utils.LogUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public  class DBHelper<T>
{
    private static DBHelper mDBHelper;

    private Context mContext;

    private DataBaseHelper mDataBaseHelper;

    private DBHelper(Context context)
    {
        mContext = context;
        mDataBaseHelper = DataBaseHelper.getInstance(context);
    }

    public static DBHelper getInstance(Context context)
    {
        if(mDBHelper == null)
        {
            synchronized(DBHelper.class)
            {
                if(mDBHelper == null)
                {
                    mDBHelper = new DBHelper(context);
                }
            }
        }
        return mDBHelper;
    }

    public synchronized void insertBean(Class<T> clazz, T data)
    {
        try
        {
            mDataBaseHelper.getBeanDao(clazz).create(data);
        }
        catch(SQLException e)
        {
            LogUtils.e(this, e);
        }
    }

    public synchronized void updateBean(Class<T> clazz, T data)
    {
        try
        {
            mDataBaseHelper.getBeanDao(clazz).update(data);
        }
        catch(SQLException e)
        {
            LogUtils.e(this, e);
        }
    }

    public synchronized void deleteBean(Class<T> clazz, T data)
    {
        try
        {
            mDataBaseHelper.getBeanDao(clazz).delete(data);
        }
        catch(SQLException e)
        {
            LogUtils.e(this, e);
        }
    }

    public synchronized List<T> queryBeans(Class<T> clazz)
    {
        List<T> list = new ArrayList<>();
        try
        {
            list.addAll(mDataBaseHelper.getBeanDao(clazz).queryForAll());
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return list;
    }

    public synchronized List<T> queryObjectBean(Class<T> clazz, Map<String, String> map)
    {
        List<T> list = new ArrayList<>();
        try
        {
            list.addAll(mDataBaseHelper.getBeanDao(clazz).queryForFieldValues(map));
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return list;
    }

    public void close()
    {
        mDataBaseHelper.close();
        mDBHelper = null;
        mDataBaseHelper = null;
    }
}
