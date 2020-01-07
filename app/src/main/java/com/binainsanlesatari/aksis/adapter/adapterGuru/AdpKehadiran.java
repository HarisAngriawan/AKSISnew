package com.binainsanlesatari.aksis.adapter.adapterGuru;

import android.annotation.SuppressLint;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.model.GuruModel.Kehadiran.DataKehadiranItem;

import java.util.ArrayList;
import java.util.List;

public class AdpKehadiran extends RecyclerView.Adapter<AdpKehadiran.ViewHolder> {
    private Context context;
    private List<DataKehadiranItem> dataKehadiranItems;

    public AdpKehadiran(Context context) {
        this.context = context;
        dataKehadiranItems = new ArrayList<>();
    }

//    public void dataKehadiran(List<DataKehadiranItem> dataKehadiranItems) {
//        this.dataKehadiranItems = dataKehadiranItems;
//    }

    public void setKehadiran(List<DataKehadiranItem> data) {
        dataKehadiranItems.clear();
        dataKehadiranItems.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.kehadiran_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(dataKehadiranItems.get(position));
    }

    @Override
    public int getItemCount() {
        return dataKehadiranItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView KhKelas, KhJumlah, KhHadir, KhNotHadir,khId,KhJurusan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            KhKelas = itemView.findViewById(R.id.KHkelas_siswa);
            KhJumlah = itemView.findViewById(R.id.KHjumlah_siswa);
            KhHadir = itemView.findViewById(R.id.KHhadir);
            KhNotHadir = itemView.findViewById(R.id.KHtidakHadir);

        }

        @SuppressLint("SetTextI18n")
        public void bindView(final DataKehadiranItem dataKehadiranItem) {

            KhKelas.setText(dataKehadiranItem.getNamaKelasJurusan()+ dataKehadiranItem.getNomorKelas());
            KhJumlah.setText(dataKehadiranItem.getJumlahSiswa()+" Siswa");
            KhHadir.setText(dataKehadiranItem.getJumlahMasuk()+" Siswa");
            KhNotHadir.setText(dataKehadiranItem.getTidakMasuk()+" Siswa");
        }
    }
}
