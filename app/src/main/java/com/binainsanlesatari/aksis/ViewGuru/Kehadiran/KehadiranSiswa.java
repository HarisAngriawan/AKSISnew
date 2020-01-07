package com.binainsanlesatari.aksis.ViewGuru.Kehadiran;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.adapter.adapterGuru.AdpKehadiran;
import com.binainsanlesatari.aksis.model.GuruModel.Kehadiran.DataKehadiranItem;
import com.binainsanlesatari.aksis.model.GuruModel.Kehadiran.KehadiranPerKelas;
import com.binainsanlesatari.aksis.network.ApiGuru;
import com.binainsanlesatari.aksis.network.RetrofitInstance;
import com.binainsanlesatari.aksis.utils.PrefManagerGuru;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class KehadiranSiswa extends AppCompatActivity {
    private PrefManagerGuru prefManagerGuru;
    private AdpKehadiran adpKehadiran;
    private RecyclerView rvKehadiran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_kehadiran_siswa);

        Toolbar tbCommone = findViewById(R.id.tbCommon);
        tbCommone.setTitle("Data Kehadiran Siswa");
        setSupportActionBar(tbCommone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        prefManagerGuru = new PrefManagerGuru(this);
        adpKehadiran = new AdpKehadiran(this);

        rvKehadiran = findViewById(R.id.rvKehadiran);
        rvKehadiran.setLayoutManager(new LinearLayoutManager(this));
        rvKehadiran.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
        rvKehadiran.setAdapter(adpKehadiran);


        getKehadiran();
    }

    private void getKehadiran() {
        Retrofit retrofit = RetrofitInstance.create();
        ApiGuru apiGuru = retrofit.create(ApiGuru.class);
        String npsn = prefManagerGuru.getUser().getNpsn();
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        String password = "duniamaya";
        String tgl = date;

        apiGuru.getKehadiran(npsn,password,tgl).enqueue(new Callback<KehadiranPerKelas>() {
            @Override
            public void onResponse(Call<KehadiranPerKelas> call, Response<KehadiranPerKelas> response) {
                List<DataKehadiranItem>list = response.body().getDataKehadiran();
                adpKehadiran.setKehadiran(list);
//                Log.d("kehadiran", data.toString());
            }

            @Override
            public void onFailure(Call<KehadiranPerKelas> call, Throwable t) {

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
