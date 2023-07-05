package com.example.logindemo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.logindemo.R;
import com.example.logindemo.bean.LoginBean;
import com.example.logindemo.utils.APIConfig;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText  et_username;
    private EditText  et_password;
    @SuppressLint("HandlerLeak")
    private final Handler   handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what==1) {
                String re = (String) msg.obj;
                Toast.makeText(getApplicationContext(),"登录成功："+re,Toast.LENGTH_LONG).show();
                handler.postDelayed(() -> {
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    finish();
                },1000);
            } else {
                Toast.makeText(getApplicationContext(),"登录失败！密码、用户名错误,请重新输入",Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }
    private void initView() {
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        View btn_login = findViewById(R.id.btn_login);
        View btn_register = findViewById(R.id.btn_register);
        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btn_login){
            Login();
            return;
        }
        if(id == R.id.btn_register){
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
//            return;
        }
    }

    private void Login() {
        String username = et_username.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }
        String password = et_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        OkHttpClient client = new OkHttpClient();
        JSONObject object = new JSONObject();
        try {
            object.put("username",username);
            object.put("password",password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        MediaType mediaType = MediaType.Companion.parse("application/json;charset=utf-8");
        RequestBody requestBody = RequestBody.Companion.create(mediaType,object.toString());
        Request request = new Request.Builder()
                .url(APIConfig.BASE_URL+"/login")
                .post(requestBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.i("onFailure",e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String result = response.body().string();
                    Log.i("请求成功",result);
                    Gson gson = new Gson();
                    LoginBean loginBean = gson.fromJson(result,LoginBean.class);
                    String token = loginBean.getToken();
                    // 把登录信息 & token保存到本地
                    SharedPreferences.Editor editor = getSharedPreferences("token_data",MODE_PRIVATE).edit();
                    editor.putString("token",token);
                    editor.putString("username",username);
                    editor.putString("password",password);
                    editor.apply();
                    // 登录成功，跳转到主界面
                    Message msg = Message.obtain();
                    msg.what=1;
                    msg.obj = result;
                    handler.sendMessage(msg);
                }
            }
        });
    }
}