package com.binainsanlesatari.aksis.ViewGuru.InputNilai;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.ViewGuru.InputNilai.GetNilai.GetNilaiUTS;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InputNilai_UTS extends AppCompatActivity {
    private Spinner spUTSKelas, spUTSMapel, spUTSSemester, spUTSTa;
    private ArrayAdapter<String> adpClass, adpMapel, adpTA, adpSemester;
    Button showUTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_nilai__uts);
        Toolbar tbCommone = findViewById(R.id.tbCommon);
        tbCommone.setTitle("Form Input Nilai UTS");
        setSupportActionBar(tbCommone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        adpClass = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);
        adpMapel = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);
        adpSemester = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);
        adpTA = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);


        spUTSKelas = findViewById(R.id.UtsKelas);
        spUTSMapel = findViewById(R.id.UtsMapel);
        spUTSSemester = findViewById(R.id.UtsSemester);
        spUTSTa = findViewById(R.id.UtsTA);
        showUTS = findViewById(R.id.showUTS);

        spUTSKelas.setAdapter(adpClass);
        spUTSMapel.setAdapter(adpMapel);
        spUTSSemester.setAdapter(adpSemester);
        spUTSTa.setAdapter(adpTA);

        final String idGuru = new PrefManagerGuru(this).getUser().getIdPengguna();
        getMyClass(idGuru);
        String npsnGuru = new PrefManagerGuru(this).getUser().getNpsn();
        getMyMapel(npsnGuru);
        getSemester();
        getTA();

        showUTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(InputNilai_UTS.this, GetNilaiUTS.class);
                String kelas = spUTSKelas.getSelectedItem().toString();
                String semester = spUTSSemester.getSelectedItem().toString();
                String th_ajaran = spUTSTa.getSelectedItem().toString();
                String mapel = spUTSMapel.getSelectedItem().toString();
                intent.putExtra(AppParams.KELAS, kelas);
                intent.putExtra(AppParams.SEMESTER, semester);
                intent.putExtra(AppParams.TAHUNAJARAN, th_ajaran);
                intent.putExtra(AppParams.MAPEL, mapel);
                startActivity(intent);
            }
        });
    }

    private void getMyClass(String idGuru) {
        Log.i("DATA KELAS", idGuru);
        ApiGuru apiGuru = RetrofitInstance.create().create(ApiGuru.class);

        apiGuru.getMyClass(idGuru, "duniamaya").enqueue(new Callback<NilaiResponse>() {
            @Override
            public void onResponse(Call<NilaiResponse> call, Response<NilaiResponse> response) {
                for (DataKelasInputNilaiItem kelas : response.body().getDataKelasInputNilai()) {
                    String item = kelas.getIdKelasDiajar();
                    adpClass.add(item);
                    adpClass.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<NilaiResponse> call, Throwable t) {

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
