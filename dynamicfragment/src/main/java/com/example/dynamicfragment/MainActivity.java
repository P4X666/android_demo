package com.example.dynamicfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1 = findViewById(R.id.btn);
        btn1.setOnClickListener(this);
        Button btn2 = findViewById(R.id.btn1);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn){
            Bundle bundle = new Bundle();
            bundle.putString("message", "传入fragment的值");
            BlankFragment1 fragment = new BlankFragment1();
            fragment.setArguments(bundle);
            fragment.setiFragmentCallback(new IFragmentCallback() {
                @Override
                public void sendMessage2Activity(String string) {
                    Toast.makeText(MainActivity.this, string, Toast.LENGTH_LONG).show();
                }

                @Override
                public String getMessageFromActivity() {
                    return "activity";
                }
            });
            replaceFragment(fragment);
        }else {
            replaceFragment(new BlankFragment2());
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
//        开始事务
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        替换当前ui
        fragmentTransaction.replace(R.id.frameLayout, fragment);
//        添加到路由栈，类似与history与组件之间的关联
        fragmentTransaction.addToBackStack(null);
//        提交事务
        fragmentTransaction.commit();
    }
}