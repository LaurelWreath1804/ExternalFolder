package com.example.externalfolder;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.io.File;

public class FolderActivity extends AppCompatActivity {

    ListView listView;
    String dirName[];
    File[] files;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);

        listView = findViewById(R.id.list_item2);
        String folderName = getIntent().getStringExtra("folderName");
        String folderToList = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + folderName;
        File folderFile = new File(folderToList);
        files = folderFile.listFiles();
        dirName = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            dirName[i] = files[i].getName();
        }

        ArrayAdapter<String> arrayAdapter;
        arrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, dirName);
        listView.setAdapter(arrayAdapter);
    }
}