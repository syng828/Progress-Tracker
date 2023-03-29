package com.example.progresstracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

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

    public void onClick(View v) {

        //creates a layout of the alert message
        Context context = v.getContext();
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText inputQuestion = new EditText(context);
        inputQuestion.setHint("Question");
        layout.addView(inputQuestion);

        final EditText inputAnswer = new EditText(context);
        inputAnswer.setHint("Answer");
        layout.addView(inputAnswer);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(layout);
        builder.setTitle("Enter your question and answer");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                String q = inputQuestion.getText().toString();
                String a = inputAnswer.getText().toString();
                questions.add(new Question(q,a));
                adapter.notifyDataSetChanged();
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