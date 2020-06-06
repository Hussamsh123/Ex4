package com.hussam.ex2postpc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DeleteActivity extends AppCompatActivity {
    private Button deleteButton, unMarkButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        deleteButton = findViewById(R.id.deleteButton);
        unMarkButton = findViewById(R.id.unMarkButton);
        final Intent intent = getIntent();
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("delete", true);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        unMarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("unMark", true);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
