package com.example.myapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.net.sip.SipSession;
import android.os.Bundle;

import com.example.myapp.R;
import com.example.myapp.adapter.MyPagerAdapter;
import com.example.myapp.entity.TabEntity;
import com.example.myapp.fragment.CollectFragment;
import com.example.myapp.fragment.HomeFragment;
import com.example.myapp.fragment.MyFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

public   class  HomeActivity extends BaseActivity {

    private String[] mTitles = {"首页", "收藏", "我的"};
    private int[] mIconSelectIds = {
            R.mipmap.home_selected, R.mipmap.collect_select,
            R.mipmap.my_selected};
    private int[] mIconUnselectIds = {
            R.mipmap.home_unselect, R.mipmap.collect_unselect,
            R.mipmap.my_unselect};
    private ViewPager viewPager;
    private CommonTabLayout commonTabLayout;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();



    @Override
    protected int initLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        viewPager=findViewById(R.id.viewpager);
        commonTabLayout=findViewById(R.id.commonTabLayout);
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(CollectFragment.newInstance());
        mFragments.add(MyFragment.newInstance());
    }

    @Override
    protected void initData() {

//        tab键设置内容
        for(int i=0;i<mTitles.length;i++){
            mTabEntities.add(new TabEntity(mTitles[i],mIconSelectIds[i],mIconUnselectIds[i]));
        }
        commonTabLayout.setTabData(mTabEntities);

//        viewpage根据tab的变化而变化
        commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

//        设置页面滑动时下面的tab也会随着变化
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                commonTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
//       防止的滑动的时候出现异常
        viewPager.setOffscreenPageLimit(mFragments.size());
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(),mFragments,mTitles));

    }
}

