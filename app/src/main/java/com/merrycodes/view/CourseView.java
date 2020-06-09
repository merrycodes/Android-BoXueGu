package com.merrycodes.view;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.merrycodes.R;
import com.merrycodes.R2;
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

/**
 * @author MerryCodes
 * @date 2020/6/8 15:05
 */
public class CourseView {

    @BindView(R2.id.lv_courseList)
    ListView lvCourseList;

    @BindView(R2.id.vp_ADBanner)
    ViewPager vpADBanner;

    public static final Integer MESSAGE_ADBANNER_SLID = 1;

    private final FragmentActivity fragmentActivity;

    private final LayoutInflater layoutInflater;

    private List<CourseBean> courseInfo;

    List<List<CourseBean>> courseInfos;
    private CourseAdapter courseAdapter;

    public CourseView(FragmentActivity fragmentActivity) {
        this.fragmentActivity = fragmentActivity;
        layoutInflater = LayoutInflater.from(fragmentActivity);
    }


    private void createView() {
        initADBannerData();
        getSourseData();
        initView();
    }

    @SuppressLint("InflateParams")
    private void initView() {
        View view = layoutInflater.inflate(R.layout.main_view_course, null);
        ButterKnife.bind(fragmentActivity, view);
        courseAdapter = new CourseAdapter(fragmentActivity);
        courseAdapter.setData(courseInfos);
        lvCourseList.setAdapter(courseAdapter);
        vpADBanner.setLongClickable(false);
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
}
