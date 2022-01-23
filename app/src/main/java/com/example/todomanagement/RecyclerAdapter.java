package com.example.todomanagement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<User> taskList;
    private OnTaskListener mOnTaskListener;

    public RecyclerAdapter(ArrayList<User> taskList, OnTaskListener onTaskListener) {
        this.taskList = taskList;
        this.mOnTaskListener = onTaskListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView nameText;

        OnTaskListener onTaskListener;

        public MyViewHolder(@NonNull View view, OnTaskListener onTaskListener) {
            super(view);
            nameText = view.findViewById(R.id.textView3);
            this.onTaskListener = onTaskListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onTaskListener.onTaskClick(getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items,parent,false);
        return new MyViewHolder(itemView, mOnTaskListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        String name = taskList.get(position).getUsername();
        holder.nameText.setText(name);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public interface OnTaskListener{
        void onTaskClick(int position);
    }
}
