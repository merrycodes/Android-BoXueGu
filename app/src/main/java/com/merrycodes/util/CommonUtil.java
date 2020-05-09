package com.merrycodes.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;
import android.widget.Toast;

import static com.merrycodes.constant.CommonConstant.LOGIN_INFO;
import static com.merrycodes.constant.CommonConstant.LOGIN_USER_NAME;

/**
 * 通用工具
 *
 * @author MerryCodes
 * @date 2020/3/24 15:10
 */
public class CommonUtil {


    /**
     * toast 工具
     *
     * @param context Context
     * @param message 提示信息
     */
    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取编辑框输入内容
     *
     * @param view EditText/TextView
     * @return 获取文本框内容
     */
    public static String getTextValue(TextView view) {
        return view.getText().toString().trim();
    }

    public static String getUserName(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(LOGIN_INFO, Context.MODE_PRIVATE);
        return sharedPreferences.getString(LOGIN_USER_NAME, "");
    }
}
