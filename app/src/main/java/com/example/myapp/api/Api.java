package com.example.myapp.api;

import android.util.Log;

import com.example.myapp.util.AppConfig;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Api {
//    单例模式
   private static  OkHttpClient client;
    private static String requestUrl;
    private static HashMap<String,Object> mParams;

    public static Api api=new Api();

    public Api(){

    }

    public static Api config(String url, HashMap<String,Object> params){
        client=new OkHttpClient.Builder().build();
        requestUrl=ApiConfig.BASE_URL+url;
        mParams=params;
        return api;
    }


    /***
     * 实现了一个回调函数，传入参数为一个接口类型，并将结果返回到接口对象的抽象方法中
     * @param callback
     */
    public void postRequest(TtitCallback callback){
        JSONObject jsonObject=new JSONObject(mParams);
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),jsonObject.toString());

        Request request=new Request.Builder()
                .url(requestUrl)
                .addHeader("Content-Type","application/json;charset=UTF-8")
                .post(requestBody)
                .build();

        final Call call=client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("onfailure",e.getMessage());
                callback.onFailure(e);

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result=response.body().string();
                callback.onSuccess(result);
            }
        });
    }

}
