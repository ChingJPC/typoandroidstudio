package com.example.typoandroidstudio.infomascota;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageButton;

import com.example.typoandroidstudio.R;
import com.example.typoandroidstudio.model.Mascota;
import com.example.typoandroidstudio.network.MascotaAPIS.MascotaAPIClient;
import com.example.typoandroidstudio.network.MascotaAPIS.MascotaAPIService;

public class IndexMascotaActivity extends AddMascotaActivity {
    public void add(View view) {
        startActivity(new Intent(this, AddMascotaActivity.class));
    }
    ListView listamascota;
    private MascotaAPIService service;
    private Mascota mascotaSeleccionada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_mascota);
        service = MascotaAPIClient.getMascotaInstance();
        listamascota = findViewById(R.id.listamascota);
        listamascota.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listamascota.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Mascota valor = (Mascota) listamascota.getItemAtPosition(position);
                Toast.makeText(IndexMascotaActivity.this, "POS= " + position + " " + valor, Toast.LENGTH_LONG).show();
            }
        });

        AppCompatImageButton btneliminar = findViewById(R.id.btnEliminar);
        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarMascota();
            }
        });
        listamascota.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mascotaSeleccionada = (Mascota) listamascota.getItemAtPosition(position);
                Toast.makeText(IndexMascotaActivity.this, "POS= " + position + " " + mascotaSeleccionada, Toast.LENGTH_LONG).show();
            }
        });
    }
}