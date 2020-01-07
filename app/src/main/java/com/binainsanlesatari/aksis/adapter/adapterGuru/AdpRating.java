package com.binainsanlesatari.aksis.adapter.adapterGuru;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.model.GuruModel.Rating.RatingItem;

import java.util.ArrayList;
import java.util.List;

public class AdpRating extends RecyclerView.Adapter<AdpRating.ViewHolder> {
    private Context context;
    private List<RatingItem> dataRating;

    public AdpRating(Context context) {
        this.context = context;
        dataRating = new ArrayList<>();
    }

    public void setRating(List<RatingItem> rating) {
        dataRating.clear();
        dataRating.addAll(rating);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.rating_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(dataRating.get(position));
    }

    @Override
    public int getItemCount() {
        return dataRating.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNamaGuru, tvKodeGuru, tvBintangGuru;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNamaGuru = itemView.findViewById(R.id.nama_guru);
            tvBintangGuru = itemView.findViewById(R.id.rating);
            tvKodeGuru = itemView.findViewById(R.id.kodeGuru);
        }

        public void bindView(RatingItem ratingItem) {
            tvNamaGuru.setText(ratingItem.getNama());
            tvKodeGuru.setText(ratingItem.getIdGuru());
            tvBintangGuru.setText(ratingItem.getJumlah());
        }
    }
}
