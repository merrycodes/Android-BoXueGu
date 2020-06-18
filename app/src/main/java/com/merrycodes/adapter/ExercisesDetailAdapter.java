package com.merrycodes.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.merrycodes.R;
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

    private final Context context;

    private List<String> selectePosition = new ArrayList<>();

    private List<ExercisesBean> exercisesBeans;

    private OnSelecteListener onSelecteListener;

    public ExercisesDetailAdapter(Context context, OnSelecteListener onSelecteListener) {
        this.context = context;
        this.onSelecteListener = onSelecteListener;
    }

    @Override
    public int getCount() {
        return exercisesBeans == null ? 0 : exercisesBeans.size();
    }

    @Override
    public ExercisesBean getItem(int position) {
        return exercisesBeans == null ? null : exercisesBeans.get(position);
    }

    public void setData(List<ExercisesBean> exercisesBeans) {
        this.exercisesBeans = exercisesBeans;
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.exercise_detail_list, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ExercisesBean item = getItem(position);
        if (item != null) {
            viewHolder.subject.setText(item.getSubject());
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
            CommonUtil.setExerciseImageEnable(true, viewHolder.im_a, viewHolder.im_b, viewHolder.im_c, viewHolder.im_d);
        } else {
            if (item != null) {
                CommonUtil.setExerciseImageEnable(false, viewHolder.im_a, viewHolder.im_b, viewHolder.im_c, viewHolder.im_d);
                switch (item.getSelect()) {
                    case 0:
                        if (item.getAnswer() == 1) {
                            viewHolder.im_a.setImageResource(R.drawable.exercises_right_icon);
                            viewHolder.im_b.setImageResource(R.drawable.exercises_b);
                            viewHolder.im_c.setImageResource(R.drawable.exercises_c);
                            viewHolder.im_d.setImageResource(R.drawable.exercises_d);
                        } else if (item.getAnswer() == 2) {
                            viewHolder.im_a.setImageResource(R.drawable.exercises_a);
                            viewHolder.im_b.setImageResource(R.drawable.exercises_right_icon);
                            viewHolder.im_c.setImageResource(R.drawable.exercises_c);
                            viewHolder.im_d.setImageResource(R.drawable.exercises_d);
                        } else if (item.getAnswer() == 3) {
                            viewHolder.im_a.setImageResource(R.drawable.exercises_a);
                            viewHolder.im_b.setImageResource(R.drawable.exercises_b);
                            viewHolder.im_c.setImageResource(R.drawable.exercises_right_icon);
                            viewHolder.im_d.setImageResource(R.drawable.exercises_d);
                        } else if (item.getAnswer() == 4) {
                            viewHolder.im_a.setImageResource(R.drawable.exercises_a);
                            viewHolder.im_b.setImageResource(R.drawable.exercises_b);
                            viewHolder.im_c.setImageResource(R.drawable.exercises_c);
                            viewHolder.im_d.setImageResource(R.drawable.exercises_right_icon);
                        }
                        break;
                    case 1:
                        viewHolder.im_a.setImageResource(R.drawable.exercises_error_icon);
                        if (item.getAnswer() == 2) {
                            viewHolder.im_b.setImageResource(R.drawable.exercises_right_icon);
                            viewHolder.im_c.setImageResource(R.drawable.exercises_c);
                            viewHolder.im_d.setImageResource(R.drawable.exercises_d);
                        } else if (item.getAnswer() == 3) {
                            viewHolder.im_b.setImageResource(R.drawable.exercises_b);
                            viewHolder.im_c.setImageResource(R.drawable.exercises_right_icon);
                            viewHolder.im_d.setImageResource(R.drawable.exercises_d);
                        } else if (item.getAnswer() == 4) {
                            viewHolder.im_b.setImageResource(R.drawable.exercises_b);
                            viewHolder.im_c.setImageResource(R.drawable.exercises_c);
                            viewHolder.im_d.setImageResource(R.drawable.exercises_right_icon);
                        }
                        break;
                    case 2:
                        viewHolder.im_b.setImageResource(R.drawable.exercises_error_icon);
                        if (item.getAnswer() == 1) {
                            viewHolder.im_a.setImageResource(R.drawable.exercises_right_icon);
                            viewHolder.im_c.setImageResource(R.drawable.exercises_c);
                            viewHolder.im_d.setImageResource(R.drawable.exercises_d);
                        } else if (item.getAnswer() == 3) {
                            viewHolder.im_a.setImageResource(R.drawable.exercises_a);
                            viewHolder.im_c.setImageResource(R.drawable.exercises_right_icon);
                            viewHolder.im_d.setImageResource(R.drawable.exercises_d);
                        } else if (item.getAnswer() == 4) {
                            viewHolder.im_a.setImageResource(R.drawable.exercises_a);
                            viewHolder.im_c.setImageResource(R.drawable.exercises_c);
                            viewHolder.im_d.setImageResource(R.drawable.exercises_right_icon);
                        }
                        break;
                    case 3:
                        viewHolder.im_c.setImageResource(R.drawable.exercises_error_icon);
                        if (item.getAnswer() == 1) {
                            viewHolder.im_a.setImageResource(R.drawable.exercises_right_icon);
                            viewHolder.im_b.setImageResource(R.drawable.exercises_b);
                            viewHolder.im_d.setImageResource(R.drawable.exercises_d);
                        } else if (item.getAnswer() == 2) {
                            viewHolder.im_a.setImageResource(R.drawable.exercises_a);
                            viewHolder.im_b.setImageResource(R.drawable.exercises_right_icon);
                            viewHolder.im_d.setImageResource(R.drawable.exercises_d);
                        } else if (item.getAnswer() == 4) {
                            viewHolder.im_a.setImageResource(R.drawable.exercises_a);
                            viewHolder.im_b.setImageResource(R.drawable.exercises_b);
                            viewHolder.im_d.setImageResource(R.drawable.exercises_right_icon);
                        }
                        break;
                    case 4:
                        viewHolder.im_d.setImageResource(R.drawable.exercises_error_icon);
                        if (item.getAnswer() == 1) {
                            viewHolder.im_a.setImageResource(R.drawable.exercises_right_icon);
                            viewHolder.im_b.setImageResource(R.drawable.exercises_b);
                            viewHolder.im_c.setImageResource(R.drawable.exercises_c);
                        } else if (item.getAnswer() == 2) {
                            viewHolder.im_a.setImageResource(R.drawable.exercises_a);
                            viewHolder.im_b.setImageResource(R.drawable.exercises_right_icon);
                            viewHolder.im_c.setImageResource(R.drawable.exercises_c);
                        } else if (item.getAnswer() == 3) {
                            viewHolder.im_a.setImageResource(R.drawable.exercises_a);
                            viewHolder.im_b.setImageResource(R.drawable.exercises_b);
                            viewHolder.im_c.setImageResource(R.drawable.exercises_right_icon);
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        viewHolder.im_a.setOnClickListener(v -> {
            if (!selectePosition.contains(String.valueOf(position))) {
                selectePosition.add(String.valueOf(position));
            }
            onSelecteListener.selectA(position, viewHolder.im_a, viewHolder.im_b, viewHolder.im_c, viewHolder.im_d);
        });

        viewHolder.im_b.setOnClickListener(v -> {
            if (!selectePosition.contains(String.valueOf(position))) {
                selectePosition.add(String.valueOf(position));
            }
            onSelecteListener.selectB(position, viewHolder.im_a, viewHolder.im_b, viewHolder.im_c, viewHolder.im_d);
        });

        viewHolder.im_c.setOnClickListener(v -> {
            if (!selectePosition.contains(String.valueOf(position))) {
                selectePosition.add(String.valueOf(position));
            }
            onSelecteListener.selectC(position, viewHolder.im_a, viewHolder.im_b, viewHolder.im_c, viewHolder.im_d);
        });

        viewHolder.im_d.setOnClickListener(v -> {
            if (!selectePosition.contains(String.valueOf(position))) {
                selectePosition.add(String.valueOf(position));
            }
            onSelecteListener.selectD(position, viewHolder.im_a, viewHolder.im_b, viewHolder.im_c, viewHolder.im_d);
        });
        return convertView;
    }

    static
    class ViewHolder {

        @BindView(R.id.tv_subject)
        TextView subject;

        @BindView(R.id.im_a)
        ImageView im_a;

        @BindView(R.id.tv_a)
        TextView tv_a;

        @BindView(R.id.im_b)
        ImageView im_b;

        @BindView(R.id.tv_b)
        TextView tv_b;

        @BindView(R.id.im_c)
        ImageView im_c;

        @BindView(R.id.tv_c)
        TextView tv_c;

        @BindView(R.id.im_d)
        ImageView im_d;

        @BindView(R.id.tv_d)
        TextView tv_d;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public interface OnSelecteListener {

        void selectA(Integer position, ImageView im_a, ImageView im_b, ImageView im_c, ImageView im_d);

        void selectB(Integer position, ImageView im_a, ImageView im_b, ImageView im_c, ImageView im_d);

        void selectC(Integer position, ImageView im_a, ImageView im_b, ImageView im_c, ImageView im_d);

        void selectD(Integer position, ImageView im_a, ImageView im_b, ImageView im_c, ImageView im_d);
    }

}
