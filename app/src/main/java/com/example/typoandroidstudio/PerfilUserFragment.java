package com.example.typoandroidstudio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.typoandroidstudio.model.User;
import com.example.typoandroidstudio.network.LoginAPIS.LoginAPIClient;
import com.example.typoandroidstudio.network.LoginAPIS.LoginAPIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PerfilUserFragment extends Fragment {
    LoginAPIService service;
    String auth;
    EditText editTextUsername;
    EditText editTextApellido;
    EditText editTextTelefono;
    EditText editTextFecha;
    TextView textViewEmail;
    Button buttonGuardar;

    User user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil_user, container, false);

        service=LoginAPIClient.getLoginService();
        editTextUsername = view.findViewById(R.id.user);
        editTextApellido = view.findViewById(R.id.apellido);
        editTextTelefono = view.findViewById(R.id.numero);
        editTextFecha = view.findViewById(R.id.fecha);
        editTextFecha = view.findViewById(R.id.fecha);
        textViewEmail = view.findViewById(R.id.email);
        buttonGuardar = view.findViewById(R.id.guardar);

        user = Datainfo.restLogin.getUser();

        editTextUsername.setText(user.getName());
        editTextApellido.setText(user.getApellido());
        editTextTelefono.setText(user.getTelefono());
        textViewEmail.setText(user.getEmail());
        editTextFecha.setText(user.getFecha_nacimiento());

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarCambios();
            }
        });
        return view;
    }

    private void guardarCambios() {
        String name = editTextUsername.getText().toString().trim();
        String apellido = editTextApellido.getText().toString().trim();
        String telefono = editTextTelefono.getText().toString().trim();
        String fecha_nacimiento = editTextFecha.getText().toString().trim();

        user.setName(name);
        user.setApellido(apellido);
        user.setTelefono(telefono);
        user.setFecha_nacimiento(fecha_nacimiento);

        // Llamar al servicio API para actualizar los datos del usuario
        Call<User> call = service.updateProfile("Bearer " + Datainfo.restLogin.getAccess_token(), user.getId(), name, apellido, telefono, fecha_nacimiento);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    // Actualización exitosa, puedes mostrar un mensaje o realizar alguna acción adicional si lo deseas
                } else {
                    // Error en la actualización, puedes mostrar un mensaje de error
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // Error en la comunicación con el servidor, puedes mostrar un mensaje de error
            }
        });
    }
}
