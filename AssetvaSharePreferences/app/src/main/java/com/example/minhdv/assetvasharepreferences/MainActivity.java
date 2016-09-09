package com.example.minhdv.assetvasharepreferences;

import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    TextView txtFontChu;
    ListView lvFont;

    ArrayList<String> dsFont;
    ArrayAdapter<String> fontAdapter;

    String tenLuuTru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addEvents() {
        lvFont.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                xuLyDoiFontChu(position);
            }
        });
    }

    private void xuLyDoiFontChu(int position) {
        Typeface typeface = Typeface.createFromAsset(getAssets(),
                "font/"+dsFont.get(position));
        txtFontChu.setTypeface(typeface);

        SharedPreferences preferences = getSharedPreferences(
                tenLuuTru,MODE_PRIVATE
        );
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("FONTCHU","font/"+dsFont.get(position));
        editor.commit();
    }

    private void addControls() {
        txtFontChu = (TextView) findViewById(R.id.txtFontChu);
        lvFont = (ListView) findViewById(R.id.lvFont);

        dsFont = new ArrayList<>();
        fontAdapter = new ArrayAdapter<String>(
                MainActivity.this,android.R.layout.simple_list_item_1,
                dsFont
        );
        lvFont.setAdapter(fontAdapter);

        try {
            AssetManager assetManager = getAssets();
            String[] arrFontName = assetManager.list("font");
            dsFont.addAll(Arrays.asList(arrFontName));
            fontAdapter.notifyDataSetChanged();

            SharedPreferences preferences = getSharedPreferences(
                    tenLuuTru,MODE_PRIVATE
            );
            String font = preferences.getString("FONTCHU","");
            if(font.length()>0){
                Typeface typeface = Typeface.createFromAsset(getAssets(),font);
                txtFontChu.setTypeface(typeface);
            }
        } catch (IOException e) {
            Log.e("LOI_FONT",e.toString());
        }

    }
}
