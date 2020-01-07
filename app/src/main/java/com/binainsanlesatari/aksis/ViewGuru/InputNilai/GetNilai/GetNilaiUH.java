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
import com.binainsanlesatari.aksis.adapter.adapterGuru.adapterNilai.AdpGetNilaiUH;
import com.binainsanlesatari.aksis.model.GuruModel.Nilai.GetUH;
import com.binainsanlesatari.aksis.model.GuruModel.Nilai.LihatNilaiItem;
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

public class GetNilaiUH extends AppCompatActivity {
    private PrefManagerGuru prefManagerGuru;
    private AdpGetNilaiUH adpGetNilaiUH;
    private RecyclerView rvUHnilai;
    ShimmerFrameLayout shimmerFrameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_nilai_uh);

        Toolbar tbCommone = findViewById(R.id.tbCommon);
        tbCommone.setTitle("Nilai Ulangan Harian");
        setSupportActionBar(tbCommone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String kelas = getIntent().getExtras().getString(AppParams.KELAS);
        String mapel = getIntent().getExtras().getString(AppParams.MAPEL);
        String th_ajaran = getIntent().getExtras().getString(AppParams.TAHUNAJARAN);
        String semester = getIntent().getExtras().getString(AppParams.SEMESTER);

        prefManagerGuru = new PrefManagerGuru(this);
        adpGetNilaiUH = new AdpGetNilaiUH(this);

        shimmerFrameLayout = findViewById(R.id.shimmer_Layout);
        rvUHnilai = findViewById(R.id.rvNilaiUH);
        rvUHnilai.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
        rvUHnilai.setLayoutManager(new LinearLayoutManager(this));
        rvUHnilai.setAdapter(adpGetNilaiUH);
        
        getNilaiUH(kelas, mapel, th_ajaran,semester);
        
    }

    private void getNilaiUH(String kelas, String mapel, String th_ajaran, String semester) {
        ApiGuru apiGuru = RetrofitInstance.create().create(ApiGuru.class);
        String npsn = prefManagerGuru.getUser().getNpsn();
        String password = "duniamaya";


        apiGuru.getNilaiUh(npsn, password,kelas, mapel, th_ajaran, semester).enqueue(new Callback<GetUH>() {
            @Override
            public void onResponse(Call<GetUH> call, Response<GetUH> response) {
                List<LihatNilaiItem> list = response.body().getLihatNilai();
                adpGetNilaiUH.setData(list);
                adpGetNilaiUH.setData(list);
                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
                rvUHnilai.setVisibility(View.VISIBLE);
                Log.d("NILAI" ,list.toString());
            }
            @Override
            public void onFailure(Call<GetUH> call, Throwable t) {

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

