package com.binainsanlesatari.aksis.ViewGuru.Prestasi;

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
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.ViewGuru.TakeAllStudents;
import com.binainsanlesatari.aksis.model.GuruModel.InsertGuru.InsertPrestasi;
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

public class InputPrestasiSiswa extends AppCompatActivity {
    DatePickerDialog datePickerDialogpicker;
    TextInputEditText edTanggalPS, edNisn, edNama, edDeskripsi;
    TextView tvKelas, tvThAjaran;
    AppCompatSpinner spKategory;
    PrefManagerGuru prefManagerGuru;
    CardView cvSearchSiswa;
    Button savePrestasi;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prestasi_siswa);

        Toolbar tbCommone = findViewById(R.id.tbCommon);
        tbCommone.setTitle("Tambah Prestasi");
        setSupportActionBar(tbCommone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        progress = new ProgressDialog(this);
        datePickerDialogpicker = new DatePickerDialog(this);
        prefManagerGuru = new PrefManagerGuru(this);

        edTanggalPS = findViewById(R.id.edTanggalPS);
        edNisn = findViewById(R.id.edNisnPS);
        edNama = findViewById(R.id.edNamaSiswaPS);
        savePrestasi = findViewById(R.id.btnSavePS);

        tvThAjaran = findViewById(R.id.tvTh_ajaranPS);
        tvKelas = findViewById(R.id.tvKelasPS);

        edDeskripsi = findViewById(R.id.edDeskripsiPS);
        spKategory = findViewById(R.id.spKategoriPS);
        cvSearchSiswa = findViewById(R.id.cvSearchSiswa);

        List<String> categories = new ArrayList<String>();
        categories.add("Sekolah");
        categories.add("Kabupaten");
        categories.add("Kota");
        categories.add("Provinsi");
        categories.add("Nasional");
        categories.add("Internasional");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, R.layout.my_spinner, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spKategory.setAdapter(dataAdapter);

        savePrestasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress.setCancelable(false);
                progress.setMessage("Loading.....");
                progress.show();

                String tanggal = edTanggalPS.getText().toString();
                String kategori = spKategory.getSelectedItem().toString();
                String nisn = edNisn.getText().toString();
                String nama = edNama.getText().toString();
                String kelas = tvKelas.getText().toString();
                String deskripsi = edDeskripsi.getText().toString();
                String th_ajaran = tvThAjaran.getText().toString();
                String npsn = prefManagerGuru.getUser().getNpsn();

                ApiInsert apiInsert = RetrofitInstance.create().create(ApiInsert.class);

                apiInsert.getPrestasiInsert(tanggal, kategori, nisn,nama, kelas,deskripsi,npsn,
                        th_ajaran).enqueue(new Callback<InsertPrestasi>() {
                    @Override
                    public void onResponse(Call<InsertPrestasi> call, Response<InsertPrestasi> response) {
                        int value = response.body().getValue();
                        String message = response.body().getMessage();

                        progress.dismiss();
                        if (value == 1) {
                            Toast.makeText(InputPrestasiSiswa.this, message, Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(mainInputPelanggaran.this, ViewPelanggaran.class);
//                            startActivity(intent);
                        } else {
                            Toast.makeText(InputPrestasiSiswa.this, message, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<InsertPrestasi> call, Throwable t) {
                        progress.dismiss();
                        Toast.makeText(InputPrestasiSiswa.this, "Jaringan Jelek", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
        cvSearchSiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InputPrestasiSiswa.this, TakeAllStudents.class);
                startActivityForResult(intent, 11);
            }
        });
        edTanggalPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialogpicker = new DatePickerDialog(InputPrestasiSiswa.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        edTanggalPS.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
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
            String nomor_kelas = data.getStringExtra(AppParams.KELASNOMOR);
            String th_ajaran = data.getStringExtra(AppParams.THAJARAN);

            edNama.setText(name);
            edNisn.setText(nisn);
            tvKelas.setText(nomor_kelas);
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
