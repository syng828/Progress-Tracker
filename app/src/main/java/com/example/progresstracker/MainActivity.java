package com.example.progresstracker;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, Serializable {
    TextView title;
    Button btnOpenSave, btnCreate, btnDeleteSave, btnGetAllNames;

    final static public String START_KEY = "Starting";
    final static public String FILE_NAME_KEY = "FileName";

    Start updatedStart;
    String inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.txtTitle);
        btnOpenSave = findViewById(R.id.btnopenSave);
        btnOpenSave.setOnClickListener(this);
        btnDeleteSave = findViewById(R.id.deleteSave);
        btnDeleteSave.setOnClickListener(this);
        btnCreate = findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(this);
        btnGetAllNames = findViewById(R.id.getAllNames);

        btnGetAllNames.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String displayText = "";
                File internalStorage = new File(getFilesDir().getAbsolutePath());
                File [] files = internalStorage.listFiles();
                for (File allFiles: files) {
                    if(allFiles.getName().endsWith(".ser"))
                    displayText+= "       " + allFiles.getName() + "\n";
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Saves:");
                final TextView names = new TextView(v.getContext());
                names.setText(displayText);
                builder.setView(names);
                builder.show();
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
                            FileInputStream fis = openFileInput(inputText + ".ser");
                            ObjectInputStream objectIn = new ObjectInputStream(fis);

                            Start read = (Start) objectIn.readObject();

                            Intent intent = new Intent(MainActivity.this, StartActivity.class);
                            intent.putExtra(START_KEY, read);
                            intent.putExtra(FILE_NAME_KEY, inputText);
                            startActivity(intent);
                            fis.close();

                        } catch (IOException | ClassNotFoundException e) {
                            Toast.makeText(MainActivity.this, "Cannot find the file.", Toast.LENGTH_LONG).show();
                        }
                        break;

                    case (R.id.btnCreate):
                        try {
                            FileOutputStream fos = openFileOutput(inputText + ".ser", MODE_PRIVATE);
                            ObjectOutputStream objectOut = new ObjectOutputStream(fos);

                            Start read = new Start();
                            objectOut.writeObject(read);

                            Intent intent2 = new Intent(MainActivity.this, StartActivity.class);
                            intent2.putExtra(START_KEY, read);
                            intent2.putExtra(FILE_NAME_KEY, inputText);
                            startActivity(intent2);
                            fos.close();
                        }
                        catch (IOException e) {
                            Toast.makeText(MainActivity.this, "Cannot create the file.", Toast.LENGTH_LONG).show();
                        }
                        break;

                    case (R.id.deleteSave):
                            File delete = new File(getFilesDir(), inputText + ".ser");
                            if (delete.delete())
                                Toast.makeText(MainActivity.this, "File deleted", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(MainActivity.this, "Unable to delete the file", Toast.LENGTH_LONG).show();

                }

            }
        });
        builder.show();
    }
}
