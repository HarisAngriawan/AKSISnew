package com.binainsanlesatari.aksis.adapter.adapterGuru.adapterNilai;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.ViewGuru.InputNilai.UpdateNilai.UpdateNilaiTugas;
import com.binainsanlesatari.aksis.model.GuruModel.Nilai.NilaiUASItem;
import com.binainsanlesatari.aksis.utils.AppParams;

import java.util.ArrayList;
import java.util.List;

public class AdpGetNilaiUAS extends RecyclerView.Adapter<AdpGetNilaiUAS.ViewHolder> {
    Context context;
    List<NilaiUASItem> nilaiUAS;

    public AdpGetNilaiUAS(Context context) {
        this.context = context;
        this.nilaiUAS = new ArrayList<>();
    }

    public void setData(List<NilaiUASItem> UASnilai) {
        nilaiUAS.clear();
        nilaiUAS.addAll(UASnilai);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.nilai_uas_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(nilaiUAS.get(position));
    }

    @Override
    public int getItemCount() {
        return nilaiUAS.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvNisn;
        EditText edNilaiUAS;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tvUASnama);
            tvNisn = itemView.findViewById(R.id.tvUASnisn);
            edNilaiUAS = itemView.findViewById(R.id.edNilaiUas);
        }

        public void bindView(final NilaiUASItem nilaiUASItem) {
            tvNama.setText(nilaiUASItem.getNamaLengkap());
            tvNisn.setText(nilaiUASItem.getNisn());
            edNilaiUAS.setText(nilaiUASItem.getNilai());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, UpdateNilaiTugas.class);
                    intent.putExtra(AppParams.UASNAMA, nilaiUASItem.getNamaLengkap());
                    intent.putExtra(AppParams.UASNISN, nilaiUASItem.getNisn());
                    intent.putExtra(AppParams.UAS, nilaiUASItem.getNilai());
                }
            });
        }
    }
}
