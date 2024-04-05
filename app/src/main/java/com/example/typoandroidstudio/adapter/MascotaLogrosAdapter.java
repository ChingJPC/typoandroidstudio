package com.example.typoandroidstudio.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.typoandroidstudio.R;
import com.example.typoandroidstudio.agendamiento.MascotaLogrosActivity;
import com.example.typoandroidstudio.infomascota.AgendamientoMascotaActivity;
import com.example.typoandroidstudio.model.Logros;
import com.example.typoandroidstudio.model.Mascotalogros;

import java.util.List;

public class MascotaLogrosAdapter extends RecyclerView.Adapter<MascotaLogrosAdapter.MascotaLogrosViewHolder> {
    private Context context;
    private List<Mascotalogros> logrosList;

    public MascotaLogrosAdapter(Context context, List<Mascotalogros> logrosList) {
        this.context = context;
        this.logrosList = logrosList;
    }

    @NonNull
    @Override
    public MascotaLogrosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_mascota_logros, parent, false);
        return new MascotaLogrosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MascotaLogrosViewHolder holder, int position) {
        Mascotalogros logro = logrosList.get(position);
        holder.bind(logro);
    }

    @Override
    public int getItemCount() {
        return logrosList.size();
    }

    public class MascotaLogrosViewHolder extends RecyclerView.ViewHolder {
        private TextView tipoLogroTextView;
        private TextView tiempoSemanalTextView;

        public MascotaLogrosViewHolder(@NonNull View itemView) {
            super(itemView);
            tipoLogroTextView = itemView.findViewById(R.id.tipoLogroTextView);
            tiempoSemanalTextView = itemView.findViewById(R.id.tiempoSemanalTextView);
        }

        public void bind(Mascotalogros logro) {
            tipoLogroTextView.setText(logro.getTipoLogro());

            StringBuilder sb = new StringBuilder();
            for (Logros logros : logro.getTiempoSemanal()) {
                sb.append(logros.getTipoLogro()).append(", ");
            }
            if (sb.length() > 0) {
                sb.setLength(sb.length() - 2); // Eliminar la última coma y espacio
            }
            tiempoSemanalTextView.setText(sb.toString());
        }
    }
}


