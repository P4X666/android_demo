package com.example.myviewpage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder> {
    private List<String> list = new ArrayList<>();
    private List<Integer> colors = new ArrayList<>();

    public ViewPagerAdapter (){
        list.add("1");
        list.add("2");
        list.add("3");
        colors.add(R.color.red);
        colors.add(R.color.green);
        colors.add(R.color.blue);
    }
    @NonNull
    @Override
    public ViewPagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewPagerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_page, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerViewHolder holder, int position) {
        holder.mTextView.setText(list.get(position));
//        之所以要使用 setBackgroundResource 而不是 setBackgroundColor,是因为colors里面装的是资源而不是指定的颜色的值
        holder.mRelativeLayout.setBackgroundResource(colors.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewPagerViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        RelativeLayout mRelativeLayout;
        public ViewPagerViewHolder(@NonNull View itemView) {
            super(itemView);
            mRelativeLayout = itemView.findViewById(R.id.container);
            mTextView = itemView.findViewById(R.id.tv);
        }
    }
}
