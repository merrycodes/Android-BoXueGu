package com.merrycodes.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.merrycodes.R;
import com.merrycodes.R2;
import com.merrycodes.constant.CommonConstant;
import com.merrycodes.util.CommonUtil;
import com.merrycodes.util.MD5Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R2.id.et_username)
    EditText etUserName;

    @BindView(R2.id.et_password)
    EditText etPassWord;

    @BindView(R2.id.btn_login)
    Button btLogin;

    @BindView(R2.id.tv_register)
    TextView tvRegister;

    @BindView(R2.id.tv_find_password)
    TextView tvFindPassword;

    @BindView(R2.id.tv_main_title)
    TextView tvMainTitle;

    @BindView(R2.id.tv_back)
    TextView tvBack;

    @BindView(R2.id.title_bar)
    RelativeLayout titleBar;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences(CommonConstant.LOGIN_INFO, MODE_PRIVATE);
        init();
    }

    private void init() {
        tvMainTitle.setText("登陆");

        // 处理点击放回按钮
        tvBack.setOnClickListener(v -> LoginActivity.this.finish());

        btLogin.setOnClickListener(v -> checkLogin());

        tvRegister.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivityForResult(intent, 1);
        });

        tvFindPassword.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, FindPasswordActivity.class);
            startActivity(intent);
        });
    }

    /**
     * 登陆的各种验证
     */
    private void checkLogin() {
        String username = CommonUtil.getEditInput(etUserName);
        String password = CommonUtil.getEditInput(etPassWord);
        String md5Password = MD5Util.springMd5(password);
        String spPassword = getPassword(username);

        if (TextUtils.isEmpty(username)) {
            CommonUtil.showToast(this, "请输入用户名");
        } else if (TextUtils.isEmpty(password)) {
            CommonUtil.showToast(this, "请输入密码");
        } else if (!TextUtils.equals(md5Password, spPassword) && !TextUtils.isEmpty(username)) {
            CommonUtil.showToast(this, "输入的用户名和密码不一致");
        } else if (TextUtils.equals(md5Password, spPassword)) {
            CommonUtil.showToast(this, "登陆成功");
            saveLoginStatus(username);
            Intent intent = new Intent();
            intent.putExtra(CommonConstant.IS_LOGIN, true);
            setResult(RESULT_OK, intent);
            LoginActivity.this.finish();
        }
    }

    /**
     * 保存登陆信息
     *
     * @param username 用户名
     */
    private void saveLoginStatus(String username) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(CommonConstant.IS_LOGIN, true);
        editor.putString(CommonConstant.LOGIN_USER_NAME, username);
        editor.apply();
    }

    /**
     * 获取账号密码
     *
     * @param username 用户名
     * @return 用户密码（md5 字符串）
     */
    private String getPassword(String username) {
        return sharedPreferences.getString(username, "");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            // 获取注册回传的用户名 并赋值到输入框中 设置光标
            String username = data.getStringExtra("username");
            if (!TextUtils.isEmpty(username)) {
                etUserName.setText(username);
                etUserName.setSelection(username.length());
            }

        }
    }
}
