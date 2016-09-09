package com.example.minhdv.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.minhdv.listviewnangcao.R;
import com.example.minhdv.model.DanhBa;

import java.util.List;

/**
 * Created by minhdv on 8/2/2016.
 */
public class DanhBaAdapter extends ArrayAdapter<DanhBa> {
    //màn hình sử dụng giao diẹn
    Activity context;
    //layout cho từng dòng muốn hiển thị
    int resource;

    //danh sách nguồn dữ liệu muốn hiển thị lên giao diện
    List<DanhBa> objects;
    public DanhBaAdapter(Activity context, int resource, List<DanhBa> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();

        View row = inflater.inflate(this.resource, null);

        TextView txtTen = (TextView) row.findViewById(R.id.txtTen);
        TextView txtPhone = (TextView) row.findViewById(R.id.txtSDT);
        ImageButton btnCall = (ImageButton) row.findViewById(R.id.btnCall);
        ImageButton btnDetail = (ImageButton) row.findViewById(R.id.btnDetail);
        ImageButton btnSms = (ImageButton) row.findViewById(R.id.btnSms);

        //tra ve danh ba hien tai muon ve
        DanhBa danhBa = this.objects.get(position);
        txtTen.setText(danhBa.getTen());
        txtPhone.setText(danhBa.getPhone());

        return row;
    }
}
