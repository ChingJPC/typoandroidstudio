package com.example.typoandroidstudio.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.typoandroidstudio.R;
import com.example.typoandroidstudio.infomascota.AgendamientoMascotaActivity;
import com.example.typoandroidstudio.model.Agendamiento;

import java.util.List;

public class AgendamientoAdapter extends BaseAdapter {
    private List<Agendamiento> agendamientos;
    private Context context;

    public AgendamientoAdapter(List<Agendamiento> agendamientos, Context context) {
        this.agendamientos = agendamientos;
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
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.agendamiento_mascota_item_layout, null);
        }

        Agendamiento agendamiento = agendamientos.get(position);

        TextView txtId = convertView.findViewById(R.id.textid);
        TextView txtActividad = convertView.findViewById(R.id.textViewActividad);
        TextView txtFecha = convertView.findViewById(R.id.textViewFechaAgendamiento);
        TextView txtTiempo = convertView.findViewById(R.id.textViewTiempo);
        TextView textView27 = convertView.findViewById(R.id.textView27);

        txtId.setText(String.valueOf(agendamiento.getId()));
        txtActividad.setText(agendamiento.getNombre_actividad());
        txtFecha.setText(String.valueOf(agendamiento.getFecha_Agendamiento()));
        txtTiempo.setText(String.valueOf(agendamiento.getTiempo_asignado_actividad()));

        ImageView imageView = convertView.findViewById(R.id.imageButton3); // Supongamos que la imagen tiene el ID imageView

        // Mostrar la imagen y el texto mientras la actividad no esté cumplida (valor 0)
        if (agendamiento.isCumplida() == 0) {
            imageView.setVisibility(View.VISIBLE);
            textView27.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
            textView27.setVisibility(View.GONE);
        }

        ImageButton imageButton = convertView.findViewById(R.id.imageButton3);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long agendamientoId = agendamiento.getId();
                byte cumplida = 1; // O el valor que corresponda para indicar que la actividad está cumplida
                if (context instanceof AgendamientoMascotaActivity) {
                    ((AgendamientoMascotaActivity) context).actualizarEstadoCumplida(agendamientoId, cumplida);
                }
                // Ocultar la imagen y el texto al marcar como cumplida
                imageView.setVisibility(View.GONE);
                textView27.setVisibility(View.GONE);
            }
        });

        return convertView;
    }
}








