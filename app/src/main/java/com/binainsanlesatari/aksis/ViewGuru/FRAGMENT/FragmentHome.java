package com.binainsanlesatari.aksis.ViewGuru.FRAGMENT;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.ViewGuru.HomeVisit.CatatanHomeVisit;
import com.binainsanlesatari.aksis.ViewGuru.InputNilaiGuru;
import com.binainsanlesatari.aksis.ViewGuru.Kehadiran.KehadiranSiswa;
import com.binainsanlesatari.aksis.ViewGuru.Ketidakhadiran;
import com.binainsanlesatari.aksis.ViewGuru.Konseling.CatatanKoseling;
import com.binainsanlesatari.aksis.ViewGuru.LaporanPelajaran.LaporanPelajaranSiswa;
import com.binainsanlesatari.aksis.ViewGuru.PanggilanOrtu.PanggilanOrtu;
import com.binainsanlesatari.aksis.ViewGuru.Pelanggaran.Pelanggaran;
import com.binainsanlesatari.aksis.ViewGuru.PenelusuranAlumni.PenelusuranAlumni;
import com.binainsanlesatari.aksis.ViewGuru.PermohonanIzin;
import com.binainsanlesatari.aksis.ViewGuru.Prestasi.Prestasi;
import com.binainsanlesatari.aksis.ViewGuru.Rating.Rating;
import com.binainsanlesatari.aksis.ViewGuru.Siswa.ViewKelas;
import com.binainsanlesatari.aksis.utils.PrefManagerGuru;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentHome extends Fragment {

    private PrefManagerGuru prefManagerGuru;
    private TextView tvNama, tvMapel, tvNpsn;

    private CardView btnPrestasi, btnPelanggaranDetail, btnSuratPanggilan, btnKehadiran, btnGuruViewSiswa, btnInputNilai,
            btnTelusurAlumni, btnCatKonseling, btnHomeVisit, btnRating, btnKetidakHadiran, btnPermohonanIzin, btnLaporanPelajaran;

    private GridLayout gridMenu;

    private CircleImageView profileImg;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dashboard_guru, container, false);


        //TODO ROLE

        prefManagerGuru = new PrefManagerGuru(getActivity());
        bindView(v);
        ViewDataLogin();


        btnHomeVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CatatanHomeVisit.class));
                Animatoo.animateSlideLeft(getActivity());
            }
        });
        btnLaporanPelajaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LaporanPelajaranSiswa.class));
                Animatoo.animateSlideLeft(getActivity());
            }
        });

        btnPrestasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Prestasi.class));
                Animatoo.animateSlideLeft(getActivity());
            }
        });

        btnPelanggaranDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Pelanggaran.class));
                Animatoo.animateSlideLeft(getActivity());
            }
        });

        btnSuratPanggilan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PanggilanOrtu.class));
                Animatoo.animateSlideLeft(getActivity());
            }
        });

        btnKetidakHadiran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Ketidakhadiran.class));
                Animatoo.animateSlideLeft(getActivity());
            }
        });

        btnPermohonanIzin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PermohonanIzin.class));
                Animatoo.animateSlideLeft(getActivity());
            }
        });

        btnRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Rating.class));
                Animatoo.animateSlideLeft(getActivity());
            }
        });

        btnKehadiran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), KehadiranSiswa.class));
                Animatoo.animateSlideLeft(getActivity());
            }
        });

        btnGuruViewSiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ViewKelas.class));
                Animatoo.animateSlideLeft(getActivity());

            }
        });

        btnCatKonseling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CatatanKoseling.class));
                Animatoo.animateSlideLeft(getActivity());
            }
        });

        btnInputNilai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), InputNilaiGuru.class));
                Animatoo.animateSlideLeft(getActivity());
            }
        });

        btnTelusurAlumni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PenelusuranAlumni.class));
                Animatoo.animateSlideLeft(getActivity());
            }
        });


        return v;
    }

    private void ViewDataLogin() {
        String namaGuru = prefManagerGuru.getUser().getNama();
        tvNama.setText(namaGuru);
        String mapelGuru = prefManagerGuru.getUser().getMapel();
        tvMapel.setText("Guru " + mapelGuru);
        String npsnGuru = prefManagerGuru.getUser().getNpsn();
        tvNpsn.setText(npsnGuru);
        String url = "http://aksis.binainsanlestari.com/android_AKSIS/" + prefManagerGuru.getUser().getFoto();
        Picasso.with(getActivity())
                .load(url)
                .fit()
                .placeholder(R.drawable.teacher)
                .into(profileImg);

    }


    private void bindView(View v) {
        profileImg = v.findViewById(R.id.profile_image);
        btnLaporanPelajaran = v.findViewById(R.id.LaporanPelajaran);
        btnKetidakHadiran = v.findViewById(R.id.ketidakHadiranSiswa);
        btnPermohonanIzin = v.findViewById(R.id.surat_permohonan);
        tvNama = v.findViewById(R.id.nama_guru);
        tvMapel = v.findViewById(R.id.mapel_guru);
        tvNpsn = v.findViewById(R.id.npsn_guru);
        gridMenu = v.findViewById(R.id.gridmenu);
        btnRating = v.findViewById(R.id.rating_Guru);
        btnGuruViewSiswa = v.findViewById(R.id.guru_data_siswa);
        btnCatKonseling = v.findViewById(R.id.guru_konseling);
        btnHomeVisit = v.findViewById(R.id.guru_home_visit);
        btnTelusurAlumni = v.findViewById(R.id.guru_search_alumni);
        btnPrestasi = v.findViewById(R.id.prestasi);
        btnPelanggaranDetail = v.findViewById(R.id.guru_pelanggaran);
        btnInputNilai = v.findViewById(R.id.guru_input_nilai);
        btnKehadiran = v.findViewById(R.id.guru_kehadiran_siswa);
        btnSuratPanggilan = v.findViewById(R.id.surat_panggilan_ortu);
    }

}
