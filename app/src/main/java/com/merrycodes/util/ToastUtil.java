package com.merrycodes.util;

import android.content.Context;
import android.widget.Toast;


/**
 * Toast 工具类
 *
 * @author MerryCodes
 * @date 2020/3/23 10:24
 */
public class ToastUtil {

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
