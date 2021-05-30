package com.example.myapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.service.quicksettings.Tile;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapp.R;
import com.example.myapp.adapter.VideoAdapter;
import com.example.myapp.entity.VideoEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VideoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VideoFragment extends Fragment {
    private  String title;


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
        RecyclerView recyclerView=v.findViewById(R.id.recyclerView);

//        使用布局管理器,能够管理RecyclerView的布局特征
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recycler需要设置布局管理器才能使用
        recyclerView.setLayoutManager(linearLayoutManager);
        List<VideoEntity> videoList=new ArrayList<>();
        for (int i=0;i<10;i++){
            VideoEntity videoEntity=new VideoEntity();
            videoEntity.setTitle("韭菜盒子新做法");
            videoEntity.setAuthor("大胃王");
            videoEntity.setDzCount(i*2);
            videoEntity.setCollectCount(i*4);
            videoEntity.setCommentCount(i*6);
            videoList.add(videoEntity);
        }
//
        VideoAdapter videoAdapter=new VideoAdapter(getActivity(),videoList);
        recyclerView.setAdapter(videoAdapter);

        return v;
    }
}