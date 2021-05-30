package com.example.myapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapp.R;
import com.example.myapp.adapter.HomeAdapter;
import com.example.myapp.adapter.MyPagerAdapter;
import com.example.myapp.view.FixedViewPager;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {
            "热门", "iOS", "Android"
            , "前端", "后端", "设计", "工具资源"
    };
    private HomeAdapter mAdapter;
    private SlidingTabLayout slidingTabLayout;
    private FixedViewPager fixedViewPager;



    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home, container, false);
        fixedViewPager=v.findViewById(R.id.fixedViewPager);
        slidingTabLayout=v.findViewById(R.id.slidingTabLayout);
        return v;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        for (String title:mTitles){
            mFragments.add(VideoFragment.newInstance(title));
        }

        mAdapter=new HomeAdapter(getFragmentManager(),mTitles,mFragments);
//        提前预加载所有的fragment,使得切换更加流畅。
        fixedViewPager.setOffscreenPageLimit(mFragments.size());
        fixedViewPager.setAdapter(mAdapter);
        slidingTabLayout.setViewPager(fixedViewPager);
    }
}