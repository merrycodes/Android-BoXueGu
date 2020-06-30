package com.merrycodes.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.merrycodes.R;
import com.merrycodes.bean.UserBean;
import com.merrycodes.util.CommonUtil;
import com.merrycodes.util.DBUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.merrycodes.constant.CommonConstant.CHANGE_NICKNAME;
import static com.merrycodes.constant.CommonConstant.CHANGE_SIGNATURE;
import static com.merrycodes.constant.CommonConstant.CONTENT;
import static com.merrycodes.constant.CommonConstant.FEMALE;
import static com.merrycodes.constant.CommonConstant.FLAG;
import static com.merrycodes.constant.CommonConstant.MALE;
import static com.merrycodes.constant.CommonConstant.NICKNAME;
import static com.merrycodes.constant.CommonConstant.SEX;
import static com.merrycodes.constant.CommonConstant.SIGNATURE;
import static com.merrycodes.constant.CommonConstant.TITLE;

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private String userName;

    @BindView(R.id.tv_main_title)
    TextView tvMainTitle;

    @BindView(R.id.tv_back)
    TextView tvBack;

    @BindView(R.id.title_bar)
    RelativeLayout titleBar;

    @BindView(R.id.username)
    RelativeLayout rlUsername;

    @BindView(R.id.nickname)
    RelativeLayout rlNickname;

    @BindView(R.id.sex)
    RelativeLayout rlSex;

    @BindView(R.id.signature)
    RelativeLayout rlSignature;

    @BindView(R.id.info_username)
    TextView tvUsername;

    @BindView(R.id.info_nickname)
    TextView tvNickname;

    @BindView(R.id.info_sex)
    TextView tvSex;

    @BindView(R.id.info_signature)
    TextView tvSignature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        userName = CommonUtil.getUserName(this);
        init();
        initData();
        setListener();
    }

    private void setListener() {
        tvBack.setOnClickListener(this);
        rlNickname.setOnClickListener(this);
        rlSex.setOnClickListener(this);
        rlSignature.setOnClickListener(this);
    }

    private void initData() {
        UserBean userBean = DBUtil.getInstance(this).getUserInfo(userName);
        if (userBean == null) {
            userBean = UserBean.builder().username(userName).nickname(userName).sex("男").signature("签名").build();
            DBUtil.getInstance(this).saveUserInfo(userBean);
        }
        setData(userBean);
    }

    private void setData(UserBean userBean) {
        tvUsername.setText(userBean.getUsername());
        tvNickname.setText(userBean.getNickname());
        tvSex.setText(userBean.getSex());
        tvSignature.setText(userBean.getSignature());
    }

    private void init() {
        tvMainTitle.setText("个人资料");
        titleBar.setBackgroundColor(Color.parseColor("#30B4FF"));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                UserInfoActivity.this.finish();
                break;
            case R.id.nickname:
                String nickname = CommonUtil.getTextValue(tvNickname);
                enterActivityForResult(CHANGE_NICKNAME, makeBundle(nickname, "昵称", 1));
                break;
            case R.id.sex:
                String sex = CommonUtil.getTextValue(tvSex);
                setDialog(sex);
                break;
            case R.id.signature:
                String signature = CommonUtil.getTextValue(tvSignature);
                enterActivityForResult(CHANGE_SIGNATURE, makeBundle(signature, "签名", 2));
                break;
            default:
                break;
        }
    }

    private void enterActivityForResult(Integer flag, Bundle bundle) {
        Intent intent = new Intent(this, ChangeUserInfoActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, flag);
    }

    private Bundle makeBundle(String content, String title, Integer flg) {
        Bundle bundle = new Bundle();
        bundle.putString(CONTENT, content);
        bundle.putString(TITLE, title);
        bundle.putInt(FLAG, flg);
        return bundle;
    }


    private void setDialog(String sex) {
        final String[] items = {MALE, FEMALE};
        int sexFlag = TextUtils.equals(FEMALE, sex) ? 1 : 0;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("性别");
        builder.setSingleChoiceItems(items, sexFlag, (dialog, which) -> {
            dialog.dismiss();
            CommonUtil.showToast(this, items[which]);
            setSex(items[which]);
        });
        builder.create().show();
    }

    private void setSex(String sex) {
        tvSex.setText(sex);
        DBUtil.getInstance(this).updateUserInfo(SEX, sex, userName);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CHANGE_NICKNAME:
                if (data != null) {
                    String nickname = data.getStringExtra(NICKNAME);
                    tvNickname.setText(nickname);
                    DBUtil.getInstance(this).updateUserInfo(NICKNAME, nickname, userName);
                }
                break;
            case CHANGE_SIGNATURE:
                if (data != null) {
                    String signature = data.getStringExtra(SIGNATURE);
                    tvSignature.setText(signature);
                    DBUtil.getInstance(this).updateUserInfo(SIGNATURE, signature, userName);
                }
                break;
            default:
                break;
        }
    }
}
