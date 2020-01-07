package com.binainsanlesatari.aksis.adapter.adapterSiswa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.model.SiswaModel.DataLaporanPelajaranItem;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adplaporanpelajaransiswa extends RecyclerView.Adapter<Adplaporanpelajaransiswa.ViewHolder>{
    Context context;
    List<DataLaporanPelajaranItem> dataLaporan;

    public Adplaporanpelajaransiswa(Context context) {
        this.context = context;
        dataLaporan = new ArrayList<>();
    }

    public void setData(List<DataLaporanPelajaranItem> laporanData) {
        dataLaporan.clear();
        dataLaporan.addAll(laporanData);
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.laporan_pelajaran_item_siswa, parent,false);
        return new ViewHolder(v);
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(dataLaporan.get(position));
    }

    public int getItemCount() {
        return dataLaporan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTgl, tvMapel, tvTema, tvNamaGuru;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTgl = itemView.findViewById(R.id.tvTanggalLaporanSiswa);
            tvMapel = itemView.findViewById(R.id.tvMapelLaporanSiswa);
            tvTema = itemView.findViewById(R.id.tvTemaLaporanSiswa);
            tvNamaGuru = itemView.findViewById(R.id.tvGuruLaporanSiswa);
        }

        public void bindView(DataLaporanPelajaranItem dataLaporanPelajaranItem) {
            tvTgl.setText(dataLaporanPelajaranItem.getTgl());
            tvMapel.setText(dataLaporanPelajaranItem.getMapel());
            tvTema.setText(dataLaporanPelajaranItem.getMateri() + "\n");
            tvNamaGuru.setText(dataLaporanPelajaranItem.getNama());
        }
    }
}
