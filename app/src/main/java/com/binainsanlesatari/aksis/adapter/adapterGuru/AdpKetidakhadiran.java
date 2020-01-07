package com.binainsanlesatari.aksis.adapter.adapterGuru;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.model.GuruModel.Ketidakhadiran.DataTidakHadirItem;

import java.util.ArrayList;
import java.util.List;

public class AdpKetidakhadiran extends RecyclerView.Adapter<AdpKetidakhadiran.ViewHolder> {
    Context context;
    List<DataTidakHadirItem> dataTidakHadir;

    public AdpKetidakhadiran(Context context) {
        this.context = context;
        dataTidakHadir = new ArrayList<>();
    }

    public void setData(List<DataTidakHadirItem> tidakHadir) {
        dataTidakHadir.clear();
        dataTidakHadir.addAll(tidakHadir);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.ketidakhadiran_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(dataTidakHadir.get(position));
    }

    @Override
    public int getItemCount() {
        return dataTidakHadir.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvKelas,tvNisn, tvAlasan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tvNopeHadirNama);
            tvNisn = itemView.findViewById(R.id.tvNopeHadirNisn);
            tvKelas = itemView.findViewById(R.id.tvNopeHadirKelas);
            tvAlasan = itemView.findViewById(R.id.tvNopeHadirAlasan);
        }

        public void bindView(DataTidakHadirItem dataTidakHadirItem) {
            tvNama.setText(dataTidakHadirItem.getNama());
            tvNisn.setText(dataTidakHadirItem.getNisn());
            tvKelas.setText(dataTidakHadirItem.getKelas());
            tvAlasan.setText(dataTidakHadirItem.getAlasan());
        }
    }
}
