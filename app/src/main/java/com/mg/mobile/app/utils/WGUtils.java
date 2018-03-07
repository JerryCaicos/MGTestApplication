package com.mg.mobile.app.utils;

import android.content.Context;

import com.mg.mobile.app.R;

import java.text.DecimalFormat;

/**
 * Created by chenaxing on 2018/3/7.
 */

public class WGUtils
{
    public static String getFormatPrice(Context context, Double price)
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append(context.getResources().getString(R.string.product_price_label));
        buffer.append(new DecimalFormat("0.00").format(price));
        return buffer.toString();
    }
    public static String getFormatAgio(Context context, Double price)
    {
        price = price * 10;
        return new DecimalFormat("0.0").format(price);
    }

}
