package com.example.myapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapp.R;

public class RegisterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}