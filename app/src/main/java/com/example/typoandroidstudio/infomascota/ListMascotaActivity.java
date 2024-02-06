package com.example.typoandroidstudio.infomascota;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.typoandroidstudio.Datainfo;
import com.example.typoandroidstudio.PerfilUserActivity;
import com.example.typoandroidstudio.R;
import com.example.typoandroidstudio.adapter.MascotaAdapter;
import com.example.typoandroidstudio.model.Mascota;
import com.example.typoandroidstudio.network.LoginAPIS.LoginAPIClient;
import com.example.typoandroidstudio.network.LoginAPIS.LoginAPIService;
import com.example.typoandroidstudio.network.MascotaAPIS.MascotaAPIClient;
import com.example.typoandroidstudio.network.MascotaAPIS.MascotaAPIService;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListMascotaActivity extends AppCompatActivity {
    ListView listamascota;
    private MascotaAPIService service;
    public void regresar (View view){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mascota);
        service= MascotaAPIClient.getMascotaInstance();
        listamascota=findViewById(R.id.listamascota);
    }
    @Override
    protected void onStart(){
        super.onStart();
        loadData();
    }
    private void loadData() {
        Log.i("Login", "Load");
        service.getAll(Datainfo.restLogin.getToken_type()+" "+
                Datainfo.restLogin.getAccess_token()).enqueue(new Callback<List<Mascota>>() {
            @Override
            public void onResponse(Call<List<Mascota>> call, Response<List<Mascota>> response) {
                Log.i("Login", response.toString());
                if (response.isSuccessful()) {
                    List<Mascota> mascota = response.body();
                    cargarDatos(mascota);
                }
            }
                @Override
                public void onFailure(Call<List<Mascota>> call, Throwable t) {
                    Log.e("Login", t.getMessage());
                }
            });
        }
        private void cargarDatos(List<Mascota> mascotas) {
            Log.i("TYPO",mascotas.toString());
            MascotaAdapter datos = new MascotaAdapter(mascotas, this);
            listamascota.setAdapter((ListAdapter) datos);
        }
    }