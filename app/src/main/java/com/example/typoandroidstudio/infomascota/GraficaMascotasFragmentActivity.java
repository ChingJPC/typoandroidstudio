package com.example.typoandroidstudio.infomascota;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.typoandroidstudio.R;
import com.example.typoandroidstudio.network.MascotaAPIS.MascotaAPIService;
import com.github.mikephil.charting.charts.BarChart;

public class GraficaMascotasFragmentActivity extends Fragment {
    private MascotaAPIService service;
    private ProgressBar progressBar;
    private TextView textView;
    private int maxProgress = 100; // Máximo progreso para la ProgressBar
    private int currentProgress = 50; // Progreso actual (puede ser un valor obtenido dinámicamente)

    public GraficaMascotasFragmentActivity() {
        // Constructor público requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grafica_mascotas_activity, container, false);

        progressBar = view.findViewById(R.id.progressBar);
        textView = view.findViewById(R.id.textView);

        progressBar.setMax(maxProgress);
        progressBar.setProgress(currentProgress);

        textView.setText("Progreso: " + currentProgress + "/" + maxProgress);

        return view;
    }
}

