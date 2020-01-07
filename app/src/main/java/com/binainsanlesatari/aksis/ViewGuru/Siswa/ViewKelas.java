package com.binainsanlesatari.aksis.ViewGuru.Siswa;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.adapter.adapterGuru.AdpKelas;
import com.binainsanlesatari.aksis.model.GuruModel.ViewSiswa.Kelas;
import com.binainsanlesatari.aksis.model.GuruModel.ViewSiswa.KelasResponse;
import com.binainsanlesatari.aksis.network.ApiGuru;
import com.binainsanlesatari.aksis.network.RetrofitInstance;
import com.binainsanlesatari.aksis.utils.PrefManagerGuru;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ViewKelas extends AppCompatActivity {
    private EditText etSearch;
    ShimmerFrameLayout shimmerFrameLayout;
    private PrefManagerGuru prefManagerGuru;
    private AdpKelas adpKelas;
    private RecyclerView rvKelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data_kelas);
        Toolbar tbCommone = findViewById(R.id.tbCommon);
        tbCommone.setTitle("Data Kelas Sekolah");
        setSupportActionBar(tbCommone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setupSearch();

        prefManagerGuru = new PrefManagerGuru(this);
        adpKelas = new AdpKelas(this,true);

        shimmerFrameLayout = findViewById(R.id.shimmer_Layout);
        rvKelas = findViewById(R.id.rvKelas);
        rvKelas.setLayoutManager(new LinearLayoutManager(this));
        rvKelas.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
        rvKelas.setAdapter(adpKelas);


        getAllKelas();
    }

    private void setupSearch() {
        LinearLayout tbMenuContainer = findViewById(R.id.tbMenuContainer);
        etSearch = new EditText(this);
        etSearch.setHint("Cari Kelas");
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = s.toString();
                adpKelas.searchData(query);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        tbMenuContainer.addView(etSearch);

    }


    private void getAllKelas() {
        Retrofit retrofit = RetrofitInstance.create();
        ApiGuru apiGuru = retrofit.create(ApiGuru.class);
        String npsn = prefManagerGuru.getUser().getNpsn();
        String password = "duniamaya";

        apiGuru.getAllKelas(npsn, password).enqueue(new Callback<KelasResponse>() {
            @Override
            public void onResponse(Call<KelasResponse> call, Response<KelasResponse> response) {
                List<Kelas> data = response.body().getListKelas();
                adpKelas.setData(data);

                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
                rvKelas.setVisibility(View.VISIBLE);

                Log.d("Kelas Response", data.toString());
            }

            @Override
            public void onFailure(Call<KelasResponse> call, Throwable t) {

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
