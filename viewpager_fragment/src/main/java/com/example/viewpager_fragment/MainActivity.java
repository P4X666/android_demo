package com.example.viewpager_fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ViewPager2 viewPager;
    View lv1,lv2,lv3,lv4;
    ImageView iv1,iv2,iv3,iv4;
    ArrayList<View> lvList = new ArrayList<>();
    ArrayList<ImageView> ivList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initPager();
        initTabView();
    }

    private void initTabView() {
//        找到容器id
        lv1 = findViewById(R.id.tab_index);
        lv2 = findViewById(R.id.tab_qh);
        lv3 = findViewById(R.id.tab_qc);
        lv4 = findViewById(R.id.tab_my);
//        绑定事件
        lv1.setOnClickListener(this);
        lv2.setOnClickListener(this);
        lv3.setOnClickListener(this);
        lv4.setOnClickListener(this);

        lvList.add(lv1);
        lvList.add(lv2);
        lvList.add(lv3);
        lvList.add(lv4);
//        找到图片id
        iv1 = findViewById(R.id.iv_index);
        iv2 = findViewById(R.id.iv_qh);
        iv3 = findViewById(R.id.iv_qc);
        iv4 = findViewById(R.id.iv_my);
        ivList.add(iv1);
        ivList.add(iv2);
        ivList.add(iv3);
        ivList.add(iv4);
//        默认第一个选中
        ivList.get(0).setSelected(true);
    }

    private void initPager() {
        viewPager = findViewById(R.id.view_pager);
        ArrayList<Fragment> fragments = new ArrayList<>();
//        之所以不用 new BlankFragment，是因为new BlankFragment只会执行一次，而newInstance则每次都会生成一个新的fragment
        fragments.add(BlankFragment.newInstance("点餐"));
        fragments.add(BlankFragment.newInstance("取号"));
        fragments.add(BlankFragment.newInstance("取餐"));
        fragments.add(BlankFragment.newInstance("我的"));
        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), getLifecycle(), fragments);
        viewPager.setAdapter(myFragmentPagerAdapter);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                changeTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    private void changeTab(int position) {
        viewPager.setCurrentItem(position);
        for (int i = 0; i < ivList.size(); i++) {
            boolean isSelected = i == position;
            ivList.get(i).setSelected(isSelected);
        }
    }

    @Override
    public void onClick(View v) {
        int position = lvList.indexOf(v);
        changeTab(position);
    }
}