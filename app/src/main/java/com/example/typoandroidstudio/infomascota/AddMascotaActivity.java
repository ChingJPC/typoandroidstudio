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
            EditText caja1 = findViewById(R.id.txtnumero);
            String value1 = caja1.getText().toString();
            EditText caja2 = findViewById(R.id.txtprograma);
            String value2 = caja2.getText().toString();
            EditText caja3 = findViewById(R.id.txtlider);
            String value3 = caja3.getText().toString();

            if (value1 != null && value1.length() > 0) {
                Mascota nuevaFicha = new Mascota(Integer.parseInt(value1), value2, value3);


                service.add(nuevaFicha).enqueue(new Callback<Ficha>() {
                    @Override
                    public void onResponse(Call<Ficha> call, Response<Ficha> response) {
                        Toast.makeText(AddFichaActivity.this, "Ficha creada correctamente", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Ficha> call, Throwable t) {
                        Toast.makeText(AddFichaActivity.this, "Error al crear la ficha", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(AddFichaActivity.this, "Ingrese un número válido", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
