package com.example.myapp.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.PixelCopy;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.R;
import com.example.myapp.entity.VideoEntity;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.zip.Inflater;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    private Context mContext;
    private List<VideoEntity> mList;

    public VideoAdapter(Context mContext, List<VideoEntity> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    /**
     * 创建viewholder的布局
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_video_layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);

        return viewHolder;
    }

    /**
     * 给控件对象赋值
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VideoEntity videoEntity=mList.get(position);

        holder.tvTitle.setText(videoEntity.getVtitle());
        holder.tvAuthor.setText(videoEntity.getAuthor());
//        注意这里要转化为string，否则显示不出来
        holder.tvCollect.setText(String.valueOf(videoEntity.getCollectNum()));
        holder.tvComment.setText(String.valueOf(videoEntity.getCommentNum()));
        holder.tvDz.setText(String.valueOf(videoEntity.getLikeNum()));

//        异步加载图片
        Picasso.with(mContext).load(videoEntity.getHeadurl()).into(holder.imgHeader);
        Picasso.with(mContext).load(videoEntity.getCoverurl()).into(holder.imgCover);


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvAuthor;
        private TextView tvComment;
        private TextView tvCollect;
        private TextView tvDz;
        private ImageView imgHeader;
        private ImageView imgCover;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle=itemView.findViewById(R.id.title);
            tvAuthor=itemView.findViewById(R.id.author);
            tvComment=itemView.findViewById(R.id.comment);
            tvCollect=itemView.findViewById(R.id.collect);
            tvDz=itemView.findViewById(R.id.dz);
            imgHeader=itemView.findViewById(R.id.img_header);
            imgCover=itemView.findViewById(R.id.img_cover);

        }
    }
}
