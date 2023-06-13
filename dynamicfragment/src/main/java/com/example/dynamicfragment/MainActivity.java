package com.example.dynamicfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
            replaceFragment(new BlankFragment1());
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