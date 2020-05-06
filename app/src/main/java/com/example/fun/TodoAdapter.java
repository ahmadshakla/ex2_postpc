package com.example.fun;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Todo> todoItems = new ArrayList<>();



    public TodoAdapter(Context context) {
        this.todoItems = new ArrayList<>();
        this.context = context;


    }

    public void setTodo(ArrayList<Todo> old) {
        todoItems.clear();
        todoItems.addAll(old);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_layout, parent, false);

        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final Todo todoItem = todoItems.get(position);
        final int taskNum = position + 1;
        if (todoItem.textView == null) {
            todoItem.textView = new TextView(context);
            todoItem.textView.setText(holder.textView.getText());
            todoItem.textView.setAlpha(holder.textView.getAlpha());
        }
        holder.textView.setText(taskNum + "- " + todoItem.getTodoText());
        holder.textView.setAlpha(todoItem.textView.getAlpha());
        if (todoItem.isClicked()) {
            holder.textView.setAlpha(0.4f);
        }
        else {
            holder.textView.setAlpha(1f);
        }

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!todoItem.isClicked()) {
                    todoItem.setClicked();
                    Toast.makeText(context, "TODO " + todoItem.getTodoText() + " is now DONE. BOOM!",
                            Toast.LENGTH_SHORT).show();
                    holder.textView.setAlpha(0.4f);
                    todoItem.textView.setAlpha(holder.textView.getAlpha());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return todoItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
