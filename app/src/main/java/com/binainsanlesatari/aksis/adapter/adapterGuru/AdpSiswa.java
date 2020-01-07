package com.binainsanlesatari.aksis.adapter.adapterGuru;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.ViewGuru.Siswa.ProfileSiswa;
import com.binainsanlesatari.aksis.model.GuruModel.ViewSiswa.SiswaItem;
import com.binainsanlesatari.aksis.utils.AppParams;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AdpSiswa extends RecyclerView.Adapter<AdpSiswa.ViewHolder> {
    private Context context;
    private List<SiswaItem> dataSiswa;
    private List<SiswaItem> tempSiswa;
    private boolean isForShowDetail;


    public AdpSiswa(Context context, boolean isForShowDetail) {
        this.context = context;
//        this.isForShowDetail = isForShowDetail;
        dataSiswa = new ArrayList<>();
        tempSiswa = new ArrayList<>();
    }

    public void setData(List<SiswaItem> data) {
        dataSiswa.clear();
        dataSiswa.addAll(data);
        tempSiswa.clear();
        tempSiswa.addAll(data);
        notifyDataSetChanged();
    }

    public void searchData(final String query) {
        if (query.length() == 0) {
            dataSiswa.clear();
            dataSiswa.addAll(tempSiswa);
        } else {
            Predicate<SiswaItem> siswaItemPredicate = new Predicate<SiswaItem>() {
                @Override
                public boolean test(SiswaItem siswaItem) {
                    return siswaItem.getNamaLengkap().toLowerCase().contains(query.toLowerCase()) ||
                            siswaItem.getNisn().contains(query);
                }
            };
            dataSiswa.clear();
            List<SiswaItem> filtered = tempSiswa.stream().filter(siswaItemPredicate).collect(Collectors.<SiswaItem>toList());
            dataSiswa.addAll(filtered);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.siswa_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(dataSiswa.get(position));
    }

    @Override
    public int getItemCount() {
        return dataSiswa.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvThAjaran, tvNis, tvNisn, tvNama, tvGender,tvKelas, tvNomor;
        CardView cvSiswa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvThAjaran = itemView.findViewById(R.id.tvView_thAjaran);
            tvNis = itemView.findViewById(R.id.tvViewNis);
            tvKelas = itemView.findViewById(R.id.tvViewKelas);
            tvNomor = itemView.findViewById(R.id.tvViewNomorKelas);
            tvNisn = itemView.findViewById(R.id.tvViewNisnSiswa);
            tvNama = itemView.findViewById(R.id.tvViewNamaLengkap);
            tvGender = itemView.findViewById(R.id.tvViewJK);
            cvSiswa = itemView.findViewById(R.id.siswa);
        }


        public void bindView(final SiswaItem siswa) {
            tvNis.setText(siswa.getNis());
            tvNisn.setText("Nisn : "+siswa.getNisn());
            tvNama.setText(siswa.getNamaLengkap());
            tvGender.setText(siswa.getJk());
            tvKelas.setText("Kelas "+ siswa.getNamaKelasJurusan());
            tvNomor.setText(siswa.getNomorKelas());

            String gender;
            if (siswa.getJk().equals("P")) {
                gender = "Perempuan";
            } else {
                gender = "Laki-Laki";
            }
            tvGender.setText(gender);
            cvSiswa.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_scale_animation));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ProfileSiswa.class);
                    intent.putExtra(AppParams.NISNSISWA,  siswa.getNisn());
                    intent.putExtra(AppParams.NISSISWA,  siswa.getNis());
                    intent.putExtra(AppParams.NAMALENGKAP,  siswa.getNamaLengkap());
                    intent.putExtra(AppParams.NAMAPANGGILAN,  siswa.getNamaPanggilan());
                    intent.putExtra(AppParams.TTL,  siswa.getTptLahir()+", "+siswa.getTglLahir());
                    intent.putExtra(AppParams.JK,  siswa.getJk());
                    intent.putExtra(AppParams.SUKU,  siswa.getSuku());
                    intent.putExtra(AppParams.AGAMA,  siswa.getAgama());
                    intent.putExtra(AppParams.NOHP,  siswa.getNoHp());
                    intent.putExtra(AppParams.ALAMAT,  siswa.getAlamat());
                    intent.putExtra(AppParams.EMAIL,  siswa.getEmail());
                    intent.putExtra(AppParams.ASALSKLH,  siswa.getAsalSekolah());
                    intent.putExtra(AppParams.TGLMASUK,  siswa.getTglMasuk());
                    intent.putExtra(AppParams.TGLDGN,  siswa.getTinggalDengan());
                    intent.putExtra(AppParams.NPSN,  siswa.getNpsn());
                    intent.putExtra(AppParams.KELAS_SISWA,  siswa.getNamaKelasJurusan()+" "+siswa.getNomorKelas());
                    context.startActivity(intent);
                }
            });
//            if (isForShowDetail) {
//                ((ViewGroup)tvNama.getParent()).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(context,"ggwp",Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }else {
//                ((ViewGroup)tvNama.getParent()).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent = new Intent();
//                        intent.putExtra("NAMA",siswa.getNamaLengkap());
//                        intent.putExtra("NISN", siswa.getNisn());
//                        ((TakeAllStudents)context).setResult(Activity.RESULT_OK, intent);
//                        ((TakeAllStudents)context).finish();
//                    }
//                });
//            }
        }
    }
}
