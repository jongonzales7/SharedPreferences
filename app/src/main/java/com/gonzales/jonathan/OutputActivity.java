package com.gonzales.jonathan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class OutputActivity extends AppCompatActivity {
    TextView tName, tSection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);
        tName = findViewById(R.id.txtName);
        tSection = findViewById(R.id.txtSection);
    }

    public void prevActivity(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void greet(View v) {
        SharedPreferences sp = getSharedPreferences("mydata", MODE_PRIVATE);
        tName.setText(sp.getString("name", null));
        tSection.setText(sp.getString("section", null));
    }

    public void showInternalData(View v) {
        FileInputStream fin = null;
        try {
            fin = openFileInput("data2.txt");
            int c;
            StringBuffer buffer = new StringBuffer();
            while((c=fin.read()) != -1) {
                buffer.append((char)c);
            }
            tName.setText(buffer);
        } catch (Exception e) {
            Toast.makeText(this, "error reading internal storage", Toast.LENGTH_LONG).show();
        } finally {
            try {
                fin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void showExternalData(View v) {
        File folder = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(folder, "data3.txt");
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(file);
            int c;
            StringBuffer buffer = new StringBuffer();
            while((c=fin.read()) != -1) {
                buffer.append((char)c);
            }
            tName.setText(buffer);
        } catch (Exception e) {
            Toast.makeText(this, "error reading external storage", Toast.LENGTH_LONG).show();
        } finally {
            try {
                fin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
