package com.merrycodes.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.merrycodes.R;
import com.merrycodes.activity.LoginActivity;
import com.merrycodes.activity.PlayHistoryActivity;
import com.merrycodes.activity.SettingActivity;
import com.merrycodes.activity.UserInfoActivity;
import com.merrycodes.util.CommonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 个人页面
 *
 * @author MerryCodes
 * @date 2020/4/7 15:16
 */
public class InfoView {

    @BindView(R.id.layout_head)
    LinearLayout linearLayout;

    @BindView(R.id.im_head)
    ImageView imHead;

    @BindView(R.id.rl_history)
    RelativeLayout rlHistory;

    @BindView(R.id.rl_setting)
    RelativeLayout rlSetting;

    @BindView(R.id.tv_username)
    TextView tvUsername;

    private View view;

    private final Activity activity;

    private final LayoutInflater layoutInflater;

    public InfoView(Activity activity) {
        this.activity = activity;
        this.layoutInflater = LayoutInflater.from(activity);
    }

    private void createView() {
        initView();
    }

    @SuppressLint("InflateParams")
    private void initView() {
        view = layoutInflater.inflate(R.layout.main_view_info, null);
        ButterKnife.bind(this, view);
        view.setVisibility(View.VISIBLE);
        setLoginParams(CommonUtil.readLoginStatus(activity));

        linearLayout.setOnClickListener(v -> {
            if (CommonUtil.readLoginStatus(activity)) {
                // 跳转到个人资料界面
                Intent intent = new Intent(activity, UserInfoActivity.class);
                activity.startActivity(intent);
            } else {
                // 跳转到登陆界面
                Intent intent = new Intent(activity, LoginActivity.class);
                activity.startActivityForResult(intent, 1);
            }
        });

        rlHistory.setOnClickListener(v -> {
            if (CommonUtil.readLoginStatus(activity)) {
                // 跳转到播放记录界面
                Intent intent = new Intent(activity, PlayHistoryActivity.class);
                activity.startActivity(intent);
            } else {
                CommonUtil.showToast(activity, "您还未登陆，请先登陆");
            }
        });

        rlSetting.setOnClickListener(v -> {
            if (CommonUtil.readLoginStatus(activity)) {
                CommonUtil.showToast(activity, "跳转到设置界面");
                Intent intent = new Intent(activity, SettingActivity.class);
                activity.startActivityForResult(intent, 1);
            } else {
                CommonUtil.showToast(activity, "您还未登陆，请先登陆");
            }
        });
    }

    public void setLoginParams(Boolean isLogin) {
        if (isLogin) {
            tvUsername.setText(CommonUtil.getUserName(activity));
        } else {
            tvUsername.setText("点击登陆");
        }
    }

    // 简单的单例设计
    public View getView() {
        if (view == null) {
            createView();
        }
        return view;
    }

    public void showView() {
        if (view == null) {
            createView();
        }
        view.setVisibility(View.VISIBLE);
    }

}
