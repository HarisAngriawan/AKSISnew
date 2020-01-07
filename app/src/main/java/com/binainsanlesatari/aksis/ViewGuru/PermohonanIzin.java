package com.binainsanlesatari.aksis.ViewGuru;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.adapter.adapterGuru.AdpPermohonanIzin;
import com.binainsanlesatari.aksis.model.GuruModel.PermohonanIzin.Izin;
import com.binainsanlesatari.aksis.model.GuruModel.PermohonanIzin.IzinItemsItem;
import com.binainsanlesatari.aksis.network.ApiGuru;
import com.binainsanlesatari.aksis.network.RetrofitInstance;
import com.binainsanlesatari.aksis.utils.PrefManagerGuru;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PermohonanIzin extends AppCompatActivity {

    private RecyclerView rvPermohonanIzin;
    private AdpPermohonanIzin adpPermohonanIzin;
    private PrefManagerGuru prefManagerGuru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_permohonan_izin);
        Toolbar tbCommone = findViewById(R.id.tbCommon);
        tbCommone.setTitle("Data Permohonan Izin Siswa");
        setSupportActionBar(tbCommone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        adpPermohonanIzin = new AdpPermohonanIzin(this);
        prefManagerGuru = new PrefManagerGuru(this);

        rvPermohonanIzin = findViewById(R.id.rvPermohonanIzin);
        rvPermohonanIzin.setLayoutManager(new LinearLayoutManager(this));
        rvPermohonanIzin.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
        rvPermohonanIzin.setAdapter(adpPermohonanIzin);

        getPermohonanIzin();

    }

    private void getPermohonanIzin() {
        ApiGuru apiGuru = RetrofitInstance.create().create(ApiGuru.class);
        String npsn = prefManagerGuru.getUser().getNpsn();

        apiGuru.getPermohonanIzin(npsn).enqueue(new Callback<Izin>() {
            @Override
            public void onResponse(Call<Izin> call, Response<Izin> response) {
                List<IzinItemsItem> permohonanIzin = response.body().getIzinItems();
                adpPermohonanIzin.setData(permohonanIzin);
            }

            @Override
            public void onFailure(Call<Izin> call, Throwable t) {

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
