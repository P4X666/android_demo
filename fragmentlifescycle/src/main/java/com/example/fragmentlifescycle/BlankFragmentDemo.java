package com.example.fragmentlifescycle;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 1.开始运行demo
 * The activity state---->onStart
 * The activity state---->onResume
 * 2.按下back键 返回 activity从stack中弹出
 * The activity state---->onPause
 * The activity state---->onStop
 * The activity state---->onDestroy
 * 3.再次启动demo
 * The activity state---->onStart
 * The activity state---->onResume
 * 4.按下了HOME键 当前TASK 处于后台状态，系统保存状态
 * The activity state---->onPause
 * The activity state---->onStop
 * 5.再次启动demo 回复原来的TASK activity在栈顶
 * The activity state---->onRestart
 * The activity state---->onStart
 * The activity state---->onResume
 */
public class BlankFragmentDemo extends Fragment {

    private final String TAG = "lifecycle";

    /** 与`Activity`绑定，因为Fragment创建完成后并不能单独使用  */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.e(TAG, "onAttach: ");
    }
    /** 通常做`bundle`相关的操作 */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: " );
    }

    /** 对ui进行操作 */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView: ");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank_demo, container, false);
    }

    /** fragment 变的可见 */
    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: " );
    }
    /** fragment 具备和用户交互的条件 */
    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: " );
    }
    /** fragment 暂停，不可见之前要调用该方法 */
    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: " );
    }
    /** fragment 停止了，变得不可见 */
    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: " );
    }
    /** fragment 销毁ui的时候调用 */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG, "onDestroyView: " );
    }
    /** fragment 销毁的时候调用 */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: " );
    }
//    fragment 和context分离的时候调用
    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TAG, "onDetach: " );
    }
}