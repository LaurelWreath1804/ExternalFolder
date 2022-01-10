package com.example.externalfolder;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.io.File;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String dirName[];
    File[] files;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list_item);
        String folderToList = Environment.getExternalStorageDirectory().getAbsolutePath();
        File folderFile = new File(folderToList);
        if (folderFile.isDirectory()) {
            files = folderFile.listFiles();
            dirName = new String[files.length];
            for (int i = 0; i < files.length; i++) {
                dirName[i] = files[i].getName();
            }
        }
        ArrayAdapter<String> arrayAdapter;
        arrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, dirName);
        listView.setAdapter(arrayAdapter);

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                Log.v("TAG", "Permission granted");
            else {
                requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1234);
            }
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                if (files[position].isFile()) {
                    intent = new Intent(getBaseContext(), FileActivity.class);
                    intent.putExtra("fileName", dirName[position]);
                    startActivity(intent);
                } else if (files[position].isDirectory()) {
                    intent = new Intent(getBaseContext(), FolderActivity.class);
                    intent.putExtra("folderName", dirName[position]);
                    startActivity(intent);
                }
            }
        });
    }
}