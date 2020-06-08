package com.merrycodes.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.merrycodes.R;
import com.merrycodes.R2;
import com.merrycodes.bean.CourseBean;
import com.merrycodes.util.CommonUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.RequiredArgsConstructor;

/**
 * @author MerryCodes
 * @date 2020/6/8 15:09
 */
public class CourseAdapter extends BaseAdapter {

    @BindView(R2.id.tv_left_title)
    TextView tvLeftTitle;

    @BindView(R2.id.tv_left)
    TextView tvLeft;

    @BindView(R2.id.tv_right_title)
    TextView tvRightTitle;

    @BindView(R2.id.tv_right)
    TextView tvRight;

    @BindView(R2.id.im_left)
    ImageView imLeft;

    @BindView(R2.id.im_right)
    ImageView imRight;

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
        return context == null ? 0 : courseBeans.size();
    }

    @Override
    public List<CourseBean> getItem(int position) {
        return context == null ? null : courseBeans.get(position);
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
            ButterKnife.bind(this, convertView);
            viewHolder = new ViewHolder(tvLeftTitle, tvLeft, tvRightTitle, tvRight, imLeft, imRight);
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
                        CommonUtil.showToast(context,"设置左边图片");
                        break;
                    default:
                        break;
                }
            }
        }


        return null;
    }

    @RequiredArgsConstructor
    private class ViewHolder {
        private final TextView tvLeftTitle;
        private final TextView tvLeft;
        private final TextView tvRightTitle;
        private final TextView tvRight;
        private final ImageView imLeft;
        private final ImageView imRight;
    }

}
