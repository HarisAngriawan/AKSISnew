package com.binainsanlesatari.aksis.ViewSiswa.FRAGMENT;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.ViewSiswa.EditProfileSiswa;
import com.binainsanlesatari.aksis.WelcomeLogin;
import com.binainsanlesatari.aksis.utils.PrefManagerGuru;
import com.binainsanlesatari.aksis.utils.PrefManagerSiswa;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class FragmentProfileSiswa extends Fragment {

    MaterialCardView cvChangePassword,cvLogoutSiswa;
    PrefManagerSiswa prefManagerSiswa;
    TextView tvNama, tvNip, tvNOHP;
    Button btnEdit;
    CircleImageView imgFotoEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile_siswa, container, false);

        prefManagerSiswa = new PrefManagerSiswa(getActivity());
        
        cvChangePassword = v.findViewById(R.id.cvChangePassword);
        cvLogoutSiswa = v.findViewById(R.id.cvLogoutS);
        tvNama = v.findViewById(R.id.tvNamaS);
        tvNip = v.findViewById(R.id.tvNisnS);
        tvNOHP = v.findViewById(R.id.tvNisS);
        btnEdit = v.findViewById(R.id.btnEditS);
        imgFotoEdit = v.findViewById(R.id.imgEditFotoS);

        String namaSiswa = prefManagerSiswa.getSiswa().getNamaLengkap();
        tvNama.setText(namaSiswa);
        String nisnSiswa = prefManagerSiswa.getSiswa().getNisn();
        tvNip.setText(nisnSiswa);
        String kelas = prefManagerSiswa.getSiswa().getNamaKelasJurusan();
        String nomorkelas = prefManagerSiswa.getSiswa().getNomorKelas();
        tvNOHP.setText(kelas+" "+nomorkelas);
        String url = "http://aksis.binainsanlestari.com/android_AKSIS/" + prefManagerSiswa.getSiswa().getFoto();
        Picasso.with(getActivity())
                .load(url)
                .fit()
                .into(imgFotoEdit);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), EditProfileSiswa.class));
            }
        });
        cvLogoutSiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefManagerSiswa.deleteSiswa();
                startActivity(new Intent(getActivity(), WelcomeLogin.class));

            }
        });
        return v;
    }

}
