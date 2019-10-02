package com.example.reefev;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.Toast;


import com.example.reefev.fragments.Camera2BasicFragment;

public class CameraActivity extends AppCompatActivity {
    String folderName,folderType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        folderName = getIntent().getStringExtra("Collectionname");
        folderType = getIntent().getStringExtra("Collectiontype");

        if (folderType=="New"){
            Toast.makeText(this, "New Collection Created", Toast.LENGTH_SHORT).show();

        }

        if (null == savedInstanceState) {
            FragmentManager fm = getSupportFragmentManager();
            Bundle arguments = new Bundle();
            arguments.putString("VALUE1", folderName);
            arguments.putString("VALUE2", "New");

            Camera2BasicFragment myFragment = Camera2BasicFragment.newInstance();
            myFragment.setArguments(arguments);

            fm.beginTransaction().replace(R.id.container, myFragment).commit();
        }
    }

}