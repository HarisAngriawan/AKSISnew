package com.binainsanlesatari.aksis.adapter.adapterGuru;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.model.GuruModel.CatatanKonseling.KonselingItem;

import java.util.ArrayList;
import java.util.List;

public class AdpCatatanKonseling extends RecyclerView.Adapter<AdpCatatanKonseling.ViewHolder> {
    private Context context;
    private List<KonselingItem> konseling;

    public AdpCatatanKonseling(Context context) {
        this.context = context;
        konseling = new ArrayList<>();
    }

    public void setData(List<KonselingItem> dataKonseling) {
        konseling.clear();
        konseling.addAll(dataKonseling);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.konseling_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(konseling.get(position));
    }

    @Override
    public int getItemCount() {
        return konseling.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView CkTanggal, Ckpermasalahan, Ckpendekatan, CkLayanan, CkKonselor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            CkTanggal = itemView.findViewById(R.id.CKtanggal);
            Ckpermasalahan = itemView.findViewById(R.id.CKpermasalahan);
            Ckpendekatan = itemView.findViewById(R.id.CKpendekatan);
            CkLayanan = itemView.findViewById(R.id.CKpelayanan);
            CkKonselor = itemView.findViewById(R.id.CKkonselor);
        }

        public void bindView(KonselingItem konselingItem) {
            CkTanggal.setText(konselingItem.getTgl());
            Ckpermasalahan.setText(konselingItem.getMasalah());
            CkLayanan.setText(konselingItem.getLayanan());
            Ckpendekatan.setText(konselingItem.getPendekatan());
            CkKonselor.setText(konselingItem.getKonselor());

        }
    }
}
