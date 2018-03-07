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
import com.mg.mobile.app.service.DBHelper;
import com.mg.mobile.app.ui.view.CartAgioView;
import com.mg.mobile.app.ui.view.CartProductView;

import java.text.SimpleDateFormat;
import java.util.List;

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

	private List<CartProductModel> productModelList;
	private List<CategoryModel> categoryModelList;
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
	public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState)
	{
		if(view == null)
		{
			view = inflater.inflate(R.layout.activity_cart_fragment,container,false);
		}
		productContainer = view.findViewById(R.id.layout_product_container);
		layoutAgio = view.findViewById(R.id.layout_agio);
		agioContainer = view.findViewById(R.id.layout_agio_container);
		layoutDiscount = view.findViewById(R.id.layout_discount);
		discountValue = view.findViewById(R.id.view_discount_value);
		discountDate = view.findViewById(R.id.view_discount_date);


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
		categoryModelList = dbHelper.queryBeans(CategoryModel.class);
		couponModelList = dbHelper.queryBeans(CouponModel.class);

		if(productModelList != null && productModelList.size() > 0)
		{
			productContainer.setVisibility(View.VISIBLE);
			productContainer.removeAllViews();
			int proLen = productModelList.size();
			CartProductView cartProductView;
			for(int i = 0;i < proLen;i++)
			{
				cartProductView = new CartProductView(getActivity(),null);
				cartProductView.setParams(productModelList.get(i),dbHelper);
				productContainer.addView(cartProductView);
			}

			if(categoryModelList != null && categoryModelList.size() > 0)
			{
				layoutAgio.setVisibility(View.VISIBLE);
				agioContainer.removeAllViews();
				int len = categoryModelList.size();
				CartAgioView agioView;
				for(int i = 0;i < len;i++)
				{
					if(categoryModelList.get(i).hasAgio == 1)
					{
						agioView = new CartAgioView(getActivity(),null);
						agioView.setParams(categoryModelList.get(i));
						agioContainer.addView(agioView);
					}
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
}
