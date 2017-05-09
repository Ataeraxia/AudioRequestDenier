package com.example.android.audiorequestdenier;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    AudioManager audioManager;
    private AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                Toast.makeText(MainActivity.this, "Lost audio focus, pause your music app", Toast.LENGTH_SHORT).show();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                Toast.makeText(MainActivity.this, "Lost audio focus, wait and try again", Toast.LENGTH_SHORT).show();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                Toast.makeText(MainActivity.this, "Lost audio focus, wait and try again", Toast.LENGTH_SHORT).show();
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                Toast.makeText(MainActivity.this, "Gained audio focus, try it now", Toast.LENGTH_SHORT).show();

            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button jammer = (Button) findViewById(R.id.jammer);
        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        jammer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = audioManager.requestAudioFocus(afChangeListener,
                        AudioManager.MODE_IN_CALL,
                        AudioManager.AUDIOFOCUS_GAIN);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    Toast.makeText(MainActivity.this, "Jammer currently on", Toast.LENGTH_SHORT).show();
                }
    }


});
    }
}
