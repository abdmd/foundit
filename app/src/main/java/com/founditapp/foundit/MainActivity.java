package com.founditapp.foundit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // load map activity
        ImageView bMap = (ImageView)findViewById(R.id.btnMap);
        assert bMap != null;
        bMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ViewMap.class));
            }
        });

        // load schedule activity
        ImageView bSchedule = (ImageView)findViewById(R.id.btnSchedule);
        assert bSchedule != null;
        bSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FlightSchedule.class));
            }
        });

        // load checkin activity
        ImageView bCheckin = (ImageView)findViewById(R.id.btnCheckin);
        assert bCheckin != null;
        bCheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Checkin.class));
            }
        });

        // load transport activity
        ImageView bTransport = (ImageView)findViewById(R.id.btnTransport);
        assert bTransport != null;
        bTransport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Transport.class));
            }
        });

        // load hotel activity
        ImageView bHotel = (ImageView)findViewById(R.id.btnHotel);
        assert bHotel != null;
        bHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Hotel.class));
            }
        });

        // load hotline activity
        ImageView bHotline = (ImageView)findViewById(R.id.btnHotline);
        assert bHotline != null;
        bHotline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Hotline.class));
            }
        });
    }
}
