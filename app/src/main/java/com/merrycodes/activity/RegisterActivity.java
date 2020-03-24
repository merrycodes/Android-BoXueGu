package com.merrycodes.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.merrycodes.R;
import com.merrycodes.R2;
import com.merrycodes.constant.CommonConstant;
import com.merrycodes.util.CommonUtil;
import com.merrycodes.util.MD5Util;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author MerryCodes
 * @date 2020/3/23 10:24
 */
public class RegisterActivity extends AppCompatActivity {

    @BindView(R2.id.et_username)
    EditText etUserName;

    @BindView(R2.id.et_password)
    EditText etPassWord;

    @BindView(R2.id.et_rePassword)
    EditText etRePassWord;

    @BindView(R2.id.btn_register)
    Button btRegister;

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
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        tvMainTitle.setText("注册");
        titleBar.setBackgroundColor(Color.TRANSPARENT);
        sharedPreferences = getSharedPreferences(CommonConstant.LOGIN_INFO, MODE_PRIVATE);

        // 处理点击放回按钮
        tvBack.setOnClickListener(v -> {
            RegisterActivity.this.finish();
        });

        // 处理点击注册按钮
        btRegister.setOnClickListener(v -> {
            checkRegister();
        });
    }

    /**
     * 验证输入内容是否合法
     */
    private void checkRegister() {
        // 获取注册页面的输入框内容
        String userName = getInput(etUserName);
        String password = getInput(etPassWord);
        String rePassword = getInput(etRePassWord);

        if (TextUtils.isEmpty(userName)) {
            CommonUtil.showToast(this, "请输入用户名");
        } else if (TextUtils.isEmpty(password)) {
            CommonUtil.showToast(this, "请输入密码");
        } else if (TextUtils.isEmpty(rePassword)) {
            CommonUtil.showToast(this, "请再次输入密码");
        } else if (!TextUtils.equals(password, rePassword)) {
            CommonUtil.showToast(this, "两次输入的密码不一致");
        } else if (isExistUserName(userName)) {
            CommonUtil.showToast(this, "此用户名已经存在");
        } else {
            CommonUtil.showToast(this, "注册成功");
            // 保存密码到 sharedPreferences
            saveRegisterInfo(userName, password);
            // 回传数据到登陆页面 （登陆页面 -> 注册页面[finish with intent] -> 登陆页面）
            Intent intent = new Intent();
            intent.putExtra("username", userName);
            setResult(RESULT_OK, intent);
            RegisterActivity.this.finish();
        }
    }

    /**
     * 保存用户密码 使用md5加密
     *
     * @param userName 用户名
     * @param password 密码
     */
    private void saveRegisterInfo(String userName, String password) {
        String md5Password = MD5Util.springMd5(password);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(userName, md5Password);
        editor.apply();
    }

    /**
     * 判断用户是否存在
     *
     * @param userName 用户名
     * @return true/false
     */
    private boolean isExistUserName(String userName) {
        boolean flag = false;
        String password = sharedPreferences.getString(userName, "");
        if (!TextUtils.isEmpty(password)) {
            flag = true;
        }
        return flag;
    }

    /**
     * 获取编辑框内的内容
     *
     * @param editText EditText
     * @return string 编辑框内的内容
     */
    private String getInput(EditText editText) {
        return editText.getText().toString().trim();
    }
}
