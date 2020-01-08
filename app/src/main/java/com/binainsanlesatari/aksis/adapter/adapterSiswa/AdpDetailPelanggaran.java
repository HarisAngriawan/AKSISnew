package com.binainsanlesatari.aksis.adapter.adapterSiswa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.model.GuruModel.Pelaggaran.ViewPelanggaran.DetailPelanggaranItem;
import com.binainsanlesatari.aksis.model.SiswaModel.PelanggaranSiswa.DetaailPelanggaranItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdpDetailPelanggaran extends RecyclerView.Adapter<AdpDetailPelanggaran.ViewHolder> {
    Context context;
    List<DetaailPelanggaranItem> detaailPelanggaran;

    public AdpDetailPelanggaran(Context context) {
        this.context = context;
        detaailPelanggaran = new ArrayList<>();
    }

    public void setData(List<DetaailPelanggaranItem> detPelangaran) {
        detaailPelanggaran.clear();
        detaailPelanggaran.addAll(detPelangaran);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.siswa_detail_pelanggaran_item, parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(detaailPelanggaran.get(position));
        String url = "http://aksis.binainsanlestari.com/android_AKSIS/" + detaailPelanggaran.get(position).getFoto();
        Picasso.with(context)
                .load(url)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.imgPelanggar);
    }

    @Override
    public int getItemCount() {
        return detaailPelanggaran.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTgl,tvJam,tvTempat,tvPelapor,tvStatus,tvKeterangan;
        ImageView imgPelanggar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTgl = itemView.findViewById(R.id.tvPelanggaranTanggal);
            tvJam = itemView.findViewById(R.id.tvPelanggaranJam);
            tvTempat = itemView.findViewById(R.id.tvTempatPelanggaran);
            tvPelapor = itemView.findViewById(R.id.tvNamaGuruPelapor);
            tvStatus = itemView.findViewById(R.id.tvStatusTeguran);
            tvKeterangan = itemView.findViewById(R.id.tvKeteranganPelangagran);
            imgPelanggar= itemView.findViewById(R.id.imgPelanggaranSiswa);
        }

        public void bindView(DetaailPelanggaranItem detaailPelanggaranItem) {
            tvTgl.setText(detaailPelanggaranItem.getTglPelanggaran());
            tvJam.setText(detaailPelanggaranItem.getJamPelanggaran());
            tvTempat.setText(detaailPelanggaranItem.getTempatPelanggaran());
            tvPelapor.setText(detaailPelanggaranItem.getNama_guru());
            tvKeterangan.setText(detaailPelanggaranItem.getKeterangan());
            String StatusTeguran;
            if (detaailPelanggaranItem.getStatusTeguran().equals("no")){
                StatusTeguran = "Belum Ditegur";
            }else {
                StatusTeguran = "Sudah Ditegur";
            }
            tvStatus.setText(StatusTeguran);
//            cvSiswa.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_scale_animation));

        }
    }
}
