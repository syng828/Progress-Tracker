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
import java.util.Objects;

public class TopicActivity extends AppCompatActivity implements TRecyclerViewInterface, View.OnClickListener {

    protected static final String QUESTION_KEY = "Question";

    ArrayList<Question> questions = new ArrayList<>();
    Topic_RecyclerViewAdapter adapter;
    Topic topic;

    FloatingActionButton addQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        addQuestion = findViewById(R.id.addQuestionBtn);
        addQuestion.setOnClickListener(this);

        //retrieves info from subjectActivity
        topic = (Topic) getIntent().getSerializableExtra(SubjectActivity.TOPIC_KEY);
        setTitle(topic.getName());

        setUpTopicModels();

        RecyclerView recyclerView = findViewById(R.id.topicRecyclerView);
        adapter = new Topic_RecyclerViewAdapter(this, questions, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public void setUpTopicModels() { questions = topic.getArrayList();}

    @Override

    //TODO: Unfortunately don't know how to get two alertdialogs, like i say you can do one after the other but show doesn't work
    public void onClick(View v) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter your question");
        final EditText inputQuestion = new EditText(this);
        final EditText inputAnswer = new EditText(this);
        builder.setView(inputQuestion);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                String q = inputQuestion.getText().toString();
            }
        });
        builder.show();

    }

    @Override //removing
    public void onLongItemClick(int position) {
        questions.remove(position);
        adapter.notifyItemRemoved(position);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //goes back to prev activity
        switch(item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, SubjectActivity.class);
                intent.putExtra("BackTopic", topic);
                setResult(20, intent);

                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}