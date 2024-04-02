package com.example.typoandroidstudio.agendamiento;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.typoandroidstudio.Datainfo;
import com.example.typoandroidstudio.R;
import com.example.typoandroidstudio.adapter.LogroAdapter;
import com.example.typoandroidstudio.adapter.MascotaAdapter;
import com.example.typoandroidstudio.infomascota.IndexMascotaFragment;
import com.example.typoandroidstudio.model.Logros;
import com.example.typoandroidstudio.model.Mascota;
import com.example.typoandroidstudio.network.MascotaAPIS.MascotaAPIClient;
import com.example.typoandroidstudio.network.MascotaAPIS.MascotaAPIService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LogrosFragmentActivity#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LogrosFragmentActivity extends Fragment {
    private MascotaAPIService service;
    private ListView listalogros;
    public LogrosFragmentActivity() {
        // Required empty public constructor
    }

    public static LogrosFragmentActivity newInstance() {
        return new LogrosFragmentActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_logros_activity, container, false);
        service = MascotaAPIClient.getMascotaInstance();
        listalogros = view.findViewById(R.id.listalogros);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        loadData();
    }
    private void loadData() {
        service.getAllLogros(Datainfo.restLogin.getToken_type() + " " +
                Datainfo.restLogin.getAccess_token()).enqueue(new Callback<List<Logros>>() {
            @Override
            public void onResponse(Call<List<Logros>> call, Response<List<Logros>> response) {
                if (response.isSuccessful()) {
                    List<Logros> logros = response.body();
                    cargarDatos(logros);
                }
            }

            @Override
            public void onFailure(Call<List<Logros>> call, Throwable t) {
                Log.e("Login", t.getMessage());
            }
        });
    }

    private void cargarDatos(List<Logros> logros) {
        Log.i("TYPO", logros.toString());
        LogroAdapter datos = new LogroAdapter(logros, getActivity());
        listalogros.setAdapter(datos);
    }
    /*public void regresar(View view) {
        requireActivity().finish();
    }*/

}