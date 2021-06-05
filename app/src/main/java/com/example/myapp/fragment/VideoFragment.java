package com.example.myapp.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.service.quicksettings.Tile;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapp.R;
import com.example.myapp.activity.LoginActivity;
import com.example.myapp.adapter.VideoAdapter;
import com.example.myapp.api.Api;
import com.example.myapp.api.ApiConfig;
import com.example.myapp.api.TtitCallback;
import com.example.myapp.entity.VideoEntity;
import com.example.myapp.entity.VideoListResponse;
import com.example.myapp.util.StringUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VideoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VideoFragment extends BaseFragment {
    private  String title;
    private  RecyclerView recyclerView;
    private  List<VideoEntity> videoList;


    public VideoFragment() {
        // Required empty public constructor
    }


    public static VideoFragment newInstance(String param1) {
        VideoFragment fragment = new VideoFragment();
        fragment.title=param1;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_video, container, false);
        recyclerView=v.findViewById(R.id.recyclerView);


        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//      使用布局管理器,能够管理RecyclerView的布局特征
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recycler需要设置布局管理器才能使用
        recyclerView.setLayoutManager(linearLayoutManager);
        getVideoList();


    }

    /**
     * 获取视频列表的值
     * 将登录获取的token值作为附加参数添加
     */
    private void getVideoList() {
        String token=getStringFromSp("token");
        HashMap<String,Object> map=new HashMap<>();

        if(!StringUtils.isEmpty(token)){
            map.put("token",token);
            Api.config(ApiConfig.VIDEO_LIST,map).getRequest(new TtitCallback() {
                @Override
                public void onSuccess(String res) {
                    VideoListResponse videoListResponse=new Gson().fromJson(res,VideoListResponse.class);
                    if(videoListResponse!=null && videoListResponse.getCode()==0){
                        List<VideoEntity> datas=videoListResponse.getPage().getList();
                        VideoAdapter videoAdapter=new VideoAdapter(getActivity(),datas);
                        recyclerView.setAdapter(videoAdapter);
                    }
                }
                @Override
                public void onFailure(Exception e) {

                }
            });

        }else{
            navigateTo(LoginActivity.class);
        }

    }
}