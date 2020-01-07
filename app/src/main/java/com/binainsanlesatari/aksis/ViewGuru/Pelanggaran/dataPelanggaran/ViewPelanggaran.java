package com.binainsanlesatari.aksis.ViewGuru.Pelanggaran.dataPelanggaran;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.adapter.adapterGuru.AdpKategoriPelanggaran;
import com.binainsanlesatari.aksis.model.GuruModel.Pelaggaran.ViewPelanggaran.DataPelanggaranPerKategoriItem;
import com.binainsanlesatari.aksis.model.GuruModel.Pelaggaran.ViewPelanggaran.KategoriPelanggaranResponse;
import com.binainsanlesatari.aksis.network.ApiGuru;
import com.binainsanlesatari.aksis.network.RetrofitInstance;
import com.binainsanlesatari.aksis.utils.PrefManagerGuru;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ViewPelanggaran extends AppCompatActivity {
    private PrefManagerGuru prefManagerGuru;
    private AdpKategoriPelanggaran adpKategoriPelanggaran;
    private RecyclerView rvKelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pelanggaran);
        Toolbar tbCommone = findViewById(R.id.tbCommon);
        tbCommone.setTitle("Pelanggaran Siswa");
        setSupportActionBar(tbCommone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        prefManagerGuru = new PrefManagerGuru(this);
        adpKategoriPelanggaran = new AdpKategoriPelanggaran(this);

        rvKelas = findViewById(R.id.rvDetailPelanggaran);
        rvKelas.setLayoutManager(new LinearLayoutManager(this));
        rvKelas.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
        rvKelas.setAdapter(adpKategoriPelanggaran);

        getKategori();
    }

    private void getKategori() {

        Retrofit retrofit = RetrofitInstance.create();
        ApiGuru apiGuru = retrofit.create(ApiGuru.class);
        String npsn = prefManagerGuru.getUser().getNpsn();
        String password = "duniamaya";

        apiGuru.getKategori(npsn, password).enqueue(new Callback<KategoriPelanggaranResponse>() {
            @Override
            public void onResponse(Call<KategoriPelanggaranResponse> call, Response<KategoriPelanggaranResponse> response) {
                List<DataPelanggaranPerKategoriItem> detailKategori = response.body().getDataPelanggaranPerKategori();
                adpKategoriPelanggaran.setPelanggran(detailKategori);
            }

            @Override
            public void onFailure(Call<KategoriPelanggaranResponse> call, Throwable t) {

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
