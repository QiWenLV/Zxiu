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
 * Created by 启文 on 2017/10/19.
 * 频道适配器
 */
public class ChannelAdapter extends BaseAdapter {
    private Context context;
    private List<ResultBeanData.ResultBean.ChannelInfoBean> data;
    public ChannelAdapter(Context context, List<ResultBeanData.ResultBean.ChannelInfoBean> data) {
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
            view = View.inflate(context, R.layout.item_channel, null);
            holder = new ViewHolder();
            holder.iv_icon = (ImageView) view.findViewById(R.id.iv_channel);
            holder.tv_title = (TextView) view.findViewById(R.id.tv_channel);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }


        ResultBeanData.ResultBean.ChannelInfoBean channelInfoBean = data.get(i);
        Glide.with(context).load(Constants.BASE_URL_IMAGE + channelInfoBean.getImage()).into(holder.iv_icon);

        holder.tv_title.setText(channelInfoBean.getChannel_name());

        return view;
    }


    static class ViewHolder{
        ImageView iv_icon;
        TextView tv_title;

    }
}
