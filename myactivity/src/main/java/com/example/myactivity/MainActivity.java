package com.example.myactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button link = findViewById(R.id.link);
        link.setOnClickListener((View view)->{
            startActivity2(view);
        });
    }

    public void startActivity2(View view){
        startActivity(new Intent(this, MainActivity2.class));
    }
}