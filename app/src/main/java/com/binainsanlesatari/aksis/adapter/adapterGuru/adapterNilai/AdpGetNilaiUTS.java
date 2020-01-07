package com.binainsanlesatari.aksis.adapter.adapterGuru.adapterNilai;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.ViewGuru.InputNilai.UpdateNilai.UpdateNilaiTugas;
import com.binainsanlesatari.aksis.model.GuruModel.Nilai.NilaiUTSItem;
import com.binainsanlesatari.aksis.utils.AppParams;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class AdpGetNilaiUTS extends RecyclerView.Adapter<AdpGetNilaiUTS.ViewHolder> {
    Context context;
    List<NilaiUTSItem> nilaiUts;

    public AdpGetNilaiUTS(Context context) {
        this.context = context;
        this.nilaiUts = new ArrayList<>();
    }

    public void setData(List<NilaiUTSItem> nilaiUTS) {
        nilaiUts.clear();
        nilaiUts.addAll(nilaiUTS);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.nilai_uts_item, parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(nilaiUts.get(position));
    }

    @Override
    public int getItemCount() {
        return nilaiUts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvUTSNama, tvUTSNisn;
        TextInputEditText edNilaiUTS;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUTSNama = itemView.findViewById(R.id.tvUtsNama);
            tvUTSNisn = itemView.findViewById(R.id.tvUtsNisn);
            edNilaiUTS = itemView.findViewById(R.id.edNilaiUts);
        }

        public void bindView(final NilaiUTSItem nilaiUTSItem) {
            tvUTSNama.setText(nilaiUTSItem.getNamaLengkap());
            tvUTSNisn.setText(nilaiUTSItem.getNisn());
            edNilaiUTS.setText(nilaiUTSItem.getNilai());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, UpdateNilaiTugas.class);
                    intent.putExtra(AppParams.UTSNAMA, nilaiUTSItem.getNamaLengkap());
                    intent.putExtra(AppParams.UTSNISN, nilaiUTSItem.getNisn());
                    intent.putExtra(AppParams.UTS, nilaiUTSItem.getNilai());
                }
            });
        }
    }
}



