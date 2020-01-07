package com.binainsanlesatari.aksis.ViewGuru.Pelanggaran.inputPelanggaran;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.ViewGuru.TakeAllStudents;
import com.binainsanlesatari.aksis.model.GuruModel.InsertGuru.InsertPelanggaran;
import com.binainsanlesatari.aksis.model.GuruModel.Pelaggaran.inputPelanggaran.DeskripsiPelanggaran;
import com.binainsanlesatari.aksis.model.GuruModel.Pelaggaran.inputPelanggaran.DeskripsiPelanggaranItem;
import com.binainsanlesatari.aksis.model.GuruModel.Pelaggaran.inputPelanggaran.PelanggaranItem;
import com.binainsanlesatari.aksis.model.GuruModel.Pelaggaran.inputPelanggaran.PelanggaranKategori;
import com.binainsanlesatari.aksis.network.ApiGuru;
import com.binainsanlesatari.aksis.network.ApiInsert;
import com.binainsanlesatari.aksis.network.RetrofitInstance;
import com.binainsanlesatari.aksis.utils.AppParams;
import com.binainsanlesatari.aksis.utils.PrefManagerGuru;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class mainInputPelanggaran extends AppCompatActivity {
    ApiGuru apiGuru;
    PrefManagerGuru prefManagerGuru;
    //    ApiSearch apiSearch;
    CardView cvSearchSiswa;
    DatePickerDialog datePickerDialogpicker;
    TimePickerDialog timePickerDialog;
    EditText IPtanggal, IPjam, edNama, edNisn, edKelas, edTempat;
    Spinner spKategori, spDeskripsi;
    ProgressDialog progress;

    Button savePelanggaran;
//    EditText findSiswa;


    ArrayAdapter<String> adpKategori, adpDeskripsi, adpKodeKategori;

    //    String kelas;
    String npsn;
    String password = "duniamaya";

    List<PelanggaranItem> pelanggaranList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pelanggaran);
        Toolbar tbCommone = findViewById(R.id.tbCommon);
        tbCommone.setTitle("Form Input Pelanggaran");
        setSupportActionBar(tbCommone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        apiGuru = RetrofitInstance.create().create(ApiGuru.class);
        progress = new ProgressDialog(this);
        prefManagerGuru = new PrefManagerGuru(this);
        npsn = prefManagerGuru.getUser().getNpsn();


        adpKategori = new ArrayAdapter<>(this, R.layout.my_spinner);
        adpKodeKategori = new ArrayAdapter<>(this, R.layout.my_spinner);
        adpDeskripsi = new ArrayAdapter<>(this, R.layout.my_spinner);

//        tvKodeKategori = findViewById(R.id.kodeKategori);
        edTempat = findViewById(R.id.IPtempat);
        savePelanggaran = findViewById(R.id.IPsimpan);
        edNama = findViewById(R.id.IPnama_siswa);
        edNisn = findViewById(R.id.IPnisn_siswa);
        edKelas = findViewById(R.id.IPkelasSiswa);
        cvSearchSiswa = findViewById(R.id.cvSearchSiswa);

        spKategori = findViewById(R.id.spKategori);
        spDeskripsi = findViewById(R.id.spDeskripsi);

        spKategori.setAdapter(adpKategori);
        spDeskripsi.setAdapter(adpDeskripsi);

        IPtanggal = findViewById(R.id.IPtanggal);
        IPjam = findViewById(R.id.IPclock);

        IPtanggal.setInputType(InputType.TYPE_NULL);
        IPjam.setInputType(InputType.TYPE_NULL);

        getPelanggaran();


        savePelanggaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress.setCancelable(false);
                progress.setMessage("Loading.....");
                progress.show();

                String nisn = edNisn.getText().toString();
                String kelas = edKelas.getText().toString();
                String kategori = spKategori.getSelectedItem().toString();
                String deskripsi_pelanggaran = spDeskripsi.getSelectedItem().toString();
                String tanggal = IPtanggal.getText().toString();
                String jam = IPjam.getText().toString();
                String tempat_pelanggaran = edTempat.getText().toString();
                String guru_input = prefManagerGuru.getUser().getIdPengguna();
                ApiInsert apiInsert = RetrofitInstance.create().create(ApiInsert.class);

                apiInsert.getPelanggaranInsert(nisn, kelas, kategori, deskripsi_pelanggaran, tanggal, jam
                        , tempat_pelanggaran, guru_input, npsn, "0").enqueue(new Callback<InsertPelanggaran>() {
                    @Override
                    public void onResponse(Call<InsertPelanggaran> call, Response<InsertPelanggaran> response) {
                        int value = response.body().getValue();
                        String message = response.body().getMessage();

                        progress.dismiss();
                        if (value == 1) {
                            Toast.makeText(mainInputPelanggaran.this, message, Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(mainInputPelanggaran.this, ViewPelanggaran.class);
//                            startActivity(intent);
                        } else {
                            Toast.makeText(mainInputPelanggaran.this, message, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<InsertPelanggaran> call, Throwable t) {
                        progress.dismiss();
                        Toast.makeText(mainInputPelanggaran.this, "Jaringan Jelek", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        cvSearchSiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainInputPelanggaran.this, TakeAllStudents.class);
                startActivityForResult(intent, 11);
            }
        });


        IPjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(mainInputPelanggaran.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        IPjam.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minute, true);
                timePickerDialog.show();

            }
        });

        IPtanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialogpicker = new DatePickerDialog(mainInputPelanggaran.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        IPtanggal.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
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
            String kelas = data.getStringExtra(AppParams.KELASNOMOR);

            edNama.setText(name);
            edNisn.setText(nisn);
            edKelas.setText(kelas);
        }
    }


    void getPelanggaran() {
        apiGuru.getPelanggaranKat(npsn, password)
                .enqueue(new Callback<PelanggaranKategori>() {
                    @Override
                    public void onResponse(Call<PelanggaranKategori> call, Response<PelanggaranKategori> response) {
                        pelanggaranList.clear();
                        pelanggaranList = response.body().getListKategori();
                        final List<String> string = new ArrayList<>();
                        for (final PelanggaranItem pelanggaran : pelanggaranList) {
                            string.add(pelanggaran.getId());
                        }
                        adpKategori.clear();
                        adpKategori.addAll(string);
                    }

                    @Override
                    public void onFailure(Call<PelanggaranKategori> call, Throwable t) {

                    }
                });


        spKategori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getDeskripsi(pelanggaranList.get(position).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    void getDeskripsi(String idPelangaran) {
        apiGuru.getPelanggaranDesc(npsn, idPelangaran, password).enqueue(new Callback<DeskripsiPelanggaran>() {
            @Override
            public void onResponse(Call<DeskripsiPelanggaran> call, Response<DeskripsiPelanggaran> response) {
                List<DeskripsiPelanggaranItem> data = response.body().getDeskripsiPelanggaran();
                List<String> string = new ArrayList<>();
                for (DeskripsiPelanggaranItem pelanggaran : data) {
                    string.add(pelanggaran.getDeskripsiPelanggaran());
                }
                adpDeskripsi.clear();
                adpDeskripsi.addAll(string);
            }

            @Override
            public void onFailure(Call<DeskripsiPelanggaran> call, Throwable t) {

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