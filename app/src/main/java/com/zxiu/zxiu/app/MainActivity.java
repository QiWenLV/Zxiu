package com.zxiu.zxiu.app;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.zxiu.zxiu.Plus.PlusFragment;
import com.zxiu.zxiu.R;
import com.zxiu.zxiu.base.BaseFragment;
import com.zxiu.zxiu.community.CommunityFragment;
import com.zxiu.zxiu.home.HomeFragment;
import com.zxiu.zxiu.shoppingcart.ShoppingCartFragment;
import com.zxiu.zxiu.user.UserFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private RadioGroup rg_main;
    private FrameLayout frameLayout;

    private List<BaseFragment> fragments;
    private int position = 0;   //记录当前Fragment的位置
    private BaseFragment tempFragment; //缓存的Fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rg_main = (RadioGroup) findViewById(R.id.rg_main);
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);

        /**
         * 初始化Fragment
         */
        initFragment();
        rg_main.setOnCheckedChangeListener(new MyRadioGroupChangeListener());
        rg_main.check(R.id.rg_main);
    }


    /**
     * 初始化Fragment
     */
    private void initFragment(){
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new CommunityFragment());
        fragments.add(new PlusFragment());
        fragments.add(new ShoppingCartFragment());
        fragments.add(new UserFragment());
    }


    /**
     * RadioGroup的切换监听
     */
    class MyRadioGroupChangeListener implements RadioGroup.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i){
                case R.id.rb_home:
                    position = 0;
                    break;
                case R.id.rb_type:
                    position = 1;
                    break;
                case R.id.rb_community:
                    position = 2;
                    break;
                case R.id.rb_cart:
                    position = 3;
                    break;
                case R.id.rb_user:
                    position = 4;
                    break;
                default:
                    position = 0;
                    break;
            }

            //根据位置取相应的Fragment
            BaseFragment baseFragment = getFragment(position);
            switchFragemnt(tempFragment, baseFragment);

        }
    }

    /**
     * 根据位置取相应的Fragment
     * @param i
     * @return
     */
    private BaseFragment getFragment(int i){

        if(fragments != null && fragments.size() > 0){
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }


    /**
     * 切换Fragment
     * @param fromFragment  正在显示的
     * @param nextFragment  要切换到的
     */
    private void switchFragemnt(BaseFragment fromFragment, BaseFragment nextFragment){

        //不相同
        if(tempFragment != nextFragment){
            tempFragment = nextFragment;
            if(nextFragment != null){
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if(!nextFragment.isAdded()){
                    //隐藏当前Fragment
                    if(fromFragment != null){
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.frameLayout, nextFragment).commit();
                }else {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }
}
