package com.binainsanlesatari.aksis.adapter.adapterGuru;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.model.GuruModel.Alumni.AlumniItem;

import java.util.ArrayList;
import java.util.List;

public class AdpPenelusuranAlumni extends RecyclerView.Adapter<AdpPenelusuranAlumni.ViewHolder> {
    private Context context;
    private List<AlumniItem> alumni;

    public AdpPenelusuranAlumni(Context context) {
        this.context = context;
        alumni = new ArrayList<>();
    }

    public void setData(List<AlumniItem> Telusuralumni) {
        alumni.clear();
        alumni.addAll(Telusuralumni);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.alumni_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(alumni.get(position));
    }

    @Override
    public int getItemCount() {
        return alumni.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView TaAngkatan, TaJumlahLulus, TaMasukNegeri, TaMasukSwasta, TaKerja, TaLain_lain;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            TaAngkatan = itemView.findViewById(R.id.TAangkatan);
            TaJumlahLulus = itemView.findViewById(R.id.TAjumlah);
            TaMasukNegeri = itemView.findViewById(R.id.TAmasukNegri);
            TaMasukSwasta = itemView.findViewById(R.id.TAswasta);
            TaKerja = itemView.findViewById(R.id.TAkerja);
            TaLain_lain = itemView.findViewById(R.id.TAlain_lain);
        }

        @SuppressLint("SetTextI18n")
        public void bindView(AlumniItem alumniItem) {
            TaAngkatan.setText(alumniItem.getAngkatan());
            TaJumlahLulus.setText(alumniItem.getJumlahLulus()+" Siswa");
            TaMasukNegeri.setText(alumniItem.getNegeri()+" Siswa");
            TaMasukSwasta.setText(alumniItem.getSwasta()+" Siswa");
            TaLain_lain.setText(alumniItem.getLain()+" Siswa");
            TaKerja.setText(alumniItem.getKerja()+" Siswa");
        }
    }
}
