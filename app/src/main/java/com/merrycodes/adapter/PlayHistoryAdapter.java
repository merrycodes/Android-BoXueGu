package com.merrycodes.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.merrycodes.R;
import com.merrycodes.activity.VideoPlayActivity;
import com.merrycodes.bean.VideoBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.merrycodes.constant.AssetsConstant.VIDEO_PATH;

/**
 * @author MerryCodes
 * @date 2020/6/28 20:58
 */
public class PlayHistoryAdapter extends BaseAdapter {

    private Context context;

    private List<VideoBean> videoBeans;

    public PlayHistoryAdapter(Context context) {
        this.context = context;
    }

    public void setDate(List<VideoBean> videoBeans) {
        this.videoBeans = videoBeans;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return videoBeans == null ? 0 : videoBeans.size();
    }

    @Override
    public VideoBean getItem(int position) {
        return videoBeans == null ? null : videoBeans.get(position);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.play_history_list_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        VideoBean videoBean = getItem(position);
        if (videoBean != null) {
            viewHolder.tvPlayHistoryItemTitle.setText(videoBean.getTitle());
            viewHolder.tvPlayHistoryItemVideoTitle.setText(videoBean.getSecondTitle());
            switch (videoBean.getChapterId()) {
                case 2:
                    viewHolder.imPlayHistoryItem.setImageResource(R.drawable.video_play_icon2);
                    break;
                case 3:
                    viewHolder.imPlayHistoryItem.setImageResource(R.drawable.video_play_icon3);
                    break;
                case 4:
                    viewHolder.imPlayHistoryItem.setImageResource(R.drawable.video_play_icon4);
                    break;
                case 5:
                    viewHolder.imPlayHistoryItem.setImageResource(R.drawable.video_play_icon5);
                    break;
                case 6:
                    viewHolder.imPlayHistoryItem.setImageResource(R.drawable.video_play_icon6);
                    break;
                case 7:
                    viewHolder.imPlayHistoryItem.setImageResource(R.drawable.video_play_icon7);
                    break;
                case 8:
                    viewHolder.imPlayHistoryItem.setImageResource(R.drawable.video_play_icon8);
                    break;
                case 9:
                    viewHolder.imPlayHistoryItem.setImageResource(R.drawable.video_play_icon9);
                    break;
                case 10:
                    viewHolder.imPlayHistoryItem.setImageResource(R.drawable.video_play_icon10);
                    break;
                default:
                    viewHolder.imPlayHistoryItem.setImageResource(R.drawable.video_play_icon1);
                    break;
            }
        }
        convertView.setOnClickListener(v -> {
            if (videoBean == null) {
                return;
            }
            Intent intent = new Intent(context, VideoPlayActivity.class);
            intent.putExtra(VIDEO_PATH, videoBean.getVideoPath());
            context.startActivity(intent);
        });
        return convertView;
    }

    class ViewHolder {

        @BindView(R.id.im_play_history_item)
        ImageView imPlayHistoryItem;

        @BindView(R.id.tv_play_history_item_title)
        TextView tvPlayHistoryItemTitle;

        @BindView(R.id.tv_play_history_item_video_title)
        TextView tvPlayHistoryItemVideoTitle;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }
}
