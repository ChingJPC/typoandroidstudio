package com.example.typoandroidstudio.infomascota;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.typoandroidstudio.Datainfo;
import com.example.typoandroidstudio.R;
import com.example.typoandroidstudio.adapter.MascotaAdapter;
import com.example.typoandroidstudio.model.Mascota;
import com.example.typoandroidstudio.network.MascotaAPIS.MascotaAPIClient;
import com.example.typoandroidstudio.network.MascotaAPIS.MascotaAPIService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IndexMascotaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IndexMascotaFragment extends Fragment {
    private MascotaAPIService service;
    private ListView listamascota;

    public IndexMascotaFragment() {
        // Required empty public constructor
    }

    public static IndexMascotaFragment newInstance() {
        return new IndexMascotaFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_index_mascota, container, false);
        service = MascotaAPIClient.getMascotaInstance();
        listamascota = view.findViewById(R.id.listamascota);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        loadData();
    }

    private void loadData() {
        service.getAll(Datainfo.restLogin.getToken_type() + " " +
                Datainfo.restLogin.getAccess_token()).enqueue(new Callback<List<Mascota>>() {
            @Override
            public void onResponse(Call<List<Mascota>> call, Response<List<Mascota>> response) {
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
        Log.i("TYPO", mascotas.toString());
        MascotaAdapter datos = new MascotaAdapter(mascotas, getActivity());
        listamascota.setAdapter(datos);
    }

    /*public void regresar(View view) {
        requireActivity().finish();
    }*/
}

