package com.merrycodes.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.merrycodes.R;
import com.merrycodes.bean.VideoBean;
import com.merrycodes.util.DBUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Cleanup;
import lombok.SneakyThrows;

import static com.merrycodes.constant.AssetsConstant.CHAPTER_ID;
import static com.merrycodes.constant.AssetsConstant.SECOND_TITLE;
import static com.merrycodes.constant.AssetsConstant.TITLE;
import static com.merrycodes.constant.AssetsConstant.VIDEO_ID;
import static com.merrycodes.constant.AssetsConstant.VIDEO_PATH;

public class VideoListActivity extends AppCompatActivity {

    @BindView(R.id.tv_intro)
    TextView tvIntro;

    @BindView(R.id.tv_video)
    TextView tvVideo;

    @BindView(R.id.lv_video_list)
    ListView lvVideoList;

    @BindView(R.id.tv_chapter_intro)
    TextView tvChapterIntro;

    @BindView(R.id.sv_chapter_intro)
    ScrollView svChapterIntro;

    private Integer id;

    private String intro;

    private DBUtil db;

    private ArrayList<VideoBean> videoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
        ButterKnife.bind(this);
        id = getIntent().getIntExtra("id", 0);
        intro = getIntent().getStringExtra("intro");
        db = DBUtil.getInstance(this);
        initData();
        init();
    }

    private void init() {

    }

    @SneakyThrows
    private void initData() {
        JSONArray jsonArray;
        @Cleanup InputStream inputStream = getResources().getAssets().open("data.json");
        jsonArray = new JSONArray(read(inputStream));
        videoList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            VideoBean videoBean = new VideoBean();
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (jsonObject.getInt(CHAPTER_ID) == id) {
                videoBean.setChapterId(jsonObject.getInt(CHAPTER_ID));
                videoBean.setVideoId(Integer.valueOf(jsonObject.getString(VIDEO_ID)));
                videoBean.setTitle(jsonObject.getString(TITLE));
                videoBean.setSecondTitle(jsonObject.getString(SECOND_TITLE));
                videoBean.setVideoPath(jsonObject.getString(VIDEO_PATH));
                videoList.add(videoBean);
            }
        }
    }

    @SneakyThrows
    private String read(InputStream inputStream) {
        @Cleanup BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        if ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append("\n");
        }
        return null;
    }
}
