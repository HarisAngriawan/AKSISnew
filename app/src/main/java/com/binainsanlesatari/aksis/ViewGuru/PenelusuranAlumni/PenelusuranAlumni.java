package com.binainsanlesatari.aksis.ViewGuru.PenelusuranAlumni;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.ViewGuru.PenelusuranAlumni.InsertPenelusuranAlumni.InsertAlumni;
import com.binainsanlesatari.aksis.adapter.adapterGuru.AdpPenelusuranAlumni;
import com.binainsanlesatari.aksis.model.GuruModel.Alumni.Alumni;
import com.binainsanlesatari.aksis.model.GuruModel.Alumni.AlumniItem;
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

public class PenelusuranAlumni extends AppCompatActivity {
    private PrefManagerGuru prefManagerGuru;
    private AdpPenelusuranAlumni adpPenelusuranAlumni;
    private RecyclerView rvTelusurAlumni;
    private FloatingActionButton addPA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_penelusuran_alumni);


        Toolbar tbCommone = findViewById(R.id.tbCommon);
        tbCommone.setTitle("Alumni Sekolah");
        setSupportActionBar(tbCommone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        prefManagerGuru = new PrefManagerGuru(this);
        adpPenelusuranAlumni = new AdpPenelusuranAlumni(this);

        addPA = findViewById(R.id.addPA);
        rvTelusurAlumni = findViewById(R.id.rvAlumni);
        rvTelusurAlumni.setLayoutManager(new LinearLayoutManager(this));
        rvTelusurAlumni.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
        rvTelusurAlumni.setAdapter(adpPenelusuranAlumni);

        addPA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PenelusuranAlumni.this, InsertAlumni.class);
                startActivity(intent);
            }
        });

        getAlumni();
    }

    private void getAlumni() {
        Retrofit retrofit = RetrofitInstance.create();
        ApiGuru apiGuru = retrofit.create(ApiGuru.class);
        String npsn = prefManagerGuru.getUser().getNpsn();
        String password = "duniamaya";

        apiGuru.getAlumni(npsn, password).enqueue(new Callback<Alumni>() {
            @Override
            public void onResponse(Call<Alumni> call, Response<Alumni> response) {
                List<AlumniItem> alumni = response.body().getAlumni();
                adpPenelusuranAlumni.setData(alumni);
            }

            @Override
            public void onFailure(Call<Alumni> call, Throwable t) {

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
