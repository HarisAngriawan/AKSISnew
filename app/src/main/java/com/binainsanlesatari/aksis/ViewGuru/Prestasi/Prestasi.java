package com.binainsanlesatari.aksis.ViewGuru.Prestasi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.adapter.adapterGuru.AdpPrestasi;
import com.binainsanlesatari.aksis.model.GuruModel.Prestasi.DataPrestasiItem;
import com.binainsanlesatari.aksis.model.GuruModel.Prestasi.PrestasiResponse;
import com.binainsanlesatari.aksis.network.ApiGuru;
import com.binainsanlesatari.aksis.network.RetrofitInstance;
import com.binainsanlesatari.aksis.utils.PrefManagerGuru;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Prestasi extends AppCompatActivity {
    private PrefManagerGuru prefManagerGuru;
    private AdpPrestasi adpPrestasi;
    private RecyclerView rvPrestasi;
    FloatingActionButton addPrestasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_prestasi);
        Toolbar tbCommone = findViewById(R.id.tbCommon);
        tbCommone.setTitle("Prestasi Siswa");
        setSupportActionBar(tbCommone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        prefManagerGuru = new PrefManagerGuru(this);
        adpPrestasi = new AdpPrestasi(this);

        addPrestasi = findViewById(R.id.addPrestasi);
        rvPrestasi = findViewById(R.id.rvPrestasi);
        rvPrestasi.setLayoutManager(new LinearLayoutManager(this));
        rvPrestasi.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
        rvPrestasi.setAdapter(adpPrestasi);

        getPrestasi();

        addPrestasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Prestasi.this, InputPrestasiSiswa.class);
                startActivity(intent);
            }
        });
    }


    private void getPrestasi() {
        Retrofit retrofit = RetrofitInstance.create();
        ApiGuru apiGuru = retrofit.create(ApiGuru.class);
        String npsn = prefManagerGuru.getUser().getNpsn();
        String passowrd = "duniamaya";

        apiGuru.getPrestasi(npsn, passowrd).enqueue(new Callback<PrestasiResponse>() {
            @Override
            public void onResponse(Call<PrestasiResponse> call, Response<PrestasiResponse> response) {
                List<DataPrestasiItem> dataprestasi = response.body().getDataPrestasi();
                adpPrestasi.setData(dataprestasi);
            }

            @Override
            public void onFailure(Call<PrestasiResponse> call, Throwable t) {

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