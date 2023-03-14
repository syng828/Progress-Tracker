package com.example.progresstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//TODO: Don't know why there's an issue here but will fix next time create recycler view for this class.
public class SubjectActivity extends AppCompatActivity {

    Button btnBack;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        TextView textView = findViewById(R.id.subjectText);

        btnBack = findViewById(R.id.btnBack);

        Subject subject = (Subject) getIntent().getSerializableExtra("SUBJECT_KEY");
        String subjectName = subject.getName();

        textView.setText(subjectName);

        //Just goes back for now, but will make it so that it can have it be updated back
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubjectActivity.this, StartActivity.class);
                startActivity(intent);
            }
        });
    }
}