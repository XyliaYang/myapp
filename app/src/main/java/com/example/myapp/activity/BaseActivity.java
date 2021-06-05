package com.example.myapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Toast;

import com.example.myapp.R;

public abstract class BaseActivity extends AppCompatActivity {
    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;

        setContentView(initLayout());
        initView();
        initData();
    }

    protected  abstract int initLayout();
    protected  abstract void initView();
    protected abstract void initData();



    public void showToast(String msg){
        Toast.makeText(mContext,msg,Toast.LENGTH_LONG).show();
    }

//    ？使用消息队列,但是每个线程貌似只能用一次，否则会报错。
    public void showToastSync(String msg){
        Looper.prepare();
        Toast.makeText(mContext,msg,Toast.LENGTH_LONG).show();
        Looper.loop();
    }



    public void navigateTo(Class cls){
        Intent intent=new Intent(mContext,cls);
        startActivity(intent);
    }

    protected  void saveStringTosp(String key,String value){
        SharedPreferences sp=getSharedPreferences("sp_ttit",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

}