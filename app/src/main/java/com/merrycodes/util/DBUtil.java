package com.merrycodes.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.merrycodes.bean.UserBean;
import com.merrycodes.bean.VideoBean;
import com.merrycodes.enums.UserInfoEnum;
import com.merrycodes.enums.VideoPlayListEnum;
import com.merrycodes.sqlite.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;


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

    public void saveVideoPlayList(VideoBean videoBean, String username) {
        if (hasVideoPlay(videoBean.getChapterId(), videoBean.getVideoId(), username)) {
            Boolean isDelete = deleteVideoPlay(videoBean.getChapterId(), videoBean.getVideoId(), username);
            if (!isDelete) {
                return;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(VideoPlayListEnum.USERNAME.getValue(), username);
        contentValues.put(VideoPlayListEnum.CHAPTER_ID.getValue(), videoBean.getChapterId());
        contentValues.put(VideoPlayListEnum.VIDEO_ID.getValue(), videoBean.getVideoId());
        contentValues.put(VideoPlayListEnum.VIDEO_PATH.getValue(), videoBean.getVideoPath());
        contentValues.put(VideoPlayListEnum.TITLE.getValue(), videoBean.getTitle());
        contentValues.put(VideoPlayListEnum.SECONDE_TITLE.getValue(), videoBean.getSecondTitle());
        database.insert(SQLiteHelper.VIDEO_PLAY_LIST, null, contentValues);
    }

    private Boolean deleteVideoPlay(Integer chapterId, Integer videoId, String username) {
        boolean isDelete = false;
        int row = database.delete(SQLiteHelper.VIDEO_PLAY_LIST, "chapterId = ? AND videoId = ? AND username = ?",
                new String[]{String.valueOf(chapterId), String.valueOf(videoId), username});
        if (row > 0) {
            isDelete = true;
        }
        return isDelete;
    }

    private Boolean hasVideoPlay(Integer chapterId, Integer videoId, String username) {
        boolean hasVideo = false;
        String sql = "SELECT * FROM " + SQLiteHelper.VIDEO_PLAY_LIST + " WHERE chapterId = ? AND " +
                "videoId = ? AND username = ?";
        Cursor cursor = database.rawQuery(sql, new String[]{String.valueOf(chapterId), String.valueOf(videoId), username});

        if (cursor.moveToFirst()) {
            hasVideo = true;
        }
        cursor.close();
        return hasVideo;
    }

    public List<VideoBean> getVideoHistory(String username) {

        String sql = "SELECT * FROM " + SQLiteHelper.VIDEO_PLAY_LIST + " WHERE username = ?";
        Cursor cursor = database.rawQuery(sql, new String[]{username});
        List<VideoBean> videoBeans = new ArrayList<>();
        VideoBean videoBean;
        while (cursor.moveToNext()) {
            videoBean = new VideoBean();
            videoBean.setChapterId(cursor.getInt(cursor.getColumnIndex(VideoPlayListEnum.CHAPTER_ID.getValue())));
            videoBean.setVideoId(cursor.getInt(cursor.getColumnIndex(VideoPlayListEnum.VIDEO_ID.getValue())));
            videoBean.setVideoPath(cursor.getString(cursor.getColumnIndex(VideoPlayListEnum.VIDEO_PATH.getValue())));
            videoBean.setTitle(cursor.getString(cursor.getColumnIndex(VideoPlayListEnum.TITLE.getValue())));
            videoBean.setSecondTitle(cursor.getString(cursor.getColumnIndex(VideoPlayListEnum.SECONDE_TITLE.getValue())));
            videoBeans.add(videoBean);
        }
        cursor.close();
        return videoBeans;
    }
}
