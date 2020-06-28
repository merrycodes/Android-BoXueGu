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
import com.merrycodes.activity.VideoListActivity;
import com.merrycodes.bean.CourseBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.merrycodes.constant.AssetsConstant.ID;
import static com.merrycodes.constant.AssetsConstant.INTRO;

/**
 * @author MerryCodes
 * @date 2020/6/8 15:09
 */
public class CourseAdapter extends BaseAdapter {

    private final Context context;

    List<List<CourseBean>> courseBeans;

    public CourseAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<List<CourseBean>> courseBeans) {
        this.courseBeans = courseBeans;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return courseBeans == null ? 0 : courseBeans.size();
    }

    @Override
    public List<CourseBean> getItem(int position) {
        return courseBeans == null ? null : courseBeans.get(position);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.course_list_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final List<CourseBean> list = getItem(position);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final CourseBean bean = list.get(i);
                switch (i) {
                    case 0:
                        viewHolder.tvLeftTitle.setText(bean.getImageTitle());
                        viewHolder.tvLeft.setText(bean.getTitle());
                        setImageView(bean.getId(), viewHolder.imLeft);
                        viewHolder.imLeft.setOnClickListener(v -> {
                            Intent intent = new Intent(context, VideoListActivity.class);
                            intent.putExtra(ID, bean.getId());
                            intent.putExtra(INTRO, bean.getIntro());
                            context.startActivity(intent);
                        });
                        break;
                    case 1:
                        viewHolder.tvRightTitle.setText(bean.getImageTitle());
                        viewHolder.tvRight.setText(bean.getTitle());
                        setImageView(bean.getId(), viewHolder.imRight);
                        viewHolder.imRight.setOnClickListener(v -> {
                            Intent intent = new Intent(context, VideoListActivity.class);
                            intent.putExtra(ID, bean.getId());
                            intent.putExtra(INTRO, bean.getIntro());
                            context.startActivity(intent);
                        });
                        break;
                    default:
                        break;
                }
            }
        }
        return convertView;
    }

    private void setImageView(Integer id, ImageView imageView) {
        switch (id) {
            case 1:
                imageView.setImageResource(R.drawable.chapter_1_icon);
                break;
            case 2:
                imageView.setImageResource(R.drawable.chapter_2_icon);
                break;
            case 3:
                imageView.setImageResource(R.drawable.chapter_3_icon);
                break;
            case 4:
                imageView.setImageResource(R.drawable.chapter_4_icon);
                break;
            case 5:
                imageView.setImageResource(R.drawable.chapter_5_icon);
                break;
            case 6:
                imageView.setImageResource(R.drawable.chapter_6_icon);
                break;
            case 7:
                imageView.setImageResource(R.drawable.chapter_7_icon);
                break;
            case 8:
                imageView.setImageResource(R.drawable.chapter_8_icon);
                break;
            case 9:
                imageView.setImageResource(R.drawable.chapter_9_icon);
                break;
            case 10:
                imageView.setImageResource(R.drawable.chapter_10_icon);
                break;
            default:
                break;
        }
    }

    static
    class ViewHolder {

        @BindView(R.id.im_left)
        ImageView imLeft;

        @BindView(R.id.tv_left_title)
        TextView tvLeftTitle;

        @BindView(R.id.tv_left)
        TextView tvLeft;

        @BindView(R.id.im_right)
        ImageView imRight;

        @BindView(R.id.tv_right_title)
        TextView tvRightTitle;

        @BindView(R.id.tv_right)
        TextView tvRight;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
