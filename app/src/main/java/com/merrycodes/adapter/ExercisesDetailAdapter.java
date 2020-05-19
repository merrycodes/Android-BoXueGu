package com.merrycodes.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.merrycodes.R;
import com.merrycodes.R2;
import com.merrycodes.bean.ExercisesBean;
import com.merrycodes.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.RequiredArgsConstructor;

/**
 * @author MerryCodes
 * @date 2020/5/19 23:37
 */
@RequiredArgsConstructor
public class ExercisesDetailAdapter extends BaseAdapter {

    @BindView(R2.id.tv_subject)
    TextView tv_subject;

    @BindView(R2.id.tv_a)
    TextView tv_a;

    @BindView(R2.id.tv_b)
    TextView tv_b;

    @BindView(R2.id.tv_c)
    TextView tv_c;

    @BindView(R2.id.tv_d)
    TextView tv_d;

    @BindView(R2.id.im_a)
    ImageView im_a;

    @BindView(R2.id.im_b)
    ImageView im_b;

    @BindView(R2.id.im_c)
    ImageView im_c;

    @BindView(R2.id.im_d)
    ImageView im_d;

    private final Context context;

    private List<String> selectePosition = new ArrayList<>();

    private List<ExercisesBean> exercisesBeans;

    @Override
    public int getCount() {
        return exercisesBeans == null ? 0 : exercisesBeans.size();
    }

    @Override
    public ExercisesBean getItem(int position) {
        return exercisesBeans == null ? null : exercisesBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.exercises_list_item, null);
            ButterKnife.bind(this, convertView);
            viewHolder = new ViewHolder(tv_subject, tv_a, im_a, tv_b, im_b, tv_c, im_c, tv_d, im_d);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ExercisesBean item = getItem(position);
        if (item != null) {
            viewHolder.tv_a.setText(item.getA());
            viewHolder.tv_b.setText(item.getB());
            viewHolder.tv_c.setText(item.getC());
            viewHolder.tv_d.setText(item.getD());
        }
        if (!selectePosition.contains(String.valueOf(position))) {
            viewHolder.im_a.setImageResource(R.drawable.exercises_a);
            viewHolder.im_b.setImageResource(R.drawable.exercises_b);
            viewHolder.im_c.setImageResource(R.drawable.exercises_c);
            viewHolder.im_d.setImageResource(R.drawable.exercises_d);
            CommonUtil.setExerciseImageEnable(true, im_a, im_b, im_c, im_d);
        }
        return null;
    }

    @RequiredArgsConstructor
    private class ViewHolder {
        private final TextView subject;
        private final TextView tv_a;
        private final ImageView im_a;
        private final TextView tv_b;
        private final ImageView im_b;
        private final TextView tv_c;
        private final ImageView im_c;
        private final TextView tv_d;
        private final ImageView im_d;
    }

}
