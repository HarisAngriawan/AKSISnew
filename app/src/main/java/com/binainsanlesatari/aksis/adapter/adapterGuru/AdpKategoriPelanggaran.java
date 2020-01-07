package com.binainsanlesatari.aksis.adapter.adapterGuru;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.ViewGuru.Pelanggaran.dataPelanggaran.getPelanggaran;
import com.binainsanlesatari.aksis.model.GuruModel.Pelaggaran.ViewPelanggaran.DataPelanggaranPerKategoriItem;
import com.binainsanlesatari.aksis.utils.AppParams;

import java.util.ArrayList;
import java.util.List;

public class AdpKategoriPelanggaran extends RecyclerView.Adapter<AdpKategoriPelanggaran.ViewHolder> {
    private Context context;
    private List<DataPelanggaranPerKategoriItem> datapelanggran;

    public AdpKategoriPelanggaran(Context context) {
        this.context = context;
        datapelanggran = new ArrayList<>();
    }

    public void setPelanggran(List<DataPelanggaranPerKategoriItem> detailKategori) {
       datapelanggran.clear();
       datapelanggran.addAll(detailKategori);
       notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.kategori_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.bindView(datapelanggran.get(position));
    }

    @Override
    public int getItemCount() {
        return datapelanggran.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNamakategori, tvJumlahKasus;
        public ViewHolder(View itemView) {
            super(itemView);

            tvJumlahKasus = itemView.findViewById(R.id.jumlah_kasus);
            tvNamakategori = itemView.findViewById(R.id.nama_kategori);

        }

        public void bindView(final DataPelanggaranPerKategoriItem dataPelanggaranPerKategoriItem) {

            tvJumlahKasus.setText(dataPelanggaranPerKategoriItem.getJumlah());
            tvNamakategori.setText(dataPelanggaranPerKategoriItem.getDeskripsi_Pelaggaran());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, getPelanggaran.class);
//                    intent.putExtra(AppParams.IDKATEGORI,dataPelanggaranPerKategoriItem.getKategoriPelanggaran());
                    intent.putExtra(AppParams.IDPELANGGARAN, dataPelanggaranPerKategoriItem.getKategoriPelanggaran());
                    context.startActivity(intent);
                }
            });
        }
    }
}
