package com.example.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<ThoiTiet> arrayList;

    public CustomAdapter(Context context, ArrayList<ThoiTiet> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view= inflater.inflate(R.layout.dong_listview,null);
             // khai bao thuoc tinh ben dong_listview

        ThoiTiet thoiTiet = arrayList.get(position);

        TextView txtDay= view.findViewById(R.id.tvDay2);
        TextView txtStatus = view.findViewById(R.id.tvStatus2);
        TextView txtMaxtemp= view.findViewById(R.id.tvMaxTemp);
        TextView txtMintemp= view.findViewById(R.id.tvMinTemp);


        txtDay.setText(thoiTiet.Day);
        txtStatus.setText(thoiTiet.Status);
        txtMaxtemp.setText(thoiTiet.Maxtemp+"°C");
        txtMintemp.setText(thoiTiet.Mintemp+"°C");

        ImageView ivStatus= (ImageView) view.findViewById(R.id.IvStatus2);

        return view;
    }
}
