package com.mg.mobile.app.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by chenaxing on 2018/3/6.
 */
@DatabaseTable(tableName = TableConstants.TABLE_COUPON)
public class CouponModel
{
    @DatabaseField(generatedId = true,columnName = TableConstants.KEY_ID)
    public int keyId;
    @DatabaseField(canBeNull = false,columnName = TableConstants.KEY_COUPON_VALUE)
    public double couponValue;
    @DatabaseField(canBeNull = false,columnName = TableConstants.KEY_DISCOUNT)
    public double discount;
    @DatabaseField(canBeNull = false,columnName = TableConstants.KEY_OUT_TIME)
    public Date outDate;
}
