package com.example.mrfiskerton.homework_3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private ImageView mImageView;
    private TextView  mStatusTextView;
    private static String uri = "http://myosu.ru/wp-content/uploads/2016/3/ave-new-feat-sakura-saori-shokuzai-belladonna_2.jpg";
    public final static String BROADCAST_NAME = "ru.startandroid.develop.mrfiskerton";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = (ImageView) findViewById(R.id.imageView);
        mStatusTextView = (TextView) findViewById(R.id.status);

        final String fileName = getFilesDir().getPath() + "/image.jpg";
        BroadcastReceiver br = new BroadcastReceiver() {

            public void onReceive(Context context, Intent intent) {
                File file = new File(fileName);
                mImageView.setImageBitmap(BitmapFactory.decodeFile(file.getPath()));
                mImageView.setVisibility(View.VISIBLE);
                mStatusTextView.setVisibility(View.GONE);
            }
        };

        File file = new File(fileName);
        if (file.exists()) {
            mImageView.setImageBitmap(BitmapFactory.decodeFile(file.getPath()));
            mImageView.setVisibility(View.VISIBLE);
            mStatusTextView.setVisibility(View.GONE);
        }
        IntentFilter filter = new IntentFilter(BROADCAST_NAME);

        registerReceiver(br, filter);
    }

    public static String getURI() {
        return uri;
    }
}
