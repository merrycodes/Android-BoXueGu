package com.merrycodes.util;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

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
     * @param editText EditText
     * @return 编辑框输入内容
     */
    public static String getEditInput(EditText editText) {
        return editText.getText().toString().trim();
    }
}
