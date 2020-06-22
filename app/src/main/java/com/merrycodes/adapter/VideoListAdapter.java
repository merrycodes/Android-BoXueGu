package com.merrycodes.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.merrycodes.R;
import com.merrycodes.bean.VideoBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author MerryCodes
 * @date 2020/6/15 16:28
 */
public class VideoListAdapter extends BaseAdapter {

    private Context context;

    private OnSelecteListener onSelecteListener;

    private Integer selectedPosition =-1;

    private List<VideoBean> videoList;

    public VideoListAdapter(Context context, OnSelecteListener onSelecteListener) {
        this.context = context;
        this.onSelecteListener = onSelecteListener;
    }

    public void selectedPosition(Integer position) {
        this.selectedPosition = position;
    }

    public void setData(List<VideoBean> videoList) {
        this.videoList = videoList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return videoList == null ? 0 : videoList.size();
    }

    @Override
    public VideoBean getItem(int position) {
        return videoList == null ? null : videoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.video_list_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        VideoBean videoBean = getItem(position);
        viewHolder.imVideoIcon.setImageResource(R.drawable.course_bar_icon);
        viewHolder.tvVideoTitle.setTextColor(Color.parseColor("#333333"));
        if (videoBean != null) {
            viewHolder.tvVideoTitle.setText(videoBean.getSecondTitle());
            if (selectedPosition == position) {
                viewHolder.imVideoIcon.setImageResource(R.drawable.course_intro_icon);
                viewHolder.tvVideoTitle.setTextColor(Color.parseColor("#009958"));
            } else {
                viewHolder.imVideoIcon.setImageResource(R.drawable.course_bar_icon);
                viewHolder.tvVideoTitle.setTextColor(Color.parseColor("#333333"));
            }
        }
        convertView.setOnClickListener(v -> {
            if (videoBean == null) {
                return;
            }
            onSelecteListener.OnSelect(position,viewHolder.imVideoIcon);
        });
        return convertView;
    }

    public interface OnSelecteListener {

        void OnSelect(Integer position, ImageView imageView);

    }

    class ViewHolder {

        @BindView(R.id.im_video_icon)
        ImageView imVideoIcon;

        @BindView(R.id.tv_video_title)
        TextView tvVideoTitle;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }
}
