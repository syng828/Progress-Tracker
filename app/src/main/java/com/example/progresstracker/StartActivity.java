package com.example.progresstracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class StartActivity extends AppCompatActivity implements RecyclerViewInterface, View.OnClickListener {

   ArrayList<startModel> startModels = new ArrayList<>();

    ArrayList<Subject> subjects;

    Start_RecyclerViewAdapter adapter;

    public static final String SUBJECT_KEY = "Subject";

    Start start;

    FloatingActionButton addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        addBtn = findViewById(R.id.btnAdd);
        addBtn.setOnClickListener(this);

    start = (Start) getIntent().getSerializableExtra(MainActivity.START_KEY);

        subjects = start.getArrayList();

        RecyclerView recyclerView = findViewById(R.id.startRecyclrView);

        setUpStartModels();

        adapter = new Start_RecyclerViewAdapter(this, startModels, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setUpStartModels() {

      for (int i = 0; i < subjects.size(); i++) {
          startModels.add(new startModel(subjects.get(i).getName()));
      }
   }

   @Override        //Go in an activity
   public void onItemClick(int position) {
        Intent intent = new Intent(this, SubjectActivity.class);
        //intent.putExtra(SUBJECT_KEY, start.getArrayList().get(position));              //TODO: There seems to be an issue, probably because you don't have the position, and it only applies to the recyclerview
        startActivity(intent);
   }


   //TODO These two methods work, but the issue is seeing if it updated the actual array to be passed back
   @Override              //Remove activity
   public void onLongItemClick(final int position) {
        startModels.remove(position);
        start.deleteInArrayList(startModels.get(position).getSubjectName());     //check to see if this works
        adapter.notifyItemRemoved(position);
   }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnAdd ) {
            Toast.makeText(StartActivity.this, "Button Clicked", Toast.LENGTH_LONG).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Enter subject name");
            final EditText input = new EditText(this);
            builder.setView(input);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String subjectName = input.getText().toString();
                    start.addInArrayList(subjectName);                      //check if this works
                    startModels.add(new startModel(subjectName));
                }
            });
            builder.show();
        }
    }




}