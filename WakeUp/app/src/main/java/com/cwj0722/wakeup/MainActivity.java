package com.cwj0722.wakeup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TimePicker timePicker;
    public Calendar calendar;
    public int day_of_week;
    private Button MonBtn;
    private Button TueBtn;
    private Button WedBtn;
    private Button ThurBtn;
    private Button FriBtn;
    private Button SatBtn;
    private Button SunBtn;
    String ad;
    String pd;
    String at;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.calendar = Calendar.getInstance();
        this.timePicker = findViewById(R.id.timePicker1);
        this.day_of_week = 0;

        findViewById(R.id.EndBtn1).setOnClickListener(mClickListener);
        findViewById(R.id.StartBtn1).setOnClickListener(mClickListener);
        findViewById(R.id.MonBtn).setOnClickListener(dClickListener);
        findViewById(R.id.TueBtn).setOnClickListener(dClickListener);
        findViewById(R.id.WedBtn).setOnClickListener(dClickListener);
        findViewById(R.id.ThurBtn).setOnClickListener(dClickListener);
        findViewById(R.id.FriBtn).setOnClickListener(dClickListener);
        findViewById(R.id.SatBtn).setOnClickListener(dClickListener);
        findViewById(R.id.SunBtn).setOnClickListener(dClickListener);


    }

    private void DayAlram(){
        Toast.makeText(this,"요일을 설정해주세요.",Toast.LENGTH_SHORT).show();
    }

    public  void day() {
        this.day_of_week = day_of_week;
    }

    public void setAlram() {
        this.calendar.set(calendar.DAY_OF_WEEK,this.day_of_week);
        this.calendar.set(Calendar.HOUR_OF_DAY, this.timePicker.getCurrentHour());
        this.calendar.set(calendar.MINUTE, this.timePicker.getCurrentMinute());
        this.calendar.set(calendar.SECOND,0);


        Intent intent = new Intent(this, AlarmReceiver1.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0 ,intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),7*24*60*60*1000, pendingIntent);

        SimpleDateFormat format = new SimpleDateFormat("EE HH:mm:ss", Locale.getDefault());
        Toast.makeText(this,"Alarm : "+ format.format(calendar.getTime()),Toast.LENGTH_LONG).show();
        at = format.format(calendar.getTime());

        Intent intent1 = new Intent(this,WakeUpActivity.class);
        startActivity(intent1);
        finish();
    }

    private void EndAlram() {
        Toast.makeText(this,"취침시간 설정을 종료합니다. ",Toast.LENGTH_SHORT).show();
    }
    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {

                case R.id.EndBtn1:
                        EndAlram();
                    break;

                case R.id.StartBtn1:
                    if(day_of_week == 0){
                        DayAlram();
                    }else {
                        setAlram();
                    }
                    break;


            }
        }

    };

    View.OnClickListener dClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){

                case R.id.MonBtn:
                    ad = "월요일";
                    pd = "화요일";
                    day_of_week = 2;
                    day();
                    break;

                case R.id.TueBtn:
                    ad = "화요일";
                    pd = "수요일";
                    day_of_week = 3;
                    day();
                    break;

                case R.id.WedBtn:
                    ad = "수요일";
                    pd = "목요일";
                    day_of_week = 4;
                    day();
                    break;

                case R.id.ThurBtn:
                    ad = "목요일";
                    pd = "금요일";
                    day_of_week = 5;
                    day();
                    break;

                case R.id.FriBtn:
                    ad = "금요일";
                    pd = "토요일";
                    day_of_week = 6;
                    day();
                    break;

                case R.id.SatBtn:
                    ad = "토요일";
                    pd = "일요일";
                    day_of_week = 7;
                    day();
                    break;

                case R.id.SunBtn:
                    ad = "일요일";
                    pd = "월요일";
                    day_of_week = 1;
                    day();
                    break;

            }
        }
    };

}
