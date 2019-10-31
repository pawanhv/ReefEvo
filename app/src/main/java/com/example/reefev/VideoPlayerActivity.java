package com.example.reefev;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import org.jcodec.api.SequenceEncoder;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;



public class VideoPlayerActivity extends AppCompatActivity {
    public String filename1, filepath1;
    ImageView myImage;
    Handler handler;
    Runnable runnable;
    final int interval = 33;
    int i=0;
    File[] files;
    TextureView textureView;
    ImageView ivBitmap;
    SeekBar customSeekBar;
    int progressChangedValue = 0;
    ProgressDialog progressDoalog;
    public ArrayList<Bitmap> bitmapArray = new ArrayList<Bitmap>();


    static {
        if (!OpenCVLoader.initDebug())
            Log.d("ERROR", "Unable to load OpenCV");
        else
            Log.d("SUCCESS", "OpenCV loaded");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        myImage = findViewById(R.id.imageView2);

        filename1 = getIntent().getStringExtra("folderName");
        filepath1 = getIntent().getStringExtra("folderPath");

        File directory = new File(filepath1);
        files = directory.listFiles();

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        progressDoalog = new ProgressDialog(VideoPlayerActivity.this);
        progressDoalog.setMax(files.length);
        progressDoalog.setMessage("Please wait...");
        progressDoalog.setTitle("Loading Images");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDoalog.show();

        final Handler handle = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                progressDoalog.incrementProgressBy(1);
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    int i=0;
                    while (progressDoalog.getProgress() <= progressDoalog
                            .getMax()) {
                        Thread.sleep(30);
                        handle.sendMessage(handle.obtainMessage());

                        if (progressDoalog.getProgress() == progressDoalog
                                .getMax()) {
                            progressDoalog.dismiss();
                        }

                        File imgFile = new File(filepath1 + "/" + files[i].getName());
                        if (imgFile.exists()) {
                            Bitmap myBitmap1 = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                            Bitmap myBitmap = getResizedBitmap(myBitmap1,120,120);
                            bitmapArray.add(myBitmap); // Add a bitmap
                        }
                        i++;
                    }
                    myImage.setImageBitmap(bitmapArray.get(1));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        customSeekBar =(SeekBar)findViewById(R.id.seekBar);
        customSeekBar.setMax(files.length);
        customSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                try {
                myImage.setImageBitmap(bitmapArray.get(progress));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth)
    {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // create a matrix for the manipulation
        Matrix matrix = new Matrix();
        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);
        // recreate the new Bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        return resizedBitmap;
    }


    public void onClick_play(View v)
    {
        SequenceEncoder enc = new SequenceEncoder(new File("filename"));
    // GOP size will be supported in 0.2
    // enc.getEncoder().setKeyInterval(25);
            for(...) {
            BufferedImage image = ... // Obtain an image to encode
            enc.encodeImage(image);
        }
            enc.finish();
    }


}

