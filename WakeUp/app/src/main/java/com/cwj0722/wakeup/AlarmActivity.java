package com.cwj0722.wakeup;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AlarmActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        this.mediaPlayer = MediaPlayer.create(this, R.raw.aaa);
        this.mediaPlayer.start();

        findViewById(R.id.btnClose).setOnClickListener(mClickListener);
    }

    protected void onDestroy(){
        super.onDestroy();

        if(this.mediaPlayer != null){
            this.mediaPlayer.release();
            this.mediaPlayer = null;
        }
    }

    private void close() {
        if (this.mediaPlayer.isPlaying()){
            this.mediaPlayer.stop();
            this.mediaPlayer.release();
            this.mediaPlayer = null;
        }

        finish();
    }

    View.OnClickListener mClickListener = new View.OnClickListener() {

        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnClose:

                    close();
            }
        }
    };

}

