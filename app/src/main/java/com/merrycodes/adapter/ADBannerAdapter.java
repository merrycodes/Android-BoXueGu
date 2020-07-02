package com.merrycodes.adapter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.merrycodes.bean.CourseBean;
import com.merrycodes.fragment.ADBannerFragment;
import com.merrycodes.view.CourseView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MerryCodes
 * @date 2020/6/8 14:45
 */
public class ADBannerAdapter extends FragmentStatePagerAdapter implements View.OnTouchListener {

    private Handler handler;

    private List<CourseBean> courseBeans;

    public ADBannerAdapter(@NonNull FragmentManager fm, int behavior, Handler handler) {
        super(fm, behavior);
        this.handler = handler;
        courseBeans = new ArrayList<>();
    }

    public void setDate(List<CourseBean> courseBeans) {
        this.courseBeans = courseBeans;
        notifyDataSetChanged();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        handler.removeMessages(CourseView.MESSAGE_ADBANNER_SLID);

        return false;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        if (courseBeans.size() > 0) {
            bundle.putString("imageFlag", courseBeans.get(position % courseBeans.size()).getIcon());
        }
        return ADBannerFragment.newInstance(bundle);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    public int getSize() {
        return courseBeans == null ? 0 : courseBeans.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }

}
