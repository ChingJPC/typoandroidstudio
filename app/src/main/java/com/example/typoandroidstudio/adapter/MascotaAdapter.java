package com.example.typoandroidstudio.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.typoandroidstudio.R;
import com.example.typoandroidstudio.infomascota.AgendamientoMascotaActivity;
import com.example.typoandroidstudio.model.Mascota;
import com.example.typoandroidstudio.model.Tipomascota;

import java.util.List;
import java.util.logging.SimpleFormatter;

public class MascotaAdapter extends BaseAdapter {
    private List<Mascota> mascotas;
    private Context context;

    public void next(View view) {
        Intent intent = new Intent(context, AgendamientoMascotaActivity.class);
        context.startActivity(intent);
    }


    public MascotaAdapter(List<Mascota> mascota, Context context) {
        this.mascotas = mascota;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mascotas.size();
    }

    @Override
    public Object getItem(int position) {
        return mascotas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mascotas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.mascota_item_layout,null);
        }
        Mascota mascota=mascotas.get(position);
        TextView txtNombre = convertView.findViewById(R.id.textnombre);
        TextView txtID = convertView.findViewById(R.id.textid);
        ImageButton agendamientomascotas = convertView.findViewById(R.id.agendamientomascotas);
        TextView txtEdad = convertView.findViewById(R.id.textedad);
        TextView txtRaza = convertView.findViewById(R.id.txtraza);
        TextView txtPeso = convertView.findViewById(R.id.textpeso);
        TextView txtTamaño = convertView.findViewById(R.id.txttamaño);
        TextView txtSexo = convertView.findViewById(R.id.textsexo);

        txtNombre.setText(mascota.getNombre_Mascota());
        txtID.setText(String.valueOf(mascota.getId()));
        txtEdad.setText(""+ mascota.getEdad());
        txtRaza.setText(mascota.getRaza());
        txtPeso.setText(""+ mascota.getPeso());
        String valueTamaño = String.format("%.2f",mascota.getTamaño());
        txtTamaño.setText(valueTamaño);
        txtSexo.setText(""+mascota.getSexo());

        agendamientomascotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AgendamientoMascotaActivity.class);
                intent.putExtra("id", mascota.getId());
                context.startActivity(intent);
                //next(v);

            }
        });
        return convertView;
    }
}
