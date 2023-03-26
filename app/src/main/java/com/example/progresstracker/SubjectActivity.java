package com.example.progresstracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class SubjectActivity extends AppCompatActivity implements RecyclerViewInterface, View.OnClickListener {

    protected static final String TOPIC_KEY = "Topic";

    ArrayList<Topic> topics = new ArrayList<>();
    Subject_RecyclerViewAdapter adapter;

    Subject subject;
    FloatingActionButton addTopic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //sets up back button

        addTopic = findViewById(R.id.addTopicBtn);
        addTopic.setOnClickListener(this);

        //retrieves information from startActivity
        subject = (Subject) getIntent().getSerializableExtra(StartActivity.SUBJECT_KEY);
        setTitle(subject.getName());

        RecyclerView recyclerView = findViewById(R.id.subjectRecyclerView);

        setUpSubjectModels();

        adapter = new Subject_RecyclerViewAdapter(this, topics, this);
         recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

        private void setUpSubjectModels() {
              topics = subject.getArrayList();
        }

    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter subject name");
        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String topicName = input.getText().toString();
                topics.add(new Topic(topicName));
            }
        });
        builder.show();
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, SubjectActivity.class);
        intent.putExtra(TOPIC_KEY, topics.get(position));
        startActivity(intent);
    }

    @Override
    public void onLongItemClick(int position) {
        topics.remove(position);
        adapter.notifyItemRemoved(position);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //goes back to prev activity
        switch(item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("Back", subject);
                setResult(10, intent);

                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    }
