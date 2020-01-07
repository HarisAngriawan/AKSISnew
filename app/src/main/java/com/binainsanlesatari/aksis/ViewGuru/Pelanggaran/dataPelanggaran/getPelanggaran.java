package com.binainsanlesatari.aksis.ViewGuru.Pelanggaran.dataPelanggaran;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.adapter.adapterGuru.AdpPelanggaran;
import com.binainsanlesatari.aksis.model.GuruModel.Pelaggaran.ViewPelanggaran.DetailPelanggaranResponse;
import com.binainsanlesatari.aksis.network.ApiGuru;
import com.binainsanlesatari.aksis.network.RetrofitInstance;
import com.binainsanlesatari.aksis.utils.AppParams;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class getPelanggaran extends AppCompatActivity {

    private AdpPelanggaran adpPelanggaran;
    private RecyclerView rvPelanggaran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_pelanggaran);
        Toolbar tbCommone = findViewById(R.id.tbCommon);
        tbCommone.setTitle("Daftar Pelanggaran Siswa");
        setSupportActionBar(tbCommone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


//        String idKategori = getIntent().getStringExtra(AppParams.IDKATEGORI);
        String idKategori = getIntent().getStringExtra(AppParams.IDPELANGGARAN);

        adpPelanggaran = new AdpPelanggaran(this);

        rvPelanggaran = findViewById(R.id.rvPelanggaran);
        rvPelanggaran.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
        rvPelanggaran.setLayoutManager(new LinearLayoutManager(this));

        rvPelanggaran.setAdapter(adpPelanggaran);

        getPelanggaran(idKategori);
    }

    private void getPelanggaran(String kategoriPelanggaran ){
        final ApiGuru apiGuru = RetrofitInstance.create().create(ApiGuru.class);

        apiGuru.getPelanggaran(kategoriPelanggaran, "duniamaya").enqueue(new Callback<DetailPelanggaranResponse>() {
            @Override
            public void onResponse(Call<DetailPelanggaranResponse> call, Response<DetailPelanggaranResponse> response) {
                adpPelanggaran.setData(response.body().getDetailPelanggaran());
            }

            @Override
            public void onFailure(Call<DetailPelanggaranResponse> call, Throwable t) {

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
