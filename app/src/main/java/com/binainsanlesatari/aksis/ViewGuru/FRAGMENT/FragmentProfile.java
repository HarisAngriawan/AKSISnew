package com.binainsanlesatari.aksis.ViewGuru.FRAGMENT;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.ViewGuru.ChangePassword;
import com.binainsanlesatari.aksis.ViewGuru.EditProfileGuru;
import com.binainsanlesatari.aksis.WelcomeLogin;
import com.binainsanlesatari.aksis.utils.PrefManagerGuru;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class FragmentProfile extends Fragment {

    MaterialCardView cvLoogout, cvChangePassword, cvAboutApps;
    PrefManagerGuru prefManagerGuru;
    TextView tvNama, tvNip, tvNOHP;
    Button btnEdit;
    CircleImageView imgFotoEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        prefManagerGuru = new PrefManagerGuru(getActivity());

        cvChangePassword = v.findViewById(R.id.cvChangePassword);
        cvLoogout = v.findViewById(R.id.cvLogout);
        tvNama = v.findViewById(R.id.tvNamaGuru);
        tvNip = v.findViewById(R.id.tvNipGuru);
        tvNOHP = v.findViewById(R.id.tvNo_hpGuru);
        btnEdit = v.findViewById(R.id.btnEdit);
        imgFotoEdit = v.findViewById(R.id.imgEditFoto);
        String namaGuru = prefManagerGuru.getUser().getNama();
        tvNama.setText(namaGuru);
        String nip = prefManagerGuru.getUser().getNip();
        tvNip.setText(nip);
        String no_hp = prefManagerGuru.getUser().getNoHp();
        tvNOHP.setText(no_hp);
        String url = "http://aksis.binainsanlestari.com/android_AKSIS/" + prefManagerGuru.getUser().getFoto();
        Picasso.with(getActivity())
                .load(url)
                .fit()
                .into(imgFotoEdit);

        cvLoogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefManagerGuru.deleteGuru();
                startActivity(new Intent(getActivity(), WelcomeLogin.class));
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), EditProfileGuru.class));
            }
        });
        cvChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ChangePassword.class));
            }
        });
        return v;
    }

}
