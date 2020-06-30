package com.merrycodes.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.merrycodes.R;
import com.merrycodes.adapter.PlayHistoryAdapter;
import com.merrycodes.bean.VideoBean;
import com.merrycodes.util.CommonUtil;
import com.merrycodes.util.DBUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayHistoryActivity extends AppCompatActivity {

    @BindView(R.id.tv_back)
    TextView tvBack;

    @BindView(R.id.tv_main_title)
    TextView tvMainTitle;

    @BindView(R.id.title_bar)
    RelativeLayout titleBar;

    @BindView(R.id.lv_play_history)
    ListView lvPlayHistory;

    @BindView(R.id.tv_play_history)
    TextView tvPlayHistory;

    private List<VideoBean> videoBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_history);
        ButterKnife.bind(this);
        DBUtil db = DBUtil.getInstance(this);
        videoBeans = db.getVideoHistory(CommonUtil.getUserName(this));
        init();
    }

    private void init() {
        tvMainTitle.setText("播放记录");
        titleBar.setBackgroundColor(Color.parseColor("#30B4FF"));
        tvBack.setOnClickListener(v -> PlayHistoryActivity.this.finish());
        if (videoBeans.size() == 0) {
            tvPlayHistory.setVisibility(View.VISIBLE);
        }
        PlayHistoryAdapter playHistoryAdapter = new PlayHistoryAdapter(this);
        playHistoryAdapter.setDate(videoBeans);
        lvPlayHistory.setAdapter(playHistoryAdapter);

    }
}
