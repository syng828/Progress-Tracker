package com.example.progresstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class StartActivity extends AppCompatActivity {

    public static final String FINISH_KEY = "Finish";
    public static final String SUBJECT_KEY = "Subject";

    TextView textView;
    Button quitBtn;
    Button btnGoIn;
    Button btnAdd;
    EditText input;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Start start = (Start) getIntent().getSerializableExtra(MainActivity.START_KEY);


        textView = findViewById(R.id.textView);
        input = findViewById(R.id.subjectEdtTxt);
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String in = input.getText().toString();
                start.addInArrayList(in);
                Toast.makeText(StartActivity.this, "Button works", Toast.LENGTH_LONG).show();
                textView.setText(start.getArrayListNames());
            }
        });

        quitBtn = findViewById(R.id.quitBtn);
        quitBtn.setOnClickListener(new View.OnClickListener() {   //QUITS and go back to file selection
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                intent.putExtra(FINISH_KEY, start);
                startActivity(intent);
            }
        });
        btnGoIn = findViewById(R.id.btnGoIn);
        btnGoIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subjectName = input.getText().toString();
                int checkName = start.goIn(subjectName);
                Subject subject;

                if (checkName!= -1) {
                    subject = start.getInstance(checkName);
                    Intent intent = new Intent(StartActivity.this, SubjectActivity.class);
                    intent.putExtra(SUBJECT_KEY, subject);
                    startActivity(intent);
                }
                else {
                    //Just make text subject not found
                }


            }
        });


        textView.setText(start.getArrayListNames());


        /*  Psuedocode:
        Subject subject = (Subject) getIntent().getSerializableExtra();
        Now that subject check it with the current array and if it has the same name, replace the old one with this one
                     */

    }
}