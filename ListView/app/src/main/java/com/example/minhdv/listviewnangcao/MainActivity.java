package com.example.minhdv.listviewnangcao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.minhdv.adapter.DanhBaAdapter;
import com.example.minhdv.model.DanhBa;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvDanhBa;
    ArrayList<DanhBa> dsDanhBa;
    DanhBaAdapter danhBaAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addEvents() {

    }

    private void addControls() {
        lvDanhBa = (ListView) findViewById(R.id.lvDanhBa);
        dsDanhBa = new ArrayList<>();
        dsDanhBa.add(new DanhBa(1,"nguyen van teo","01932"));
        dsDanhBa.add(new DanhBa(2,"kaka","123"));

        danhBaAdapter = new DanhBaAdapter(MainActivity.this,
                R.layout.item,
                dsDanhBa);
        lvDanhBa.setAdapter(danhBaAdapter);
    }


}
