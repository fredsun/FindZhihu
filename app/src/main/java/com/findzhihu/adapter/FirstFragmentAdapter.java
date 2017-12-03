package com.findzhihu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.findzhihu.R;

import java.util.ArrayList;

/**
 * Created by fred on 2017/10/31.
 */

//2. 再把 DemoAdapter.ViewHolder 填进 RecyclerView.Adapter<>
public class FirstFragmentAdapter extends RecyclerView.Adapter<FirstFragmentAdapter.ViewHolder> {
    private ArrayList<String> datas;

    //3. 最后再重写onCreateViewHolder 和 onBindViewHolder 方法
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_first_fragment, parent, false);
        final ViewHolder holder =  new ViewHolder(view);
//        holder.itemText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(), "you click text", Toast.LENGTH_LONG).show();
//            }
//        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemText.setText(datas.get(position)+"");
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    //1. 先写viewHolder
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView itemText;
        public ViewHolder(View itemView) {
            super(itemView);
            itemText = itemView.findViewById(R.id.tv_item);
        }
    }

    public FirstFragmentAdapter(ArrayList<String> datas) {
        this.datas = datas;
    }
}
