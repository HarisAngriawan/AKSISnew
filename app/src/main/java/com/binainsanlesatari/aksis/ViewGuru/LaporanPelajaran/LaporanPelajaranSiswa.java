package com.binainsanlesatari.aksis.ViewGuru.LaporanPelajaran;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.adapter.adapterGuru.AdpLaporanPelajaran;
import com.binainsanlesatari.aksis.model.GuruModel.LaporanPelajaran.DataLaporanPelajaranItem;
import com.binainsanlesatari.aksis.model.GuruModel.LaporanPelajaran.LaporanPelajaran;
import com.binainsanlesatari.aksis.network.ApiGuru;
import com.binainsanlesatari.aksis.network.RetrofitInstance;
import com.binainsanlesatari.aksis.utils.PrefManagerGuru;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaporanPelajaranSiswa extends AppCompatActivity {
    AdpLaporanPelajaran adpLaporanPelajaran;
    RecyclerView rvLaporanPelajaran;
    PrefManagerGuru prefManagerGuru;
    ProgressBar pbSearch;
    MaterialCardView cvTanggal, cvShowData;
    TextView tvTanggal, tvLaporanNama;
    DatePickerDialog datePickerDialogpicker;
    FloatingActionButton addLaporan;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_laporan_pelajaran);
        Toolbar tbCommone = findViewById(R.id.tbCommon);
        tbCommone.setTitle("MainPelanggaran");
        setSupportActionBar(tbCommone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        adpLaporanPelajaran = new AdpLaporanPelajaran(this);
        prefManagerGuru = new PrefManagerGuru(this);

        addLaporan = findViewById(R.id.addLaporan);
        pbSearch = findViewById(R.id.pbSearch);
        cvTanggal = findViewById(R.id.cvDateShow);
        cvShowData = findViewById(R.id.cvShowData);
        tvTanggal = findViewById(R.id.dateSearch);

        rvLaporanPelajaran = findViewById(R.id.rvLaporanPelajaran);
        rvLaporanPelajaran.setLayoutManager(new LinearLayoutManager(this));
        rvLaporanPelajaran.setAdapter(adpLaporanPelajaran);


        addLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LaporanPelajaranSiswa.this, addLaporanPelajaran.class);
                startActivity(intent);
            }
        });

        cvShowData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLaporanPelajaran();
            }
        });

        cvTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialogpicker = new DatePickerDialog(LaporanPelajaranSiswa.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        tvTanggal.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, year, month, day);
                datePickerDialogpicker.show();
            }
        });

    }




    private void getLaporanPelajaran() {

        ApiGuru apiGuru = RetrofitInstance.create().create(ApiGuru.class);
        String npsn = prefManagerGuru.getUser().getNpsn();
        String status = "1";
        String id_pengguna = prefManagerGuru.getUser().getIdPengguna();
        String tanggal = tvTanggal.getText().toString();
        pbSearch.setVisibility(View.VISIBLE);
        apiGuru.getLaporanPelajaran(npsn, status, id_pengguna, tanggal).enqueue(new Callback<LaporanPelajaran>() {
            @Override
            public void onResponse(Call<LaporanPelajaran> call, Response<LaporanPelajaran> response) {
                List<DataLaporanPelajaranItem> pelajaranItem = response.body().getDataLaporanPelajaran();
                adpLaporanPelajaran.setData(pelajaranItem);
                pbSearch.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<LaporanPelajaran> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBackPressed() {
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
