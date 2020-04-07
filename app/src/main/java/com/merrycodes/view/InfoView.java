package com.merrycodes.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.merrycodes.R;
import com.merrycodes.R2;
import com.merrycodes.activity.LoginActivity;
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

    @BindView(R2.id.layout_head)
    private LinearLayout linearLayout;

    private final Activity activity;

    private final LayoutInflater layoutInflater;

    public InfoView(Activity activity) {
        this.activity = activity;
        this.layoutInflater = LayoutInflater.from(activity);
    }

    private void createView() {
        initView();
    }

    private void initView() {
        @SuppressLint("InflateParams")
        View view = layoutInflater.inflate(R.layout.main_view_info, null);
        ButterKnife.bind(activity, view);

        linearLayout.setOnClickListener(v -> {
            if (readLoginStatus()) {
                CommonUtil.showToast(activity, "跳转到个人资料界面");
            } else {
                Intent intent = new Intent(activity, LoginActivity.class);
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    private Boolean readLoginStatus() {
        return false;
    }
}
