package com.mg.mobile.app.service;

import android.content.Context;

import com.mg.mobile.app.model.CategoryModel;
import com.mg.mobile.app.model.CouponModel;
import com.mg.mobile.app.model.ProductModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by chenaxing on 2018/3/7.
 */

public class DataBaseService implements BasicService
{
    private DBHelper mDbHelper;

    private Date date;

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void onApplicationCreated(Context context)
    {
        mDbHelper = DBHelper.getInstance(context);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, 24);

        date = calendar.getTime();

        initData();
    }

    @Override
    public void onApplicationDestory(Context context)
    {
        mDbHelper.close();
    }

    private void initData()
    {
        if(mDbHelper.queryBeans(CategoryModel.class).size() <= 0)
        {
            List<CategoryModel> categoryModelList = new ArrayList<>();
            CategoryModel categoryModel = new CategoryModel();
            categoryModel.cateId = "electric";
            categoryModel.cateName = "电子";
            categoryModel.agio = 0.7;
            categoryModel.hasAgio = 1;
            categoryModel.outDate = date;
            categoryModelList.add(categoryModel);

            categoryModel = null;
            categoryModel = new CategoryModel();
            categoryModel.cateId = "food";
            categoryModel.cateName = "食品";
            categoryModel.agio = 1.0;
            categoryModel.hasAgio = 0;
            categoryModel.outDate = date;
            categoryModelList.add(categoryModel);

            categoryModel = null;
            categoryModel = new CategoryModel();
            categoryModel.cateId = "dateuse";
            categoryModel.cateName = "日用品";
            categoryModel.agio = 1.0;
            categoryModel.hasAgio = 0;
            categoryModel.outDate = date;
            categoryModelList.add(categoryModel);

            categoryModel = null;
            categoryModel = new CategoryModel();
            categoryModel.cateId = "wines";
            categoryModel.cateName = "酒类";
            categoryModel.agio = 1.0;
            categoryModel.hasAgio = 0;
            categoryModel.outDate = date;
            categoryModelList.add(categoryModel);

            int len = categoryModelList.size();
            for(int i = 0; i < len; i++)
            {
                mDbHelper.insertBean(CategoryModel.class, categoryModelList.get(i));
            }
        }

        if(mDbHelper.queryBeans(ProductModel.class).size() <= 0)
        {
            List<ProductModel> productModelList = new ArrayList<>();
            ProductModel model = new ProductModel();
            model.cateId = "electric";
            model.productName = "ipad";
            model.productPrice = 2399.00;
            productModelList.add(model);

            model = null;
            model = new ProductModel();
            model.cateId = "electric";
            model.productName = "iphone";
            model.productPrice = 5288.00;
            productModelList.add(model);

            model = null;
            model = new ProductModel();
            model.cateId = "electric";
            model.productName = "显示器";
            model.productPrice = 899.00;
            productModelList.add(model);

            model = null;
            model = new ProductModel();
            model.cateId = "electric";
            model.productName = "笔记本电脑";
            model.productPrice = 4599.00;
            productModelList.add(model);

            model = null;
            model = new ProductModel();
            model.cateId = "electric";
            model.productName = "键盘";
            model.productPrice = 68.00;
            productModelList.add(model);

            model = null;
            model = new ProductModel();
            model.cateId = "food";
            model.productName = "面包";
            model.productPrice = 3.00;
            productModelList.add(model);

            model = null;
            model = new ProductModel();
            model.cateId = "food";
            model.productName = "饼干";
            model.productPrice = 5.00;
            productModelList.add(model);

            model = null;
            model = new ProductModel();
            model.cateId = "food";
            model.productName = "蛋糕";
            model.productPrice = 20.00;
            productModelList.add(model);

            model = null;
            model = new ProductModel();
            model.cateId = "food";
            model.productName = "牛肉";
            model.productPrice = 25.00;
            productModelList.add(model);

            model = null;
            model = new ProductModel();
            model.cateId = "food";
            model.productName = "鱼";
            model.productPrice = 13.00;
            productModelList.add(model);

            model = null;
            model = new ProductModel();
            model.cateId = "food";
            model.productName = "蔬菜";
            model.productPrice = 3.00;
            productModelList.add(model);

            model = null;
            model = new ProductModel();
            model.cateId = "dateuse";
            model.productName = "餐巾纸";
            model.productPrice = 10.00;
            productModelList.add(model);

            model = null;
            model = new ProductModel();
            model.cateId = "dateuse";
            model.productName = "收纳箱";
            model.productPrice = 20.00;
            productModelList.add(model);

            model = null;
            model = new ProductModel();
            model.cateId = "dateuse";
            model.productName = "咖啡杯";
            model.productPrice = 5.00;
            productModelList.add(model);

            model = null;
            model = new ProductModel();
            model.cateId = "wines";
            model.productName = "雨伞";
            model.productPrice = 45.00;
            productModelList.add(model);

            model = null;
            model = new ProductModel();
            model.cateId = "wines";
            model.productName = "啤酒";
            model.productPrice = 2.00;
            productModelList.add(model);

            model = null;
            model = new ProductModel();
            model.cateId = "wines";
            model.productName = "白酒";
            model.productPrice = 150.00;
            productModelList.add(model);

            model = null;
            model = new ProductModel();
            model.cateId = "wines";
            model.productName = "伏特加";
            model.productPrice = 230.00;
            productModelList.add(model);

            int size = productModelList.size();
            for(int i = 0; i < size; i++)
            {
                mDbHelper.insertBean(ProductModel.class, productModelList.get(i));
            }
        }

        if(mDbHelper.queryBeans(CouponModel.class).size() <= 0)
        {
            CouponModel couponModel = new CouponModel();
            couponModel.couponValue = 1000;
            couponModel.discount = 200;
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, 2020);
            calendar.set(Calendar.MONTH, 2);
            calendar.set(Calendar.DATE, 1);
            couponModel.outDate = calendar.getTime();

            mDbHelper.insertBean(CouponModel.class, couponModel);
        }
    }
}
