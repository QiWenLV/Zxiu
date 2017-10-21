package com.zxiu.zxiu.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 启文 on 2017/10/19.
 * 基类Fragment：
 *      首页  HomeFragment
 *      分类  TypeFragment
 *      发现  CommunityFragment
 *      购物车     ShoppingCartFragment
 *      用户中心   UserFragment
 */
public abstract class BaseFragment extends Fragment {

    protected Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getActivity();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return initView();
    }

    /**
     * 抽象方法，实现布局
     * @return
     */
    protected abstract View initView();


    /**
     * 当Activity被创建之后
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
    }

    /**
     * 当子类需要请求数据的时候，重写该方法
     */
    public void initData(){

    }
}
