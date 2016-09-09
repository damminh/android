package com.example.minhdv.quanlydanhbasqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String DATABASE_NAME="dbConTact.sqlite";
    private static final String DB_PATH_SUFFIX = "/databases/";
    SQLiteDatabase database=null;

    ListView lvDanhBa;
    ArrayList<String> dsDanhBa;
    ArrayAdapter<String> adapterDanhBa;

    Button btnThemDanhBa,btnChinhSuaDanhBa,btnXoa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xuLySaoChepCSDLTuAssetsVaoHeThongMobile();

        addControls();
        addEvents();

        showAllConTactOnListView();
    }

    private void showAllConTactOnListView() {
        database = openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
        Cursor cursor = database.query("ConTact",null,null,null,null,null,null);

        dsDanhBa.clear();
        while(cursor.moveToNext()) {
            int ma = cursor.getInt(0);
            String ten = cursor.getString(1);
            String phone = cursor.getString(2);
            dsDanhBa.add(ma+"-"+ten+"\n"+phone);
        }
        cursor.close();
        adapterDanhBa.notifyDataSetChanged();
    }

    private void addEvents() {
        btnThemDanhBa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyThemDanhBa();
            }
        });

        btnChinhSuaDanhBa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyChinhSuaDanhBa();
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyXoa();
            }
        });
    }

    private void xuLyXoa() {
        database.delete("ConTact","ma=?",new String[]{"2"});
        showAllConTactOnListView();
    }

    private void xuLyChinhSuaDanhBa() {
        ContentValues row = new ContentValues();
        row.put("Ten","Hoang Van Huy");
        database.update("ConTact", row, "ma=?", new String[]{"3"});
        showAllConTactOnListView();
    }

    private void xuLyThemDanhBa() {
        ContentValues row = new ContentValues();
        row.put("Ma",113);
        row.put("Ten","Tran Duy Thanh");
        row.put("Phone","0977422000");
        long r = database.insert("ConTact",null,row);
        Toast.makeText(MainActivity.this,"vua them moi 1 contact",Toast.LENGTH_LONG).show();
        showAllConTactOnListView();
    }

    private void addControls() {
        lvDanhBa = (ListView) findViewById(R.id.lvDanhBa);
        dsDanhBa = new ArrayList<>();
        adapterDanhBa = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,dsDanhBa);

        lvDanhBa.setAdapter(adapterDanhBa);

        btnThemDanhBa = (Button) findViewById(R.id.btnThemDanhBa);
        btnChinhSuaDanhBa = (Button) findViewById(R.id.btnChinhSuaDanhBa);
        btnXoa = (Button) findViewById(R.id.btnXoaMotSo);
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
            Log.e("Loi sao chep",ex.toString());
        }
    }

    private String layDuongDanLuuTru() {
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX+ DATABASE_NAME;
    }
}
