package com.binainsanlesatari.aksis.ViewGuru.InputNilai.GetNilai;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.adapter.adapterGuru.adapterNilai.AdpGetNilaiTugas;
import com.binainsanlesatari.aksis.model.GuruModel.Nilai.GetTugas;
import com.binainsanlesatari.aksis.model.GuruModel.Nilai.NilaiTugasItem;
import com.binainsanlesatari.aksis.network.ApiGuru;
import com.binainsanlesatari.aksis.network.RetrofitInstance;
import com.binainsanlesatari.aksis.utils.AppParams;
import com.binainsanlesatari.aksis.utils.PrefManagerGuru;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetNilaiTugas extends AppCompatActivity {

    private PrefManagerGuru prefManagerGuru;
    private AdpGetNilaiTugas adpGetNilaiTugas;
    private RecyclerView rvNilaiTugas;

    ShimmerFrameLayout shimmerFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_nilai_tugas);

        Toolbar tbCommone = findViewById(R.id.tbCommon);
        tbCommone.setTitle("Nilai Tugas");
        setSupportActionBar(tbCommone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String kelas = getIntent().getExtras().getString(AppParams.KELAS);
        String mapel = getIntent().getExtras().getString(AppParams.MAPEL);
        String th_ajaran = getIntent().getExtras().getString(AppParams.TAHUNAJARAN);
        String semester = getIntent().getExtras().getString(AppParams.SEMESTER);

        prefManagerGuru = new PrefManagerGuru(this);
        adpGetNilaiTugas = new AdpGetNilaiTugas(this);

        shimmerFrameLayout = findViewById(R.id.shimmer_Layout);
        rvNilaiTugas = findViewById(R.id.rvNilaiTugas);
        rvNilaiTugas.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
        rvNilaiTugas.setLayoutManager(new LinearLayoutManager(this));
        rvNilaiTugas.setAdapter(adpGetNilaiTugas);

        getNilaiTugas(kelas, mapel, th_ajaran, semester);
    }

    private void getNilaiTugas(String kelas, String mapel, String th_ajaran, String semester) {
        ApiGuru apiGuru = RetrofitInstance.create().create(ApiGuru.class);
        String npsn = prefManagerGuru.getUser().getNpsn();


        apiGuru.getNilaiTugas(npsn, kelas, mapel, th_ajaran, semester).enqueue(new Callback<GetTugas>() {
            @Override
            public void onResponse(Call<GetTugas> call, Response<GetTugas> response) {
                List<NilaiTugasItem> list = response.body().getNilaiTugas();
                adpGetNilaiTugas.setData(list);
                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
                rvNilaiTugas.setVisibility(View.VISIBLE);
                Log.d("NILAI", list.toString());
            }

            @Override
            public void onFailure(Call<GetTugas> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        shimmerFrameLayout.startShimmer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        shimmerFrameLayout.stopShimmer();
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
