package com.binainsanlesatari.aksis.adapter.adapterGuru;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.ViewGuru.TakeAllStudents;
import com.binainsanlesatari.aksis.model.GuruModel.ViewSiswa.InsertSiswaItem;
import com.binainsanlesatari.aksis.utils.AppParams;

import java.util.ArrayList;
import java.util.List;

public class AdpGetSearchSiswa extends RecyclerView.Adapter<AdpGetSearchSiswa.ViewHolder> {
    Context context;
    List<InsertSiswaItem> insertSiswaItems;

    public AdpGetSearchSiswa(Context context) {
        this.context = context;
        insertSiswaItems = new ArrayList<>();
    }

    public void setData(List<InsertSiswaItem> siswa) {
        insertSiswaItems.clear();
        insertSiswaItems.addAll(siswa);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.search_siswa_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(insertSiswaItems.get(position));
    }

    @Override
    public int getItemCount() {
        return insertSiswaItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvStudentNisn, tvStudentName, tvStudentClass;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvStudentNisn = itemView.findViewById(R.id.tvStudentNISN);
            tvStudentName = itemView.findViewById(R.id.tvStudentName);
            tvStudentClass = itemView.findViewById(R.id.tvSeacrhSiswaKelas);

        }

        public void bindView(final InsertSiswaItem insertSiswaItem) {
            tvStudentNisn.setText(insertSiswaItem.getNisn());
            tvStudentName.setText(insertSiswaItem.getNamaLengkap());
            tvStudentClass.setText(insertSiswaItem.getKelas());

            ((ConstraintLayout)tvStudentName.getParent()).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.putExtra(AppParams.NAMA,insertSiswaItem.getNamaLengkap());
                        intent.putExtra(AppParams.NISN, insertSiswaItem.getNisn());
                        intent.putExtra(AppParams.KELAS_SAATINI, insertSiswaItem.getKelasSaatIni());
                        intent.putExtra(AppParams.KELASNOMOR,insertSiswaItem.getKelas());
                        intent.putExtra(AppParams.THAJARAN, insertSiswaItem.getThAjaran());
                        ((TakeAllStudents)context).setResult(Activity.RESULT_OK, intent);
                        ((TakeAllStudents)context).finish();
                    }
                });
        }
    }
}
