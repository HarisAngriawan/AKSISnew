package com.binainsanlesatari.aksis.ViewGuru.HomeVisit.InputHomeVisit;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.ViewGuru.TakeAllStudents;
import com.binainsanlesatari.aksis.network.ApiInsert;
import com.binainsanlesatari.aksis.network.RetrofitInstance;
import com.binainsanlesatari.aksis.utils.PrefManagerGuru;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertHomeVisit extends AppCompatActivity {

    PrefManagerGuru prefManagerGuru;
    ApiInsert apiInsert;

    CardView cvSearchSiswa;
    ProgressDialog progress;
    DatePickerDialog datePickerDialogpicker;
    TextView tvThAjaranHV;
    Button saveHomeVisit;
    TextInputEditText edTanggalPelaksanaan, edTujuanHV, edNamaSiswaHV, edNisnHV, edTanggapanWali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_home_visit);

        Toolbar tbCommone = findViewById(R.id.tbCommon);
        tbCommone.setTitle("Form Input Homevisit");
        setSupportActionBar(tbCommone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        apiInsert = RetrofitInstance.create().create(ApiInsert.class);
        progress = new ProgressDialog(this);
        prefManagerGuru = new PrefManagerGuru(this);

        tvThAjaranHV = findViewById(R.id.tvThAjaranHV);
        cvSearchSiswa = findViewById(R.id.cvSearchSiswa);
        edTanggalPelaksanaan = findViewById(R.id.edTanggalHV);
        edTujuanHV = findViewById(R.id.edTuhuanHV);
        edNamaSiswaHV = findViewById(R.id.edNamaSiswaHV);
        edNisnHV = findViewById(R.id.edNisnHV);
        edTanggapanWali = findViewById(R.id.edTanggapanHV);

        saveHomeVisit = findViewById(R.id.btnSaveHV);
        edTanggalPelaksanaan.setInputType(InputType.TYPE_NULL);

        saveHomeVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress.setCancelable(false);
                progress.setMessage("Loading.....");
                progress.show();

                String tanggal = edTanggalPelaksanaan.getText().toString();
                String tujuan = edTujuanHV.getText().toString();
                String nama = edNamaSiswaHV.getText().toString();
                String nisn = edNisnHV.getText().toString();
                String tanggapan = edTanggapanWali.getText().toString();
                String npsn = prefManagerGuru.getUser().getNpsn();
                String konselor = prefManagerGuru.getUser().getIdPengguna();
                String th_ajaran = tvThAjaranHV.getText().toString();

                apiInsert.getHomeVisitInsert(tanggal, tujuan, nisn, nama, tanggapan, konselor,th_ajaran, npsn)
                        .enqueue(new Callback<com.binainsanlesatari.aksis.model.GuruModel.InsertGuru.InsertHomeVisit>() {
                            @Override
                            public void onResponse(Call<com.binainsanlesatari.aksis.model.GuruModel.InsertGuru.InsertHomeVisit> call, Response<com.binainsanlesatari.aksis.model.GuruModel.InsertGuru.InsertHomeVisit> response) {
                                int value = response.body().getValue();
                                String message = response.body().getMessage();

                                progress.dismiss();
                                if (value == 1) {
                                    Toast.makeText(InsertHomeVisit.this, message, Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(InsertHomeVisit.this, message, Toast.LENGTH_SHORT).show();

                                }

                            }

                            @Override
                            public void onFailure(Call<com.binainsanlesatari.aksis.model.GuruModel.InsertGuru.InsertHomeVisit> call, Throwable t) {
                                Toast.makeText(InsertHomeVisit.this, "Jaringan Error !", Toast.LENGTH_SHORT).show();

                            }
                        });
            }
        });

        edTanggalPelaksanaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialogpicker = new DatePickerDialog(InsertHomeVisit.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        edTanggalPelaksanaan.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, year, month, day);
                datePickerDialogpicker.show();
            }
        });
        cvSearchSiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InsertHomeVisit.this, TakeAllStudents.class);
                startActivityForResult(intent, 11);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 11) {
            String name = data.getStringExtra("NAMA");
            String nisn = data.getStringExtra("NISN");
            String th_ajaran = data.getStringExtra("THAJARAN");

            edNamaSiswaHV.setText(name);
            edNisnHV.setText(nisn);
            tvThAjaranHV.setText(th_ajaran);
        }
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
