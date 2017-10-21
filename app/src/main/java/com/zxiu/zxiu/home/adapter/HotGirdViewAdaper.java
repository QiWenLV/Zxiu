package com.zxiu.zxiu.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zxiu.zxiu.R;
import com.zxiu.zxiu.home.bean.ResultBeanData;
import com.zxiu.zxiu.utils.Constants;

import java.util.List;

/**
 * Created by 启文 on 2017/10/20.
 */
public class HotGirdViewAdaper extends BaseAdapter {

    private final Context context;
    private final List<ResultBeanData.ResultBean.HotInfoBean> data;

    public HotGirdViewAdaper(Context context, List<ResultBeanData.ResultBean.HotInfoBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            view = View.inflate(context, R.layout.item_hot_grid_view, null);
            holder = new ViewHolder();
            holder.iv_hot = (ImageView) view.findViewById(R.id.iv_hot);
            holder.tv_name = (TextView) view.findViewById(R.id.tv_name);
            holder.tv_price = (TextView) view.findViewById(R.id.tv_price);

            view.setTag(holder);
        }else {

            holder = (ViewHolder) view.getTag();
        }
        ResultBeanData.ResultBean.HotInfoBean hotInfoBean = data.get(i);
        Glide.with(context).load(Constants.BASE_URL_IMAGE + hotInfoBean.getFigure()).into(holder.iv_hot);

        holder.tv_name.setText(hotInfoBean.getName());
        holder.tv_price.setText("￥"+hotInfoBean.getCover_price());



        return view;
    }

    static class ViewHolder{
        ImageView iv_hot;
        TextView tv_name;
        TextView tv_price;
    }
}
