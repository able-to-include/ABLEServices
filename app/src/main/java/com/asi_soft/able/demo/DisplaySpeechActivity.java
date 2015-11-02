package com.asi_soft.able.demo;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 *
 */
public class DisplaySpeechActivity extends ActionBarActivity {

    private MediaPlayer mediaPlayer;
    private ImageButton buttonPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_speech);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        Intent intent = getIntent();
        String object = intent.getStringExtra("object");
        try {
            JSONObject objectABLE = new JSONObject(object);
            String audioSpeechAddress = objectABLE.getString("audioSpeech");
            Uri audioSpeechUri = Uri.parse(audioSpeechAddress);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(getApplicationContext(), audioSpeechUri);
            mediaPlayer.prepare();

            buttonPlay = (ImageButton) findViewById(R.id.buttonPlay);
            buttonPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    play();
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
            //TODO Show error
        } catch (IOException e) {
            e.printStackTrace();
            //TODO Show error
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void play() {
        mediaPlayer.start();
    }
}
