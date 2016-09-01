package com.njupt.sniper.app.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.njupt.sniper.app.model.entity.AudioEntity;
import com.njupt.sniper.mylibrary.ui.adapter.HeaderFooterRecyclerAdapter;
import com.njupt.sniper.mylibrary.utils.ToastUtils;
import com.njupt.sniper.ui.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenshenglong
 * @date 16-8-30
 */
public class AudioAdapter extends HeaderFooterRecyclerAdapter<AudioEntity, RecyclerView.Adapter> {

    private ImageLoader mImageLoader;

    private DisplayImageOptions options;

    private Activity mActivity;

    private List<AudioEntity> audioList = new ArrayList<>();

    public AudioAdapter(Activity activity, List<AudioEntity> mDatas, RecyclerView.Adapter adapter) {
        super(mDatas, adapter);
        audioList = mDatas;
        mActivity = activity;

        options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.mipmap.default_strategy) // resource or drawable
                .showImageOnFail(R.mipmap.default_strategy) // resource or drawable
                .cacheInMemory(true)    //设置下载的图片是否缓存在内存中
                .cacheOnDisk(true).build();   //设置下载的图片是否缓存在SD卡中
    }

    @Override
    protected RecyclerView.ViewHolder initRecyclerViewHolder(View itemView, int viewType) {
        return new AudioViewHolder(itemView, viewType);
    }

    @Override
    protected View getItemLayout(ViewGroup parent) {
        return LayoutInflater.from(mActivity).inflate(R.layout.item_recycler_audio, parent, false);
    }

    @Override
    protected void setDataToView(RecyclerView.ViewHolder viewHolder, List<AudioEntity> datas, int position) {
        AudioViewHolder holder = (AudioViewHolder) viewHolder;
        holder.title.setText(audioList.get(position).getTitle());
        holder.content.setText(audioList.get(position).getDescription());
//        mImageLoader.displayImage(audioList.get(position).getImg(), holder.img, options);
        mImageLoader = ImageLoader.getInstance();
//        mImageLoader.displayImage(audioList.get(position).getSmallImg(), holder.img, options);
        mImageLoader.displayImage("http://noodles-image.b0.upaiyun.com/audio_img_test/9.jpg!400x400", holder.img, options);

        holder.audioAll.setTag(holder.viewTime);
        holder.audioAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                IntentStart.startAudioPlayActivity(mActivity, audioList.get(position));

                ToastUtils.getInstance().showToast("audio item clicked!");
                TextView textView = (TextView) v.getTag();
                int i = (int) textView.getTag();
                textView.setText(i + 1 + "");
                textView.setTag(i + 1);
            }
        });

//        holder.viewTime.setText(audioList.get(position).getViewTimes() + "");
        holder.viewTime.setText(1354 + "");

//        holder.viewTime.setTag(audioList.get(position).getViewTimes());
        holder.viewTime.setTag(1354);

    }

    class AudioViewHolder extends RecyclerViewHolder {

        ImageView img;
        TextView title;
        TextView content;
        RelativeLayout audioAll;
        TextView viewTime;

        public AudioViewHolder(View itemView, int viewType) {
            super(itemView, viewType);
            img = (ImageView) itemView.findViewById(R.id.navi_audio_img);
            title = (TextView) itemView.findViewById(R.id.navi_audio_title);
            content = (TextView) itemView.findViewById(R.id.navi_audio_content);
            audioAll = (RelativeLayout) itemView.findViewById(R.id.audio_navigation_all);
            viewTime = (TextView) itemView.findViewById(R.id.audio_view_times);
        }

    }
}
