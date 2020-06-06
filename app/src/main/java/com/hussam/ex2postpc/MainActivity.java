package com.hussam.ex2postpc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    private static final String MSG = "TODO list size is: ";
    private static final String TAG = "todoboom";
    RecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    TodoBoomApp myApp;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        myApp = (TodoBoomApp) getApplicationContext();
        final ArrayList<Item> todoList = myApp.storage.todoList;
        super.onCreate(savedInstanceState);
        Log.i(TAG, MSG + todoList.size());
        setContentView(R.layout.activity_main);
        adapter = new RecyclerViewAdapter(new ArrayList<Item>(), this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        adapter.updateData(todoList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(myApp));
        final Button CreateButton = findViewById(R.id.CreateButton);
        CreateButton.setText("create");
        final EditText TextEditor = findViewById(R.id.TextEditor);
        CreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String msg = TextEditor.getText().toString();
                if (msg.length() == 0) {
                    Toast toast = Toast.makeText(myApp, "In order to do add something you need to ACTUALLY" +
                            " ADD SOMETHING", Toast.LENGTH_SHORT);

                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else {
                    myApp.storage.createItem(msg);
                    TextEditor.setText("");
                    adapter.updateData(todoList);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    recyclerView.setLayoutManager(new LinearLayoutManager(myApp));
                }

            }
        });
    }

    TodoBoomApp getTodoApp() {
        return (TodoBoomApp) getApplicationContext();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            assert data != null;
            int position = data.getIntExtra("position", -1);
            if (requestCode == 555) {
                if (data.getBooleanExtra("edited", false)) {
                    String newTask = data.getStringExtra("newText");
                    adapter.editName(position, newTask);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
                    myApp.storage.todoList.get(position).setEditTime(simpleDateFormat.format(new Date()));
                    myApp.storage.editItem(position);

                }
                if (data.getBooleanExtra("mark", false)) {
                    adapter.markTask(position);
                    myApp.storage.editItem(position);

                }
            } else {
                if (data.getBooleanExtra("delete", false)) {
                    adapter.deleteTodo(position);
                    myApp.storage.deleteItem(position);
                } else if (data.getBooleanExtra("unMark", false)) {
                    adapter.unMarkTask(position);
                    myApp.storage.editItem(position);

                }

            }
            adapter.updateData(myApp.storage.todoList);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            recyclerView.setLayoutManager(new LinearLayoutManager(myApp));
        }
    }

    void deleteActivity(int position) {
        Intent intent = new Intent(this, DeleteActivity.class);
        intent.putExtra("position", position);
        startActivityForResult(intent, 666);

    }

    void editActivity(int position) {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("position", position);
        startActivityForResult(intent, 555);
    }


}

