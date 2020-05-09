package com.merrycodes.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.merrycodes.R;
import com.merrycodes.R2;
import com.merrycodes.util.CommonUtil;
import com.merrycodes.view.InfoView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.merrycodes.constant.CommonConstant.IS_LOGIN;
import static com.merrycodes.constant.CommonConstant.LOGIN_INFO;
import static com.merrycodes.constant.CommonConstant.LOGIN_USER_NAME;

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

    private InfoView infoView;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences(LOGIN_INFO, MODE_PRIVATE);
        init();
        setListener();
        setInitStatus();
    }

    private void setInitStatus() {
        clearBottomImageState();
        selectDisplayView(0);
        createView(0);
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
                if (infoView == null) {
                    infoView = new InfoView(this);
                    mainBody.addView(infoView.getView());
                } else {
                    infoView.getView();
                }
                infoView.showView();
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

    protected Long exitTime;

    /**
     * 点击返回按钮处理事件
     *
     * @param keyCode 点击的按钮
     * @param event   点击事件
     * @return boolean
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                CommonUtil.showToast(this, "再按一次退出博学谷");
                exitTime = System.currentTimeMillis();
            } else {
                MainActivity.this.finish();
                if (readLoginStatus()) {
                    clearLoginStatus();
                }
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 清理登陆状态
     */
    private void clearLoginStatus() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_LOGIN, false);
        editor.putString(LOGIN_USER_NAME, "");
        editor.apply();
    }

    /**
     * 读取登陆状态
     *
     * @return 登陆状态
     */
    private Boolean readLoginStatus() {
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            boolean isLogin = data.getBooleanExtra(IS_LOGIN, false);
            if (isLogin) {
                clearBottomImageState();
                selectDisplayView(2);
            }
            if (infoView != null) {
                infoView.setLoginParams(isLogin);
            }
        }

    }
}
