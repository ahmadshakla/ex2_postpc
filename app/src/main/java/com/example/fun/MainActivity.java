package com.example.fun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private  ArrayList<Todo> todos = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RecyclerView recyclerView = findViewById(R.id.recycle_view);
        final Context context = this;
        final TextView insertion = findViewById(R.id.editText2);
        final Button button = findViewById(R.id.button);
        final TodoAdapter todoAdapter = new TodoAdapter(context);
        if (savedInstanceState != null){
            ArrayList<String> tasks = savedInstanceState.getStringArrayList("todos");
            boolean[] clicked = savedInstanceState.getBooleanArray("clicked");
            for (int i=0;i<tasks.size();i++)
            {
                todos.add(new Todo(tasks.get(i),clicked[i]));
                todoAdapter.setTodo(todos);
                recyclerView.setAdapter(todoAdapter);
                todoAdapter.notifyDataSetChanged();
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            }
        }

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String message = insertion.getText().toString();
                    if (message.length() != 0 && !message.matches(" *")) {
                        insertion.setText("");
                        todos.add(new Todo(message, false));
                        todoAdapter.setTodo(todos);
                        recyclerView.setAdapter(todoAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    } else {
                        Toast.makeText(context, "You must write something!", Toast.LENGTH_SHORT).show();

                    }

                }
            });


    }
//
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        ArrayList<String> tasks = new ArrayList<>();
        boolean[] clicked = new boolean[todos.size()];
        int i = 0;
        for (Todo todo:todos){
            tasks.add(todo.getTodoText());
            clicked[i] = todos.get(i).isClicked();
            i++;
        }
        outState.putStringArrayList("todos",tasks);
        outState.putBooleanArray("clicked",clicked);
        super.onSaveInstanceState(outState);
    }
//
//    @Override
//    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        textView.setText(savedInstanceState.getString("my_text"));
//    }
}
