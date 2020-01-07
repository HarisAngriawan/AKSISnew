package com.binainsanlesatari.aksis.adapter.adapterGuru;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.model.GuruModel.Prestasi.DataPrestasiItem;

import java.util.ArrayList;
import java.util.List;

public class AdpPrestasi extends RecyclerView.Adapter<AdpPrestasi.ViewHolder> {
    private Context context;
    private List<DataPrestasiItem>prestasi;

    public AdpPrestasi(Context context) {
        this.context = context;
        prestasi = new ArrayList<>();
    }

    public void setData(List<DataPrestasiItem> dataPrestasi) {
        prestasi.clear();
        prestasi.addAll(dataPrestasi);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.prestasi_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.bindView(prestasi.get(position));
    }

    @Override
    public int getItemCount() {
        return prestasi.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTglPrestasi, tvNisnPrestasi,tvKategori, tvNamaSiswa,tvDeskripsi, tvKelas, tvTahunAjaran;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTglPrestasi = itemView.findViewById(R.id.tvTglPrestasi);
            tvNisnPrestasi = itemView.findViewById(R.id.tvNisnPrestasi);
            tvNamaSiswa = itemView.findViewById(R.id.tvSiswaPretasi);
            tvKelas = itemView.findViewById(R.id.tvKelasPrestasi);
            tvTahunAjaran = itemView.findViewById(R.id.tvTahunajaranPrestasi);
            tvKategori = itemView.findViewById(R.id.tvKategoriPrestasi);
            tvDeskripsi = itemView.findViewById(R.id.tvJuara);
        }

        public void bindView(DataPrestasiItem prestasi) {
            tvTglPrestasi.setText(prestasi.getTgl());
            tvNisnPrestasi.setText(prestasi.getNisn());
            tvNamaSiswa.setText(prestasi.getNama());
            tvDeskripsi.setText(prestasi.getDeskripsi());
            tvKelas.setText(prestasi.getKelas());
            tvTahunAjaran.setText(prestasi.getThAjaran());
            tvKategori.setText(prestasi.getKategori());
        }
    }
}
