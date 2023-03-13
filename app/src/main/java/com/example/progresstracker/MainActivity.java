package com.example.progresstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, Serializable {
    TextView title;
    Button btnOpenSave;
    Button btnCreate;
    Button btnTesting;  //@TEST

    private String inputText = "";
    final static public String START_KEY = "Starting";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.txtTitle);

        btnOpenSave = findViewById(R.id.btnopenSave);
        btnOpenSave.setOnClickListener(this);
        btnCreate = findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(this);
        btnTesting = findViewById(R.id.testBtn); //@TEST

        Start test = new Start();
        test.addInArrayList("Math");
        test.addInArrayList("Science");
        btnTesting.setOnClickListener(new View.OnClickListener() {   //@TEST
            @Override
            public void onClick(View v) {
                Intent intentTest = new Intent(MainActivity.this, StartActivity.class) ;
                intentTest.putExtra(START_KEY, test);
                startActivity(intentTest);
            }
        });
    }

    @Override
    public void onClick(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter file name");
        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                inputText = input.getText().toString();

                switch (view.getId()) {
                    case (R.id.btnopenSave):
                        try {
                            FileInputStream fis = openFileInput(inputText + ".txt");
                            ObjectInputStream objectIn = new ObjectInputStream(fis);
                            Start read = (Start) objectIn.readObject();

                            objectIn.close();
                            //TODO then open the window, pass the data back to overwrite it

                            Intent intent = new Intent(MainActivity.this, StartActivity.class);
                            intent.putExtra(START_KEY, read);
                            startActivity(intent);

                            FileOutputStream fos = openFileOutput(inputText+ ".txt", MODE_PRIVATE); //Rewrites the file
                            ObjectOutputStream objectOut = new ObjectOutputStream(fos);

                            objectOut.writeObject(read);
                            objectOut.close();
                            Toast.makeText(MainActivity.this, "File Overwritten.", Toast.LENGTH_LONG).show();

                        } catch (IOException | ClassNotFoundException e) {
                            Toast.makeText(MainActivity.this, "Cannot find the file.", Toast.LENGTH_LONG).show();
                        }
                        break;

                    case (R.id.btnCreate):
                        try {
                            FileOutputStream fos = openFileOutput(inputText + ".txt", MODE_PRIVATE);
                            ObjectOutputStream oos = new ObjectOutputStream(fos);
                            Start read = new Start();
                            //TODO Then move to the start page
                            Intent intent2 = new Intent(MainActivity.this, StartActivity.class);
                            intent2.putExtra(START_KEY, read);
                            startActivity(intent2);
                            //TODO Then retrieve info


                            oos.writeObject(read);
                            Toast.makeText(MainActivity.this, "Successfully written into a file to" + getFilesDir(), Toast.LENGTH_LONG).show();

                            fos.close();

                        } catch (IOException e) {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                        break;
                }

            }
        });
        builder.show();


        }
    }

