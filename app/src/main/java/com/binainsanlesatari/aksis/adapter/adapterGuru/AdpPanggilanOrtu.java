package com.binainsanlesatari.aksis.adapter.adapterGuru;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.model.GuruModel.SuratPanggilanOrtu.ListPanggilanOrtuItem;

import java.util.ArrayList;
import java.util.List;

public class AdpPanggilanOrtu extends RecyclerView.Adapter<AdpPanggilanOrtu.ViewHolder> {
    Context context;
    List<ListPanggilanOrtuItem> panggilanItem;

    public AdpPanggilanOrtu(Context context) {
        this.context = context;
        panggilanItem = new ArrayList<>();
    }

    public void setData(List<ListPanggilanOrtuItem> panggilan) {
        panggilanItem.clear();
        panggilanItem.addAll(panggilan);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.panggilan_ortu_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(panggilanItem.get(position));
    }

    @Override
    public int getItemCount() {
        return panggilanItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNisn, tvNama, tvKasus, tvStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNisn = itemView.findViewById(R.id.tvNisnSurat);
            tvNama = itemView.findViewById(R.id.tvNamaSurat);
            tvKasus = itemView.findViewById(R.id.tvKasusSurat);
            tvStatus = itemView.findViewById(R.id.tvStatusSurat);
        }

        public void bindView(ListPanggilanOrtuItem listPanggilanOrtuItem) {
            tvNisn.setText(listPanggilanOrtuItem.getNisn());
            tvNama.setText(listPanggilanOrtuItem.getNama());
            tvKasus.setText(listPanggilanOrtuItem.getKasus());
            tvStatus.setText(listPanggilanOrtuItem.getStatus());
        }
    }
}
