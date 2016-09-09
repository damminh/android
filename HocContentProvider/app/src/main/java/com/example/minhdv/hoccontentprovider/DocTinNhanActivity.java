package com.example.minhdv.hoccontentprovider;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class DocTinNhanActivity extends AppCompatActivity {

    ListView lvTinNhan;
    ArrayList<String> dsTinNhan;
    ArrayAdapter<String> adapterTinNhan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_tin_nhan);

        addControls();
        addEvents();

        docToanBoTinNhan();
    }

    private void docToanBoTinNhan() {
        Uri uri = Uri.parse("content://sms/inbox");
        Cursor cursor = getContentResolver().query(uri,null,null,null,null);
        dsTinNhan.clear();
        while (cursor.moveToNext()) {
            int indexPhoneNumber = cursor.getColumnIndex("address");
            int indexTimeStamp = cursor.getColumnIndex("date");
            int indexBody = cursor.getColumnIndex("body");

            String phonenumber = cursor.getString(indexPhoneNumber);
            String timeStamp = cursor.getString(indexTimeStamp);
            String body = cursor.getString(indexBody);

            dsTinNhan.add(phonenumber + "\n" + timeStamp +
            "\n" + body);
        }
        cursor.close();
        adapterTinNhan.notifyDataSetChanged();
    }

    private void addEvents() {

    }

    private void addControls() {
        lvTinNhan = (ListView) findViewById(R.id.lvDocTinNhan);
        dsTinNhan = new ArrayList<>();
        adapterTinNhan = new ArrayAdapter<String>(DocTinNhanActivity.this,
                android.R.layout.simple_list_item_1,dsTinNhan);

        lvTinNhan.setAdapter(adapterTinNhan);
    }
}
