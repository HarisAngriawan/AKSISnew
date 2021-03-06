package com.binainsanlesatari.aksis.ViewGuru.InputNilai;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.ViewGuru.InputNilai.GetNilai.GetNilaiTugas;
import com.binainsanlesatari.aksis.model.GuruModel.LaporanPelajaran.DataKelas;
import com.binainsanlesatari.aksis.model.GuruModel.LaporanPelajaran.DataKelasItem;
import com.binainsanlesatari.aksis.model.GuruModel.Nilai.DataKelasInputNilaiItem;
import com.binainsanlesatari.aksis.model.GuruModel.Nilai.DataMapelItem;
import com.binainsanlesatari.aksis.model.GuruModel.Nilai.MapelResponse;
import com.binainsanlesatari.aksis.model.GuruModel.Nilai.NilaiResponse;
import com.binainsanlesatari.aksis.model.GuruModel.Nilai.Semester;
import com.binainsanlesatari.aksis.model.GuruModel.Nilai.SemesterItem;
import com.binainsanlesatari.aksis.model.GuruModel.Nilai.TahunAjaran;
import com.binainsanlesatari.aksis.model.GuruModel.Nilai.TahunAjaranItem;
import com.binainsanlesatari.aksis.network.ApiGuru;
import com.binainsanlesatari.aksis.network.RetrofitInstance;
import com.binainsanlesatari.aksis.utils.AppParams;
import com.binainsanlesatari.aksis.utils.PrefManagerGuru;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InputNilai_Tugas extends AppCompatActivity {
    private Spinner spTugasKelas, spTugasMapel, spTugasSemes, spTugasTa,spNomorKelas;
    private ArrayAdapter<String> adpClass, adpMapel, adpSemester, adpTA,adpNomorKelas;
    Button showTugas;

    List<DataKelasInputNilaiItem> kelasList = new ArrayList<>();
    PrefManagerGuru prefManagerGuru;
    String npsn;
    String idguru;
    String password = "duniamaya";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_nilai__tugas);

        Toolbar tbCommone = findViewById(R.id.tbCommon);
        tbCommone.setTitle("Form Input Nilai Tugas");
        setSupportActionBar(tbCommone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        prefManagerGuru = new PrefManagerGuru(this);
        adpClass = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);
        adpNomorKelas = new ArrayAdapter<>(this, R.layout.my_spinner);
        adpMapel = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);
        adpSemester = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);
        adpTA = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);

        spNomorKelas = findViewById(R.id.spNomorKelasnilaiTugas);
        spTugasMapel = findViewById(R.id.select_mapel);
        spTugasSemes = findViewById(R.id.select_semester);
        spTugasTa = findViewById(R.id.select_th_ajaran);
        spTugasKelas = findViewById(R.id.select_kelas);
        showTugas = findViewById(R.id.showTugas);

        npsn = prefManagerGuru.getUser().getNpsn();
        idguru = prefManagerGuru.getUser().getIdPengguna();

        spTugasKelas.setAdapter(adpClass);
        spTugasMapel.setAdapter(adpMapel);
        spTugasSemes.setAdapter(adpSemester);
        spTugasTa.setAdapter(adpTA);
        spNomorKelas.setAdapter(adpNomorKelas);

        getMyClass();
        String npsnGuru = new PrefManagerGuru(this).getUser().getNpsn();
        getMyMapel(npsnGuru);
        getSemester();
        getTA();

        showTugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(InputNilai_Tugas.this, GetNilaiTugas.class);
                String kelas = spTugasKelas.getSelectedItem().toString();
                String semester = spTugasSemes.getSelectedItem().toString();
                String th_ajaran = spTugasTa.getSelectedItem().toString();
                String mapel = spTugasMapel.getSelectedItem().toString();
                intent.putExtra(AppParams.KELAS, kelas);
                intent.putExtra(AppParams.SEMESTER, semester);
                intent.putExtra(AppParams.TAHUNAJARAN, th_ajaran);
                intent.putExtra(AppParams.MAPEL, mapel);
                startActivity(intent);
            }
        });
    }

    private void getMyClass() {
        ApiGuru apiGuru = RetrofitInstance.create().create(ApiGuru.class);

        apiGuru.getMyClass(idguru, password).
                enqueue(new Callback<NilaiResponse>() {
                    @Override
                    public void onResponse(Call<NilaiResponse> call, Response<NilaiResponse> response) {
                        kelasList.clear();
                        kelasList = response.body().getDataKelasInputNilai();
                        List<String> string = new ArrayList<>();
                        for (DataKelasInputNilaiItem kelas : kelasList) {
                            string.add(kelas.getIdKelasDiajar());
                        }
                        adpClass.clear();
                        adpClass.addAll(string);
                    }

                    @Override
                    public void onFailure(Call<NilaiResponse> call, Throwable t) {

                    }
                });
        spTugasKelas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getNomorKelas(kelasList.get(i).getIdKelasDiajar());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void getNomorKelas(String idKelasDiajar) {
        ApiGuru apiGuru = RetrofitInstance.create().create(ApiGuru.class);
        String npsn = prefManagerGuru.getUser().getNpsn();
        String idguru = prefManagerGuru.getUser().getIdPengguna();

        apiGuru.getKelasLaporan(npsn, idguru, idKelasDiajar, "duniamaya").enqueue(new Callback<DataKelas>() {
            @Override
            public void onResponse(Call<DataKelas> call, Response<DataKelas> response) {
                List<DataKelasItem> data = response.body().getDataKelas();
                List<String> string = new ArrayList<>();
                for (DataKelasItem kelas : data) {
                    string.add(kelas.getNamaKelas());
                }
                adpNomorKelas.clear();
                adpNomorKelas.addAll(string);
            }

            @Override
            public void onFailure(Call<DataKelas> call, Throwable t) {

            }
        });
    }

    private void getMyMapel(String npsnMapel) {
        ApiGuru apiGuru = RetrofitInstance.create().create(ApiGuru.class);

        apiGuru.getMyMapel(npsnMapel, "duniamaya").enqueue(new Callback<MapelResponse>() {
            @Override
            public void onResponse(Call<MapelResponse> call, Response<MapelResponse> response) {
                for (DataMapelItem mapel : response.body().getDataMapel()) {
                    String item = mapel.getMapel();
                    adpMapel.add(item);
                    adpMapel.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MapelResponse> call, Throwable t) {

            }
        });
    }

    private void getSemester() {
        ApiGuru apiGuru = RetrofitInstance.create().create(ApiGuru.class);

        apiGuru.getSemester("duniamaya").enqueue(new Callback<Semester>() {
            @Override
            public void onResponse(Call<Semester> call, Response<Semester> response) {
                for (SemesterItem semester : response.body().getSemester()) {
                    String item = semester.getSemester();
                    adpSemester.add(item);
                    adpSemester.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Semester> call, Throwable t) {

            }
        });
    }

    private void getTA() {
        ApiGuru apiGuru = RetrofitInstance.create().create(ApiGuru.class);
        String password = "duniamaya";

        apiGuru.getTahunAjaran(password).enqueue(new Callback<TahunAjaran>() {
            @Override
            public void onResponse(Call<TahunAjaran> call, Response<TahunAjaran> response) {
                for (TahunAjaranItem ajaranItem : response.body().getTahunAjaran()) {
                    String item = ajaranItem.getThAjaran();
                    adpTA.add(item);
                    adpTA.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<TahunAjaran> call, Throwable t) {

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
