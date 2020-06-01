package com.merrycodes.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.merrycodes.R;
import com.merrycodes.R2;
import com.merrycodes.adapter.ExercisesAdapter;
import com.merrycodes.bean.ExercisesBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author MerryCodes
 * @date 2020/5/12 21:36
 */
public class ExercisesView {

    @BindView(R2.id.lv_exerviseslist)
    ListView listView;

    private View view;

    private Activity activity;

    private LayoutInflater layoutInflater;

    private ArrayList<ExercisesBean> exercisesBeans;

    public ExercisesView(Activity activity) {
        this.activity = activity;
        layoutInflater = LayoutInflater.from(activity);
    }

    @SuppressLint("InflateParams")
    private void initView() {
        view = layoutInflater.inflate(R.layout.main_view_exercises, null);
        ButterKnife.bind(this, view);
        ExercisesAdapter adapter = new ExercisesAdapter(activity);
        initData();
        adapter.setData(exercisesBeans);
        listView.setAdapter(adapter);
    }

    private void initData() {
        exercisesBeans = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            ExercisesBean exercisesBean = new ExercisesBean();
            exercisesBean.setId(i + 1);
            switch (i) {
                case 0:
                    exercisesBean.setTitle("第 1 章 Android 基础入门");
                    exercisesBean.setContent("共计 5 题");
                    exercisesBean.setBackground(R.drawable.exercises_bg_1);
                    break;
                case 1:
                    exercisesBean.setTitle("第 2 章 Android UI开发");
                    exercisesBean.setContent("共计 5 题");
                    exercisesBean.setBackground(R.drawable.exercises_bg_2);
                    break;
                case 2:
                    exercisesBean.setTitle("第 3 章 Activity");
                    exercisesBean.setContent("共计 5 题");
                    exercisesBean.setBackground(R.drawable.exercises_bg_3);
                    break;
                case 3:
                    exercisesBean.setTitle("第 4 章 数据存储");
                    exercisesBean.setContent("共计 5 题");
                    exercisesBean.setBackground(R.drawable.exercises_bg_4);
                    break;
                case 4:
                    exercisesBean.setTitle("第 5 章 SQLite数据库");
                    exercisesBean.setContent("共计 5 题");
                    exercisesBean.setBackground(R.drawable.exercises_bg_1);
                    break;
                case 5:
                    exercisesBean.setTitle("第 6 章 广播接收者");
                    exercisesBean.setContent("共计 5 题");
                    exercisesBean.setBackground(R.drawable.exercises_bg_2);
                    break;
                case 6:
                    exercisesBean.setTitle("第 7 章 服务");
                    exercisesBean.setContent("共计 5 题");
                    exercisesBean.setBackground(R.drawable.exercises_bg_3);
                    break;
                case 7:
                    exercisesBean.setTitle("第 8 章 内容提供者");
                    exercisesBean.setContent("共计 5 题");
                    exercisesBean.setBackground(R.drawable.exercises_bg_4);
                    break;
                case 8:
                    exercisesBean.setTitle("第 9 章 网络编程");
                    exercisesBean.setContent("共计 5 题");
                    exercisesBean.setBackground(R.drawable.exercises_bg_1);
                    break;
                case 9:
                    exercisesBean.setTitle("第 10 章 高级编程");
                    exercisesBean.setContent("共计 5 题");
                    exercisesBean.setBackground(R.drawable.exercises_bg_2);
                    break;
                default:
                    break;
            }
            exercisesBeans.add(exercisesBean);
        }
    }

    private void createView() {
        initView();
    }

    public View getView() {
        if (view == null) {
            createView();
        }
        return view;
    }

    public void showView() {
        if (view == null) {
            createView();
        }
        view.setVisibility(View.VISIBLE);
    }
}
