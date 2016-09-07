package com.njupt.sniper.app.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.njupt.sniper.app.R;
import com.njupt.sniper.app.model.entity.AudioEntity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * author：Zsl
 * date：2016/9/7
 */
public class AudiooAdapter extends RecyclerView.Adapter<AudiooAdapter.MyViewHolder> {
    private ImageLoader mImageLoader;

    private DisplayImageOptions options;

    private Activity mContext;

    private List<AudioEntity> audioList = new ArrayList<>();

    public AudiooAdapter(Activity mContext, List<AudioEntity> list) {
        this.mContext = mContext;
        audioList.addAll(list);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_recycler_audio, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.title.setText(audioList.get(position).getTitle());
        holder.content.setText(audioList.get(position).getDescription());
//        mImageLoader.displayImage(audioList.get(position).getImg(), holder.img, options);
        mImageLoader = ImageLoader.getInstance();

        holder.audioAll.setTag(holder.viewTime);

    }

    @Override
    public int getItemCount() {
        return audioList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView title;
        TextView content;
        RelativeLayout audioAll;
        TextView viewTime;

        public MyViewHolder(View view) {
            super(view);
            img = (ImageView) view.findViewById(R.id.navi_audio_img);
            title = (TextView) view.findViewById(R.id.navi_audio_title);
            content = (TextView) view.findViewById(R.id.navi_audio_content);
            audioAll = (RelativeLayout) view.findViewById(R.id.audio_navigation_all);
            viewTime = (TextView) view.findViewById(R.id.audio_view_times);
        }
    }
}
