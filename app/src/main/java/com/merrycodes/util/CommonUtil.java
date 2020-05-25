package com.merrycodes.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Xml;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.merrycodes.bean.ExercisesBean;
import com.merrycodes.constant.ExerciseXMLConstant;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

import static com.merrycodes.constant.ExerciseXMLConstant.*;
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
     * @param context ExerciseContext
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

    public static List<ExercisesBean> parseExercises(InputStream inputStream) throws XmlPullParserException, IOException {
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(inputStream, "UTF-8");
        List<ExercisesBean> exercisesBeans = null;
        ExercisesBean exercisesBean = null;
        int type = parser.getEventType();
        while (type != XmlPullParser.END_DOCUMENT) {
            switch (type) {
                case XmlPullParser.START_TAG:
                    if (TextUtils.equals(INFO, parser.getName())) {
                        exercisesBeans = new ArrayList<>();
                    } else if (TextUtils.equals(EXERCISES, parser.getName())) {
                        exercisesBean = new ExercisesBean();
                        String id = parser.getAttributeValue(0);
                        exercisesBean.setId(Integer.valueOf(id));
                    }
                    if (exercisesBean != null) {
                        if (TextUtils.equals(SUBJECT, parser.getName())) {
                            String subject = parser.nextText();
                            exercisesBean.setSubject(subject);
                        } else if (TextUtils.equals(A, parser.getName())) {
                            String a = parser.nextText();
                            exercisesBean.setA(a);
                        } else if (TextUtils.equals(B, parser.getName())) {
                            String b = parser.nextText();
                            exercisesBean.setA(b);
                        } else if (TextUtils.equals(C, parser.getName())) {
                            String c = parser.nextText();
                            exercisesBean.setA(c);
                        } else if (TextUtils.equals(D, parser.getName())) {
                            String d = parser.nextText();
                            exercisesBean.setA(d);
                        } else if (TextUtils.equals(ANSWER, parser.getName())) {
                            String answer = parser.nextText();
                            exercisesBean.setAnswer(Integer.valueOf(answer));
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if (TextUtils.equals(EXERCISES, parser.getName())) {
                        assert exercisesBeans != null;
                        exercisesBeans.add(exercisesBean);
                        exercisesBean = null;
                    }
                    break;

                default:
                    break;
            }
            type = parser.next();
        }
        return exercisesBeans;
    }

    public static void setExerciseImageEnable(Boolean value, ImageView... imageViews) {
        for (ImageView imageView : imageViews) {
            imageView.setEnabled(value);
        }
    }
}
