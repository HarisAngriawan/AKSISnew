package com.binainsanlesatari.aksis.ViewGuru.Konseling;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.adapter.adapterGuru.AdpCatatanKonseling;
import com.binainsanlesatari.aksis.model.GuruModel.CatatanKonseling.CatatanKonseling;
import com.binainsanlesatari.aksis.model.GuruModel.CatatanKonseling.KonselingItem;
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

public class CatatanKoseling extends AppCompatActivity {
    private PrefManagerGuru prefManagerGuru;
    private AdpCatatanKonseling adpCatatanKonseling;
    private RecyclerView rvCatKos;
    FloatingActionButton addCatatanKonseling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_catatan_koseling);
        Toolbar tbCommone = findViewById(R.id.tbCommon);
        tbCommone.setTitle("Daftar Catatan Konseling");
        setSupportActionBar(tbCommone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        prefManagerGuru = new PrefManagerGuru(this);
        adpCatatanKonseling = new AdpCatatanKonseling(this);

        addCatatanKonseling = findViewById(R.id.addCK);
        rvCatKos = findViewById(R.id.rvCatatanKonseling);
        rvCatKos.setLayoutManager(new LinearLayoutManager(this));
        rvCatKos.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
        rvCatKos.setAdapter(adpCatatanKonseling);

        getCatatanKoseling();

        addCatatanKonseling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatatanKoseling.this, InputKonseling.class);
                startActivity(intent);
            }
        });
    }

    private void getCatatanKoseling() {
        Retrofit retrofit = RetrofitInstance.create();
        ApiGuru apiGuru = retrofit.create(ApiGuru.class);
        String npsn = prefManagerGuru.getUser().getNpsn();
        String password = "duniamaya";
        String status = "1";
        apiGuru.getCatatanKonseling(npsn, password, status).enqueue(new Callback<CatatanKonseling>() {
            @Override
            public void onResponse(Call<CatatanKonseling> call, Response<CatatanKonseling> response) {
                List<KonselingItem> koseling = response.body().getKonseling();
                adpCatatanKonseling.setData(koseling);
            }

            @Override
            public void onFailure(Call<CatatanKonseling> call, Throwable t) {

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
