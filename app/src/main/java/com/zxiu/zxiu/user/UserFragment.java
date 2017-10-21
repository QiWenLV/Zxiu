package com.zxiu.zxiu.user;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.zxiu.zxiu.base.BaseFragment;

/**
 * Created by 启文 on 2017/10/19.
 */
public class UserFragment extends BaseFragment{
    private TextView textView;

    /**
     * 初始化，实现布局
     * @return
     */
    @Override
    protected View initView() {
        textView = new TextView(context);
        textView.setText("用户中心");
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        return textView;
    }
}
