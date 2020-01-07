package com.binainsanlesatari.aksis.ViewGuru.Konseling;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.cardview.widget.CardView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.ViewGuru.TakeAllStudents;
import com.binainsanlesatari.aksis.model.GuruModel.InsertGuru.InsertCatatanKonseling;
import com.binainsanlesatari.aksis.network.ApiInsert;
import com.binainsanlesatari.aksis.network.RetrofitInstance;
import com.binainsanlesatari.aksis.utils.AppParams;
import com.binainsanlesatari.aksis.utils.PrefManagerGuru;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InputKonseling extends AppCompatActivity {

    DatePickerDialog datePickerDialogpicker;
    TextInputEditText edTanggal, edNisn, edNama, edMasalah, edPendekatan;
    TextView tvThAjaran,tvKonselor;
    AppCompatSpinner spLayanan;
    PrefManagerGuru prefManagerGuru;
    CardView cvSearchSiswa;
    Button saveCatatanKonseling;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_konseling);
        datePickerDialogpicker = new DatePickerDialog(this);
        prefManagerGuru = new PrefManagerGuru(this);
        progress = new ProgressDialog(this);


        spLayanan = findViewById(R.id.spLayananCK);
        edTanggal = findViewById(R.id.edTanggalCK);
        edNisn = findViewById(R.id.edNisnCK);
        edNama = findViewById(R.id.edNamaSiswaCK);
        edMasalah = findViewById(R.id.edPermasalahanCK);
        edPendekatan = findViewById(R.id.edPendekatanCK);
        tvThAjaran = findViewById(R.id.tvThAjaranCK);
        tvKonselor = findViewById(R.id.tvKonselor);
        cvSearchSiswa = findViewById(R.id.cvSearchSiswaCK);
        saveCatatanKonseling = findViewById(R.id.saveCK);

        tvKonselor.setText(prefManagerGuru.getUser().getNama());

        List<String> layanan = new ArrayList<String>();
        layanan.add("Koseling Individu");
        layanan.add("Konseling Kelompok");
        layanan.add("Bimbingan Individu");
        layanan.add("Bimbingan Kelompok");
        layanan.add("Mediasi");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, R.layout.my_spinner, layanan);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spLayanan.setAdapter(dataAdapter);

        saveCatatanKonseling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress.setCancelable(false);
                progress.setMessage("Loading.....");
                progress.show();

                String tanggal = edTanggal.getText().toString();
                String layanan = spLayanan.getSelectedItem().toString();
                String nisn = edNisn.getText().toString();
                String nama = edNama.getText().toString();
                String masalah = edMasalah.getText().toString();
                String pendekatan = edPendekatan.getText().toString();
                String th_ajaran = tvThAjaran.getText().toString();
                String konselor = prefManagerGuru.getUser().getNama();
                String npsn = prefManagerGuru.getUser().getNpsn();


                ApiInsert apiInsert = RetrofitInstance.create().create(ApiInsert.class);

                apiInsert.getCatatanKonseling(tanggal,masalah ,nisn, nama ,pendekatan,layanan,konselor,npsn,
                        th_ajaran).enqueue(new Callback<InsertCatatanKonseling>() {
                    @Override
                    public void onResponse(Call<InsertCatatanKonseling> call, Response<InsertCatatanKonseling> response) {
                        int value = response.body().getValue();
                        String message = response.body().getMessage();

                        progress.dismiss();
                        if (value == 1) {
                            Toast.makeText(InputKonseling.this, message, Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(mainInputPelanggaran.this, ViewPelanggaran.class);
//                            startActivity(intent);
                        } else {
                            Toast.makeText(InputKonseling.this, message, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<InsertCatatanKonseling> call, Throwable t) {
                        progress.dismiss();
                        Toast.makeText(InputKonseling.this, "Jaringan Jelek", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        cvSearchSiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InputKonseling.this, TakeAllStudents.class);
                startActivityForResult(intent, 11);
            }
        });

        edTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialogpicker = new DatePickerDialog(InputKonseling.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        edTanggal.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, year, month, day);
                datePickerDialogpicker.show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 11) {
            String name = data.getStringExtra(AppParams.NAMA);
            String nisn = data.getStringExtra(AppParams.NISN);
            String th_ajaran = data.getStringExtra(AppParams.THAJARAN);

            edNama.setText(name);
            edNisn.setText(nisn);
            tvThAjaran.setText(th_ajaran);
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
