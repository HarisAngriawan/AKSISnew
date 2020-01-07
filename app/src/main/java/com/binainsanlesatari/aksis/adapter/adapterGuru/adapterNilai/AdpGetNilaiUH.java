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
import com.binainsanlesatari.aksis.model.GuruModel.Nilai.LihatNilaiItem;
import com.binainsanlesatari.aksis.utils.AppParams;

import java.util.ArrayList;
import java.util.List;

public class AdpGetNilaiUH extends RecyclerView.Adapter<AdpGetNilaiUH.ViewHolder> {
    private Context context;
    private List<LihatNilaiItem> lihatNilai;

    public AdpGetNilaiUH(Context context) {
        this.context = context;
        lihatNilai = new ArrayList<>();
    }

    public void setData(List<LihatNilaiItem> nilai) {
        lihatNilai.clear();
        lihatNilai.addAll(nilai);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.nilai_uh_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.bindView(lihatNilai.get(position));
    }

    @Override
    public int getItemCount() {
        return lihatNilai.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNama, tvNisn;
        private EditText nilaiUH1, nilaiUH2, nilaiUH3;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tvNamaUH);
            tvNisn = itemView.findViewById(R.id.tvNisnUH);
            nilaiUH1 = itemView.findViewById(R.id.edUh1);
            nilaiUH2 = itemView.findViewById(R.id.edUh2);
            nilaiUH3 = itemView.findViewById(R.id.edUh3);
        }

        public void bindView(final LihatNilaiItem lihatNilaiItem) {

            tvNama.setText(lihatNilaiItem.getNamaLengkap());
            tvNisn.setText(lihatNilaiItem.getNisn());
            nilaiUH1.setText(lihatNilaiItem.getNUh1());
            nilaiUH2.setText(lihatNilaiItem.getNUh2());
            nilaiUH3.setText(lihatNilaiItem.getUh3());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, UpdateNilaiTugas.class);
                    intent.putExtra(AppParams.UHNAMA, lihatNilaiItem.getNamaLengkap());
                    intent.putExtra(AppParams.UHNISN, lihatNilaiItem.getNisn());
                    intent.putExtra(AppParams.UH1, lihatNilaiItem.getNUh1());
                    intent.putExtra(AppParams.UH2, lihatNilaiItem.getNUh2());
                    intent.putExtra(AppParams.UH3, lihatNilaiItem.getUh3());
                }
            });

        }
    }
}
