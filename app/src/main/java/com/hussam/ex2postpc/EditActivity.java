package com.hussam.ex2postpc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {
    TextView text;
    Button markAsDoneButton, applyButton;
    private static final String markAsDone = "Mark As Done";
    private static final String applyChange = "Apply Changes";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        text = findViewById(R.id.edit_activity_text);
        markAsDoneButton = findViewById(R.id.markButton);
        applyButton = findViewById(R.id.editButton);
        markAsDoneButton.setText(markAsDone);
        applyButton.setText(applyChange);
        final Intent intent = getIntent();
        markAsDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("mark", true);
                setResult(RESULT_OK, intent);
                finish();

            }
        });
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = text.getText().toString();
                if (!txt.equals("")) {
                    intent.putExtra("newText", text.getText().toString());
                    intent.putExtra("edited", true);
                    setResult(RESULT_OK, intent);
                    text.setText("");
                }else{
                    Toast toast = Toast.makeText((TodoBoomApp)getApplicationContext(),
                            "You cant change a todo into empty task", Toast.LENGTH_SHORT);

                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });




    }
}
