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
public class RecommendGridViewAdapter extends BaseAdapter {


    private final Context context;
    private final List<ResultBeanData.ResultBean.RecommendInfoBean> data;

    public RecommendGridViewAdapter(Context context, List<ResultBeanData.ResultBean.RecommendInfoBean> data) {
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
            view = View.inflate(context, R.layout.item_recommend_grid_view, null);
            holder = new ViewHolder();
            holder.iv_recommend = (ImageView) view.findViewById(R.id.iv_recommend);
            holder.tv_name = (TextView) view.findViewById(R.id.tv_name);
            holder.tv_price = (TextView) view.findViewById(R.id.tv_price);

            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        Glide.with(context).load(Constants.BASE_URL_IMAGE + data.get(i).getFigure()).into(holder.iv_recommend);

        holder.tv_name.setText(data.get(i).getName());
        holder.tv_price.setText("￥"+data.get(i).getCover_price());



        return view;
    }

    static class ViewHolder{
        ImageView iv_recommend;
        TextView tv_name;
        TextView tv_price;
    }
}
