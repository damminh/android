package com.example.minhdv.hoccontentprovider;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void xuLyMoManHinhDocDanhBa(View view) {
        Intent intent = new Intent(MainActivity.this,DanhBaActivity.class);
        startActivity(intent);
    }

    public void xuLyManHinhDocTinNhan(View view) {
        Intent intent = new Intent(MainActivity.this,DocTinNhanActivity.class);
        startActivity(intent);
    }
}
