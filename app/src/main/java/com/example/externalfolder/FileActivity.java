package com.example.externalfolder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class FileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        TextView textView = findViewById(R.id.txtFileView);

        String fileName = getIntent().getStringExtra("fileName");
        String fileDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + fileName;
        try {
            FileInputStream fis = new FileInputStream(fileDir);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = reader.readLine()) != null)
                builder.append(line + "\n");
            reader.close();
            textView.setText(builder.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}