package com.example.minhdv.hoccontextmenu;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnRonaldo,btnMessi,btnKaka;

    Button btnLastedSelected = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnRonaldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLastedSelected = btnRonaldo;
            }
        });

        btnRonaldo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                btnLastedSelected = btnRonaldo;
                return false;
            }
        });

        btnMessi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLastedSelected = btnMessi;
            }
        });
        btnMessi.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                btnLastedSelected = btnMessi;
                return false;
            }
        });

        btnKaka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLastedSelected = btnKaka;
            }
        });
        btnKaka.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                btnLastedSelected = btnKaka;
                return false;
            }
        });
    }

    private void addControls() {
        btnKaka = (Button) findViewById(R.id.btnkaka);
        btnMessi = (Button) findViewById(R.id.btnMessi);
        btnRonaldo = (Button) findViewById(R.id.btnRonaldo);

        registerForContextMenu(btnKaka);
        registerForContextMenu(btnMessi);
        registerForContextMenu(btnRonaldo);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contextmenu_main,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.mnuInDam) {
            btnLastedSelected.setTypeface(null, Typeface.BOLD);
        }
        if(item.getItemId() == R.id.mnuMauDo) {
            btnLastedSelected.setTextColor(Color.RED);
        }
        if(item.getItemId() == R.id.mnuXoa) {
            btnLastedSelected.setVisibility(View.INVISIBLE);
        }
        return super.onContextItemSelected(item);
    }
}
