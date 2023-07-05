package com.example.logindemo.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.logindemo.R;
import com.example.logindemo.bean.LoginBean;
import com.example.logindemo.utils.APIConfig;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private       EditText    et_name;
    private       EditText    et_nicke;
    private       EditText    et_phone;
    private       EditText    et_password;
    private       RadioButton rb_man;
    private       RadioGroup  rg_sex;
    private       Button      btn_register;
    private       Button      btn_login;
    private       String      sex     = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }
    @SuppressLint("HandlerLeak")
    private final Handler    handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                String result = msg.obj.toString();
                Gson gson = new Gson();
                LoginBean loginBean = gson.fromJson(result, LoginBean.class);
                String successCode = loginBean.getCode();
                if (successCode.equals("200")) {
                    Log.e("api", "success");
                    Toast.makeText(getApplicationContext(), "注册成功：" + result + "\n立马去登陆", Toast.LENGTH_LONG).show();
                    handler.postDelayed(() -> {
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }, 2000);
                }
            }
        }
    };

    private void initView() {
        et_name = findViewById(R.id.et_name);
        et_nicke = findViewById(R.id.et_nicke);
        et_phone = findViewById(R.id.et_phone);
        et_password = findViewById(R.id.et_password);
        rb_man = findViewById(R.id.rb_man);
//        RadioButton rb_woman = (RadioButton) findViewById(R.id.rb_woman);
        rg_sex = findViewById(R.id.rg_sex);
        btn_register = findViewById(R.id.btn_register);
        btn_login = findViewById(R.id.btn_login);

        btn_register.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        rg_sex.setOnCheckedChangeListener((radioGroup, i) -> sex = i == rb_man.getId() ? "1" : "0");

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_register) {
            Register();
            return;
        }
        if (id == R.id.btn_login) {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void Register() {
        String name = et_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }
        String nicke = et_nicke.getText().toString().trim();
        if (TextUtils.isEmpty(nicke)) {
            Toast.makeText(this, "请输入昵称", Toast.LENGTH_SHORT).show();
            return;
        }
        String phone = et_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入你的手机号码", Toast.LENGTH_SHORT).show();
            return;
        }
        String password = et_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(sex)) {
            Toast.makeText(this, "请选择您的性别", Toast.LENGTH_SHORT).show();
            return;
        }
        OkHttpClient client = new OkHttpClient();
        JSONObject object = new JSONObject();
        try {
            object.put("userName", name);
            object.put("nickName", nicke);
            object.put("phonenumber", phone);
            object.put("sex", sex);
            object.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, object.toString());
        Request request = new Request.Builder()
                .url(APIConfig.BASE_URL + "/system/user/register")
                .post(requestBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.i("onFailure", e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String result = response.body().string();
                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj = result;
                    handler.sendMessage(msg);
                }
            }
        });
    }


}