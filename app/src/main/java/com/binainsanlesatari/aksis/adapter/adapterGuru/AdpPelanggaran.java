package com.binainsanlesatari.aksis.adapter.adapterGuru;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.model.GuruModel.Pelaggaran.ViewPelanggaran.DetailPelanggaranItem;

import java.util.ArrayList;
import java.util.List;

public class AdpPelanggaran extends RecyclerView.Adapter<AdpPelanggaran.ViewHolder> {

    private Context context;
    private List<DetailPelanggaranItem> detailPelanggaran;

    public AdpPelanggaran(Context context) {
        this.context = context;
        detailPelanggaran = new ArrayList<>();
    }

    public void setData(List<DetailPelanggaranItem> data){
        detailPelanggaran.clear();
        detailPelanggaran.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.pelanggaran_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(detailPelanggaran.get(position));
    }

    @Override
    public int getItemCount() {
        return detailPelanggaran.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTglPel,tvJamPel,tvNisn, tvKelasPel, tvNamaPelanggar, tvKeteranganPelanggaran;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTglPel= itemView.findViewById(R.id.tvTanggalPelanggaran);
            tvJamPel= itemView.findViewById(R.id.tvJamPelanggaran);
            tvNisn= itemView.findViewById(R.id.tvNisnPelanggar);
            tvKelasPel= itemView.findViewById(R.id.tvKelasPelanggar);
            tvNamaPelanggar= itemView.findViewById(R.id.tvNamaPelanggaran);
            tvKeteranganPelanggaran= itemView.findViewById(R.id.tvKeterangan);
        }

        public void bindView(DetailPelanggaranItem detailPelanggaranItem) {
            tvTglPel.setText(detailPelanggaranItem.getTglPelanggaran());
            tvJamPel.setText(detailPelanggaranItem.getJamPelanggaran());
            tvNisn.setText(detailPelanggaranItem.getNisn());
            tvKelasPel.setText(detailPelanggaranItem.getKelas());
            tvNamaPelanggar.setText(detailPelanggaranItem.getNamaLengkap());
            tvKeteranganPelanggaran.setText(detailPelanggaranItem.getDeskripsiPelanggaran());
        }
    }
}
