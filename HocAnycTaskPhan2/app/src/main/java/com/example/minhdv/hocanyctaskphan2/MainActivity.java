package com.example.minhdv.hocanyctaskphan2;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button btnTaiHinh;
    ImageView imgHinh;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addControls() {
        btnTaiHinh = (Button) findViewById(R.id.btnTaiHinh);
        imgHinh = (ImageView) findViewById(R.id.imageView);
        progressDialog = new ProgressDialog(MainActivity.this);

        progressDialog.setTitle("thong bao");
        progressDialog.setMessage("dang tai hinh vui long cho...");
        progressDialog.setCanceledOnTouchOutside(false);
    }

    private void addEvents() {
        btnTaiHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyTaiTinh();
            }
        });
    }

    private void xuLyTaiTinh() {
        String link = "http://media.baogiaothong.vn/files/f1/2014/09/05/hoa-hau-mai-phuong-thuy-khoe-vong-1-cang-tron-4.jpg";
        imageTask task = new imageTask();
        task.execute(link);
    }

    class imageTask extends AsyncTask<String,Void,Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imgHinh.setImageBitmap(bitmap);
            progressDialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                String link = params[0];
                Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(link).getContent());
                return bitmap;
            }
            catch (Exception ex) {
                Log.e("loi",ex.toString());
            }
            return null;
        }
    }
}
