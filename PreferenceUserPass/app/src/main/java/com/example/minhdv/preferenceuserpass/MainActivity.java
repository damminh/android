package com.example.minhdv.preferenceuserpass;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText txtUsername, txtPassword;
    Button btnDangNhap,btnThoat;
    CheckBox chkLuuThongTin;

    String tenThongTinDangNhap = "login";
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
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        chkLuuThongTin = (CheckBox) findViewById(R.id.ckLuuThongTinDangNhap);
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        btnThoat = (Button) findViewById(R.id.btnThoat);
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences preferences = getSharedPreferences(
                tenThongTinDangNhap,MODE_PRIVATE
        );
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("UserName",txtUsername.getText().toString());
        editor.putString("PassWord",txtPassword.getText().toString());
        editor.putBoolean("SAVE", chkLuuThongTin.isChecked());
        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences(tenThongTinDangNhap
        ,MODE_PRIVATE);
        String userName = preferences.getString("UserName","");
        String passWord = preferences.getString("PassWord","");
        boolean save = preferences.getBoolean("SAVE",false);

        if(save) {
            txtUsername.setText(userName);
            txtPassword.setText(passWord);
        }
    }
}
