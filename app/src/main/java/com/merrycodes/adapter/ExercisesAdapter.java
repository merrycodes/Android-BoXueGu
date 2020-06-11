package com.merrycodes.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.merrycodes.R;
import com.merrycodes.activity.ExerciseDetailActivity;
import com.merrycodes.bean.ExercisesBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.RequiredArgsConstructor;


/**
 * @author MerryCodes
 * @date 2020/5/12 21:15
 */
@RequiredArgsConstructor
public class ExercisesAdapter extends BaseAdapter {

    private final Context context;

    private List<ExercisesBean> exercisesBeans;

    public void setData(List<ExercisesBean> exercisesBeans) {
        this.exercisesBeans = exercisesBeans;
        notifyDataSetChanged();
    }

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

    @Override
    @SuppressLint({"ViewHolder", "InflateParams"})
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.exercises_list_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = ((ViewHolder) convertView.getTag());
        }
        ExercisesBean exercisesBean = getItem(position);
        if (exercisesBean != null) {
            viewHolder.order.setText(String.valueOf(position + 1));
            viewHolder.order.setBackgroundResource(exercisesBean.getBackground());
            viewHolder.title.setText(exercisesBean.getTitle());
            viewHolder.content.setText(exercisesBean.getContent());
        }
        convertView.setOnClickListener(v -> {
            if (exercisesBean == null) {
                return;
            }
            Intent intent = new Intent(context, ExerciseDetailActivity.class);
            intent.putExtra("id", exercisesBean.getId());
            intent.putExtra("title", exercisesBean.getTitle());
            context.startActivity(intent);
        });

        return convertView;
    }

    static
    class ViewHolder {
        @BindView(R.id.tv_order)
        TextView order;
        @BindView(R.id.tv_title)
        TextView title;
        @BindView(R.id.tv_content)
        TextView content;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
