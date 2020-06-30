package com.merrycodes.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.merrycodes.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.merrycodes.constant.CommonConstant.IS_LOGIN;
import static com.merrycodes.constant.CommonConstant.LOGIN_INFO;
import static com.merrycodes.constant.CommonConstant.LOGIN_USER_NAME;

public class SettingActivity extends AppCompatActivity {

    @BindView(R.id.tv_back)
    TextView tvBack;

    @BindView(R.id.tv_main_title)
    TextView tvMainTitle;

    @BindView(R.id.title_bar)
    RelativeLayout titleBar;

    @BindView(R.id.rl_modify_password)
    RelativeLayout rlModifyPassword;

    @BindView(R.id.rl_security_setting)
    RelativeLayout rlSecuritySetting;

    @BindView(R.id.rl_login_out)
    RelativeLayout rlLoginOut;

    private SharedPreferences sharedPreferences;

    @SuppressLint("StaticFieldLeak")
    protected static SettingActivity instance = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        instance = this;
        ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences(LOGIN_INFO, MODE_PRIVATE);
        init();
    }

    private void init() {
        tvMainTitle.setText("设置");
        titleBar.setBackgroundColor(Color.parseColor("#30B4FF"));
        tvBack.setOnClickListener(v -> SettingActivity.this.finish());

        rlModifyPassword.setOnClickListener(v -> {
            Intent intent = new Intent(SettingActivity.this, ModifyPasswordActivity.class);
            startActivity(intent);
        });

        rlSecuritySetting.setOnClickListener(v -> {
            Intent intent = new Intent(SettingActivity.this, FindPasswordActivity.class);
            intent.putExtra("from", "security");
            startActivity(intent);
        });

        rlLoginOut.setOnClickListener(v -> {
            clearLoginStatus();
            Intent intent = new Intent();
            intent.putExtra(IS_LOGIN, false);
            setResult(RESULT_OK, intent);
            SettingActivity.this.finish();
        });

    }

    private void clearLoginStatus() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_LOGIN, false);
        editor.putString(LOGIN_USER_NAME, "");
        editor.apply();
    }

}
