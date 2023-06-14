package com.example.dynamicfragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class BlankFragment1 extends Fragment {
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        String str = bundle != null ? bundle.getString("message") : "";
        Log.e("TAG", "onCreate: " + str );
    }

    private IFragmentCallback iFragmentCallback;

    public void setiFragmentCallback(IFragmentCallback iFragmentCallback) {
        this.iFragmentCallback = iFragmentCallback;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(R.layout.fragment_blank1, container, false);
        }
        Button btn = view.findViewById(R.id.btn3);
        btn.setOnClickListener((View v)->{
//            iFragmentCallback.sendMessage2Activity("fragment 传递给 activity");
            String string = iFragmentCallback.getMessageFromActivity();
            Toast.makeText(getContext(), string, Toast.LENGTH_LONG).show();
        });
        return view;
    }
}