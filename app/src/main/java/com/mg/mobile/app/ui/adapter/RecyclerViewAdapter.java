package com.mg.mobile.app.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mg.mobile.app.R;
import com.mg.mobile.app.model.CartProductModel;
import com.mg.mobile.app.model.ProductModel;
import com.mg.mobile.app.model.TableConstants;
import com.mg.mobile.app.service.DBHelper;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder>
{
    private Context mContext;

    private List<ProductModel> list;

    private DBHelper dbHelper;

    public RecyclerViewAdapter(Context context, List<ProductModel> l, DBHelper dbHelper)
    {
        mContext = context;
        list = l;
        this.dbHelper = dbHelper;
    }

    @Override
    public void onViewAttachedToWindow(ViewHolder holder)
    {
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public int getItemViewType(int position)
    {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position)
    {
        return super.getItemId(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        final ProductModel model = list.get(position);
        holder.viewName.setText(model.productName);

        StringBuffer buffer = new StringBuffer();
        buffer.append(mContext.getResources().getString(R.string.product_price_label));
        buffer.append(new DecimalFormat("0.00").format(model.productPrice));
        holder.viewPrice.setText(buffer.toString());

        holder.viewAddCart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                addCart(model);
            }
        });
    }

    private void addCart(ProductModel productModel)
    {
        if(dbHelper == null)
        {
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put(TableConstants.KEY_CATE_ID, productModel.cateId);
        map.put(TableConstants.KEY_PRODUCT_NAME, productModel.productName);
        map.put(TableConstants.KEY_PRODUCT_PRICE, String.valueOf(productModel.productPrice));
        List<CartProductModel> list = dbHelper.queryObjectBean(CartProductModel.class, map);
        if(list != null && list.size() == 1)
        {
            CartProductModel model = list.get(0);
            model.num++;
            dbHelper.updateBean(CartProductModel.class, model);
        }
        else
        {
            dbHelper.insertBean(CartProductModel.class, new CartProductModel(productModel));
        }
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }
}

class ViewHolder extends RecyclerView.ViewHolder
{
    public LinearLayout container;
    public TextView viewName;
    public TextView viewPrice;
    public ImageView viewAddCart;

    public ViewHolder(View itemView)
    {
        super(itemView);
        container = (LinearLayout) itemView.findViewById(R.id.item_view_container);
        viewName = itemView.findViewById(R.id.item_view_name);
        viewPrice = itemView.findViewById(R.id.item_view_price);
        viewAddCart = itemView.findViewById(R.id.item_view_add_cart);
    }
}
