package com.binainsanlesatari.aksis.adapter.adapterGuru;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.model.GuruModel.LaporanPelajaran.DataLaporanPelajaranItem;

import java.util.ArrayList;
import java.util.List;

public class AdpLaporanPelajaran extends RecyclerView.Adapter<AdpLaporanPelajaran.ViewHolder> {
    Context context;
    List<DataLaporanPelajaranItem> dataLaporan;

    public AdpLaporanPelajaran(Context context) {
        this.context = context;
        dataLaporan = new ArrayList<>();
    }

    public void setData(List<DataLaporanPelajaranItem> laporanData) {
        dataLaporan.clear();
        dataLaporan.addAll(laporanData);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.laporan_pelajaran_item, parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(dataLaporan.get(position));
    }

    @Override
    public int getItemCount() {
        return dataLaporan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTgl, tvMapel, tvKelas, tvTema, tvNamaGuru;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTgl = itemView.findViewById(R.id.tvTanggalLaporan);
            tvMapel = itemView.findViewById(R.id.tvMapelLaporan);
            tvKelas = itemView.findViewById(R.id.tvKelasLaporan);
            tvTema = itemView.findViewById(R.id.tvTemaLaporan);
            tvNamaGuru = itemView.findViewById(R.id.tvGuruLaporan);
        }

        public void bindView(DataLaporanPelajaranItem dataLaporanPelajaranItem) {
            tvTgl.setText(dataLaporanPelajaranItem.getTgl());
            tvKelas.setText(dataLaporanPelajaranItem.getKelas() + dataLaporanPelajaranItem.getNomorKelas() + dataLaporanPelajaranItem.getNomorKelas());
            tvMapel.setText(dataLaporanPelajaranItem.getMapel());
            tvTema.setText(dataLaporanPelajaranItem.getMateri() + "\n");
            tvNamaGuru.setText(dataLaporanPelajaranItem.getNama());
        }
    }
}
