package com.example.typoandroidstudio.infomascota;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.typoandroidstudio.Datainfo;
import com.example.typoandroidstudio.R;
import com.example.typoandroidstudio.model.Mascota;
import com.example.typoandroidstudio.model.Tipomascota;
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
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    public IndexMascotaFragment() {
        // Required empty public constructor
    }

    public static IndexMascotaFragment


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IndexMascotaFragment.
     */
    // TODO: Rename and change types and number of parameters
    newInstance(String param1, String param2) {
        IndexMascotaFragment fragment = new IndexMascotaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_index_mascota, container, false);
        service = MascotaAPIClient.getMascotaInstance();

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Spinner caja7 = view.findViewById(R.id.spinner);
        Log.i("Spinner",String.valueOf(caja7));

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Spinner spinnerSexo = view.findViewById(R.id.txtgenero);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.ArraySexo, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSexo.setAdapter(adapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        service.getTipos(Datainfo.restLogin.getToken_type()+" "+
                Datainfo.restLogin.getAccess_token()).enqueue(new Callback<List<Tipomascota>>() {
            @Override
            public void onResponse(Call<List<Tipomascota>> call, Response<List<Tipomascota>> response) {
                if (response.isSuccessful()){
                    //Log.i("Lista", response.body().toString());
                    cargarspinner(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Tipomascota>> call, Throwable t) {

            }
        });
    }

    private void cargarspinner(List<Tipomascota> body) {
        Spinner caja7 = getView().findViewById(R.id.spinner);
        Log.i("Spinner",String.valueOf(caja7));
        ArrayAdapter<Tipomascota> tipomascotaArrayAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_expandable_list_item_1
        );
        caja7.setAdapter(tipomascotaArrayAdapter);
    }

    public void click(View view) {
        if (view.getId() == R.id.btnadd) {
            EditText caja1 = getView().findViewById(R.id.txtnombre);
            String value1 = caja1.getText().toString();
            EditText caja2 = getView().findViewById(R.id.txtedad);
            String value2 = caja2.getText().toString();
            EditText caja3 = getView().findViewById(R.id.txtraza);
            String value3 = caja3.getText().toString();
            EditText caja4 = getView().findViewById(R.id.txtpeso);
            String value4 = caja4.getText().toString();
            Spinner spinnerSexo = getView().findViewById(R.id.txtgenero);
            String value6 = (String) spinnerSexo.getItemAtPosition(spinnerSexo.getSelectedItemPosition());
            EditText caja5 = getView().findViewById(R.id.txttamaño);
            String value5 = caja5.getText().toString();

            if (value1.length() > 0) {
                Mascota nuevaMascota = new Mascota(value1,Integer.parseInt(value2),value3, Integer.parseInt(value4), Float.parseFloat(value5),value6);
                nuevaMascota.setUser_id(Datainfo.restLogin.getUser().getId());
                Spinner spinner = getView().findViewById(R.id.spinner);
                Tipomascota tipomascota = (Tipomascota) spinner.getItemAtPosition(spinner.getSelectedItemPosition());
                nuevaMascota.setId_tipomascota(tipomascota.getId());
                service.add(Datainfo.restLogin.getToken_type()+" "+
                        Datainfo.restLogin.getAccess_token(),nuevaMascota ).enqueue(new Callback<Mascota>() {
                    @Override
                    public void onResponse(Call<Mascota> call, Response<Mascota> response) {
                        Toast.makeText(getActivity(), "Mascota creada correctamente", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Mascota> call, Throwable t) {
                        Toast.makeText(getActivity(), "Error al crear la mascota", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(getActivity(), "Ingrese un número válido", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
