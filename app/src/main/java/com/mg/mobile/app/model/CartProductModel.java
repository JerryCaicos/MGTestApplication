package com.mg.mobile.app.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by chenaxing on 2018/3/7.
 */
@DatabaseTable(tableName = TableConstants.TABLE_CART)
public class CartProductModel
{
    @DatabaseField(generatedId = true, columnName = TableConstants.KEY_ID)
    public int keyId;
    @DatabaseField(canBeNull = false, columnName = TableConstants.KEY_CATE_ID)
    public String cateId;
    @DatabaseField(canBeNull = false, columnName = TableConstants.KEY_PRODUCT_NAME)
    public String productName;
    @DatabaseField(canBeNull = false, columnName = TableConstants.KEY_PRODUCT_PRICE)
    public double productPrice;
    @DatabaseField(canBeNull = false, columnName = TableConstants.KEY_NUM)
    public int num;

    public CartProductModel()
    {

    }

    public CartProductModel(ProductModel productModel)
    {
        cateId = productModel.cateId;
        productName = productModel.productName;
        productPrice = productModel.productPrice;
        num = 1;
    }
}
