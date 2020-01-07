package com.binainsanlesatari.aksis.ViewGuru.HomeVisit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.ViewGuru.HomeVisit.InputHomeVisit.InsertHomeVisit;
import com.binainsanlesatari.aksis.adapter.adapterGuru.AdpHomeVisit;
import com.binainsanlesatari.aksis.model.GuruModel.HomeVisit.HomeVisit;
import com.binainsanlesatari.aksis.model.GuruModel.HomeVisit.HomeVisitItem;
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

public class CatatanHomeVisit extends AppCompatActivity {
    private AdpHomeVisit adpHomeVisit;
    private PrefManagerGuru prefManagerGuru;
    private RecyclerView rvHomeVisit;
    private FloatingActionButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_catatan_home_visit);

        Toolbar tbCommone = findViewById(R.id.tbCommon);
        tbCommone.setTitle("Daftar Catatan HomeVisit");
        setSupportActionBar(tbCommone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        prefManagerGuru = new PrefManagerGuru(this);
        adpHomeVisit = new AdpHomeVisit(this);

        btnAdd = findViewById(R.id.addHV);
        rvHomeVisit = findViewById(R.id.rvHomeVisit);
        rvHomeVisit.setLayoutManager(new LinearLayoutManager(this));
        rvHomeVisit.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
        rvHomeVisit.setAdapter(adpHomeVisit);

        getHomeVisit();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatatanHomeVisit.this, InsertHomeVisit.class);
                startActivity(intent);
            }
        });
    }

    private void getHomeVisit() {
        Retrofit retrofit = RetrofitInstance.create();
        ApiGuru apiGuru = retrofit.create(ApiGuru.class);

        String npsn = prefManagerGuru.getUser().getNpsn();
        String pass = "duniamaya";

        apiGuru.getHomeVisit(npsn, pass).enqueue(new Callback<HomeVisit>() {
            @Override
            public void onResponse(Call<HomeVisit> call, Response<HomeVisit> response) {
                List<HomeVisitItem> visit = response.body().getHomeVisit();
                adpHomeVisit.setData(visit);
            }

            @Override
            public void onFailure(Call<HomeVisit> call, Throwable t) {

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
