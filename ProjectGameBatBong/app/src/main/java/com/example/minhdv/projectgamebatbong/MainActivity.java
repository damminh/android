package com.example.minhdv.projectgamebatbong;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int score=0;
    Random rd;
    TextView txtscore;
    ViewGroup.LayoutParams params;
    LinearLayout layoutBubble;
    Button btnCreateBubble;
    ObjectAnimator objectAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addEvents() {
        btnCreateBubble.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                for (int i = 0; i <= rd.nextInt(5); i++) {
                   // ProcessAnim();
                }
            }
        });

    }

    private void addControls() {
        txtscore = (TextView)
                findViewById(R.id.txtScore);
        rd = new Random();
        layoutBubble = (LinearLayout)
                findViewById(R.id.layoutBubble);

        params = new ViewGroup.LayoutParams
                (
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        btnCreateBubble = (Button)
                findViewById(R.id.btnCreateBubble);


    }
}
