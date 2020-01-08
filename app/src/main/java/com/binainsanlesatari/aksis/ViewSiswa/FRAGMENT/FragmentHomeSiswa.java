package com.binainsanlesatari.aksis.ViewSiswa.FRAGMENT;


import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.ViewSiswa.Suratpermohonan;
import com.binainsanlesatari.aksis.ViewSiswa.laporan_pelajaran_siswa;
import com.binainsanlesatari.aksis.utils.PrefManagerSiswa;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentHomeSiswa extends Fragment {
    private CardView btnSuratpermohonan, btnlaporansiswa;
    private TextView tvNama, tvNisn,tvNpsn;
    private PrefManagerSiswa prefManagerSiswa;
    private CircleImageView imgFotoProfil;


    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard_siswa, container, false);

        prefManagerSiswa = new PrefManagerSiswa(getActivity());

        bindView(view);
        ViewDataLoginSiswa();



        btnSuratpermohonan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Suratpermohonan.class));

            }
        });

        btnlaporansiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), laporan_pelajaran_siswa.class));
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
        imgFotoProfil = view.findViewById(R.id.imgProfileSiswa);
        btnSuratpermohonan = view.findViewById(R.id.cvIzinS);
        btnlaporansiswa = view.findViewById(R.id.cvLaporanS);

    }

}
