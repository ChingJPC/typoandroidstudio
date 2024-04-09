package com.example.typoandroidstudio.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.typoandroidstudio.Datainfo;
import com.example.typoandroidstudio.R;
import com.example.typoandroidstudio.adapter.AgendamientoAdapter;
import com.example.typoandroidstudio.adapter.MascotaAdapter;
import com.example.typoandroidstudio.adapter.ReporteAdapter;
import com.example.typoandroidstudio.model.Reportes;
import com.example.typoandroidstudio.model.User;
import com.example.typoandroidstudio.network.MascotaAPIS.MascotaAPIClient;
import com.example.typoandroidstudio.network.MascotaAPIS.MascotaAPIService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReporteFragmentActivity#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReporteFragmentActivity extends Fragment {
    private MascotaAPIService service;
    private TextView textViewTotalAgendamientos;
    private ProgressBar progressBarPorcentajeCumplimiento;
    private TextView textViewMesReporte;
    private ListView listacumplidos, listanocumplidos;

    public ReporteFragmentActivity() {
        // Required empty public constructor
    }

    public static ReporteFragmentActivity newInstance() {
        return new ReporteFragmentActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reporte_activity, container, false);
        service = MascotaAPIClient.getMascotaInstance();

        listacumplidos = view.findViewById(R.id.listacumplidos);
        listanocumplidos = view.findViewById(R.id.listanocumplidos);
        textViewTotalAgendamientos = view.findViewById(R.id.total_agendamientos);
        progressBarPorcentajeCumplimiento = view.findViewById(R.id.porcentaje_cumplimiento);
        textViewMesReporte = view.findViewById(R.id.mes_reporte);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        loadData();
    }

    private void loadData() {
        if (Datainfo.restLogin != null) {
            String token = Datainfo.restLogin.getToken_type() + " " + Datainfo.restLogin.getAccess_token();
            User user = Datainfo.restLogin.getUser();
            if (user != null) {
                long usuarioId = user.getId(); // Suponiendo que getId() devuelve el ID del usuario
                service.ReporteCumplimiento(token, usuarioId).enqueue(new Callback<Reportes>() {
                    @Override
                    public void onResponse(Call<Reportes> call, Response<Reportes> response) {
                        if (response.isSuccessful()) {
                            Reportes reportes = response.body();
                            mostrarDatos(reportes);
                        } else {
                            // Manejar respuesta no exitosa
                        }
                    }

                    @Override
                    public void onFailure(Call<Reportes> call, Throwable t) {
                        Log.e("Reportes", t.getMessage());
                    }
                });
            }
        }
    }

    private void mostrarDatos(Reportes reportes) {
        // Suponiendo que solo quieres mostrar el primer reporte
        if (reportes!=null) {
            textViewTotalAgendamientos.setText(String.valueOf(reportes.getTotal_agendamientos()));
            progressBarPorcentajeCumplimiento.setProgress((int) reportes.getPorcentaje_cumplimiento());
            textViewMesReporte.setText(reportes.getMes_reporte());
            ReporteAdapter datos = new ReporteAdapter(reportes.getAgendamientos_cumplidos(), getActivity());
            listacumplidos.setAdapter(datos);
            ReporteAdapter datos1 = new ReporteAdapter(reportes.getAgendamientos_no_cumplidos(), getActivity());
            listanocumplidos.setAdapter(datos1);
        }
    }
}

