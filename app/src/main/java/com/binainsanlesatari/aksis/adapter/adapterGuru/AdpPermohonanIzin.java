package com.binainsanlesatari.aksis.adapter.adapterGuru;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.ViewGuru.Izin.DetailPermohonanIzin;
import com.binainsanlesatari.aksis.model.GuruModel.PermohonanIzin.IzinItemsItem;
import com.binainsanlesatari.aksis.utils.AppParams;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdpPermohonanIzin extends RecyclerView.Adapter<AdpPermohonanIzin.ViewHolder> {

    Context context;
    List<IzinItemsItem> permohonanIzin;

    public AdpPermohonanIzin(Context context) {
        this.context = context;
        permohonanIzin = new ArrayList<>();
    }

    public void setData(List<IzinItemsItem> permohonan) {
        permohonanIzin.clear();
        permohonanIzin.addAll(permohonan);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.permohonan_izin_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String url = "http://aksis.binainsanlestari.com/android_AKSIS/" + permohonanIzin.get(position).getFotoprofil();
        Picasso.with(context)
                .load(url)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.fotoIzin);

        holder.bindView(permohonanIzin.get(position));
    }

    @Override
    public int getItemCount() {
        return permohonanIzin.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatImageView fotoIzin;
        TextView izinNisn,izinNama,izinKelas,izinIzinUntuk;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            izinIzinUntuk = itemView.findViewById(R.id.tvIzinNamaPermohonan);
            fotoIzin = itemView.findViewById(R.id.imgIzinPicture);
            izinNisn = itemView.findViewById(R.id.tvIzinNisn);
            izinNama = itemView.findViewById(R.id.tvIzinNama);
            izinKelas = itemView.findViewById(R.id.tvIzinKelas);
        }
        public void bindView(final IzinItemsItem permohonanIzinItem) {
            izinIzinUntuk.setText(permohonanIzinItem.getNama());
            izinNisn.setText(permohonanIzinItem.getNisn());
            izinNama.setText(permohonanIzinItem.getNama());
            izinKelas.setText(permohonanIzinItem.getKelas());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetailPermohonanIzin.class);
                    intent.putExtra(AppParams.IZINID,  permohonanIzinItem.getId());
                    intent.putExtra(AppParams.IZINTGL,  permohonanIzinItem.getTgl());
                    intent.putExtra(AppParams.IZINNISN,  permohonanIzinItem.getNisn());
                    intent.putExtra(AppParams.IZINNPSN,  permohonanIzinItem.getNpsn());
                    intent.putExtra(AppParams.IZINNAMA,  permohonanIzinItem.getNama());
                    intent.putExtra(AppParams.IZINKELAS,  permohonanIzinItem.getKelas());
                    intent.putExtra(AppParams.IZINKETERANGAN,  permohonanIzinItem.getKeterangan());
                    intent.putExtra(AppParams.IZINSTATUS,  permohonanIzinItem.getStatus());
                    intent.putExtra(AppParams.IZINFOTO,  permohonanIzinItem.getFoto());
                    context.startActivity(intent);
                }
            });
        }
    }
}
