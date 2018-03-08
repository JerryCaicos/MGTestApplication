package com.mg.mobile.app.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mg.mobile.app.R;
import com.mg.mobile.app.model.CartProductModel;
import com.mg.mobile.app.model.CategoryModel;
import com.mg.mobile.app.model.CouponModel;
import com.mg.mobile.app.model.TableConstants;
import com.mg.mobile.app.service.DBHelper;
import com.mg.mobile.app.ui.view.CartAgioView;
import com.mg.mobile.app.ui.view.CartProductView;
import com.mg.mobile.app.utils.WGUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenaxing on 2018/3/6.
 */

public class CartFragment extends Fragment
{
    private View view;

    private LinearLayout productContainer;
    private LinearLayout layoutAgio;
    private LinearLayout agioContainer;
    private LinearLayout layoutDiscount;
    private TextView discountValue;
    private TextView discountDate;

    private TextView totalPrice;

    private TextView balanceBtn;

    private List<CartProductModel> productModelList;
    private List<CategoryModel> categoryModelList;
    private List<CategoryModel> agioList = new ArrayList<>();
    private List<CouponModel> couponModelList;

    public DBHelper dbHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        dbHelper = DBHelper.getInstance(getContext().getApplicationContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState)
    {
        if(view == null)
        {
            view = inflater.inflate(R.layout.activity_cart_fragment, container, false);
            productContainer = view.findViewById(R.id.layout_product_container);
            layoutAgio = view.findViewById(R.id.layout_agio);
            agioContainer = view.findViewById(R.id.layout_agio_container);
            layoutDiscount = view.findViewById(R.id.layout_discount);
            discountValue = view.findViewById(R.id.view_discount_value);
            discountDate = view.findViewById(R.id.view_discount_date);

            totalPrice = view.findViewById(R.id.view_total_price);
            balanceBtn = view.findViewById(R.id.view_balance_account);

            balanceBtn.setOnClickListener(clickListener);
        }

        return view;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        initData();
    }

    private void initData()
    {
        productModelList = dbHelper.queryBeans(CartProductModel.class);
        Map<String, String> map = new HashMap<>();
        map.put(TableConstants.KEY_HAS_AGIO, "1");
        categoryModelList = dbHelper.queryObjectBean(CategoryModel.class, map);
        couponModelList = dbHelper.queryBeans(CouponModel.class);

        if(productModelList != null && productModelList.size() > 0)
        {
            productContainer.setVisibility(View.VISIBLE);
            productContainer.removeAllViews();
            int proLen = productModelList.size();
            CartProductView cartProductView;
            for(int i = 0; i < proLen; i++)
            {
                cartProductView = new CartProductView(getActivity(), null);
                cartProductView.setParams(productModelList.get(i), dbHelper);
                productContainer.addView(cartProductView);
            }

            if(categoryModelList != null && categoryModelList.size() > 0)
            {
                layoutAgio.setVisibility(View.VISIBLE);
                agioContainer.removeAllViews();
                int len = categoryModelList.size();

                CartAgioView agioView;
                for(int i = 0; i < len; i++)
                {
                    if(showAgio(categoryModelList.get(i)))
                    {
                        agioView = new CartAgioView(getActivity(), null);
                        agioView.setParams(categoryModelList.get(i));
                        agioContainer.addView(agioView);
                    }
                }

                if(agioList.size() <= 0)
                {
                    layoutAgio.setVisibility(View.GONE);
                }
            }
            else
            {
                layoutAgio.setVisibility(View.GONE);
            }

            if(couponModelList != null && couponModelList.size() > 0)
            {
                layoutDiscount.setVisibility(View.VISIBLE);

                StringBuffer buffer = new StringBuffer();
                buffer.append(getResources().getString(R.string.app_discount_man));
                buffer.append(couponModelList.get(0).couponValue);
                buffer.append(getResources().getString(R.string.app_discount_jian));
                buffer.append(couponModelList.get(0).discount);

                discountValue.setText(buffer.toString());

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                buffer = new StringBuffer();
                buffer.append(getResources().getString(R.string.app_agio_date));
                buffer.append(format.format(couponModelList.get(0).outDate));
                discountDate.setText(buffer.toString());
            }
            else
            {
                layoutDiscount.setVisibility(View.GONE);
            }
        }
        else
        {
            productContainer.setVisibility(View.GONE);
            layoutAgio.setVisibility(View.GONE);
            layoutDiscount.setVisibility(View.GONE);
        }
    }

    private View.OnClickListener clickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            switch(v.getId())
            {
                case R.id.view_balance_account:
                    calcTotalPrice();
                    break;
                default:
                    break;
            }
        }
    };

    private void calcTotalPrice()
    {
        int len = productModelList.size();
        CartProductModel model;
        double price = 0.0;
        for(int i = 0; i < len; i++)
        {
            model = productModelList.get(i);
            price = price + model.productPrice * getAgio(model) * model.num;
        }

        totalPrice.setText(WGUtils.getFormatPrice(getActivity(),price));
    }

    private double getAgio(CartProductModel productModel)
    {
        double result = 1.0;

        if(agioList != null && agioList.size() > 0)
        {
            int len = agioList.size();
            for(int i = 0; i < len; i++)
            {
                if(agioList.get(i).hasAgio == 1 && productModel.cateId.equals(agioList.get(i).cateId))
                {
                    result = agioList.get(i).agio;
                }
            }
        }

        return result;
    }

    private boolean showAgio(CategoryModel model)
    {
        boolean result = false;

        int proLen = productModelList.size();
        CartProductModel product;
        for(int i = 0; i < proLen; i++)
        {
            product = productModelList.get(i);
            if(product.cateId.equals(model.cateId))
            {
                agioList.add(model);
                result = true;
                break;
            }
        }

        return result;
    }
}
