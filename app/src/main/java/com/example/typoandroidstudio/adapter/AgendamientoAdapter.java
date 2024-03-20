package com.example.typoandroidstudio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.typoandroidstudio.R;
import com.example.typoandroidstudio.model.Agendamiento;

import java.util.List;

public class AgendamientoAdapter extends BaseAdapter {
    private List<Agendamiento> agendamientos;
    private Context context;
    public AgendamientoAdapter(List<Agendamiento> agendamiento, Context context) {
        this.agendamientos = agendamiento;
        this.context = context;
    }

    @Override
    public int getCount() {
        return agendamientos.size();
    }

    @Override
    public Object getItem(int position) {
        return agendamientos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return agendamientos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.agendamiento_mascota_item_layout,null);
        }
        Agendamiento agendamiento=agendamientos.get(position);

        TextView txtId = convertView.findViewById(R.id.textid);
        TextView txtActividad = convertView.findViewById(R.id.textViewActividad);
        TextView txtFecha = convertView.findViewById(R.id.textViewFechaAgendamiento);
        TextView txtTiempo = convertView.findViewById(R.id.textViewTiempo);

        txtId.setText(String.valueOf(agendamiento.getId()));
        txtActividad.setText(agendamiento.getNombre_actividad());
        txtFecha.setText(String.valueOf(agendamiento.getFecha_Agendamiento()));
        txtTiempo.setText(String.valueOf(agendamiento.getTiempo_asignado_actividad()));
        if (agendamiento.isCumplida()==1) {
            TextView textView26 = convertView.findViewById(R.id.textView26);
            textView26.setVisibility(View.GONE);
            ImageButton imageButton2 = convertView.findViewById(R.id.imageButton2);
            imageButton2.setVisibility(View.GONE);
        }
        return convertView;
    }
}

