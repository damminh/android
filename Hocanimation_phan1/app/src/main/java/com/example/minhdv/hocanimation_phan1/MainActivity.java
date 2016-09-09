package com.example.minhdv.hocanimation_phan1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    Button btnXoayControl, btnXoayManHinh, btnXoay3s, btnHieuUng;

    Animation animation = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addEvents() {
        btnXoayControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnXoayControl.startAnimation(animation);
            }
        });

        btnXoayManHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout layout = (LinearLayout) findViewById(R.id.layoutManHinh);
                layout.startAnimation(animation);
            }
        });

        btnXoay3s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnXoay3s.startAnimation(animation);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        finish();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });
    }

    private void addControls() {
        btnXoayControl = (Button) findViewById(R.id.btnXoayControl);
        btnXoay3s = (Button) findViewById(R.id.btnXoay3s);
        btnXoayManHinh = (Button) findViewById(R.id.btnXoayManHinh);
        btnHieuUng = (Button) findViewById(R.id.btnHieuUngListView);

        animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.xoaycontrol);
    }
}
