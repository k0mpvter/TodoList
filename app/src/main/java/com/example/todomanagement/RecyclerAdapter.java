package com.example.todomanagement;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private final OnTaskListener mOnTaskListener;
    private final DatabaseHandler databaseHandler;

    private int globalPosition;

    public RecyclerAdapter(Context context,OnTaskListener onTaskListener) {
        this.mOnTaskListener = onTaskListener;
        this.databaseHandler = new DatabaseHandler(context);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView titleView;
        private final TextView descView;
        private final TextView duedateView;
        private final CheckBox checkedView;

        OnTaskListener onTaskListener;

        public MyViewHolder(@NonNull View view, OnTaskListener onTaskListener) {
            super(view);

            databaseHandler.openDatabase();
            titleView = view.findViewById(R.id.title_view);
            descView = view.findViewById(R.id.description_view);
            duedateView = view.findViewById(R.id.date_view);
            checkedView =  (CheckBox) view.findViewById(R.id.checkbox_view);

            this.onTaskListener = onTaskListener;
            itemView.setOnClickListener(this);
            checkedView.setOnCheckedChangeListener((compoundButton, isChecked) -> {
                Log.d("ajslkdfjasldf","sldflasdjfasdf " + isChecked);
                Log.d("ajslkdfjasldf","sldflasdjfasdf " + globalPosition);
                Log.d("Checkbox", "check test");
                if (isChecked) {
                    databaseHandler.updateStatus(globalPosition, 1);
                } else {
                    databaseHandler.updateStatus(globalPosition, 0);
                }
            });
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
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        globalPosition = position;
        databaseHandler.openDatabase();
        ArrayList<Task> tasks = databaseHandler.getAllTasks();

        String title = tasks.get(position).getTitle();
        String desc = tasks.get(position).getDescription();
        String date = tasks.get(position).getDueDate();
        boolean checked = tasks.get(position).getIfChecked();

        holder.titleView.setText(title);
        holder.duedateView.setText(date);
        holder.checkedView.setChecked(checked);

        try{
            holder.descView.setText(desc);
        }
        catch (NullPointerException nullPointerException){
            //holder.descView.setText("No description");
        }

    }

    @Override
    public int getItemCount() {
        databaseHandler.openDatabase();
        return databaseHandler.getSize();
    }

    public interface OnTaskListener{
        void onTaskClick(int position);
    }
}
