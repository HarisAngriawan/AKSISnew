package com.binainsanlesatari.aksis.ViewGuru.PenelusuranAlumni.InsertPenelusuranAlumni;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.ViewGuru.PenelusuranAlumni.PenelusuranAlumni;
import com.binainsanlesatari.aksis.network.ApiInsert;
import com.binainsanlesatari.aksis.network.RetrofitInstance;
import com.binainsanlesatari.aksis.utils.PrefManagerGuru;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertAlumni extends AppCompatActivity {

    private EditText edJumlah_lulus, edNegeri, edSwasta, edKerja, edLain;
    private PrefManagerGuru prefManagerGuru;
    private Button SaveAlumni;
    private Spinner spAlumni;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alumni);
        Toolbar tbCommone = findViewById(R.id.tbCommon);
        tbCommone.setTitle("Tambah Alumni Sekolah");
        setSupportActionBar(tbCommone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        prefManagerGuru = new PrefManagerGuru(this);
        progress = new ProgressDialog(this);

        spAlumni = findViewById(R.id.spAlumni);
        edJumlah_lulus = findViewById(R.id.jumlah_lulus);
        edNegeri = findViewById(R.id.masuk_negeri);
        edSwasta = findViewById(R.id.masuk_swasta);
        edKerja = findViewById(R.id.kerja);
        edLain = findViewById(R.id.lain);
        SaveAlumni = findViewById(R.id.saveAlumni);

        List<String> tahun_angkatan = new ArrayList<String>();
        tahun_angkatan.add("2009-2010");
        tahun_angkatan.add("2010-2011");
        tahun_angkatan.add("2011-2012");
        tahun_angkatan.add("2012-2013");
        tahun_angkatan.add("2013-2014");
        tahun_angkatan.add("2014-2015");
        tahun_angkatan.add("2015-2016");
        tahun_angkatan.add("2016-2017");
        tahun_angkatan.add("2017-2018");
        tahun_angkatan.add("2018-2019");
        tahun_angkatan.add("2019-2020");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, tahun_angkatan);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spAlumni.setAdapter(dataAdapter);

        SaveAlumni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress.setCancelable(false);
                progress.setMessage("Loading.....");
                progress.show();

                String angkatan = spAlumni.getSelectedItem().toString();
                String jumlah_lulus = edJumlah_lulus.getText().toString();
                String negeri = edNegeri.getText().toString();
                String swasta = edSwasta.getText().toString();
                String kerja = edKerja.getText().toString();
                String lain = edLain.getText().toString();
                String npsn = prefManagerGuru.getUser().getNpsn();


                ApiInsert apiInsert = RetrofitInstance.create().create(ApiInsert.class);

                apiInsert.getAlumniInsert(angkatan, jumlah_lulus, negeri, swasta, kerja,
                        lain, npsn).enqueue(new Callback<com.binainsanlesatari.aksis.model.GuruModel.InsertGuru.InsertAlumni>() {
                    @Override
                    public void onResponse(Call<com.binainsanlesatari.aksis.model.GuruModel.InsertGuru.InsertAlumni> call, Response<com.binainsanlesatari.aksis.model.GuruModel.InsertGuru.InsertAlumni> response) {
                        int value = response.body().getValue();
                        String message = response.body().getMessage();

                        progress.dismiss();
                        if (value == 1) {
                            Toast.makeText(InsertAlumni.this, message, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(InsertAlumni.this, PenelusuranAlumni.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(InsertAlumni.this, message, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<com.binainsanlesatari.aksis.model.GuruModel.InsertGuru.InsertAlumni> call, Throwable t) {
                        progress.dismiss();
                        Toast.makeText(InsertAlumni.this, "Jaringan Jelek", Toast.LENGTH_SHORT).show();
                    }
                });
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