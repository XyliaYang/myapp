package com.example.myapp.api;

import android.util.Log;

import com.example.myapp.util.AppConfig;
import com.example.myapp.util.StringUtils;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Timeout;

public class Api {
//    单例模式
   private static  OkHttpClient client;
    private static String requestUrl;
//    mParams表示传入的一些参数等，如user和pwd
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

//        第三步：创建request
        Request request=new Request.Builder()
                .url(requestUrl)
                .addHeader("Content-Type","application/json;charset=UTF-8")
                .post(requestBody)
                .build();

//        第四步：创建call回调对象
        final Call call=client.newCall(request);
//        第五步：发起请求
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

    /**
     * 发送get请求
     * @param callback
     */
    public  void getRequest(final TtitCallback callback){

        String url=getAppendUrl(requestUrl,mParams);
        Request request=new Request.Builder()
                .url(url)
                .get()
                .build();

        final  Call call=client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("failed",e.getMessage());
//                还是不是很明白使用这个回调的原因
                callback.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result=response.body().string();
                callback.onSuccess(result);
            }
        });
    }

    /**
     * 将map值用=和&连接在url地址后面
     * @param requestUrl
     * @param map
     * @return
     */
    public String getAppendUrl(String requestUrl, HashMap<String, Object> map) {
        if(map!=null &&  !map.isEmpty()){
            StringBuffer buffer=new StringBuffer();
            Iterator<Map.Entry<String,Object>> iterator=map.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<String,Object> entry=iterator.next();
                if (StringUtils.isEmpty(buffer.toString())){
                    buffer.append("?");
                }else{
                    buffer.append("&");
                }
                buffer.append(entry.getKey()+"="+entry.getValue());
            }
            requestUrl+=buffer;
        }
        return  requestUrl;
    }
}
