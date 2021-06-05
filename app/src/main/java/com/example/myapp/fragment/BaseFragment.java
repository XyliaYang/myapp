package com.example.myapp.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Looper;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {

    public void showToast(String msg){
        Toast.makeText(getActivity(),msg,Toast.LENGTH_LONG).show();
    }

    //    ？使用消息队列,但是每个线程貌似只能用一次，否则会报错。
    public void showToastSync(String msg){
        Looper.prepare();
        Toast.makeText(getActivity(),msg,Toast.LENGTH_LONG).show();
        Looper.loop();
    }

    public void navigateTo(Class cls){
        Intent intent=new Intent(getActivity(),cls);
        startActivity(intent);
    }

    /**
     * 编辑SharedPreferences
     * @param key
     * @param value
     */
    protected  void saveStringTosp(String key,String value){
        SharedPreferences sp=getActivity().getSharedPreferences("sp_ttit",getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * 获取SharedPreferences
     * @param key
     * @return
     */
    protected  String getStringFromSp(String key){
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("sp_ttit",getActivity().MODE_PRIVATE);
        return sharedPreferences.getString("token","");
    }


}
