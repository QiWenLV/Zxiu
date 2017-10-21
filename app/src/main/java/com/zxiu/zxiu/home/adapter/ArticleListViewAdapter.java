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
public class ArticleListViewAdapter extends BaseAdapter {

    private Context context;
    private List<ResultBeanData.ResultBean.ArticleInfoBean> data;

    public ArticleListViewAdapter(Context context, List<ResultBeanData.ResultBean.ArticleInfoBean> data) {
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
            view = View.inflate(context, R.layout.item_article_list_view, null);
            holder = new ViewHolder();
            holder.iv_hot = view.findViewById(R.id.iv_hot);
            holder.tv_name = view.findViewById(R.id.tv_name);
            holder.tv_abstract = view.findViewById(R.id.tv_abstract);

            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        ResultBeanData.ResultBean.ArticleInfoBean articleInfoBean = data.get(i);
        Glide.with(context).load(Constants.BASE_URL_IMAGE + articleInfoBean.getIcon_url()).into(holder.iv_hot);

        holder.tv_name.setText(articleInfoBean.getTitle());
        holder.tv_abstract.setText(articleInfoBean.getAbstracte());

        return view;
    }

    static class ViewHolder{
        ImageView iv_hot;
        TextView tv_name;
        TextView tv_abstract;
    }
}
