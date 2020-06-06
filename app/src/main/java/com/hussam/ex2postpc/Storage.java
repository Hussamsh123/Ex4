package com.hussam.ex2postpc;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static java.util.Collections.*;

class Storage {
    private int nextId = 0;
    private SharedPreferences sp;

    ArrayList<Item> todoList;
    ArrayList<Item> temp;
    Storage(Context context) {
        todoList = new ArrayList<>();
        temp = new ArrayList<>();
        sp  = PreferenceManager.getDefaultSharedPreferences(context);
        FirebaseFirestore.getInstance().collection("todoboom")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                Item item = document.toObject(Item.class);
                            }
                        }
                    }
                });
        retrieveData();
    }

     void createItem(String str) {
         SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");

        Item newItem = new Item(str, false, nextId, simpleDateFormat.format(new Date()), simpleDateFormat.format(new Date()));
        todoList.add(newItem);
        FirebaseFirestore.getInstance().collection("todoboom").document(newItem.getID())
                .set(newItem);
        saveData();

     }

    void deleteItem(int position) {
        Item item =todoList.get(position);
        todoList.remove(position);
        FirebaseFirestore.getInstance().collection("todoboom").document(item.getID())
                .delete();
        saveData();
    }

    void editItem(int position) {
        Item item = todoList.get(position);
        FirebaseFirestore.getInstance().collection("todoboom").document(String.valueOf(item.getID()))
                .set(item);
        saveData();
    }
    void saveData(){
        Gson gson = new Gson();
        String json = gson.toJson(todoList);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("save", json).apply();
    }
    void retrieveData(){
        Gson gson = new Gson();
        String json = sp.getString("save", null);
        Type type = new TypeToken<ArrayList<Item>>(){}.getType();
        todoList = gson.fromJson(json, type);
        if(todoList == null){
            todoList = new ArrayList<>();
        }
    }
}
