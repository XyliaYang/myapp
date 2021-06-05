package com.example.myapp.activity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.myapp.R;
import com.example.myapp.api.Api;
import com.example.myapp.api.ApiConfig;
import com.example.myapp.api.TtitCallback;
import com.example.myapp.entity.LoginResponse;
import com.example.myapp.util.StringUtils;
import com.google.gson.Gson;
import java.util.HashMap;

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
        HashMap<String,Object> params=new HashMap();
        params.put("mobile",user);
        params.put("password",pwd);
        Api.config(ApiConfig.LOGIN, params).postRequest(new TtitCallback() {
            @Override
            public void onSuccess(String res) {
                Log.e("onSuccess",res);
//                showToastSync(res);
                Gson gson=new Gson();
                LoginResponse loginResponse=gson.fromJson(res,LoginResponse.class);
                if(loginResponse.getCode()==0){
                    String token=loginResponse.getToken();
                    showToastSync("登录成功");

                }else{
                    showToastSync("登录失败");
                }
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("failed",e.getMessage());
            }
        });

//        应该放在登录成功那个位置
        navigateTo(HomeActivity.class);
    }
}