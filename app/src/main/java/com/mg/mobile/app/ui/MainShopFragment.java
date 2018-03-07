package com.mg.mobile.app.ui;


import android.app.Activity;
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

import com.mg.mobile.app.R;
import com.mg.mobile.app.model.CategoryModel;
import com.mg.mobile.app.model.ProductModel;
import com.mg.mobile.app.model.TableConstants;
import com.mg.mobile.app.service.DBHelper;
import com.mg.mobile.app.ui.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenaxing on 2018/3/6.
 */

public class MainShopFragment extends Fragment
{
    public static final int MSG_QUERY_CATEGORY_SUCCESS = 1001;

    private View view;
    private LinearLayout cateElc;
    private LinearLayout cateFood;
    private LinearLayout cateDate;
    private LinearLayout cateWine;

    private TextView cateElcName;
    private TextView cateFoodName;
    private TextView cateDateName;
    private TextView cateWineName;

    private View lineElc;
    private View lineFood;
    private View lineDate;
    private View lineWine;

    private List<View> lineList;

    private RecyclerView recyclerView;

    private List<CategoryModel> categoryList;

    private RecyclerViewAdapter elecAdapter;

    private RecyclerViewAdapter foodAdapter;

    private RecyclerViewAdapter dateUseAdapter;

    private RecyclerViewAdapter wineAdapter;

    public DBHelper dbHelper;

    private Handler handler = new BasicHandler(getActivity())
    {
        @Override
        public void handleMessage(Message msg, int what)
        {
            switch(what)
            {
                case MSG_QUERY_CATEGORY_SUCCESS:
                    setCateName();
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        lineList = new ArrayList<>();
        if(view == null){
            view = inflater.inflate(R.layout.activity_shop_fragment, container, false);

            cateElc = view.findViewById(R.id.layout_category_1);
            cateFood = view.findViewById(R.id.layout_category_2);
            cateDate = view.findViewById(R.id.layout_category_3);
            cateWine = view.findViewById(R.id.layout_category_4);

            cateElcName = view.findViewById(R.id.view_category_1);
            cateFoodName = view.findViewById(R.id.view_category_2);
            cateDateName = view.findViewById(R.id.view_category_3);
            cateWineName = view.findViewById(R.id.view_category_4);

            lineElc = view.findViewById(R.id.line_category_1);
            lineFood = view.findViewById(R.id.line_category_2);
            lineDate = view.findViewById(R.id.line_category_3);
            lineWine = view.findViewById(R.id.line_category_4);
            lineList.add(lineElc);
            lineList.add(lineFood);
            lineList.add(lineDate);
            lineList.add(lineWine);

            cateElc.setOnClickListener(clickListener);
            cateFood.setOnClickListener(clickListener);
            cateDate.setOnClickListener(clickListener);
            cateWine.setOnClickListener(clickListener);


            recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_content);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
            recyclerView.addItemDecoration(new MyDividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

            queryCategory();
            onLabelClicked(cateElc);
        }

        return view;
    }

    private void setCateName()
    {
        int len = categoryList.size();
        for(int i = 0; i < len; i++)
        {
            CategoryModel model = categoryList.get(i);
            switch(i)
            {
                case 0:
                    cateElcName.setText(model.cateName);
                    break;
                case 1:
                    cateFoodName.setText(model.cateName);
                    break;
                case 2:
                    cateDateName.setText(model.cateName);
                    break;
                case 3:
                    cateWineName.setText(model.cateName);
                    break;
                default:
                    break;
            }
        }
    }

    private void queryCategory()
    {
        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                categoryList = dbHelper.queryBeans(CategoryModel.class);
                if(categoryList != null && categoryList.size() > 0)
                {
                    handler.sendEmptyMessage(MSG_QUERY_CATEGORY_SUCCESS);
                }
            }
        });
        thread.start();
    }

    private void queryElecProduct()
    {
        Map<String, String> map = new HashMap<>();
        map.put(TableConstants.KEY_CATE_ID, "electric");
        if(elecAdapter == null)
        {
            elecAdapter = new RecyclerViewAdapter(getContext(), queryProduct(ProductModel.class, map),dbHelper);
        }
        recyclerView.setAdapter(elecAdapter);
    }

    private void queryFoodProduct()
    {
        Map<String, String> map = new HashMap<>();
        map.put(TableConstants.KEY_CATE_ID, "food");
        if(foodAdapter == null)
        {
            foodAdapter = new RecyclerViewAdapter(getContext(), queryProduct(ProductModel.class, map),dbHelper);
        }
        recyclerView.setAdapter(foodAdapter);
    }

    private void queryDateUseProduct()
    {
        Map<String, String> map = new HashMap<>();
        map.put(TableConstants.KEY_CATE_ID, "dateuse");
        if(dateUseAdapter == null)
        {
            dateUseAdapter = new RecyclerViewAdapter(getContext(), queryProduct(ProductModel.class, map),dbHelper);
        }
        recyclerView.setAdapter(dateUseAdapter);
    }

    private void queryWineProduct()
    {
        Map<String, String> map = new HashMap<>();
        map.put(TableConstants.KEY_CATE_ID, "wines");
        if(wineAdapter == null)
        {
            wineAdapter = new RecyclerViewAdapter(getContext(), queryProduct(ProductModel.class, map),dbHelper);
        }
        recyclerView.setAdapter(wineAdapter);
    }

    private List<ProductModel> queryProduct(Class clazz, Map<String, String> map)
    {
        List<ProductModel> list = dbHelper.queryObjectBean(clazz, map);
        return list;
    }

    View.OnClickListener clickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            switch(v.getId())
            {
                case R.id.layout_category_1:
                case R.id.layout_category_2:
                case R.id.layout_category_3:
                case R.id.layout_category_4:
                    onLabelClicked(v);
                    break;
                default:
                    break;
            }
        }
    };

    private void onLabelClicked(View view)
    {
        int len = lineList.size();
        for(int i = 0; i < len; i++)
        {
            lineList.get(i).setVisibility(View.INVISIBLE);
        }
        switch(view.getId())
        {
            case R.id.layout_category_1:
                lineElc.setVisibility(View.VISIBLE);
                queryElecProduct();
                break;
            case R.id.layout_category_2:
                lineFood.setVisibility(View.VISIBLE);
                queryFoodProduct();
                break;
            case R.id.layout_category_3:
                lineDate.setVisibility(View.VISIBLE);
                queryDateUseProduct();
                break;
            case R.id.layout_category_4:
                lineWine.setVisibility(View.VISIBLE);
                queryWineProduct();
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
