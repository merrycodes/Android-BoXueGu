package com.merrycodes.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.merrycodes.R;
import com.merrycodes.R2;
import com.merrycodes.adapter.ExercisesDetailAdapter;
import com.merrycodes.bean.ExercisesBean;
import com.merrycodes.util.CommonUtil;

import java.io.InputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExerciseDetailActivity extends AppCompatActivity {

    private Integer id;

    private String title;

    private List<ExercisesBean> exercisesBeans;

    @BindView(R2.id.tv_main_title)
    TextView tvMainTitle;

    @BindView(R2.id.tv_back)
    TextView tvBack;

    @BindView(R2.id.title_bar)
    RelativeLayout titleBar;

    @BindView(R2.id.exercise_list)

    ListView exerciseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_detail);
        ButterKnife.bind(this);

        id = getIntent().getIntExtra("id", 0);
        title = getIntent().getStringExtra("title");

        initData();
        init();
    }

    private void init() {
        titleBar.setBackgroundColor(Color.parseColor("#30B4FF"));
        tvMainTitle.setText(title);
        tvBack.setOnClickListener(v -> ExerciseDetailActivity.this.finish());
        TextView textView = new TextView(this);
        textView.setTextColor(Color.parseColor("#000000"));
        textView.setText("一、选择题");
        textView.setTextSize(16.0f);
        textView.setPadding(10, 15, 0, 0);
        exerciseList.addHeaderView(textView);
        ExercisesDetailAdapter adapter = new ExercisesDetailAdapter(this, new ExercisesDetailAdapter.OnSelecteListener() {
            @Override
            public void selectA(Integer position, ImageView im_a, ImageView im_b, ImageView im_c, ImageView im_d) {
                if (exercisesBeans.get(position).getAnswer() != 1) {
                    exercisesBeans.get(position).setSelect(1);
                } else {
                    exercisesBeans.get(position).setSelect(0);
                }
                switch (exercisesBeans.get(position).getAnswer()) {
                    case 1:
                        im_a.setImageResource(R.drawable.exercises_right_icon);
                        break;
                    case 2:
                        im_a.setImageResource(R.drawable.exercises_error_icon);
                        im_b.setImageResource(R.drawable.exercises_right_icon);
                        break;
                    case 3:
                        im_a.setImageResource(R.drawable.exercises_error_icon);
                        im_c.setImageResource(R.drawable.exercises_right_icon);
                        break;
                    case 4:
                        im_a.setImageResource(R.drawable.exercises_error_icon);
                        im_d.setImageResource(R.drawable.exercises_right_icon);
                        break;
                    default:
                        break;
                }
                CommonUtil.setExerciseImageEnable(false, im_a, im_b, im_c, im_d);
            }

            @Override
            public void selectB(Integer position, ImageView im_a, ImageView im_b, ImageView im_c, ImageView im_d) {
                if (exercisesBeans.get(position).getAnswer() != 2) {
                    exercisesBeans.get(position).setSelect(2);
                } else {
                    exercisesBeans.get(position).setSelect(0);
                }
                switch (exercisesBeans.get(position).getAnswer()) {
                    case 1:
                        im_a.setImageResource(R.drawable.exercises_right_icon);
                        im_b.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 2:
                        im_b.setImageResource(R.drawable.exercises_right_icon);
                        break;
                    case 3:
                        im_b.setImageResource(R.drawable.exercises_error_icon);
                        im_c.setImageResource(R.drawable.exercises_right_icon);
                        break;
                    case 4:
                        im_b.setImageResource(R.drawable.exercises_error_icon);
                        im_d.setImageResource(R.drawable.exercises_right_icon);
                        break;
                    default:
                        break;
                }
                CommonUtil.setExerciseImageEnable(false, im_a, im_b, im_c, im_d);
            }

            @Override
            public void selectC(Integer position, ImageView im_a, ImageView im_b, ImageView im_c, ImageView im_d) {
                if (exercisesBeans.get(position).getAnswer() != 3) {
                    exercisesBeans.get(position).setSelect(3);
                } else {
                    exercisesBeans.get(position).setSelect(0);
                }
                switch (exercisesBeans.get(position).getAnswer()) {
                    case 1:
                        im_a.setImageResource(R.drawable.exercises_right_icon);
                        im_c.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 2:
                        im_b.setImageResource(R.drawable.exercises_right_icon);
                        im_c.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 3:
                        im_c.setImageResource(R.drawable.exercises_right_icon);
                        break;
                    case 4:
                        im_c.setImageResource(R.drawable.exercises_error_icon);
                        im_d.setImageResource(R.drawable.exercises_right_icon);
                        break;
                    default:
                        break;
                }
                CommonUtil.setExerciseImageEnable(false, im_a, im_b, im_c, im_d);
            }

            @Override
            public void selectD(Integer position, ImageView im_a, ImageView im_b, ImageView im_c, ImageView im_d) {
                if (exercisesBeans.get(position).getAnswer() != 4) {
                    exercisesBeans.get(position).setSelect(4);
                } else {
                    exercisesBeans.get(position).setSelect(0);
                }
                switch (exercisesBeans.get(position).getAnswer()) {
                    case 1:
                        im_a.setImageResource(R.drawable.exercises_right_icon);
                        im_d.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 2:
                        im_b.setImageResource(R.drawable.exercises_right_icon);
                        im_d.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 3:
                        im_c.setImageResource(R.drawable.exercises_right_icon);
                        im_d.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 4:
                        im_d.setImageResource(R.drawable.exercises_right_icon);
                        break;
                    default:
                        break;
                }
                CommonUtil.setExerciseImageEnable(false, im_a, im_b, im_c, im_d);
            }
        });
        adapter.setData(exercisesBeans);
        exerciseList.setAdapter(adapter);
    }

    private void initData() {
        try (InputStream inputStream = getResources().getAssets().open("chapter" + id + ".xml")) {
            exercisesBeans = CommonUtil.parseExercises(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
