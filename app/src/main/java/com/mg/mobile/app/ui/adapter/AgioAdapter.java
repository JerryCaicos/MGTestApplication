package com.mg.mobile.app.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mg.mobile.app.R;
import com.mg.mobile.app.model.CategoryModel;
import com.mg.mobile.app.utils.WGUtils;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by adminchen on 2018/3/7 23:44.
 */

public class AgioAdapter extends RecyclerView.Adapter<ViewHolderss>
{
	private List<CategoryModel> categoryModelList;
	private Context mContext;

	public AgioAdapter(Context context,List<CategoryModel> list)
	{
		mContext = context;
		categoryModelList = list;
	}

	@Override
	public ViewHolderss onCreateViewHolder(ViewGroup parent,int viewType)
	{
		View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item_agio,null);
		return new ViewHolderss(view);
	}

	@Override
	public void onBindViewHolder(ViewHolderss holder,int position)
	{
		CategoryModel categoryModel = categoryModelList.get(position);
		StringBuffer buffer = new StringBuffer();
		buffer.append(categoryModel.cateName);
		buffer.append(mContext.getResources().getString(R.string.app_discount_mine));

		holder.viewName.setText(buffer.toString());

		buffer = new StringBuffer();
		buffer.append(WGUtils.getFormatAgio(mContext,categoryModel.agio));
		buffer.append(mContext.getResources().getString(R.string.app_agio));
		holder.viewValue.setText(buffer.toString());

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		buffer = new StringBuffer();
		buffer.append(mContext.getResources().getString(R.string.app_agio_date));
		buffer.append(format.format(categoryModel.outDate));
		holder.viewDate.setText(buffer.toString());
	}

	@Override
	public int getItemCount()
	{
		return categoryModelList.size();
	}
}

class ViewHolderss extends RecyclerView.ViewHolder
{
	public TextView viewName;
	public TextView viewValue;
	public TextView viewDate;

	public ViewHolderss(View itemView)
	{
		super(itemView);
		viewName = itemView.findViewById(R.id.view_item_discount_name);
		viewValue = itemView.findViewById(R.id.view_item_discount_value);
		viewDate = itemView.findViewById(R.id.view_item_discount_date);
	}
}
