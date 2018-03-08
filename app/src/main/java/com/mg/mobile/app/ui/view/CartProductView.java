package com.mg.mobile.app.ui.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mg.mobile.app.R;
import com.mg.mobile.app.model.CartProductModel;
import com.mg.mobile.app.service.DBHelper;
import com.mg.mobile.app.utils.WGUtils;

/**
 * Created by chenaxing on 2018/3/7.
 */

public class CartProductView extends LinearLayout
{
    private Context mContext;
    private LayoutInflater inflater;

    private CartProductModel productModel;

    private DBHelper dbHelper;

    private TextView productName;
    private TextView productPrice;
    private EditText productNum;
    private View container;

    public CartProductView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mContext = context;
        inflater = LayoutInflater.from(mContext);
        container = inflater.inflate(R.layout.layout_cart_product_view, null);
        LinearLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        container.setLayoutParams(params);
        productName = container.findViewById(R.id.item_view_name);
        productPrice = container.findViewById(R.id.item_view_price);
        productNum = container.findViewById(R.id.editText);
        addView(container);
    }

    public void setParams(CartProductModel model, DBHelper helper)
    {
        productModel = model;
        dbHelper = helper;

        productName.setText(productModel.productName);
        productPrice.setText(WGUtils.getFormatPrice(mContext, productModel.productPrice));
        productNum.setText(String.valueOf(productModel.num));

        productNum.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
//                if(TextUtils.isEmpty(s))
//                {
//                    return;
//                }
//
//                int num = Integer.parseInt(s.toString());
//                if(num != productModel.num)
//                {
//                    Toast.makeText(mContext,"暂不支持修改数量",Toast.LENGTH_SHORT).show();
//                    productNum.setText(String.valueOf(productModel.num));
//                }
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });
    }
}
