package com.merrycodes.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.merrycodes.R;

import butterknife.ButterKnife;

public class ModifyPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_password);
        ButterKnife.bind(this);
    }
}
