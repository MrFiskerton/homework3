package com.example.mrfiskerton.homework_3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Mr.Fiskerton on 30.11.2016.
 */
public class ActionReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive");
        context.startService(new Intent(context, ImageLoaderService.class).putExtra("uri", MainActivity.getURI()));
    }

    private final String TAG = "ActionReceiver";
}
