package com.gaohui.coordinatorlayoutfix;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> dataList;

    public RecyclerViewAdapter(ArrayList<String> list) {
        dataList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_text, viewGroup, false);
        return new SimpleTextViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((TextView)viewHolder.itemView.findViewById(R.id.textView)).setText(dataList.get(i));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class SimpleTextViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public SimpleTextViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }

    }
}
