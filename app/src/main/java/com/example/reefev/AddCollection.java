package com.example.reefev;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;

public class AddCollection extends AppCompatActivity {

    EditText inpTextName;
    Button btnInsert;
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_collection);

        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);


        inpTextName = this.findViewById(R.id.foldername_txt);
        btnInsert = this.findViewById(R.id.click_here_btn);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(inpTextName.getText())) {


                } else {
                    File folder = new File(Environment.getExternalStorageDirectory() ,"ReefEVO"+ File.separator +inpTextName.getText());
                    boolean success = true;
                    if (!folder.exists()) {
                        success = folder.mkdirs();

                        Intent intent = new Intent(AddCollection.this, CameraActivity.class);

                        intent.putExtra("Collectionname",inpTextName.getText());
                        intent.putExtra("Collectiontype","New");

                        startActivity(intent);
                    }


                }
            }
        });

    }
}
