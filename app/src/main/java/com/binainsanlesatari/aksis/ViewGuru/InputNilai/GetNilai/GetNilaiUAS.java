package com.binainsanlesatari.aksis.ViewGuru.InputNilai.GetNilai;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.adapter.adapterGuru.adapterNilai.AdpGetNilaiUAS;
import com.binainsanlesatari.aksis.model.GuruModel.Nilai.GetUAS;
import com.binainsanlesatari.aksis.model.GuruModel.Nilai.NilaiUASItem;
import com.binainsanlesatari.aksis.network.ApiGuru;
import com.binainsanlesatari.aksis.network.RetrofitInstance;
import com.binainsanlesatari.aksis.utils.AppParams;
import com.binainsanlesatari.aksis.utils.PrefManagerGuru;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetNilaiUAS extends AppCompatActivity {
    RecyclerView rvNilaiUas;
    PrefManagerGuru prefManagerGuru;
    AdpGetNilaiUAS adpGetNilaiUAS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_nilai_uas);

        Toolbar tbCommone = findViewById(R.id.tbCommon);
        tbCommone.setTitle("Nilai UAS");
        setSupportActionBar(tbCommone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String kelas = getIntent().getStringExtra(AppParams.KELAS);
        String semester = getIntent().getStringExtra(AppParams.SEMESTER);
        String th_ajaran = getIntent().getStringExtra(AppParams.TAHUNAJARAN);
        String mapel = getIntent().getStringExtra(AppParams.MAPEL);

        prefManagerGuru = new PrefManagerGuru(this);
        adpGetNilaiUAS = new AdpGetNilaiUAS(this);

        rvNilaiUas = findViewById(R.id.rvNilaiUAS);
        rvNilaiUas.addItemDecoration(new DividerItemDecoration(this,RecyclerView.VERTICAL));
        rvNilaiUas.setLayoutManager(new LinearLayoutManager(this));
        rvNilaiUas.setAdapter(adpGetNilaiUAS);

        getNilaiUas(kelas,mapel,semester,th_ajaran);
    }

    private void getNilaiUas(String kelas, String mapel, String semester, String th_ajaran) {
        ApiGuru apiGuru = RetrofitInstance.create().create(ApiGuru.class);
        String npsn = prefManagerGuru.getUser().getNpsn();

        apiGuru.getNilaiUas(npsn,kelas, mapel, th_ajaran, semester).enqueue(new Callback<GetUAS>() {
            @Override
            public void onResponse(Call<GetUAS> call, Response<GetUAS> response) {
                List<NilaiUASItem> item = response.body().getNilaiUAS();
                adpGetNilaiUAS.setData(item);
            }

            @Override
            public void onFailure(Call<GetUAS> call, Throwable t) {

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
