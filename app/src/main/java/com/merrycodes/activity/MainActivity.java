package com.merrycodes.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.merrycodes.R;
import com.merrycodes.R2;
import com.merrycodes.util.CommonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author MerryCodes
 * @date 2020/3/23 10:24
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R2.id.tv_back)
    TextView tvBack;

    @BindView(R2.id.tv_main_title)
    TextView tvMainTitle;

    @BindView(R2.id.title_bar)
    RelativeLayout titleBar;

    @BindView(R2.id.main_body)
    FrameLayout mainBody;

    @BindView(R2.id.main_bar)
    LinearLayout mainBar;

    // course
    @BindView(R2.id.course_btn)
    RelativeLayout courseBtn;

    @BindView(R2.id.image_course)
    ImageView imCourse;

    @BindView(R2.id.text_course)
    TextView tvCourse;

    // exercises
    @BindView(R2.id.exercises_btn)
    RelativeLayout exercisesBtn;

    @BindView(R2.id.image_exercises)
    ImageView imExercises;

    @BindView(R2.id.text_exercises)
    TextView tvExercises;

    // info
    @BindView(R2.id.info_btn)
    RelativeLayout infoBtn;

    @BindView(R2.id.image_info)
    ImageView imInfo;

    @BindView(R2.id.text_info)
    TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    /**
     * 给所有的Button设置监听
     */
    private void setListener() {
        for (int i = 0; i < mainBar.getChildCount(); i++) {
            mainBar.getChildAt(i).setOnClickListener(this);
        }
    }

    /**
     * 初始化
     */
    private void init() {
        tvBack.setVisibility(View.GONE);
        tvMainTitle.setText("博学谷课程");
        titleBar.setBackgroundColor(Color.parseColor("#30B4FF"));
        setListener();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 课程点击事件
            case R.id.course_btn:
                clearBottomImageState();
                selectDisplayView(0);
                break;
            // 习题点击事件
            case R.id.exercises_btn:
                clearBottomImageState();
                selectDisplayView(1);
                break;
            // 我的点击事件
            case R.id.info_btn:
                clearBottomImageState();
                selectDisplayView(2);
                break;
            default:
                break;
        }
    }

    /**
     * 清除mainBar的样式
     */
    private void clearBottomImageState() {
        tvCourse.setTextColor(Color.parseColor("#666666"));
        tvExercises.setTextColor(Color.parseColor("#666666"));
        tvInfo.setTextColor(Color.parseColor("#666666"));

        imCourse.setImageResource(R.drawable.main_course_icon);
        imExercises.setImageResource(R.drawable.main_exercises_icon);
        imInfo.setImageResource(R.drawable.main_my_icon);

        for (int i = 0; i < mainBar.getChildCount(); i++) {
            mainBar.getChildAt(i).setSelected(false);
        }

    }

    /**
     * 点击图标设置图标状态
     *
     * @param index 点击图标在Layout的小标
     */
    private void selectDisplayView(int index) {
        removeAllView();
        createView(index);
        setSelectedStatus(index);
    }

    private void setSelectedStatus(int index) {
        switch (index) {
            case 0:
                courseBtn.setSelected(true);
                tvCourse.setTextColor(Color.parseColor("#0097F7"));
                imCourse.setImageResource(R.drawable.main_course_icon_selected);
                titleBar.setVisibility(View.VISIBLE);
                tvMainTitle.setText("博学谷课程");
                break;
            case 1:
                exercisesBtn.setSelected(true);
                tvExercises.setTextColor(Color.parseColor("#0097F7"));
                imExercises.setImageResource(R.drawable.main_exercises_icon_selected);
                titleBar.setVisibility(View.VISIBLE);
                tvMainTitle.setText("博学谷习题");
                break;
            case 2:
                infoBtn.setSelected(true);
                tvInfo.setTextColor(Color.parseColor("#0097F7"));
                imInfo.setImageResource(R.drawable.main_my_icon_selected);
                titleBar.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    private void createView(int index) {
        switch (index) {
            case 0:
                CommonUtil.showToast(this, "课程界面");
                break;
            case 1:
                CommonUtil.showToast(this, "习题界面");
                break;
            case 2:
                CommonUtil.showToast(this, "我的界面");
                break;
            default:
                break;
        }
    }

    private void removeAllView() {
        for (int i = 0; i < mainBody.getChildCount(); i++) {
            mainBody.getChildAt(i).setVisibility(View.GONE);
        }
    }
}
