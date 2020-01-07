package com.binainsanlesatari.aksis.ViewGuru;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.adapter.adapterGuru.AdpKetidakhadiran;
import com.binainsanlesatari.aksis.model.GuruModel.Ketidakhadiran.DataTidakHadir;
import com.binainsanlesatari.aksis.model.GuruModel.Ketidakhadiran.DataTidakHadirItem;
import com.binainsanlesatari.aksis.network.ApiGuru;
import com.binainsanlesatari.aksis.network.RetrofitInstance;
import com.binainsanlesatari.aksis.utils.PrefManagerGuru;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.card.MaterialCardView;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Ketidakhadiran extends AppCompatActivity {
    DatePickerDialog datePickerDialogpicker;

    AdpKetidakhadiran adpKetidakhadiran;
    RecyclerView rvKetidakhadiran;
    PrefManagerGuru prefManagerGuru;
    ProgressBar pbSearch;
    MaterialCardView cvTanggal, cvShowData;
    TextView tvTanggal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ketidakhadiran);

        Toolbar tbCommone = findViewById(R.id.tbCommon);
        tbCommone.setTitle("Data Ketidakhadiran Siswa");
        setSupportActionBar(tbCommone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        adpKetidakhadiran = new AdpKetidakhadiran(this);
        prefManagerGuru = new PrefManagerGuru(this);
        datePickerDialogpicker = new DatePickerDialog(this);

        pbSearch = findViewById(R.id.pbSearch);
        cvTanggal = findViewById(R.id.cvDateShow);
        cvShowData = findViewById(R.id.cvShowData);
        tvTanggal = findViewById(R.id.dateSearch);

        rvKetidakhadiran = findViewById(R.id.rvKetidakHadiran);
        rvKetidakhadiran.setLayoutManager(new LinearLayoutManager(this));
        rvKetidakhadiran.setAdapter(adpKetidakhadiran);


        cvShowData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getKetidakhadiran();
            }
        });
        cvTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialogpicker = new DatePickerDialog(Ketidakhadiran.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        tvTanggal.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, year, month, day);
                datePickerDialogpicker.show();
            }
        });

    }

    private void getKetidakhadiran() {
        ApiGuru apiGuru = RetrofitInstance.create().create(ApiGuru.class);
        String npsn = prefManagerGuru.getUser().getNpsn();
        String tanggal = tvTanggal.getText().toString();
        pbSearch.setVisibility(View.VISIBLE);
        apiGuru.getKetidakhadiran(npsn, tanggal).enqueue(new Callback<DataTidakHadir>() {
            @Override
            public void onResponse(Call<DataTidakHadir> call, Response<DataTidakHadir> response) {
                List<DataTidakHadirItem> tidakHadir = response.body().getDataTidakHadir();
                adpKetidakhadiran.setData(tidakHadir);
                pbSearch.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<DataTidakHadir> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        Animatoo.animateSlideRight(this);
        return true;
    }
}
