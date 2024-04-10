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
import android.widget.Toast;

import com.example.typoandroidstudio.R;
import com.example.typoandroidstudio.agendamiento.MascotaLogrosActivity;
import com.example.typoandroidstudio.infomascota.AgendamientoMascotaActivity;
import com.example.typoandroidstudio.model.Logros;
import com.example.typoandroidstudio.model.Mascota;
import com.example.typoandroidstudio.model.Mascotalogros;
import com.example.typoandroidstudio.model.Tipomascota;

import java.util.List;
import java.util.logging.SimpleFormatter;

public class MascotaAdapter extends BaseAdapter {
    private List<Mascota> mascotas;
    private List<Mascotalogros> logros;
    private Context context;

    // Constructor que recibe la lista de mascotas y el contexto
    public MascotaAdapter(List<Mascota> mascota, Context context) {
        this.mascotas = mascota;
        this.context = context;
    }

    // Método para obtener la cantidad de elementos en la lista
    @Override
    public int getCount() {
        return mascotas.size();
    }

    // Método para obtener un elemento en una posición específica
    @Override
    public Object getItem(int position) {
        return mascotas.get(position);
    }

    // Método para obtener el ID de un elemento en una posición específica
    @Override
    public long getItemId(int position) {
        return mascotas.get(position).getId();
    }

    // Constructor que recibe la lista de mascotas y el contexto


    // Método para mostrar cada fila en la lista
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Si convertView es nulo, inflar el diseño de la fila
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.mascota_item_layout, null);
        }

        // Obtener el objeto Mascota en la posición actual
        Mascota mascota = mascotas.get(position);

        // Obtener las referencias a los elementos de la fila
        TextView txtNombre = convertView.findViewById(R.id.textnombre);
        TextView txtID = convertView.findViewById(R.id.textid);
        ImageButton agendamientomascotas = convertView.findViewById(R.id.agendamientomascotas);
        ImageButton medalla = convertView.findViewById(R.id.medalla);
        TextView txtEdad = convertView.findViewById(R.id.textedad);
        TextView txtRaza = convertView.findViewById(R.id.txtraza);
        TextView txtPeso = convertView.findViewById(R.id.textpeso);
        TextView txtTamaño = convertView.findViewById(R.id.txttamaño);
        TextView txtSexo = convertView.findViewById(R.id.textsexo);

        // Establecer los valores de los elementos de la fila con los datos de la mascota actual
        txtNombre.setText(mascota.getNombre_Mascota());
        txtID.setText(String.valueOf(mascota.getId()));
        txtEdad.setText("" + mascota.getEdad());
        txtRaza.setText(mascota.getRaza());
        txtPeso.setText("" + mascota.getPeso() + " kg"); // Agregar " kg" al final del valor de peso
        String valueTamaño = String.format("%.2f", mascota.getTamaño());
        txtTamaño.setText(valueTamaño + " cm"); // Agregar " cm" al final del valor de tamaño
        txtSexo.setText("" + mascota.getSexo());

        // Acción del botón "Agendar" para abrir la actividad AgendamientoMascotaActivity
        agendamientomascotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un intent para abrir la actividad AgendamientoMascotaActivity
                Intent intent = new Intent(context, AgendamientoMascotaActivity.class);
                // Pasar el ID de la mascota seleccionada como extra en el intent
                intent.putExtra("id", mascota.getId());
                // Iniciar la actividad
                context.startActivity(intent);
            }
        });

        medalla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un intent para abrir la actividad AgendamientoMascotaActivity
                Intent intent = new Intent(context, MascotaLogrosActivity.class);
                // Pasar el ID de la mascota seleccionada como extra en el intent
                intent.putExtra("id", mascota.getId());
                // Iniciar la actividad
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}

