package com.merrycodes.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.merrycodes.R;
import com.merrycodes.activity.RegisterActivity;

/**
 * @author MerryCodes
 * @date 2020/3/23 10:24
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(this, RegisterActivity.class));
    }
}
