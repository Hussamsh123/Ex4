package com.hussam.ex2postpc;

import android.app.Application;

import java.util.ArrayList;


public class TodoBoomApp extends Application {
    public Storage storage;

    @Override
    public void onCreate() {
        super.onCreate();
        storage = new Storage(this);
    }
}
