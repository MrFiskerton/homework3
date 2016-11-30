package com.example.mrfiskerton.homework_3;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Mr.Fiskerton on 30.11.2016.
 */
public class ImageLoaderService extends IntentService {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    public ImageLoaderService(){
        super("ImageLoaderService");
    }
    @Override
    protected void onHandleIntent(Intent intent){
        Log.d(TAG, "onHandleIntent");
        try {
            URL url = new URL(intent.getStringExtra("uri"));
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            FileOutputStream fout = openFileOutput("image.jpg", Context.MODE_PRIVATE);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fout);
            fout.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sendBroadcast(new Intent(MainActivity.BROADCAST_NAME));
    }

    private static final String TAG = "ImageLoaderService";
}
