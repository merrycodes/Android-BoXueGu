package com.merrycodes.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.merrycodes.R;
import com.merrycodes.R2;
import com.merrycodes.adapter.ADBannerAdapter;
import com.merrycodes.adapter.CourseAdapter;
import com.merrycodes.bean.CourseBean;
import com.merrycodes.util.CommonUtil;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.SneakyThrows;

/**
 * @author MerryCodes
 * @date 2020/6/8 15:05
 */
public class CourseView {

    @BindView(R2.id.lv_courseList)
    ListView lvCourseList;

    @BindView(R2.id.vp_ADBanner)
    ViewPager vpADBanner;

    @BindView(R2.id.vpi_indicator)
    ViewPagerIndicator vpiIndicator;

    @BindView(R2.id.rl_ADBanner)
    RelativeLayout rlADBanner;

    public static final int MESSAGE_ADBANNER_SLID = 1;

    private final FragmentActivity fragmentActivity;

    private final LayoutInflater layoutInflater;

    private List<CourseBean> courseInfo;

    List<List<CourseBean>> courseInfos;

    private CourseAdapter courseAdapter;

    private Handler handler;

    private ADBannerAdapter adBannerAdapter;
    private View currentView;

    public CourseView(FragmentActivity fragmentActivity) {
        this.fragmentActivity = fragmentActivity;
        layoutInflater = LayoutInflater.from(fragmentActivity);
    }


    private void createView() {
        handler = new CourseHandler();
        initADBannerData();
        getSourseData();
        initView();
        new ADBannerAutoSlideThread().start();
    }

    @SuppressLint({"InflateParams", "ClickableViewAccessibility"})
    private void initView() {
        currentView = layoutInflater.inflate(R.layout.main_view_course, null);
        ButterKnife.bind(this, currentView);
        courseAdapter = new CourseAdapter(fragmentActivity);
        courseAdapter.setData(courseInfos);
        lvCourseList.setAdapter(courseAdapter);
        vpADBanner.setLongClickable(false);
        adBannerAdapter = new ADBannerAdapter(fragmentActivity.getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, handler);
        vpADBanner.setAdapter(adBannerAdapter);
        vpADBanner.setOnTouchListener(adBannerAdapter);
        vpiIndicator.setCount(adBannerAdapter.getSize());
        vpADBanner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (adBannerAdapter.getSize() > 0) {
                    vpiIndicator.setCurrentPosition(position % adBannerAdapter.getSize());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        resetSize();
        if (courseInfo.size() > 0) {
            vpiIndicator.setCount(courseInfo.size());
            vpiIndicator.setCurrentPosition(0);
        }
        adBannerAdapter.setDate(courseInfo);
    }

    public View getView() {
        if (currentView == null) {
            createView();
        }
        return currentView;
    }

    public void showView() {
        if (currentView == null) {
            createView();
        }
        currentView.setVisibility(View.VISIBLE);
    }

    private void resetSize() {
        Integer width = getScreenWidth(fragmentActivity);
        Integer height = width / 2;
        ViewGroup.LayoutParams layoutParams = rlADBanner.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
        rlADBanner.setLayoutParams(layoutParams);
    }

    private Integer getScreenWidth(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        Display display = activity.getWindowManager().getDefaultDisplay();
        display.getMetrics(metrics);
        return metrics.widthPixels;
    }

    private void getSourseData() {
        try (InputStream inputStream = fragmentActivity.getResources().getAssets().open("chaptertitle.xml")) {
            courseInfos = CommonUtil.getCourseInfos(inputStream);
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }
    }

    private void initADBannerData() {
        courseInfo = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            CourseBean courseBean = new CourseBean();
            courseBean.setId(i + 1);
            switch (i) {
                case 0:
                    courseBean.setIcon("banner_1");
                    break;
                case 1:
                    courseBean.setIcon("banner_2");
                    break;
                case 2:
                    courseBean.setIcon("banner_3");
                    break;
                default:
                    break;
            }
            courseInfo.add(courseBean);
        }
    }

    @SuppressLint("HandlerLeak")
    private class CourseHandler extends Handler {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            if (msg.what == MESSAGE_ADBANNER_SLID) {
                if (adBannerAdapter.getCount() > 0) {
                    vpADBanner.setCurrentItem(vpADBanner.getCurrentItem() + 1);
                }
            }
        }
    }

    private class ADBannerAutoSlideThread extends Thread {

        @Override
        @SneakyThrows
        public void run() {
            super.run();
            while (true) {
                sleep(5000);
                if (handler != null) {
                    handler.sendEmptyMessage(MESSAGE_ADBANNER_SLID);
                }
            }
        }

    }

}
