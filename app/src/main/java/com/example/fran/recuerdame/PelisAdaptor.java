package com.example.fran.recuerdame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by yatan on 6/29/16.
 */
public class PelisAdaptor extends BaseAdapter {
    Context context;
    String[] data;
    private static LayoutInflater inflater = null;

    public PelisAdaptor(Context context, String[] data) {
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null)
            view = inflater.inflate(R.layout.item_lista, null);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(data[position]);
        return view;
    }
}
