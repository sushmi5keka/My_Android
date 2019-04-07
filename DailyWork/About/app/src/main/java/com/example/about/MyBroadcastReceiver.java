package com.example.about;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;

public class MyBroadcastReceiver extends BroadcastReceiver {

    MediaPlayer mp;
    private Context context;
    private AudioManager audioManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        mp=MediaPlayer.create(context,R.raw.alrm);
        mp.start();
    }
    public void makePhoneSilent(){
        audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
    }
}
