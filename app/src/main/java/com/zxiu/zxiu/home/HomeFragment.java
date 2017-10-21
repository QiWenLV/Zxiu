package com.zxiu.zxiu.home;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zxiu.zxiu.R;
import com.zxiu.zxiu.base.BaseFragment;
import com.zxiu.zxiu.home.adapter.HomeFragmentAdapter;
import com.zxiu.zxiu.home.bean.ResultBeanData;
import com.zxiu.zxiu.utils.Constants;

/**
 * Created by 启文 on 2017/10/19.
 */
public class HomeFragment extends BaseFragment {

    private TextView tv_search_home;
    private TextView tv_message_home;
    private RecyclerView rv_home;
    private ImageButton ib_top;

    private ResultBeanData.ResultBean result;   //联网请求返回的数据

    private HomeFragmentAdapter adapter;

    /**
     * 初始化，实现布局
     * @return
     */
    @Override
    protected View initView() {
        View view = View.inflate(context, R.layout.fragment_home, null);
        tv_search_home = view.findViewById(R.id.tv_search_home);
        tv_message_home = view.findViewById(R.id.tv_message_home);
        rv_home = view.findViewById(R.id.rv_home);
        ib_top = view.findViewById(R.id.ib_top);

        /**
         * 设置点击事件
         */
        initListener();
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        /**
         * 联网请求
         */
        getDataFromNet();
    }

    /**
     * 设置点击事件
     */
    private void initListener() {
        /**
         * 置顶监听
         */
        ib_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //回到顶部
                rv_home.scrollToPosition(0);
            }
        });

        /**
         * 搜索监听
         */
        tv_search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "点击了搜索", Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * 点击消息监听
         */
        tv_message_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "点击了我的消息", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 网络请求
     */
    public void getDataFromNet() {

        String url = Constants.HOME_URL;
        OkGo.<String>get(url)
                .retryCount(2)
                .cacheTime(5000)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.i("TAG", "失败");
                    }

                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        Log.i("TAG", "成功");

                       processData(body);

                    }
                });
    }

    /**
     * 解析并显示数据
     * @param json
     */
    private void processData(String json) {
        ResultBeanData resultBeanData = JSON.parseObject(json, ResultBeanData.class);
        result = resultBeanData.getResult();

        if(result != null){
            //有数据时。设置适配器
            adapter = new HomeFragmentAdapter(context, result);
            rv_home.setAdapter(adapter);
            //设置布局管理者
            GridLayoutManager manager = new GridLayoutManager(context, 1);
           // WrapHeightGridLayoutManager manager = new WrapHeightGridLayoutManager(context, 1);
            //rv_home.setLayoutManager(manager);  //设置Item高度自适应
            manager.setAutoMeasureEnabled(true);
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {      //设置跨度大小的滑动监听
                @Override
                public int getSpanSize(int position) {
                    if(position <= 3){
                        ib_top.setVisibility(View.GONE);    //隐藏
                    }else {
                        ib_top.setVisibility(View.VISIBLE);    //显示
                    }
                    return 1;
                }
            });
            rv_home.setLayoutManager(manager);
        }


    }
}
