package com.example.minhdv.hocbroadcastreceiver_coding;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnDangNhap;

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if(connectivityManager.getActiveNetworkInfo() != null) {
                btnDangNhap.setEnabled(true);
                Toast.makeText(context,"ban vua mo internet",Toast.LENGTH_LONG);
            }
            else  {
                btnDangNhap.setEnabled(false);
                Toast.makeText(context, "ban vua mo internet", Toast.LENGTH_LONG);
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(broadcastReceiver,filter);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(broadcastReceiver != null) unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();

    }

    private void addControls() {
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
    }
}
