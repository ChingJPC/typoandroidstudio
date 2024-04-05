package com.example.typoandroidstudio.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.typoandroidstudio.R;
import com.example.typoandroidstudio.agendamiento.MascotaLogrosActivity;
import com.example.typoandroidstudio.infomascota.AgendamientoMascotaActivity;
import com.example.typoandroidstudio.model.Logros;
import com.example.typoandroidstudio.model.Mascota;
import com.example.typoandroidstudio.model.Mascotalogros;

import java.util.List;

public class MascotaLogrosAdapter extends BaseAdapter {
    private Context context;
    private List<Mascotalogros> logrosList;

    public MascotaLogrosAdapter(Context context, List<Mascotalogros> logrosList) {
        this.context = context;
        this.logrosList = logrosList;
    }

    @Override
    public int getCount() {return logrosList.size();
    }

    @Override
    public Object getItem(int position) {
        return logrosList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return logrosList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Si convertView es nulo, inflar el diseño de la fila
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_mascota_logros_item_layout, null);
        }

        // Obtener el objeto Mascota en la posición actual
        Mascotalogros mascotalogros = logrosList.get(position);

        // Obtener las referencias a los elementos de la fila
        TextView txtNombre = convertView.findViewById(R.id.tipoLogroTextView);
        TextView txtTiempo = convertView.findViewById(R.id.tiempoSemanalTextView);

        // Establecer los valores de los elementos de la fila con los datos de la mascota actual
        txtNombre.setText(mascotalogros.getTipoLogro());
        txtTiempo.setText(String.valueOf(mascotalogros.getTiempoSemanal()));

        return convertView;
    }
}

