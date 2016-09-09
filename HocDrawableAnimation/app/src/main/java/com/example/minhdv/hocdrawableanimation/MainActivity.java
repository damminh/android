package com.example.minhdv.hocdrawableanimation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btnStart,btnStop;
    ImageView imgHieuUng;

    AnimationDrawable animationDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addEvents() {

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationDrawable.start();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationDrawable.stop();
            }
        });

    }

    private void addControls() {
        btnStart = (Button) findViewById(R.id.btnstart);
        btnStop = (Button) findViewById(R.id.btnstop);
        imgHieuUng = (ImageView) findViewById(R.id.imgAnimation);

        imgHieuUng.setBackgroundResource(R.drawable.anima);

        animationDrawable = (AnimationDrawable) imgHieuUng.getBackground();
    }
}
