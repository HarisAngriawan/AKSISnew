package com.binainsanlesatari.aksis.adapter.adapterGuru.adapterNilai;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.ViewGuru.InputNilai.UpdateNilai.UpdateNilaiTugas;
import com.binainsanlesatari.aksis.model.GuruModel.Nilai.NilaiTugasItem;
import com.binainsanlesatari.aksis.utils.AppParams;

import java.util.ArrayList;
import java.util.List;

public class AdpGetNilaiTugas extends RecyclerView.Adapter<AdpGetNilaiTugas.ViewHolder> {
    Context context;
    List<NilaiTugasItem> nilaiTugas;

    public AdpGetNilaiTugas(Context context) {
        this.context = context;
        this.nilaiTugas = new ArrayList<>();
    }

    public void setData(List<NilaiTugasItem> tugasNilai) {
        nilaiTugas.clear();
        nilaiTugas.addAll(tugasNilai);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.nilai_tugas_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindViewl(nilaiTugas.get(position));
    }

    @Override
    public int getItemCount() {
        return nilaiTugas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTugasNama, tvTugasNisn;
        private EditText edTugas1, edTugas2, edTugas3;
        CardView cvTugas;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTugasNama = itemView.findViewById(R.id.tvTugasNama);
            tvTugasNisn = itemView.findViewById(R.id.tvTugasNins);
            edTugas1 = itemView.findViewById(R.id.edTugas1);
            edTugas2 = itemView.findViewById(R.id.edTugas2);
            edTugas3 = itemView.findViewById(R.id.edTugas3);
            cvTugas = itemView.findViewById(R.id.cvTugas);
        }

        public void bindViewl(final NilaiTugasItem nilaiTugasItem) {
            tvTugasNama.setText(nilaiTugasItem.getNamaLengkap());
//            tvTugasNama.setSingleLine(false);
//            tvTugasNama.setImeOptions(EditorInfo.IME_FLAG_NO_ENTER_ACTION);
            tvTugasNisn.setText(nilaiTugasItem.getNisn());
            edTugas1.setText(nilaiTugasItem.getNUh1());
            edTugas2.setText(nilaiTugasItem.getNUh2());
            edTugas3.setText(nilaiTugasItem.getUh3());

            cvTugas.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, UpdateNilaiTugas.class);
                    intent.putExtra(AppParams.TUGASNAMA, nilaiTugasItem.getNamaLengkap());
                    intent.putExtra(AppParams.TUGASNISN, nilaiTugasItem.getNisn());
                    intent.putExtra(AppParams.TUGAS1, nilaiTugasItem.getNUh1());
                    intent.putExtra(AppParams.TUGAS2, nilaiTugasItem.getNUh2());
                    intent.putExtra(AppParams.TUGAS3, nilaiTugasItem.getUh3());
                }
            });
        }
    }
}
