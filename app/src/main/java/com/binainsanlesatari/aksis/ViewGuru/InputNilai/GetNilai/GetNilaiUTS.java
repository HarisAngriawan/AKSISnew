package com.binainsanlesatari.aksis.ViewGuru.InputNilai.GetNilai;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.adapter.adapterGuru.adapterNilai.AdpGetNilaiUTS;
import com.binainsanlesatari.aksis.model.GuruModel.Nilai.GetUTS;
import com.binainsanlesatari.aksis.model.GuruModel.Nilai.NilaiUTSItem;
import com.binainsanlesatari.aksis.network.ApiGuru;
import com.binainsanlesatari.aksis.network.RetrofitInstance;
import com.binainsanlesatari.aksis.utils.AppParams;
import com.binainsanlesatari.aksis.utils.PrefManagerGuru;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetNilaiUTS extends AppCompatActivity {
    private PrefManagerGuru prefManagerGuru;
    private AdpGetNilaiUTS adpGetNilaiUTS;
    private RecyclerView rvNilaiUTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_nilai_uts);

        Toolbar tbCommone = findViewById(R.id.tbCommon);
        tbCommone.setTitle("Nilai UTS");
        setSupportActionBar(tbCommone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String kelas = getIntent().getExtras().getString(AppParams.KELAS);
        String mapel = getIntent().getExtras().getString(AppParams.MAPEL);
        String th_ajaran = getIntent().getExtras().getString(AppParams.TAHUNAJARAN);
        String semester = getIntent().getExtras().getString(AppParams.SEMESTER);

        prefManagerGuru = new PrefManagerGuru(this);
        adpGetNilaiUTS = new AdpGetNilaiUTS(this);

        rvNilaiUTS = findViewById(R.id.rvNilaiUTS);
        rvNilaiUTS.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
        rvNilaiUTS.setLayoutManager(new LinearLayoutManager(this));
        rvNilaiUTS.setAdapter(adpGetNilaiUTS);

        getNilaiUTS(kelas, mapel, th_ajaran, semester);
    }

    private void getNilaiUTS(String kelas, String mapel, String th_ajaran, String semester) {
        ApiGuru apiGuru = RetrofitInstance.create().create(ApiGuru.class);
        String npsn = prefManagerGuru.getUser().getNpsn();
        String password = "duniamaya";


        apiGuru.getNilaiUTS(npsn, kelas, mapel, th_ajaran, semester).enqueue(new Callback<GetUTS>() {
            @Override
            public void onResponse(Call<GetUTS> call, Response<GetUTS> response) {
                List<NilaiUTSItem> list = response.body().getNilaiUTS();
                adpGetNilaiUTS.setData(list);
            }

            @Override
            public void onFailure(Call<GetUTS> call, Throwable t) {

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
