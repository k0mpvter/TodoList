package com.example.todomanagement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private TaskManager tasks;
    private OnTaskListener mOnTaskListener;

    public RecyclerAdapter(TaskManager tasks, OnTaskListener onTaskListener) {
        this.tasks = tasks;
        this.mOnTaskListener = onTaskListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView titleView;
        private TextView descView;
        private TextView duedateView;
        private CheckBox checkedView;

        OnTaskListener onTaskListener;

        public MyViewHolder(@NonNull View view, OnTaskListener onTaskListener) {
            super(view);
            titleView = view.findViewById(R.id.title_view);
            descView = view.findViewById(R.id.description_view);
            duedateView = view.findViewById(R.id.date_view);
            checkedView =  (CheckBox) view.findViewById(R.id.checkbox_view);


            //CheckBox mCheckBox = (CheckBox) view.findViewById(R.id.checkbox_view);
            //mCheckBox.setChecked(true);

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
        String title = tasks.get(position).getTitle();
        String desc = tasks.get(position).getDescription();
        String date = tasks.get(position).getDueDate().toString();
        boolean checked = tasks.get(position).getIfChecked();

        holder.titleView.setText(title);
        holder.duedateView.setText(date);

        try{
            holder.descView.setText(desc);
        }
        catch (NullPointerException nullPointerException){
            //holder.descView.setText("No description");
        }
        //holder.checkedView.setSelected(checked);
        holder.checkedView.setSelected(true);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public interface OnTaskListener{
        void onTaskClick(int position);
    }
}
