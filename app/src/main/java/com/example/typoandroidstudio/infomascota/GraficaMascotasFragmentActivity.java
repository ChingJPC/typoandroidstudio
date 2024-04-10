package com.example.typoandroidstudio.infomascota;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.mikephil.charting.charts.BarChart;

import com.example.typoandroidstudio.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GraficaMascotasFragmentActivity#newInstance} factory method to
 * create an instance of this fragment.
 */
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.typoandroidstudio.model.CantidadMascota;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GraficaMascotasFragmentActivity extends Fragment {

    private BarChart barChart; // Variable para la gráfica de barras

    public GraficaMascotasFragmentActivity() {
        // Constructor público requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        View view = inflater.inflate(R.layout.fragment_grafica_mascotas_activity, container, false);
        // Obtener la referencia de la gráfica de barras desde el diseño
        barChart = view.findViewById(R.id.barChart);
        // Llamar al método para obtener los datos de la API y crear la gráfica
        obtenerDatos();
        return view;
    }

    // Método para obtener los datos de la API
    private void obtenerDatos() {
        // Obtener el servicio de la API
        LoginAPIService service = LoginAPIClient.getLoginService();
        // Hacer la llamada para obtener los datos de cantidad de mascotas
        service.getCantidadMascota("your_authorization_header_here").enqueue(new Callback<List<CantidadMascota>>() {
            @Override
            public void onResponse(Call<List<CantidadMascota>> call, Response<List<CantidadMascota>> response) {
                if (response.isSuccessful()) {
                    // Si la respuesta es exitosa, obtener la lista de mascotas y crear la gráfica
                    List<CantidadMascota> mascotas = response.body();
                    createBarChart(mascotas);
                } else {
                    // Manejo de errores en caso de que la respuesta no sea exitosa
                }
            }

            @Override
            public void onFailure(Call<List<CantidadMascota>> call, Throwable t) {
                // Manejo de errores en caso de fallo en la llamada
            }
        });
    }

    // Método para crear la gráfica de barras
    private void createBarChart(List<CantidadMascota> mascotas) {
        // Crear una lista de entradas para la gráfica
        List<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < mascotas.size(); i++) {
            entries.add(new BarEntry(i, mascotas.get(i).getCantidad()));
        }

        // Crear un conjunto de datos de barras con las entradas
        BarDataSet dataSet = new BarDataSet(entries, "Cantidad de mascotas");
        dataSet.setColor(Color.rgb(0, 155, 0)); // Establecer el color de las barras

        // Crear una lista de conjuntos de datos de barras y agregar el conjunto de datos creado
        List<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet);

        // Crear los datos de la gráfica con la lista de conjuntos de datos
        BarData data = new BarData(dataSets);
        data.setBarWidth(0.5f); // Establecer el ancho de las barras

        // Configurar el eje X de la gráfica
        XAxis xAxis = barChart.getXAxis();
        List<String> labels = new ArrayList<>();
        for (CantidadMascota mascota : mascotas) {
            labels.add(mascota.getTipo_Mascota());
        }
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setCenterAxisLabels(true);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(mascotas.size() - 1);

        // Establecer los datos y configuraciones adicionales de la gráfica
        barChart.setData(data);
        barChart.setFitBars(true);
        barChart.getDescription().setEnabled(false);
        barChart.invalidate(); // Actualizar la gráfica
    }
}
