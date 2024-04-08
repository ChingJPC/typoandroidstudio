package com.example.typoandroidstudio.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.typoandroidstudio.Datainfo;
import com.example.typoandroidstudio.R;
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
    private TextView textViewPorcentajeCumplimiento;
    private TextView textViewMesReporte;

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

        textViewTotalAgendamientos = view.findViewById(R.id.total_agendamientos);
        textViewPorcentajeCumplimiento = view.findViewById(R.id.porcentaje_cumplimiento);
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
                service.ReporteCumplimiento(token, usuarioId).enqueue(new Callback<List<Reportes>>() {
                    @Override
                    public void onResponse(Call<List<Reportes>> call, Response<List<Reportes>> response) {
                        if (response.isSuccessful()) {
                            List<Reportes> reportes = response.body();
                            mostrarDatos(reportes);
                        } else {
                            // Manejar respuesta no exitosa
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Reportes>> call, Throwable t) {
                        Log.e("Reportes", t.getMessage());
                    }
                });
            }
        }
    }

    private void mostrarDatos(List<Reportes> reportes) {
        // Suponiendo que solo quieres mostrar el primer reporte
        if (!reportes.isEmpty()) {
            Reportes primerReporte = reportes.get(0);
            textViewTotalAgendamientos.setText(String.valueOf(primerReporte.getTotal_agendamientos()));
            textViewPorcentajeCumplimiento.setText(String.valueOf(primerReporte.getPorcentaje_cumplimiento()));
            textViewMesReporte.setText(primerReporte.getMes_reporte());
        }
    }
}

