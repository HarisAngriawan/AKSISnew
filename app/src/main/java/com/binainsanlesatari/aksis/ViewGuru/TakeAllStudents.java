package com.binainsanlesatari.aksis.ViewGuru;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.adapter.adapterGuru.AdpGetSearchSiswa;
import com.binainsanlesatari.aksis.model.GuruModel.ViewSiswa.InsertSiswa;
import com.binainsanlesatari.aksis.network.ApiSearch;
import com.binainsanlesatari.aksis.network.RetrofitInstance;
import com.binainsanlesatari.aksis.utils.PrefManagerGuru;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TakeAllStudents extends AppCompatActivity {
    EditText etSearchSiswa;
    RecyclerView rvSearchSiswa;
    AdpGetSearchSiswa adpGetSearchSiswa;
    ProgressBar pbSearch;
    PrefManagerGuru prefManagerGuru;
    ApiSearch apiSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_all_students);
        Toolbar tbCommone = findViewById(R.id.tbCommon);
        tbCommone.setTitle("Cari Siswa");
        setSupportActionBar(tbCommone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        apiSearch = RetrofitInstance.create().create(ApiSearch.class);
        prefManagerGuru = new PrefManagerGuru(this);
        etSearchSiswa = findViewById(R.id.etSearch);
        pbSearch = findViewById(R.id.pbSearch);
        rvSearchSiswa = findViewById(R.id.rvSearchSiswa);

        adpGetSearchSiswa = new AdpGetSearchSiswa(this);
        rvSearchSiswa.setLayoutManager(new LinearLayoutManager(this));
        rvSearchSiswa.setAdapter(adpGetSearchSiswa);

        etSearchSiswa.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String searchQuery = v.getText().toString();
                    searchStudent(searchQuery);
                }
                pbSearch.setVisibility(View.VISIBLE);

                return true;
            }
        });
    }

    private void searchStudent(String searchQuery) {
        apiSearch.searchSiswaByName(prefManagerGuru.getUser().getNpsn(), searchQuery)
                .enqueue(new Callback<InsertSiswa>() {
                    @Override
                    public void onResponse(Call<InsertSiswa> call, Response<InsertSiswa> response) {
                        pbSearch.setVisibility(View.GONE);
                        adpGetSearchSiswa.setData(response.body().getInsertSiswa());
                    }

                    @Override
                    public void onFailure(Call<InsertSiswa> call, Throwable t) {

                    }
                });
    }
}
