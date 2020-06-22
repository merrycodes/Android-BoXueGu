package com.merrycodes.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.merrycodes.R;
import com.merrycodes.util.CommonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VidePlayActivity extends AppCompatActivity {

    @BindView(R.id.video_view)
    VideoView videoView;

    private String videoPath;

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vide_play);
        ButterKnife.bind(this);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        videoPath = getIntent().getStringExtra("videoPath");
        position = getIntent().getIntExtra("position", 0);
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
        String url = "android:resource//" + getPackageName() + "/" + videoResourcesId;
        videoView.setVideoPath(url);
    }

}
