package com.mg.mobile.app.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by chenaxing on 2018/3/6.
 */

@DatabaseTable(tableName = TableConstants.TABLE_CATEGORY)
public class CategoryModel
{
    @DatabaseField(generatedId = true,columnName = TableConstants.KEY_ID)
    public int keyId;
    @DatabaseField(canBeNull = false,columnName = TableConstants.KEY_CATE_ID)
    public String cateId;
    @DatabaseField(canBeNull = false,columnName = TableConstants.KEY_CATE_NAME)
    public String cateName;
    @DatabaseField(canBeNull = false,columnName = TableConstants.KEY_AGIO)
    public double agio;
    @DatabaseField(canBeNull = false,columnName = TableConstants.KEY_HAS_AGIO)
    public int hasAgio;
    @DatabaseField(canBeNull = false,columnName = TableConstants.KEY_OUT_TIME)
    public Date outDate;
}
