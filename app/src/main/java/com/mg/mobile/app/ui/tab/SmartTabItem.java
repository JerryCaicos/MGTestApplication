package com.mg.mobile.app.ui.tab;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mg.mobile.app.R;

/**
 * Created by chenaxing on 2018/3/6.
 */

public class SmartTabItem extends LinearLayout
{
    private LayoutInflater inflater;
    private Context mContext;
    private ImageView tabIcon;
    private TextView tabName;

    public SmartTabItem(Context context)
    {
        super(context);
        mContext = context;
        inflater = LayoutInflater.from(mContext);
        View view = initView();
        addView(view);
    }

    private View initView()
    {
        View view = inflater.inflate(R.layout.layout_tab_item, null);
        tabIcon = view.findViewById(R.id.view_tab_item_bg);
        tabName = view.findViewById(R.id.view_tab_item_name);
        return view;
    }

    public void onTabSelect(boolean select){
        tabIcon.setSelected(select);
    }
}
