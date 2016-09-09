package com.example.minhdv.hocanyctaskphan1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText txtSoButton;
    Button btnVe;
    ProgressBar progressBarPerent;
    LinearLayout layoutButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addEvents() {
        btnVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyVeButton();
            }
        });
    }

    private void xuLyVeButton() {
        int n = Integer.parseInt(txtSoButton.getText().toString());

        ButtonTask task = new ButtonTask();
        task.execute(n);
    }


    private void addControls() {
        txtSoButton = (EditText) findViewById(R.id.txtSoButton);
        btnVe = (Button) findViewById(R.id.btnVe);
        progressBarPerent = (ProgressBar) findViewById(R.id.progressBar);
        layoutButton = (LinearLayout) findViewById(R.id.layoutButton);
    }

    class ButtonTask extends AsyncTask<Integer,Integer,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            layoutButton.removeAllViews();
            progressBarPerent.setProgress(0);
        }

        @Override
        protected Void doInBackground(Integer... params) {
            int n = params[0];
            Random random = new Random();
            for(int i = 0 ; i < n ; i++) {
                int percent = i*100/n;
                int value = random.nextInt(500);
                publishProgress(percent,value);
                SystemClock.sleep(100);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBarPerent.setProgress(100);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

            super.onProgressUpdate(values);
            int value = values[1];
            int percent = values[0];
            progressBarPerent.setProgress(percent);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            Button btn = new Button(MainActivity.this);
            btn.setLayoutParams(params);
            btn.setText(value + "");

            layoutButton.addView(btn);
        }
    }
}
