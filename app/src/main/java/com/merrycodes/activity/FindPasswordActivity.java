package com.merrycodes.activity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.merrycodes.R;
import com.merrycodes.R2;
import com.merrycodes.util.CommonUtil;
import com.merrycodes.util.MD5Util;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.merrycodes.constant.CommonConstant.FROM;
import static com.merrycodes.constant.CommonConstant.LOGIN_INFO;
import static com.merrycodes.constant.CommonConstant.PREFIX_SECURITY;
import static com.merrycodes.constant.CommonConstant.SECURITY;

public class FindPasswordActivity extends AppCompatActivity {

    @BindView(R2.id.tv_main_title)
    TextView tvMainTitle;

    @BindView(R2.id.tv_back)
    TextView tvBack;

    @BindView(R2.id.tv_username)
    TextView tvUsername;

    @BindView(R2.id.et_username)
    EditText etUsername;

    @BindView(R2.id.et_validate_name)
    EditText etValidateName;

    @BindView(R2.id.tv_reset_password)
    TextView tvResetPassword;

    @BindView(R2.id.btn_validate)
    Button btnValidate;

    private String from;

    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);
        ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences(LOGIN_INFO, MODE_PRIVATE);
        from = getIntent().getStringExtra(FROM);
        init();

    }

    @SuppressLint("SetTextI18n")
    private void init() {
        if (TextUtils.equals(SECURITY, from)) {
            tvMainTitle.setText("设置密保");
        } else {
            tvMainTitle.setText("找回密码");
            tvUsername.setVisibility(View.VISIBLE);
            etUsername.setVisibility(View.VISIBLE);
        }
        tvBack.setOnClickListener(v -> FindPasswordActivity.this.finish());

        btnValidate.setOnClickListener(v -> {
            String validateName = CommonUtil.getTextValue(etValidateName);
            // 保存密保
            if (TextUtils.equals(SECURITY, from)) {
                if (TextUtils.isEmpty(validateName)) {
                    CommonUtil.showToast(this, "请输入要验证的姓名");
                } else {
                    CommonUtil.showToast(this, "密保设置成功");
                    saveSecurity(validateName);
                    FindPasswordActivity.this.finish();
                }
                // 找回密码
            } else {
                CommonUtil.showToast(this, "找回密码");
                String username = CommonUtil.getTextValue(etUsername);
                String security = readSecurity(username);
                if (TextUtils.isEmpty(username)) {
                    CommonUtil.showToast(this, "请输入您的用户名");
                } else if (!isExitUserName(username)) {
                    CommonUtil.showToast(this, "您输入的用户名不存在");
                } else if (TextUtils.isEmpty(validateName)) {
                    CommonUtil.showToast(this, "请输入要验证的姓名");
                } else if (!TextUtils.equals(security, validateName)) {
                    CommonUtil.showToast(this, "输入密保不正确");
                } else {
                    tvResetPassword.setVisibility(View.VISIBLE);
                    tvResetPassword.setText("初始密码 : 123456");
                    resetPassword(username);
                }
            }
        });
    }

    private void resetPassword(String username) {
        String md5Password = MD5Util.springMd5("123456");
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(username, md5Password);
        editor.apply();
    }

    private boolean isExitUserName(String username) {
        return sharedPreferences.getString(username, "") != null;
    }

    private String readSecurity(String username) {
        return sharedPreferences.getString(username + PREFIX_SECURITY, "");
    }

    private void saveSecurity(String validateName) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(CommonUtil.getUserName(this) + PREFIX_SECURITY, validateName);
        editor.apply();
    }
}
