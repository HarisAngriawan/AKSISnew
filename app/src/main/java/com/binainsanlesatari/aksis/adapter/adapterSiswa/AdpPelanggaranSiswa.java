package com.binainsanlesatari.aksis.adapter.adapterSiswa;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.ViewSiswa.PelanggaranSiswa.DetailPelanggaranSiswa;
import com.binainsanlesatari.aksis.model.SiswaModel.PelanggaranSiswa.JumlahDataPelanggaranSiwaItem;
import com.binainsanlesatari.aksis.utils.AppParams;
import com.binainsanlesatari.aksis.utils.AppParamsSiswa;

import java.util.ArrayList;
import java.util.List;

public class AdpPelanggaranSiswa extends RecyclerView.Adapter<AdpPelanggaranSiswa.ViewHolder> {
    Context context;
    List<JumlahDataPelanggaranSiwaItem>dataPelanggaranSiswa;

    public AdpPelanggaranSiswa(Context context) {
        this.context = context;
        dataPelanggaranSiswa = new ArrayList<>();
    }

    public void setData(List<JumlahDataPelanggaranSiwaItem> jumlahPelanggaran) {
        dataPelanggaranSiswa.clear();
        dataPelanggaranSiswa.addAll(jumlahPelanggaran);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.siswa_pelanggaran_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(dataPelanggaranSiswa.get(position));
    }

    @Override
    public int getItemCount() {
        return dataPelanggaranSiswa.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPelanggaranKategori, tvCountPelanggaran;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPelanggaranKategori = itemView.findViewById(R.id.tvDataPelanggaranKatergori);
            tvCountPelanggaran = itemView.findViewById(R.id.tvCountPelanggaran);
        }

        public void bindView(final JumlahDataPelanggaranSiwaItem jumlahDataPelanggaranSiwaItem) {
            tvPelanggaranKategori.setText(jumlahDataPelanggaranSiwaItem.getPelanggaran());
            tvCountPelanggaran.setText("Banyak Pelanggaran\n" + jumlahDataPelanggaranSiwaItem.getJumlah());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetailPelanggaranSiswa.class);
                    intent.putExtra(AppParamsSiswa.KODEPELANGGARANSISWA,jumlahDataPelanggaranSiwaItem.getKategoriPelanggaran());
                    context.startActivity(intent);
                }
            });

        }
    }
}
