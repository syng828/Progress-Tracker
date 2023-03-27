package com.example.progresstracker;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class StartActivity extends AppCompatActivity implements RecyclerViewInterface, View.OnClickListener {

    protected static final String SUBJECT_KEY = "Subject";


    ArrayList<Subject> subjects;
    Start_RecyclerViewAdapter adapter;

    String fileName;
    Start start;
    Subject updatedSubject;

    FloatingActionButton addBtn;
    Button saveBtn;

    int toReplacePosition;

    //allows to return back with updated value
    ActivityResultLauncher<Intent> activityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == 10) {
                        Intent intent = result.getData();

                        if (intent != null) {
                            updatedSubject = (Subject)intent.getSerializableExtra("Back");
                            subjects.set(toReplacePosition, updatedSubject); //replaces info in previously clicked value
                        }
                    }
                }
            }
    );



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //sets up a back button

        addBtn = findViewById(R.id.addSubjectBtn);
        addBtn.setOnClickListener(this);
        saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(this);

        start = (Start) getIntent().getSerializableExtra(MainActivity.START_KEY);
        fileName = getIntent().getStringExtra(MainActivity.FILE_NAME_KEY);

        setTitle(fileName);

        RecyclerView recyclerView = findViewById(R.id.startRecyclerView);

       setUpSubjects();

       adapter = new Start_RecyclerViewAdapter(this, subjects, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

     private void setUpSubjects() {
        subjects = start.getArrayList();
   }

     @Override
   public void onClick(View v) {
        if (v.getId() == R.id.addSubjectBtn) {     //ADDS IN A SUBJECT
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Enter subject name");
            final EditText input = new EditText(this);
            builder.setView(input);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String subjectName = input.getText().toString();
                    subjects.add(new Subject(subjectName));
                }
            });
            builder.show();
        }
        if (v.getId() == R.id.saveBtn) {     //SAVES TO THE FILE
            Toast.makeText(this, "Button works", Toast.LENGTH_LONG).show();
            try {
                FileOutputStream fos = openFileOutput(fileName+ ".ser", MODE_PRIVATE);
                ObjectOutputStream oos = new ObjectOutputStream(fos);

                oos.writeObject(start);
                fos.close();
            }
            catch (IOException e) {
                Toast.makeText(this, "Unable to save to file", Toast.LENGTH_LONG).show();
            }
        }
    }


   @Override        //Go in a subject
   public void onItemClick(int position) {
        toReplacePosition = position; //makes sure to replace after going back
        Intent intent = new Intent(this, SubjectActivity.class);
        intent.putExtra(SUBJECT_KEY, subjects.get(position));
        activityLauncher.launch(intent);
   }


   @Override              //Remove subject
   public void onLongItemClick(final int position) {
        subjects.remove(position);
        adapter.notifyItemRemoved(position);
   }

   @Override
    public boolean onOptionsItemSelected(MenuItem item) { //goes back to prev activity
        switch(item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
   }
}