package com.example.myapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.util.LocaleData;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.textclassifier.ConversationActions;
import android.view.textclassifier.TextClassification;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapp.R;
import com.example.myapp.api.Api;
import com.example.myapp.api.ApiConfig;
import com.example.myapp.api.TtitCallback;
import com.example.myapp.util.AppConfig;
import com.example.myapp.util.StringUtils;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends BaseActivity {

    private EditText editUser;
    private EditText editPwd;
    private Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editUser=findViewById(R.id.edt_user);
        editPwd=findViewById(R.id.edt_pwd);
        btnLogin=findViewById(R.id.btn_login_in);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=editUser.getText().toString().trim();
                String pwd=editPwd.getText().toString().trim();
                loginIn(user,pwd);
            }
        });

    }

    private void loginIn(String user, String pwd) {

        if(StringUtils.isEmpty(user)){
            showToast("请输入用户名");
            return;
        }
        if(StringUtils.isEmpty(pwd)){
            showToast("请输入密码");
            return;
        }

        HashMap<String,Object> params=new HashMap();
        params.put("mobile",user);
        params.put("password",pwd);
        Api.config(ApiConfig.LOGIN, params).postRequest(new TtitCallback() {
            @Override
            public void onSuccess(String res) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showToast(res);
                    }
                });
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("failed",e.getMessage());
            }
        });



//        //第一步获取okHttpClient对象
//        OkHttpClient client = new OkHttpClient.Builder()
//                .build();
//        //第二步构建Request对象
//        Map map=new HashMap();
//        map.put("mobile",user);
//        map.put("password",pwd);
//        JSONObject jsonObject=new JSONObject(map);
//        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),jsonObject.toString());
//
//        Request request=new Request.Builder()
//                .url(AppConfig.BASE_URL+"/login")
//                .addHeader("Content-Type","application/json;charset=UTF-8")
//                .post(requestBody)
//                .build();
//
//        final Call call=client.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.e("onfailure","failed");
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String result=response.body().string();
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        showToast(result);
//                    }
//                });
//
//            }
//        });

    }
}