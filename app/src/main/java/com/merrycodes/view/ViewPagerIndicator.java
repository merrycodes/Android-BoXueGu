package com.merrycodes.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.merrycodes.R;

/**
 * @author MerryCodes
 * @date 2020/6/1 14:24
 */
public class ViewPagerIndicator extends LinearLayout {

    private Context context;

    private Integer currentIndex;

    private Integer count;

    public ViewPagerIndicator(Context context) {
        super(context);
    }

    public ViewPagerIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setGravity(Gravity.CENTER);
    }

    public void setCurrentPosition(Integer currentIndex) {
        this.currentIndex = currentIndex;
        removeAllViews();
        Integer pex = 5;
        for (Integer i = 0; i < count; i++) {
            ImageView imageView = new ImageView(context);
            if (currentIndex.equals(i)) {
                imageView.setImageResource(R.drawable.indicator_on);
            } else {
                imageView.setImageResource(R.drawable.indicator_off);
            }
            imageView.setPadding(pex, 0, pex, 0);
            addView(imageView);
        }
    }
}
