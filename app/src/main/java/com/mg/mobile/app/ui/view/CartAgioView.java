package com.mg.mobile.app.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mg.mobile.app.R;
import com.mg.mobile.app.model.CategoryModel;

import java.text.SimpleDateFormat;

/**
 * Created by chenaxing on 2018/3/7.
 */

public class CartAgioView extends LinearLayout
{
    private Context mContext;
    private View view;
    private TextView agioValue;
    private TextView agioDate;

    public CartAgioView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        mContext = context;
        view = LayoutInflater.from(context).inflate(R.layout.layout_view_agio,null);
        LinearLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        agioValue = view.findViewById(R.id.view_agio_value);
        agioDate = view.findViewById(R.id.view_agio_date);
        addView(view);
    }

    public void setParams(CategoryModel categoryModel)
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append(categoryModel.cateName);
        buffer.append("  ");
        buffer.append(categoryModel.agio);
        buffer.append(mContext.getString(R.string.app_agio));

        agioValue.setText(buffer.toString());

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        buffer = new StringBuffer();
        buffer.append(mContext.getString(R.string.app_agio_date));
        buffer.append(format.format(categoryModel.outDate));
        agioDate.setText(buffer.toString());
    }
}
