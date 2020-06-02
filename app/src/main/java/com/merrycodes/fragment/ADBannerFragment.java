package com.merrycodes.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.merrycodes.R;

/**
 * @author MerryCodes
 * @date 2020/6/2 16:09
 */
public class ADBannerFragment extends Fragment {

    private ImageView imageView;

    private String imageFlag;

    public static ADBannerFragment newInstance(Bundle args) {
        ADBannerFragment adBannerFragment = new ADBannerFragment();
        adBannerFragment.setArguments(args);
        return adBannerFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        assert args != null;
        imageFlag = args.getString("imageFlag");
    }

    @Override
    public void onResume() {
        super.onResume();
        if (imageFlag != null) {
            if (TextUtils.equals("banner_1", imageFlag)) {
                imageView.setImageResource(R.drawable.banner_1);
            } else if (TextUtils.equals("banner_2", imageFlag)) {
                imageView.setImageResource(R.drawable.banner_2);
            }else if (TextUtils.equals("banner_3", imageFlag)) {
                imageView.setImageResource(R.drawable.banner_3);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (imageView != null) {
            imageView.setImageDrawable(null);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        imageView = new ImageView(getActivity());
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(layoutParams);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        return imageView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
