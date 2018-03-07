package com.mg.mobile.app.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mg.mobile.app.R;
import com.mg.mobile.app.model.CategoryModel;
import com.mg.mobile.app.model.CouponModel;
import com.mg.mobile.app.service.DBHelper;
import com.mg.mobile.app.ui.adapter.AgioAdapter;
import com.mg.mobile.app.ui.adapter.DiscountAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenaxing on 2018/3/6.
 */

public class MineFragment extends Fragment
{
	public static final int MSG_QUERY_DATA_SUCCESS = 2001;

	private View view;

	private LinearLayout layoutAgio;
	private LinearLayout layoutDiscount;

	private View lineAgio;
	private View lineDiscount;

	private RecyclerView recyclerView;

	private TextView addDiscountBtn;

	private DBHelper dbHelper;

	private List<CategoryModel> categoryModelList;

	private List<CouponModel> couponModelList;

	private AgioAdapter agioAdapter;

	private DiscountAdapter discountAdapter;

	private Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			switch(msg.what)
			{
				case MSG_QUERY_DATA_SUCCESS:
					onAgioClick();
					break;
			}
		}
	};

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		dbHelper = DBHelper.getInstance(getContext().getApplicationContext());
	}

	@Override
	public void onResume()
	{
		super.onResume();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState)
	{
		if(view == null)
		{
			view = inflater.inflate(R.layout.activity_mine_fragment,container,false);
			layoutAgio = view.findViewById(R.id.layout_mine_agio);
			layoutDiscount = view.findViewById(R.id.layout_mine_discount);
			lineAgio = view.findViewById(R.id.line_mine_agio);
			lineDiscount = view.findViewById(R.id.line_mine_discount);
			recyclerView = view.findViewById(R.id.recyclerview_content);
			addDiscountBtn = view.findViewById(R.id.add_mine_discount);

			recyclerView.setLayoutManager(
					new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
			((SimpleItemAnimator)recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
			recyclerView.addItemDecoration(
					new MyDividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));

			layoutDiscount.setOnClickListener(clickListener);
			layoutAgio.setOnClickListener(clickListener);
			addDiscountBtn.setOnClickListener(clickListener);
		}

		initData();
		return view;
	}

	private void initData()
	{
		Thread thread = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				categoryModelList = dbHelper.queryBeans(CategoryModel.class);
				couponModelList = dbHelper.queryBeans(CouponModel.class);
				handler.sendEmptyMessage(MSG_QUERY_DATA_SUCCESS);
			}
		});
		thread.start();

	}

	private View.OnClickListener clickListener = new View.OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			switch(v.getId())
			{
				case R.id.layout_mine_agio:
					onAgioClick();
					break;
				case R.id.layout_mine_discount:
					onDiscountClick();
					break;
				case R.id.add_mine_discount:
					Toast.makeText(getActivity(),"待开发",Toast.LENGTH_SHORT).show();
					break;
			}
		}
	};

	private void onAgioClick()
	{
		lineDiscount.setVisibility(View.INVISIBLE);
		lineAgio.setVisibility(View.VISIBLE);

		if(categoryModelList != null)
		{
			List<CategoryModel> list = new ArrayList<>();

			int len = categoryModelList.size();
			for(int i = 0;i < len;i++)
			{
				if(categoryModelList.get(i).hasAgio == 1)
				{
					list.add(categoryModelList.get(i));
				}
			}
			if(agioAdapter == null)
			{
				agioAdapter = new AgioAdapter(getActivity(),list);
			}
			recyclerView.setAdapter(agioAdapter);
		}
	}

	private void onDiscountClick()
	{
		lineAgio.setVisibility(View.INVISIBLE);
		lineDiscount.setVisibility(View.VISIBLE);

		if(couponModelList != null)
		{
			if(discountAdapter == null)
			{
				discountAdapter = new DiscountAdapter(getActivity(),couponModelList);
			}
			recyclerView.setAdapter(discountAdapter);
		}
	}
}
