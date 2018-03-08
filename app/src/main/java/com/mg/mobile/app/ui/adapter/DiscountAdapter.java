package com.mg.mobile.app.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mg.mobile.app.R;
import com.mg.mobile.app.model.CouponModel;

import java.util.List;

/**
 * Created by chenaxing on 2018/3/7 23:44.
 */

public class DiscountAdapter extends RecyclerView.Adapter<ViewHolders>
{

	private List<CouponModel> couponModelList;
	private Context mContext;

	public DiscountAdapter(Context context,List<CouponModel> list)
	{
		mContext = context;
		couponModelList = list;
	}

	@Override
	public ViewHolders onCreateViewHolder(ViewGroup parent,int viewType)
	{
		View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item_discount,parent,
				false);
		return new ViewHolders(view);
	}

	@Override
	public void onBindViewHolder(ViewHolders holder,int position)
	{
		CouponModel couponModel = couponModelList.get(position);
		StringBuffer buffer = new StringBuffer();
		buffer.append(mContext.getResources().getString(R.string.app_discount_man));
		buffer.append(couponModel.couponValue);
		buffer.append(mContext.getResources().getString(R.string.app_discount_jian));
		buffer.append(couponModel.discount);
		buffer.append(mContext.getResources().getString(R.string.app_discount_fix));
		holder.viewName.setText(buffer.toString());
	}

	@Override
	public int getItemCount()
	{
		return couponModelList.size();
	}
}

class ViewHolders extends RecyclerView.ViewHolder
{
	public TextView viewName;

	public ViewHolders(View itemView)
	{
		super(itemView);
		viewName = itemView.findViewById(R.id.view_discount_item_value);
	}
}
