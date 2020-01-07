package com.binainsanlesatari.aksis.ViewGuru.PanggilanOrtu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.adapter.adapterGuru.AdpPanggilanOrtu;
import com.binainsanlesatari.aksis.model.GuruModel.SuratPanggilanOrtu.ListPanggilan;
import com.binainsanlesatari.aksis.model.GuruModel.SuratPanggilanOrtu.ListPanggilanOrtuItem;
import com.binainsanlesatari.aksis.network.ApiGuru;
import com.binainsanlesatari.aksis.network.RetrofitInstance;
import com.binainsanlesatari.aksis.utils.PrefManagerGuru;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PanggilanOrtu extends AppCompatActivity {

    PrefManagerGuru prefManagerGuru;
    AdpPanggilanOrtu adpPanggilanOrtu;
    RecyclerView rvPanggilan;
    FloatingActionButton fbtnAddPanggilan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_panggilan_ortu);

        Toolbar tbCommone = findViewById(R.id.tbCommon);
        tbCommone.setTitle("Panggilan Orang Tua");
        setSupportActionBar(tbCommone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        prefManagerGuru = new PrefManagerGuru(this);
        adpPanggilanOrtu = new AdpPanggilanOrtu(this);

        fbtnAddPanggilan = findViewById(R.id.addPanggilanOrtu);
        rvPanggilan = findViewById(R.id.rvPanggilanoOrtu);
        rvPanggilan.setLayoutManager(new LinearLayoutManager(this));
        rvPanggilan.setAdapter(adpPanggilanOrtu);


        getPanggilan();
        fbtnAddPanggilan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PanggilanOrtu.this, AddPanggilanOrtu.class);
                startActivity(intent);
            }
        });

    }

    private void getPanggilan() {
        ApiGuru apiGuru = RetrofitInstance.create().create(ApiGuru.class);
        String npsn = prefManagerGuru.getUser().getNpsn();
        String status = "Belum diverifikasi";

        apiGuru.getPanggilanOrtu(npsn,status).enqueue(new Callback<ListPanggilan>() {
            @Override
            public void onResponse(Call<ListPanggilan> call, Response<ListPanggilan> response) {
                List<ListPanggilanOrtuItem> items = response.body().getListPanggilanOrtu();
                adpPanggilanOrtu.setData(items);
            }

            @Override
            public void onFailure(Call<ListPanggilan> call, Throwable t) {

            }
        });

    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Animatoo.animateSlideRight(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        Animatoo.animateSlideRight(this);
        return true;
    }
}
