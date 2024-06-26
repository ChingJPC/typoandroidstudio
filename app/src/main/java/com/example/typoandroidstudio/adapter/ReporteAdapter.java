package com.example.typoandroidstudio.adapter;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.typoandroidstudio.R;
import com.example.typoandroidstudio.model.Agendamiento;
import com.example.typoandroidstudio.model.Reportes;

import java.util.List;

public class ReporteAdapter extends BaseAdapter {

    private List<Agendamiento> agendamientoList;
    private Context context;

    public ReporteAdapter(List<Agendamiento> agendamientoList, Context context) {
        this.context = context;
        this.agendamientoList = agendamientoList;
    }

    @Override
    public int getCount() {
        return agendamientoList.size();
    }

    @Override
    public Object getItem(int position) {
        return agendamientoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.reporte_uno_item_layout, parent, false);
        }

        // Obtener el objeto Mascota en la posición actual
        Agendamiento agendamiento = agendamientoList.get(position);

        // Obtener las referencias a los elementos de la fila
        TextView txtID = convertView.findViewById(R.id.idagendamiento);
        TextView txtFECHA = convertView.findViewById(R.id.fechaagendamiento);

        // Establecer los valores de los elementos de la fila con los datos de la mascota actual
        txtID.setText(String.valueOf(agendamiento.getId()));
        txtFECHA.setText(String.valueOf(agendamiento.getFecha_Agendamiento()));

        return convertView;
    }
}

