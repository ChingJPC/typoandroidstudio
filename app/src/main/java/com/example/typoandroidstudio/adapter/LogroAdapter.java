package com.example.typoandroidstudio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.typoandroidstudio.R;
import com.example.typoandroidstudio.model.Logros;

import java.util.List;

public class LogroAdapter extends BaseAdapter {
    private List<Logros> logros;
    private Context context;

    public LogroAdapter(List<Logros> logros, Context context) {
        this.logros = logros;
        this.context = context;
    }

    @Override
    public int getCount() {
        return logros.size();
    }

    @Override
    public Object getItem(int position) {
        return logros.get(position);
    }

    @Override
    public long getItemId(int position) {
        return logros.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.logros_item_layout, null);
        }

        Logros logro = logros.get(position);

        TextView txtTipoLogro = convertView.findViewById(R.id.textnombrelogro);
        TextView txtTiempoSemanal = convertView.findViewById(R.id.texttiempologro);

        txtTipoLogro.setText(logro.getTipoLogro());
        txtTiempoSemanal.setText(logro.getTiempoSemanal());

        return convertView;
    }
}

