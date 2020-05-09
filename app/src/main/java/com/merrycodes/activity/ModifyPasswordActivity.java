package com.merrycodes.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
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

import static com.merrycodes.constant.CommonConstant.LOGIN_INFO;

public class ModifyPasswordActivity extends AppCompatActivity {

    @BindView(R2.id.tv_back)
    TextView tvBack;

    @BindView(R2.id.tv_main_title)
    TextView tvMainTitle;

    @BindView(R2.id.et_original_password)
    EditText etOriginalPassword;

    @BindView(R2.id.et_new_password)
    EditText etNewPassword;

    @BindView(R2.id.et_re_new_password)
    EditText etReNewPassword;

    @BindView(R2.id.btn_save)
    Button btnSave;

    private String userName;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_password);
        ButterKnife.bind(this);
        userName = CommonUtil.getUserName(this);
        sharedPreferences = getSharedPreferences(LOGIN_INFO, MODE_PRIVATE);
        init();
    }

    private void init() {
        tvMainTitle.setText("修改密码");
        tvBack.setOnClickListener(v -> ModifyPasswordActivity.this.finish());

        btnSave.setOnClickListener(v -> {
            String originalPassword = CommonUtil.getTextValue(etOriginalPassword);
            String newPassword = CommonUtil.getTextValue(etReNewPassword);
            String reNewPassword = CommonUtil.getTextValue(etReNewPassword);
            if (TextUtils.isEmpty(originalPassword)) {
                CommonUtil.showToast(this, "请输入原密码");
            } else if (!TextUtils.equals(MD5Util.springMd5(originalPassword), readPassword())) {
                CommonUtil.showToast(this, "输入的密码和原密码不一致");
            } else if (TextUtils.equals(MD5Util.springMd5(newPassword), reNewPassword)) {
                CommonUtil.showToast(this, "输入的新密码不能和原密码一致");
            } else if (TextUtils.isEmpty(newPassword)) {
                CommonUtil.showToast(this, "请输入新密码");
            } else if (TextUtils.isEmpty(reNewPassword)) {
                CommonUtil.showToast(this, "请再次输入新密码");
            } else if (!TextUtils.equals(newPassword, reNewPassword)) {
                CommonUtil.showToast(this, "两次输入的密码不一致");
            } else {
                CommonUtil.showToast(this,"新密码设置成功");
                modifyPassword(newPassword);
                Intent intent = new Intent(ModifyPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
                ModifyPasswordActivity.this.finish();
                SettingActivity.instance.finish();

            }
        });
    }

    private void modifyPassword(String newPassword) {
        String password = MD5Util.springMd5(newPassword);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(userName, password);
        editor.apply();
    }

    private String readPassword() {
        return sharedPreferences.getString(userName, "");
    }

}
