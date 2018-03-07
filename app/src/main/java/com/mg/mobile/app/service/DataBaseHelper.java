package com.mg.mobile.app.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.mg.mobile.app.model.CartProductModel;
import com.mg.mobile.app.model.CategoryModel;
import com.mg.mobile.app.model.CouponModel;
import com.mg.mobile.app.model.ProductModel;
import com.mg.mobile.app.utils.LogUtils;

import java.sql.SQLException;

public class DataBaseHelper<T, ID> extends OrmLiteSqliteOpenHelper
{
    // name of the database file for your application -- change to something appropriate for your app
    private static final String DATABASE_NAME = "wgtest.db";
    // any time you make changes to your database objects, you may have to increase the database version
    private static int DATABASE_VERSION = 1;

    public static DataBaseHelper mDataBaseHelper;

    public Dao<T, ID> beanDao;

    private DataBaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DataBaseHelper getInstance(Context context)
    {
        if(mDataBaseHelper == null)
        {
            synchronized(DataBaseHelper.class)
            {
                if(mDataBaseHelper == null){
                    mDataBaseHelper = new DataBaseHelper(context);
                }
            }
        }
        return mDataBaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource)
    {
        try
        {
            TableUtils.createTableIfNotExists(connectionSource, CategoryModel.class);
            TableUtils.createTableIfNotExists(connectionSource, ProductModel.class);
            TableUtils.createTableIfNotExists(connectionSource, CouponModel.class);
            TableUtils.createTableIfNotExists(connectionSource, CartProductModel.class);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion)
    {
        try
        {
            TableUtils.dropTable(connectionSource, CategoryModel.class, true);
            TableUtils.dropTable(connectionSource, ProductModel.class, true);
            TableUtils.dropTable(connectionSource, CouponModel.class, true);
            TableUtils.dropTable(connectionSource, CartProductModel.class, true);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        onCreate(database, connectionSource);
    }

    public Dao<T, ID> getBeanDao(Class<T> clazz)
    {
        try
        {
            beanDao = getDao(clazz);
        }
        catch(SQLException e)
        {
            LogUtils.e(this, e);
        }
        return beanDao;
    }

    @Override
    public void close()
    {
        super.close();
        beanDao = null;
        mDataBaseHelper = null;
    }
}
