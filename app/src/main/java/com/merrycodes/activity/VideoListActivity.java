package com.merrycodes.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.merrycodes.R;
import com.merrycodes.adapter.VideoListAdapter;
import com.merrycodes.bean.VideoBean;
import com.merrycodes.util.CommonUtil;
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
import static com.merrycodes.constant.AssetsConstant.ID;
import static com.merrycodes.constant.AssetsConstant.INTRO;
import static com.merrycodes.constant.AssetsConstant.POSITION;
import static com.merrycodes.constant.AssetsConstant.SECOND_TITLE;
import static com.merrycodes.constant.AssetsConstant.TITLE;
import static com.merrycodes.constant.AssetsConstant.VIDEO_ID;
import static com.merrycodes.constant.AssetsConstant.VIDEO_PATH;

public class VideoListActivity extends AppCompatActivity implements View.OnClickListener {

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

    private VideoListAdapter videoListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
        ButterKnife.bind(this);
        id = getIntent().getIntExtra(ID, 0);
        intro = getIntent().getStringExtra(INTRO);
        db = DBUtil.getInstance(this);
        initData();
        init();
    }

    private void init() {
        videoListAdapter = new VideoListAdapter(this, (position, imageView) -> {
            videoListAdapter.selectedPosition(position);
            VideoBean videoBean = videoList.get(position);
            String videoPath = videoBean.getVideoPath();
            videoListAdapter.notifyDataSetChanged();
            if (TextUtils.isEmpty(videoPath)) {
                Intent intent = new Intent(this, VidePlayActivity.class);
                intent.putExtra(VIDEO_PATH, videoPath);
                intent.putExtra(POSITION, position);
                startActivityForResult(intent, 1);
            } else {
                if (CommonUtil.readLoginStatus(this)) {
                    String username = CommonUtil.getUserName(this);
                    db.saveVideoPlayList(videoBean, username);
                }
                CommonUtil.showToast(this, "跳转到播放视频界面");
            }
        });
        lvVideoList.setAdapter(videoListAdapter);
        tvIntro.setOnClickListener(this);
        tvVideo.setOnClickListener(this);
        videoListAdapter.setData(videoList);
        tvChapterIntro.setText(intro);
        tvIntro.setBackgroundColor(Color.parseColor("#30B4FF"));
        tvVideo.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tvIntro.setTextColor(Color.parseColor("#FFFFFF"));
        tvVideo.setTextColor(Color.parseColor("#000000"));
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
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_intro:
                lvVideoList.setVisibility(View.GONE);
                svChapterIntro.setVisibility(View.VISIBLE);
                tvIntro.setBackgroundColor(Color.parseColor("#30B4FF"));
                tvVideo.setBackgroundColor(Color.parseColor("#FFFFFF"));
                tvIntro.setTextColor(Color.parseColor("#FFFFFF"));
                tvVideo.setTextColor(Color.parseColor("#000000"));
                break;
            case R.id.tv_video:
                lvVideoList.setVisibility(View.VISIBLE);
                svChapterIntro.setVisibility(View.GONE);
                tvIntro.setBackgroundColor(Color.parseColor("#FFFFFF"));
                tvVideo.setBackgroundColor(Color.parseColor("#30B4FF"));
                tvIntro.setTextColor(Color.parseColor("#000000"));
                tvVideo.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            default:
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            int position = data.getIntExtra(POSITION, 0);
            videoListAdapter.selectedPosition(position);
            lvVideoList.setVisibility(View.VISIBLE);
            svChapterIntro.setVisibility(View.GONE);
            tvIntro.setBackgroundColor(Color.parseColor("#FFFFFF"));
            tvVideo.setBackgroundColor(Color.parseColor("#30B4FF"));
            tvIntro.setTextColor(Color.parseColor("#000000"));
            tvVideo.setTextColor(Color.parseColor("#FFFFFF"));
        }
    }

}
