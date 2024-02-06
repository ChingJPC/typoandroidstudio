package com.example.typoandroidstudio.infomascota;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageButton;

import com.example.typoandroidstudio.Datainfo;
import com.example.typoandroidstudio.PerfilUserActivity;
import com.example.typoandroidstudio.R;
import com.example.typoandroidstudio.adapter.MascotaAdapter;
import com.example.typoandroidstudio.model.Mascota;
import com.example.typoandroidstudio.network.MascotaAPIS.MascotaAPIClient;
import com.example.typoandroidstudio.network.MascotaAPIS.MascotaAPIService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IndexMascotaActivity extends AddMascotaActivity {
    public void add(View view) {
        startActivity(new Intent(this, AddMascotaActivity.class));
    }
    public void back(View view) {
        finish();
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


        listamascota.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mascotaSeleccionada= (Mascota) listamascota.getItemAtPosition(position);
                Toast.makeText(IndexMascotaActivity.this, "POS= " + position + " " + mascotaSeleccionada, Toast.LENGTH_LONG).show();
            }
        });

        @SuppressLint("WrongViewCast") AppCompatImageButton btneliminar = findViewById(R.id.btnEliminar);
        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {eliminarMascota();}
        });
        listamascota.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mascotaSeleccionada = (Mascota) listamascota.getItemAtPosition(position);
                Toast.makeText(IndexMascotaActivity.this, "POS= " + position + " " + mascotaSeleccionada, Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        loadData();
    }

    private void loadData() {
        service.getAll(Datainfo.restLogin.getToken_type()+" "+
                Datainfo.restLogin.getAccess_token()).enqueue(new Callback<List<Mascota>>() {
            @Override
            public void onResponse(Call<List<Mascota>> call, Response<List<Mascota>> response) {
                if (response.isSuccessful()) {
                    List<Mascota> mascotas = response.body();
                    cargarDatos(mascotas);
                }
            }

            @Override
            public void onFailure(Call<List<Mascota>> call, Throwable t) {

            }
        });
    }

    private void cargarDatos(List<Mascota> mascotas) {
        Log.i("TYPO",mascotas.toString());
        MascotaAdapter datos = new MascotaAdapter(mascotas, this);
        listamascota.setAdapter(datos);
    }

    public void eliminarMascota() {
        if (mascotaSeleccionada != null) {
            Toast.makeText(this, "*****"+mascotaSeleccionada.getId(), Toast.LENGTH_SHORT).show();
            service.delete(mascotaSeleccionada.getId()).enqueue(new Callback<Mascota>() {
                @Override
                public void onResponse(Call<Mascota> call, Response<Mascota> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(IndexMascotaActivity.this, "Mascota eliminada correctamente", Toast.LENGTH_SHORT).show();
                        loadData();
                    } else {
                        Toast.makeText(IndexMascotaActivity.this, "Error al eliminar la mascota", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Mascota> call, Throwable t) {
                    Toast.makeText(IndexMascotaActivity.this, "Error de red al eliminar la mascota", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(IndexMascotaActivity.this, "Seleccione una mascota antes de eliminar", Toast.LENGTH_SHORT).show();
        }
    }
}