package com.binainsanlesatari.aksis.adapter.adapterGuru;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.model.GuruModel.HomeVisit.HomeVisitItem;

import java.util.ArrayList;
import java.util.List;

public class AdpHomeVisit extends RecyclerView.Adapter<AdpHomeVisit.ViewHolder> {
    private Context context;
    private List<HomeVisitItem> homeVisit;

    public AdpHomeVisit(Context context) {
        this.context = context;
        homeVisit = new ArrayList<>();
    }

    public void setData(List<HomeVisitItem> HomeVisit) {
        homeVisit.clear();
        homeVisit.addAll(HomeVisit);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdpHomeVisit.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.homevisit_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdpHomeVisit.ViewHolder holder, int position) {
        holder.bindView(homeVisit.get(position));
    }

    @Override
    public int getItemCount() {
        return homeVisit.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView ChTanggal, ChKonselor, ChTujuan, ChNisn,ChNama_siswa, ChKelas, Chtanggapan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ChTanggal = itemView.findViewById(R.id.CHtanggal);
            ChKonselor = itemView.findViewById(R.id.CHnama_konselor);
            ChTujuan = itemView.findViewById(R.id.CHtujuan);
            ChNisn = itemView.findViewById(R.id.CHnisn_siswa);
            ChNama_siswa = itemView.findViewById(R.id.CHnama_siswa);
            ChKelas = itemView.findViewById(R.id.CHkelas);
            Chtanggapan = itemView.findViewById(R.id.CHtanggapan);
        }

        public void bindView(HomeVisitItem homeVisitItem) {
            ChTanggal.setText(homeVisitItem.getTgl());
            ChKonselor.setText(homeVisitItem.getKonselor());
            ChTujuan.setText(homeVisitItem.getTujuan());
            ChNisn.setText(homeVisitItem.getNisn());
            ChNama_siswa.setText(homeVisitItem.getNama());
            ChKelas.setText(homeVisitItem.getNamaKelas());
            Chtanggapan.setText(homeVisitItem.getTanggapan());

        }
    }
}
