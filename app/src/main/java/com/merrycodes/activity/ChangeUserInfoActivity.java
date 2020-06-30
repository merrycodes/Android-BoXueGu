package com.merrycodes.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.merrycodes.R;
import com.merrycodes.util.CommonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.merrycodes.constant.CommonConstant.CHANGE_NICKNAME;
import static com.merrycodes.constant.CommonConstant.CHANGE_SIGNATURE;
import static com.merrycodes.constant.CommonConstant.CONTENT;
import static com.merrycodes.constant.CommonConstant.FLAG;
import static com.merrycodes.constant.CommonConstant.NICKNAME;
import static com.merrycodes.constant.CommonConstant.SIGNATURE;
import static com.merrycodes.constant.CommonConstant.TITLE;

public class ChangeUserInfoActivity extends AppCompatActivity {

    @BindView(R.id.tv_main_title)
    TextView tvMainTitle;

    @BindView(R.id.tv_back)
    TextView tvBack;

    @BindView(R.id.tv_save)
    TextView tvSave;

    @BindView(R.id.title_bar)
    RelativeLayout titleBar;

    @BindView(R.id.et_content)
    EditText etContent;

    @BindView(R.id.im_delete)
    ImageView imDelete;

    private Integer flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_info);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        String content = getIntent().getStringExtra(CONTENT);
        String title = getIntent().getStringExtra(TITLE);
        flag = getIntent().getIntExtra(FLAG, 0);
        tvMainTitle.setText(title);
        titleBar.setBackgroundColor(Color.parseColor("#30B4FF"));
        tvSave.setVisibility(View.VISIBLE);
        if (!TextUtils.isEmpty(content)) {
            etContent.setText(content);
            etContent.setSelection(content.length());
        }
        contentListener();
        tvBack.setOnClickListener(v -> ChangeUserInfoActivity.this.finish());
        imDelete.setOnClickListener(v -> etContent.setText(""));
        tvSave.setOnClickListener(v -> {
            Intent intent = new Intent();
            String textValue = CommonUtil.getTextValue(etContent);
            switch (flag) {
                case CHANGE_NICKNAME:
                    if (!TextUtils.isEmpty(textValue)) {
                        intent.putExtra(NICKNAME, textValue);
                        setResult(RESULT_OK, intent);
                        CommonUtil.showToast(this, "昵称保存成功");
                        ChangeUserInfoActivity.this.finish();
                    }else {
                        CommonUtil.showToast(this, "昵称不能为空");
                    }
                    break;
                case CHANGE_SIGNATURE:
                    if (!TextUtils.isEmpty(textValue)) {
                        intent.putExtra(SIGNATURE, textValue);
                        setResult(RESULT_OK, intent);
                        CommonUtil.showToast(this, "签名保存成功");
                        ChangeUserInfoActivity.this.finish();
                    }else{
                        CommonUtil.showToast(this,"签名不能为空");
                    }
                    break;
                default:
                    break;
            }
        });
    }

    private void contentListener() {
        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Editable editable = etContent.getText();
                int length = editable.length();
                if (length > 0) {
                    imDelete.setVisibility(View.VISIBLE);
                } else {
                    imDelete.setVisibility(View.VISIBLE);
                }
                switch (flag) {
                    case CHANGE_NICKNAME:
                        if (length > 8) {
                            int selectionEnd = Selection.getSelectionEnd(editable);
                            String content = editable.toString().trim();
                            etContent.setText(content.substring(0, 8));
                            editable = etContent.getText();
                            if (selectionEnd > editable.length()) {
                                selectionEnd = editable.length();
                            }
                            Selection.setSelection(editable, selectionEnd);
                        }
                        break;

                    case CHANGE_SIGNATURE:
                        if (length > 12) {
                            int selectionEnd = Selection.getSelectionEnd(editable);
                            String content = editable.toString().trim();
                            etContent.setText(content.substring(0, 16));
                            editable = etContent.getText();
                            if (selectionEnd > editable.length()) {
                                selectionEnd = editable.length();
                            }
                            Selection.setSelection(editable, selectionEnd);
                        }
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
