package com.example.minhdv.gson;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.minhdv.model.GoogleData;
import com.example.minhdv.model.data;
import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lvKetQua;
    ArrayList<data> dsKetQua;
    ArrayAdapter<data> adapterKetQua;

    EditText txtKeyword;
    Button btnTim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addControls() {
        txtKeyword = (EditText) findViewById(R.id.txtTim);
        btnTim = (Button) findViewById(R.id.btnTim);
        lvKetQua = (ListView) findViewById(R.id.listView);

        dsKetQua = new ArrayList<>();
        adapterKetQua = new ArrayAdapter<data>(MainActivity.this,
                android.R.layout.simple_list_item_1,dsKetQua);
        lvKetQua.setAdapter(adapterKetQua);
    }

    private void addEvents() {
        btnTim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyTimKiem();
            }
        });
    }

    private void xuLyTimKiem() {
        GoogleDataTask task = new GoogleDataTask();
        task.execute(txtKeyword.getText().toString());
    }

    class GoogleDataTask extends AsyncTask<String, Void, List<data>> {

        @Override
        protected List<data> doInBackground(String... params) {
            ArrayList<data> ds = new ArrayList<>();
            try {
                String keyword = params[0];
                String api = "http://vnexpress.net/block/crawler?arrKeys[]=ty_gia";
                String linkAPI_Search = api + URLEncoder.encode(keyword, "UTF-8")+"&start="+"&rsz=8";

                URL url = new URL(linkAPI_Search);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openStream(),"UTF-8");
                GoogleData googleData = new Gson().fromJson(inputStreamReader,GoogleData.class);
                return googleData.getTyGia().getDatas();

            }
            catch (Exception ex) {
                Log.e("Loi",ex.toString());
            }
            return ds;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            adapterKetQua.clear();
        }

        @Override
        protected void onPostExecute(List<data> datas) {
            super.onPostExecute(datas);
            adapterKetQua.clear();
            adapterKetQua.addAll(datas);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
