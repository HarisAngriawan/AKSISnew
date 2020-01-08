package com.binainsanlesatari.aksis.ViewSiswa.FRAGMENT;


import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.ViewSiswa.PelanggaranSiswa.BoardPelanggaranSiswa;
import com.binainsanlesatari.aksis.ViewSiswa.Suratpermohonan;
import com.binainsanlesatari.aksis.ViewSiswa.LaporanPelajaranSiswa;
import com.binainsanlesatari.aksis.utils.PrefManagerSiswa;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentHomeSiswa extends Fragment {
    GridLayout gridMenu;
    private CardView cvSuratPermohonan, cvlaporansiswa, cvDataPelanggaran, cvSuratPanggilan, cvKehadian, cvQuetioner;
    private TextView tvNama, tvNisn, tvNpsn;
    private PrefManagerSiswa prefManagerSiswa;
    private CircleImageView imgFotoProfil;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard_siswa, container, false);

        prefManagerSiswa = new PrefManagerSiswa(getActivity());

        bindView(view);
        ViewDataLoginSiswa();
//        gridMenu.removeView(btnSuratpermohonan);


        cvSuratPermohonan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Suratpermohonan.class));

            }
        });

        cvSuratPanggilan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), Suratpermohonan.class));

            }
        });

        cvDataPelanggaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), BoardPelanggaranSiswa.class));

            }
        });

        cvlaporansiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LaporanPelajaranSiswa.class));
            }
        });
        return view;
    }

    private void ViewDataLoginSiswa() {
        String namaSiswa = prefManagerSiswa.getSiswa().getNamaLengkap();
        tvNama.setText(namaSiswa);
        String nisnSiswa = prefManagerSiswa.getSiswa().getNisn();
        tvNisn.setText(nisnSiswa);
        String npsn = prefManagerSiswa.getSiswa().getNpsn();
        tvNpsn.setText(npsn);
        String url = "http://aksis.binainsanlestari.com/android_AKSIS/" + prefManagerSiswa.getSiswa().getFoto();
        Picasso.with(getActivity())
                .load(url)
                .fit()
                .placeholder(R.drawable.teacher)
                .into(imgFotoProfil);
    }

    private void bindView(View view) {
        tvNpsn = view.findViewById(R.id.tvNpsnS);
        tvNama = view.findViewById(R.id.tvNamaSiswa);
        tvNisn = view.findViewById(R.id.tvNisnS);
        gridMenu = view.findViewById(R.id.gridmenu);
        imgFotoProfil = view.findViewById(R.id.imgProfileSiswa);
        cvSuratPermohonan = view.findViewById(R.id.cvIzinS);
        cvlaporansiswa = view.findViewById(R.id.cvLaporanS);
        cvSuratPanggilan = view.findViewById(R.id.cvSuratPanggilanS);
        cvDataPelanggaran = view.findViewById(R.id.cvPelnggaranS);

    }

}
