package com.example.minhdv.contactmanager;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minhdv.model.Contact;

public class NhanTinSmsActivity extends AppCompatActivity {
    TextView txtNguoiNhan;
    EditText txtNoiDung;
    ImageButton btnGuiTinNhan;

    Contact selectedContact = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_tin);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnGuiTinNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyTinNhan();
            }
        });
    }

    private void xuLyTinNhan() {
        final SmsManager sms = SmsManager.getDefault();
        Intent msgSent = new Intent("ACTION_MSG_SENT");
        final PendingIntent pendingMsgSent =
                PendingIntent.getBroadcast(this, 0, msgSent, 0);
        registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                int result = getResultCode();
                String msg="Send OK";
                if (result != Activity.RESULT_OK) {
                    msg="Send failed";
                }
                Toast.makeText(NhanTinSmsActivity.this, msg,
                        Toast.LENGTH_LONG).show();
            }
        }, new IntentFilter("ACTION_MSG_SENT"));

        sms.sendTextMessage(selectedContact.getPhone(), null, txtNoiDung.getText()+"",
                pendingMsgSent, null);
        finish();
    }



    private void addControls() {
        txtNguoiNhan = (TextView) findViewById(R.id.txtSoDienThoaiNguoiNhan);
        txtNoiDung = (EditText) findViewById(R.id.txtNoiDungTinNhan);
        btnGuiTinNhan = (ImageButton) findViewById(R.id.btnGuiTinNhan);

        Intent intent = getIntent();
        selectedContact = (Contact) intent.getSerializableExtra("CONTACT");
        txtNguoiNhan.setText(selectedContact.getName() + "-" + selectedContact.getPhone());
    }
}
