package com.binainsanlesatari.aksis.ViewGuru.Siswa;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.adapter.adapterGuru.AdpSiswa;
import com.binainsanlesatari.aksis.model.GuruModel.ViewSiswa.Siswa;
import com.binainsanlesatari.aksis.network.ApiGuru;
import com.binainsanlesatari.aksis.network.RetrofitInstance;
import com.binainsanlesatari.aksis.utils.PrefManagerGuru;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.facebook.shimmer.ShimmerFrameLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.binainsanlesatari.aksis.utils.AppParams.IDKELAS;
import static com.binainsanlesatari.aksis.utils.AppParams.NAMAGURU;

public class GetSiswa extends AppCompatActivity {
    EditText etSearch;
    private AdpSiswa adpSiswa;
    private RecyclerView rvSiswa;
    PrefManagerGuru prefManagerGuru;
    ShimmerFrameLayout shimmerFrameLayout;
    ProgressBar pbPlaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_siswa);
        String idKelas = getIntent().getStringExtra(IDKELAS);
        String namaguru = getIntent().getStringExtra(NAMAGURU);
        Toolbar tbCommone = findViewById(R.id.tbCommon);
        tbCommone.setTitle("Data Siswa");
        tbCommone.setSubtitle(namaguru);
        setSupportActionBar(tbCommone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setupSearch();

        prefManagerGuru = new PrefManagerGuru(this);
        adpSiswa = new AdpSiswa(this,true);

        pbPlaceHolder = findViewById(R.id.pbPlaceHolder);
        shimmerFrameLayout = findViewById(R.id.shimmer_Layout);
        rvSiswa = findViewById(R.id.rvSiswa);
        rvSiswa.setLayoutManager(new LinearLayoutManager(this));

        rvSiswa.setAdapter(adpSiswa);

        getDataSiswaPerkelas(idKelas);
    }

    private void setupSearch() {
        LinearLayout tbMenuContainer = findViewById(R.id.tbMenuContainer);
        etSearch = new EditText(this);
        etSearch.setHint("Cari Siswa");
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = s.toString();
                adpSiswa.searchData(query);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        tbMenuContainer.addView(etSearch);
    }

    private void getDataSiswaPerkelas(String idKelas) {
        pbPlaceHolder.setVisibility(View.VISIBLE);
        final ApiGuru apiGuru = RetrofitInstance.create().create(ApiGuru.class);

        apiGuru.getDataSiswaPerkelas(idKelas, "duniamaya").enqueue(new Callback<Siswa>() {
            @Override
            public void onResponse(Call<Siswa> call, Response<Siswa> response) {
                adpSiswa.setData(response.body().getSiswa());
                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
                pbPlaceHolder.setVisibility(View.GONE);
                rvSiswa.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<Siswa> call, Throwable t) {

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
        shimmerFrameLayout.startShimmer();
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
