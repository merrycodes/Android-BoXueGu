package com.merrycodes.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.merrycodes.bean.UserBean;
import com.merrycodes.enums.UserInfoEnum;
import com.merrycodes.sqlite.SQLiteHelper;

/**
 * @author MerryCodes
 * @date 2020/4/27 12:50
 */
public class DBUtil {

    private final SQLiteDatabase database;

    private static DBUtil instance;

    private DBUtil(Context context) {
        SQLiteHelper sqLiteHelper = new SQLiteHelper(context);
        database = sqLiteHelper.getWritableDatabase();
    }

    public static DBUtil getInstance(Context context) {
        if (instance == null) {
            instance = new DBUtil(context);
        }
        return instance;
    }

    public void saveUserInfo(UserBean userBean) {
        ContentValues values = new ContentValues();
        values.put(UserInfoEnum.USERNAME.getValue(), userBean.getUsername());
        values.put(UserInfoEnum.NICKNAME.getValue(), userBean.getNickname());
        values.put(UserInfoEnum.SEX.getValue(), userBean.getSex());
        values.put(UserInfoEnum.SIGNATURE.getValue(), userBean.getSignature());
        database.insert(SQLiteHelper.USER_INFO, null, values);
    }

    public UserBean getUserInfo(String username) {
        String sql = "SELECT * FROM " + SQLiteHelper.USER_INFO + " WHERE username = ?";
        Cursor cursor = database.rawQuery(sql, new String[]{username});
        UserBean userBean = null;
        while (cursor.moveToNext()) {
            userBean = new UserBean();
            userBean.setUsername(cursor.getString(cursor.getColumnIndex(UserInfoEnum.USERNAME.getValue())));
            userBean.setNickname(cursor.getString(cursor.getColumnIndex(UserInfoEnum.NICKNAME.getValue())));
            userBean.setSex(cursor.getString(cursor.getColumnIndex(UserInfoEnum.SEX.getValue())));
            userBean.setSignature(cursor.getString(cursor.getColumnIndex(UserInfoEnum.SIGNATURE.getValue())));
        }
        cursor.close();
        return userBean;
    }

    public void updateUserInfo(String key, String value, String username) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(key, value);
        database.update(SQLiteHelper.USER_INFO, contentValues, "username = ?", new String[]{username});
    }
}
