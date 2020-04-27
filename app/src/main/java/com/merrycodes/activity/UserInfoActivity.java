package com.merrycodes.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.merrycodes.R;
import com.merrycodes.R2;
import com.merrycodes.util.CommonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private String userName;

    @BindView(R2.id.tv_main_title)
    TextView tvMainTitle;

    @BindView(R2.id.tv_back)
    TextView tvBack;

    @BindView(R2.id.title_bar)
    RelativeLayout titleBar;

    @BindView(R2.id.nickname)
    RelativeLayout rlNickname;

    @BindView(R2.id.sex)
    RelativeLayout rlSex;

    @BindView(R2.id.signature)
    RelativeLayout rlSignature;

    @BindView(R2.id.info_nickname)
    TextView tvNickname;

    @BindView(R2.id.info_sex)
    TextView tvSex;

    @BindView(R2.id.info_signature)
    TextView tvSignature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        userName = CommonUtil.getUserName(this);

        init();
    }

    private void init() {
        tvMainTitle.setText("个人资料");
        titleBar.setBackgroundColor(Color.parseColor("#30B4FF"));

    }

    @Override
    public void onClick(View v) {

    }
}
