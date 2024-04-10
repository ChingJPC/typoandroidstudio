/*package com.example.typoandroidstudio.infomascota;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.typoandroidstudio.R;
import com.example.typoandroidstudio.model.CantidadMascota;
import com.example.typoandroidstudio.network.MascotaAPIS.MascotaAPIClient;
import com.example.typoandroidstudio.network.MascotaAPIS.MascotaAPIService;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ValueFormatter;
import com.github.mikephil.charting.utils.XLabels;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GraficaMascotasFragmentActivity extends Fragment {

    private BarChart barChart; // Variable para la gráfica de barras
    private MascotaAPIService service;

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
        // Obtener el servicio de la API
        service = MascotaAPIClient.getMascotaInstance();
        // Llamar al método para obtener los datos de la API y crear la gráfica
        obtenerDatos();
        return view;
    }

    // Método para obtener los datos de la API
    private void obtenerDatos() {
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
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < mascotas.size(); i++) {
            entries.add(new BarEntry(mascotas.get(i).getCantidad(), i));
        }

        // Crear un conjunto de datos de barras con las entradas
        BarDataSet dataSet = new BarDataSet(entries, "Cantidad de mascotas");
        dataSet.setColor(Color.rgb(0, 155, 0)); // Establecer el color de las barras

        // Configurar el eje X de la gráfica
        XLabels xAxis = barChart.getXLabels();
        xAxis.setPosition(XLabels.XLabelPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(mascotas.size()); // Mostrar una etiqueta por cada entrada
        xAxis.setCenterAxisLabels(true);
        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum(mascotas.size() - 1);

        // Crear una lista de etiquetas para el eje X
        final String[] labels = new String[mascotas.size()];
        for (int i = 0; i < mascotas.size(); i++) {
            labels[i] = mascotas.get(i).getTipo_Mascota();
        }

        // Establecer las etiquetas en el eje X
        xAxis.setLabelsToSkip(0); // No saltar ninguna etiqueta
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                int index = (int) value;
                if (index >= 0 && index < labels.length) {
                    return labels[index];
                }
                return "";
            }
        });

        // Crear los datos de la gráfica con el conjunto de datos
        BarData data = new BarData(dataSet);
        data.setBarWidth(0.5f); // Establecer el ancho de las barras

        // Establecer los datos y configuraciones adicionales de la gráfica
        barChart.setData(data);
        barChart.setFitBars(true);
        barChart.getDescription().setEnabled(false);
        barChart.invalidate(); // Actualizar la gráfica
    }
}*/

