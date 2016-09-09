package com.example.minhdv.contactmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.minhdv.adapter.ContactAdapter;
import com.example.minhdv.model.Contact;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText edtTen;
    EditText edtPhone;
    Button btnLuu;

    ListView lvDanhBa;
    ArrayList<Contact> dsDanhBa;
    ContactAdapter adapterContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addEvents() {
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyLuuDanhBa();
            }
        });
    }

    private void xuLyLuuDanhBa() {
        Contact contact = new Contact(edtTen.getText().toString(),
                edtPhone.getText().toString()
                );
        dsDanhBa.add(contact);
        adapterContact.notifyDataSetChanged();
    }

    private void addControls() {
        edtTen = (EditText) findViewById(R.id.edtName);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        btnLuu = (Button) findViewById(R.id.btnLuu);

        lvDanhBa = (ListView) findViewById(R.id.lvDanhBa);
        dsDanhBa = new ArrayList<>();
        adapterContact = new ContactAdapter(MainActivity.this,R.layout.items,dsDanhBa);
        lvDanhBa.setAdapter(adapterContact);

    }


}
