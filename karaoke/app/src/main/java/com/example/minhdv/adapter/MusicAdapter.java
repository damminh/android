package com.example.minhdv.adapter;

import android.app.Activity;
import android.content.ContentValues;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.minhdv.karaoke.MainActivity;
import com.example.minhdv.karaoke.R;
import com.example.minhdv.model.music;

import java.util.List;

/**
 * Created by minhdv on 8/2/2016.
 */
public class MusicAdapter extends ArrayAdapter<music> {
    Activity context;
    int resource;
    List<music> objects;
    public MusicAdapter(Activity context, int resource, List<music> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource, null);

        TextView txtMa = (TextView) row.findViewById(R.id.txtMa);
        TextView txtTen = (TextView)row.findViewById(R.id.txtTen);
        TextView txtCasi = (TextView) row.findViewById(R.id.txtCaSi);
        final ImageButton btnLike = (ImageButton) row.findViewById(R.id.btnLike);
        final ImageButton btnDisLike = (ImageButton) row.findViewById(R.id.btnDisLike);

        final music ms = this.objects.get(position);
        txtTen.setText(ms.getTen());
        txtCasi.setText(ms.getCaSi());
        txtMa.setText(ms.getMa());

        if(ms.isThich()) {
            btnLike.setVisibility(View.INVISIBLE);
            btnDisLike.setVisibility(View.VISIBLE);
        }
        else {
            btnLike.setVisibility(View.VISIBLE);
            btnDisLike.setVisibility(View.INVISIBLE);
        }

        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyThich(ms);
                btnLike.setVisibility(View.INVISIBLE);
                btnDisLike.setVisibility(View.VISIBLE);
            }
        });

        btnDisLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyKhongThich(ms);
                btnLike.setVisibility(View.VISIBLE);
                btnDisLike.setVisibility(View.INVISIBLE);
            }
        });

        return row;
    }



    private void xuLyKhongThich(music ms) {
        ContentValues row = new ContentValues();
        row.put("YEUTHICH","0");
        MainActivity.database.update("ArirangSongList", row,
                "MABH=?", new String[]{ms.getMa()});
    }

    private void xuLyThich(music ms) {
        ContentValues row = new ContentValues();
        row.put("YEUTHICH", "1");
        MainActivity.database.update("ArirangSongList", row,
                "MABH=?", new String[]{ms.getMa()});
    }
}
