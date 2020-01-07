package com.binainsanlesatari.aksis.adapter.adapterGuru;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.ViewGuru.Siswa.GetSiswa;
import com.binainsanlesatari.aksis.model.GuruModel.ViewSiswa.Kelas;
import com.binainsanlesatari.aksis.utils.AppParams;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AdpKelas extends RecyclerView.Adapter<AdpKelas.ViewHolder> {
    private Context context;
    private List<Kelas> dataKelas;
    private List<Kelas> tempKelas;
    private boolean isForShowDetail;

    public AdpKelas(Context context, boolean isForShowDetail) {
        this.context = context;
//        this.isForShowDetail = isForShowDetail;

        dataKelas = new ArrayList<>();
        tempKelas = new ArrayList<>();
    }

    public void setData(List<Kelas> data) {
        dataKelas.clear();
        dataKelas.addAll(data);
        tempKelas.clear();
        tempKelas.addAll(data);
        notifyDataSetChanged();
    }

    public void searchData(final String query) {
        if (query.length() == 0) {
            dataKelas.clear();
            dataKelas.addAll(tempKelas);
        } else {
            Predicate<Kelas> kelasPredicate = new Predicate<Kelas>() {
                @Override
                public boolean test(Kelas kelas) {
                    return kelas.getIdKelasJurusan().toLowerCase().contains(query.toLowerCase()) ||
                            kelas.getJumlah().contains(query) ||
                            kelas.getIdWaliKelas().toLowerCase().contains(query.toLowerCase()) ||
                            kelas.getNama().toLowerCase().contains(query.toLowerCase());
                }
            };
            dataKelas.clear();
            List<Kelas> filtered = tempKelas.stream().filter(kelasPredicate).collect(Collectors.<Kelas>toList());
            dataKelas.addAll(filtered);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.kelas_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(dataKelas.get(position));

    }

    @Override
    public int getItemCount() {
        return dataKelas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView container;
        private TextView txtKejur, txtJumlah, txtWali, txtNamaWali, txtNpsn;

        public ViewHolder(@Nullable View itemView) {
            super(itemView);

            container = itemView.findViewById(R.id.container);
            txtKejur = itemView.findViewById(R.id.txtKeJur);
            txtWali = itemView.findViewById(R.id.txtWali);
            txtNamaWali = itemView.findViewById(R.id.txtNamaWali);
            txtJumlah = itemView.findViewById(R.id.txtJumlah);
            txtNpsn = itemView.findViewById(R.id.txtNPSN);

        }

        @SuppressLint("SetTextI18n")
        public void bindView(final Kelas kelas) {

            txtKejur.setText(kelas.getNamaKelas());
            txtNamaWali.setText(kelas.getNama());
            txtJumlah.setText(kelas.getJumlah() + " Siswa");

            container.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_scale_animation));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, GetSiswa.class);
                    intent.putExtra(AppParams.IDKELAS, kelas.getIdKelasJurusan());
                    intent.putExtra(AppParams.NAMAGURU, kelas.getNama());
                    context.startActivity(intent);
                }
            });
        }
    }
}
