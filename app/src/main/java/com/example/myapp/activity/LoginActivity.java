package com.example.myapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.icu.util.LocaleData;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.os.TokenWatcher;
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
import com.example.myapp.entity.LoginResponse;
import com.example.myapp.util.AppConfig;
import com.example.myapp.util.StringUtils;
import com.google.gson.Gson;

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
    protected int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        editUser=findViewById(R.id.edt_user);
        editPwd=findViewById(R.id.edt_pwd);
        btnLogin=findViewById(R.id.btn_login_in);

    }

    @Override
    protected void initData() {
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
        
//        用接口登录部分
//
//        HashMap<String,Object> params=new HashMap();
//        params.put("mobile",user);
//        params.put("password",pwd);
//        Api.config(ApiConfig.LOGIN, params).postRequest(new TtitCallback() {
//            @Override
//            public void onSuccess(String res) {
//                Log.e("onSuccess",res);
////                showToastSync(res);
//                Gson gson=new Gson();
//                LoginResponse loginResponse=gson.fromJson(res,LoginResponse.class);
//                if(loginResponse.getCode()==0){
//                    String token=loginResponse.getToken();
////                    SharedPreferences sp=getSharedPreferences("sp_ttit",MODE_PRIVATE);
////                    SharedPreferences.Editor editor=sp.edit();
////                    editor.putString("token", token);
////                    editor.apply();
//                    saveStringTosp("token",token);
//                    showToastSync("登录成功");
//
//                }else{
//                    showToastSync("登录失败");
//                }
//
//
//            }
//
//            @Override
//            public void onFailure(Exception e) {
//                Log.e("failed",e.getMessage());
//            }
//        });

//        应该放在登录成功那个位置
        navigateTo(HomeActivity.class);


    }
}