package com.example.typoandroidstudio.infomascota;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.typoandroidstudio.R;
import com.example.typoandroidstudio.model.Mascota;
import com.example.typoandroidstudio.network.MascotaAPIS.MascotaAPIClient;
import com.example.typoandroidstudio.network.MascotaAPIS.MascotaAPIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMascotaActivity extends AppCompatActivity {
    private MascotaAPIService service;

    public void back(View view) {
        startActivity(new Intent(this, IndexMascotaActivity.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mascota);
        service = MascotaAPIClient.getMascotaInstance();

    }

    public void click(View view) {
        if (view.getId() == R.id.btnadd) {
            EditText caja1 = findViewById(R.id.txtnombre);
            String value1 = caja1.getText().toString();
            EditText caja2 = findViewById(R.id.txtedad);
            String value2 = caja2.getText().toString();
            EditText caja3 = findViewById(R.id.txtraza);
            String value3 = caja3.getText().toString();
            EditText caja4 = findViewById(R.id.txtpeso);
            String value4 = caja4.getText().toString();
            EditText caja5 = findViewById(R.id.txttamaño);
            String value5 = caja5.getText().toString();
            EditText caja6 = findViewById(R.id.txtsexo);
            String value6 = caja6.getText().toString();


            if (value1.length() > 0) {
                Mascota nuevaMascota = new Mascota(value1,Integer.parseInt(value2),value3, Integer.parseInt(value4), Integer.parseInt(value5),value6);

                service.add(nuevaMascota).enqueue(new Callback<Mascota>() {
                    @Override
                    public void onResponse(Call<Mascota> call, Response<Mascota> response) {
                        Toast.makeText(AddMascotaActivity.this, "Mascota creada correctamente", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Mascota> call, Throwable t) {
                        Toast.makeText(AddMascotaActivity.this, "Error al crear la mascota", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(AddMascotaActivity.this, "Ingrese un número válido", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
