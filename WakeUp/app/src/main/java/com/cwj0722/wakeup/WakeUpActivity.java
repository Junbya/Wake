package com.cwj0722.wakeup;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Set;

public class WakeUpActivity extends MainActivity {

    private TimePicker timePicker;
    private RadioGroup RG1;
    public String result;
    public int soundi;
    String pt;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wakeup);

        this.calendar = Calendar.getInstance();
        this.timePicker = findViewById(R.id.timePicker2);
        super.day_of_week = day_of_week;
        soundi = 0;

        findViewById(R.id.EndBtn2).setOnClickListener(m2ClickListener);
        findViewById(R.id.StartBtn2).setOnClickListener(m2ClickListener);

        if(day_of_week == 7) {
            day_of_week = 1;
        }else if (day_of_week < 7) {
            day_of_week ++;
        }



        RG1 = findViewById(R.id.RG1);


        RG1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radioButton1) {
                    result = "탁상시계";
                    soundi = 1;
                }else if(checkedId == R.id.radioButton2){
                    result = "꼬기오";
                    soundi = 2;
                }else if(checkedId == R.id.radioButton3){
                    result = "기상나팔";
                    soundi = 3;
                }
            }
        });

    };


    private void SetAlram() {
        this.calendar.set(Calendar.HOUR_OF_DAY, this.timePicker.getCurrentHour());
        this.calendar.set(calendar.MINUTE, this.timePicker.getCurrentMinute());
        this.calendar.set(calendar.SECOND,0);

        Intent intent3 = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0 ,intent3, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),7*24*60*60*1000, pendingIntent);

        SimpleDateFormat format = new SimpleDateFormat("EE HH:mm:ss", Locale.getDefault());
        Toast.makeText(this,"Alarm : "+ format.format(calendar.getTime()),Toast.LENGTH_LONG).show();
        pt = format.format(calendar.getTime());
        Intent intent4 = new Intent(this,ListActivity.class);
        startActivity(intent4);
        finish();
    }
    private void EndAlram() {
        Toast.makeText(this,"기상시간 설정을 종료합니다.",Toast.LENGTH_SHORT).show();

    }

    private void soundSet() {

        Toast.makeText(this,"알람 소리를 설정해주세요.",Toast.LENGTH_SHORT).show();
    }



    View.OnClickListener m2ClickListener = new View.OnClickListener() {
        public void onClick(View v) {

            switch (v.getId()) {

                case R.id.EndBtn2:

                    EndAlram();
                    break;

                case R.id.StartBtn2:
                    if(soundi == 0){
                        soundSet();
                    }else {
                        SetAlram();
                    }
                    break;


            }
        }
    };
}
