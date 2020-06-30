package com.merrycodes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.merrycodes.R;
import com.merrycodes.util.CommonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.merrycodes.constant.AssetsConstant.POSITION;
import static com.merrycodes.constant.AssetsConstant.VIDEO_PATH;

public class VideoPlayActivity extends AppCompatActivity {

    @BindView(R.id.video_view)
    VideoView videoView;

    private String videoPath;

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        ButterKnife.bind(this);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        videoPath = getIntent().getStringExtra(VIDEO_PATH);
        position = getIntent().getIntExtra(POSITION, 0);
        init();
    }

    private void init() {
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        play();
    }

    private void play() {
        if (TextUtils.isEmpty(videoPath)) {
            CommonUtil.showToast(this, "本地没有此视频，暂无法播放");
            return;
        }
        int videoResourcesId = getResources().getIdentifier(videoPath, "raw", getPackageName());
        String url = "android.resource//" + getPackageName() + "/" + videoResourcesId;
        videoView.setVideoPath(url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intent intent = new Intent();
        intent.putExtra(POSITION, position);
        setResult(RESULT_OK, intent);
        return super.onKeyDown(keyCode, event);
    }
}
