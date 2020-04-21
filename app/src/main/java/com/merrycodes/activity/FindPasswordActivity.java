package com.merrycodes.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.merrycodes.R;
import com.merrycodes.R2;
import com.merrycodes.constant.CommonConstant;
import com.merrycodes.util.CommonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        sharedPreferences = getSharedPreferences(CommonConstant.LOGIN_INFO, MODE_PRIVATE);
        from = getIntent().getStringExtra(CommonConstant.FROM);
        init();

    }

    private void init() {
        if (TextUtils.equals(CommonConstant.SECURITY, from)) {
            tvMainTitle.setText("设置密保");
        } else {
            tvMainTitle.setText("找回密码");
            tvUsername.setVisibility(View.VISIBLE);
            etUsername.setVisibility(View.VISIBLE);
        }
        tvBack.setOnClickListener(v -> FindPasswordActivity.this.finish());

        btnValidate.setOnClickListener(v -> {
            // 保存密保
            if (TextUtils.equals(CommonConstant.SECURITY, from)) {
                String validateName = CommonUtil.getEditInput(etValidateName);
                if (TextUtils.isEmpty(validateName)) {
                    CommonUtil.showToast(this, "请输入要验证的姓名");
                } else {
                    CommonUtil.showToast(this, "密保设置成功");
                    saveSecurity(validateName);
                }
                // 找回密码
            } else {
                CommonUtil.showToast(this, "找回密码");
            }
        });
    }

    private void saveSecurity(String validateName) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(CommonUtil.getUserName(this) + CommonConstant.PREFIX_SECURITY, validateName);
        editor.apply();
    }
}
