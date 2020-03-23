package com.merrycodes.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.merrycodes.MainActivity;
import com.merrycodes.R;
import com.merrycodes.R2;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author MerryCodes
 * @date 2020/3/23 10:24
 */
public class SplashActivity extends AppCompatActivity {

    @BindView(R2.id.splash_version)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        try {
            // 通过包管理获取版本号
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0);
            textView.setText(String.format("V %s", info.versionName));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            textView.setText("V");
        }
        // 定时任务 延迟3秒进入页面
        timer.schedule(timertask, 3000);
    }

    Timer timer = new Timer();

    TimerTask timertask = new TimerTask() {

        @Override
        public void run() {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            SplashActivity.this.finish();
        }
    };
}
