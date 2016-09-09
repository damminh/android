package com.example.minhdv.karaoke;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.minhdv.adapter.MusicAdapter;
import com.example.minhdv.model.music;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public  static String DATABASE_NAME="Arirang.sqlite";
    private static final String DB_PATH_SUFFIX = "/databases/";
    public  static SQLiteDatabase database=null;


    ListView lvBaiHatGoc;
    ArrayList<music>dsBaiHatGoc;
    MusicAdapter adapterBaiHatGoc;

    ListView lvBaiHatYeuThich;
    ArrayList<music>dsBaiHatYeuThich;
    MusicAdapter adapterBaiHatYeuThich;

    TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xuLySaoChepCSDLTuAssetsVaoHeThongMobile();

        addControls();
        addEvents();
    }

    private void xuLySaoChepCSDLTuAssetsVaoHeThongMobile() {
        File dbFile = getDatabasePath(DATABASE_NAME);
        if (!dbFile.exists())   {
            try  {
                CopyDataBaseFromAsset();
                Toast.makeText(this, "Copying sucess from Assets folder", Toast.LENGTH_LONG).show();
            }
            catch (Exception e)     {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void CopyDataBaseFromAsset() {
        try {
            InputStream myInput = getAssets().open(DATABASE_NAME);
            String outFileName = layDuongDanLuuTru();
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!f.exists())      f.mkdir();
            OutputStream myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }        // Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
        }
        catch (Exception ex) {
            Log.e("Loi sao chep", ex.toString());
        }
    }

    private String layDuongDanLuuTru() {
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX+ DATABASE_NAME;
    }

    private void addEvents() {
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (tabId.equalsIgnoreCase("t1")) {
                    xuLyHienThiBaiHatGoc();
                } else if (tabId.equalsIgnoreCase("t2")) {
                    xuLyHienThiBaiHatYeuThich();
                }
            }
        });
    }

    private void xuLyHienThiBaiHatYeuThich() {
        database = openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
        Cursor cursor = database.query("ArirangSongList",null,"YEUTHICH=?",new String[]{"1"},null,null,null);
        dsBaiHatYeuThich.clear();
        while (cursor.moveToNext()) {
            String mabh = cursor.getString(0);
            String tenbh = cursor.getString(1);
            String casi = cursor.getString(3);
            int yeuthich = cursor.getInt(5);

            music ms = new music();
            ms.setMa(mabh);
            ms.setTen(tenbh);
            ms.setCaSi(casi);
            ms.setThich(yeuthich == 1);

            dsBaiHatYeuThich.add(ms);
        }
        cursor.close();
        adapterBaiHatYeuThich.notifyDataSetChanged();
    }

    private void xuLyHienThiBaiHatGoc() {
        database = openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
        Cursor cursor = database.query("ArirangSongList",null,null,null,null,null,null);
        dsBaiHatGoc.clear();
        while (cursor.moveToNext()) {
            String mabh = cursor.getString(0);
            String tenbh = cursor.getString(1);
            String casi = cursor.getString(3);
            int yeuthich = cursor.getInt(5);

            music ms = new music();
            ms.setMa(mabh);
            ms.setTen(tenbh);
            ms.setCaSi(casi);
            ms.setThich(yeuthich == 1);

            dsBaiHatGoc.add(ms);
        }
        cursor.close();
        adapterBaiHatGoc.notifyDataSetChanged();
    }

    private void addControls() {

        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tab1 = tabHost.newTabSpec("t1");
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("", getResources().getDrawable(R.drawable.music));
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2 = tabHost.newTabSpec("t2");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("", getResources().getDrawable(R.drawable.musicfoverite));
        tabHost.addTab(tab2);


        lvBaiHatGoc = (ListView) findViewById(R.id.lvBaiHatGoc);
        dsBaiHatGoc=new ArrayList<>();
        adapterBaiHatGoc = new MusicAdapter(MainActivity.this,R.layout.item,dsBaiHatGoc);
        lvBaiHatGoc.setAdapter(adapterBaiHatGoc);

        lvBaiHatYeuThich = (ListView) findViewById(R.id.lvBaiHatYeuThich);
        dsBaiHatYeuThich=new ArrayList<>();
        adapterBaiHatYeuThich = new MusicAdapter(MainActivity.this,R.layout.item,dsBaiHatYeuThich);
        lvBaiHatYeuThich.setAdapter(adapterBaiHatYeuThich);

        //giaLapBaiHat();
    }

    private void giaLapBaiHat() {
        dsBaiHatGoc.add(new music("12345", "khong yeu thi thoi", "ngot ngao", true));
        dsBaiHatGoc.add(new music("34532","long me","ngoc ka",true));
        dsBaiHatGoc.add(new music("43333","khong yeu","ngot ngao 1",false));
        dsBaiHatGoc.add(new music("55555", "a hieu long em", "akira phan", true));

        adapterBaiHatGoc.notifyDataSetChanged();
    }
}
