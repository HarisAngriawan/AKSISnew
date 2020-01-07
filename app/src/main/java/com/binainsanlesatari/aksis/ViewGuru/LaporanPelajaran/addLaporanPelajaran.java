package com.binainsanlesatari.aksis.ViewGuru.LaporanPelajaran;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.model.GuruModel.Inputguru.InsertLaporanPelajaranGuru;
import com.binainsanlesatari.aksis.model.GuruModel.LaporanPelajaran.DataKelas;
import com.binainsanlesatari.aksis.model.GuruModel.LaporanPelajaran.DataKelasItem;
import com.binainsanlesatari.aksis.model.GuruModel.Nilai.DataKelasInputNilaiItem;
import com.binainsanlesatari.aksis.model.GuruModel.Nilai.DataMapelItem;
import com.binainsanlesatari.aksis.model.GuruModel.Nilai.MapelResponse;
import com.binainsanlesatari.aksis.model.GuruModel.Nilai.NilaiResponse;
import com.binainsanlesatari.aksis.model.SiswaModel.uploadimg;
import com.binainsanlesatari.aksis.network.ApiGuru;
import com.binainsanlesatari.aksis.network.ApiInsert;
import com.binainsanlesatari.aksis.network.ApiUpload;
import com.binainsanlesatari.aksis.network.RetrofitInstance;
import com.binainsanlesatari.aksis.utils.Getpath;
import com.binainsanlesatari.aksis.utils.PrefManagerGuru;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class addLaporanPelajaran extends AppCompatActivity {
    TextInputEditText edTanggal, edNamaGuru, edJudulMateri, edDetailMateri;
    Spinner spKelas, spNomorKelas, spMapel, spTahunAjaran;
    Button saveLaporan, pilihfile;
    DatePickerDialog datePickerDialog;
    Getpath filePath;
    ApiUpload apiUpload;
    TextView selectedpdf;
    ArrayAdapter<String> adpKelas, adpNomorKelas, adpMapel, adpTahunAjaran;
    PrefManagerGuru prefManagerGuru;

    ProgressDialog progress;

    List<DataKelasInputNilaiItem> kelasList = new ArrayList<>();

    String npsn, pdfpath;
    String idguru;
    String password = "duniamaya";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_laporan_pelajaran);

        Toolbar tbCommone = findViewById(R.id.tbCommon);
        tbCommone.setTitle("Form Laporan Pelajaran");
        setSupportActionBar(tbCommone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        datePickerDialog = new DatePickerDialog(this);
        prefManagerGuru = new PrefManagerGuru(this);
        progress = new ProgressDialog(this);

        npsn = prefManagerGuru.getUser().getNpsn();
        idguru = prefManagerGuru.getUser().getIdPengguna();

        adpKelas = new ArrayAdapter<>(this, R.layout.my_spinner);
        adpNomorKelas = new ArrayAdapter<>(this, R.layout.my_spinner);
        adpMapel = new ArrayAdapter<>(this, R.layout.my_spinner);
        List<String> tahun_angkatan = new ArrayList<String>();
        tahun_angkatan.add("2018-2019");
        tahun_angkatan.add("2019-2020");
        tahun_angkatan.add("2020-2021");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                (this, R.layout.my_spinner, tahun_angkatan);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        edTanggal = findViewById(R.id.edLaporanTanggal);
        edNamaGuru = findViewById(R.id.edLaporanNama);
        edJudulMateri = findViewById(R.id.edJudulMateri);
        edDetailMateri = findViewById(R.id.edDetailLaporan);
        spKelas = findViewById(R.id.spLaporanKelas);
        spNomorKelas = findViewById(R.id.spNomorKelas);
        spMapel = findViewById(R.id.spMapelLaporan);
        spTahunAjaran = findViewById(R.id.spThAjaranLaporan);
        saveLaporan = findViewById(R.id.addItemLaporan);

        spKelas.setAdapter(adpKelas);
        spNomorKelas.setAdapter(adpNomorKelas);
        spMapel.setAdapter(adpMapel);
        spTahunAjaran.setAdapter(dataAdapter);

        String namaGuru = prefManagerGuru.getUser().getNama();
        edNamaGuru.setText(namaGuru);
        edTanggal.setInputType(InputType.TYPE_NULL);
        getMyClass();
        String npsn = prefManagerGuru.getUser().getNpsn();
        getMapel(npsn);

        saveLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress.setCancelable(false);
                progress.setMessage("Loading.....");
                progress.show();
                final String tgl = edTanggal.getText().toString();
                String kelas = spKelas.getSelectedItem().toString();
                String tahun_ajaran = spTahunAjaran.getSelectedItem().toString();
                String mapel = spMapel.getSelectedItem().toString();
                final String judul_materi = edJudulMateri.getText().toString();
                final String detail_materi = edDetailMateri.getText().toString();
                String idguru = prefManagerGuru.getUser().getIdPengguna();
                String npsn = prefManagerGuru.getUser().getNpsn();
                String file = "";

                if (edTanggal.getText().toString().length()==0) {
                    progress.dismiss();
                    edTanggal.setError("Pilih Tanggal!");
                    edTanggal.requestFocus();
                    return;
                }
                if (judul_materi.isEmpty()) {
                    progress.dismiss();
                    edJudulMateri.setError("Masukkan Judul");
                    edJudulMateri.requestFocus();
                    return;
                }
                if (detail_materi.isEmpty()) {
                    progress.dismiss();
                    edDetailMateri.setError("Masukkan Detail");
                    edDetailMateri.requestFocus();
                    return;
                }
                ApiInsert apiInsert = RetrofitInstance.create().create(ApiInsert.class);

                apiInsert.postLaporanPelajaran(tgl, mapel, judul_materi, kelas, detail_materi,
                        idguru, file, npsn, tahun_ajaran).enqueue(new Callback<InsertLaporanPelajaranGuru>() {
                    @Override
                    public void onResponse(Call<InsertLaporanPelajaranGuru> call, Response<InsertLaporanPelajaranGuru> response) {
                        Boolean value = response.body().isValue();
                        String message = response.body().getMessage();

                        progress.show();
                        if (value.equals(true)) {
                            progress.dismiss();
                            Toast.makeText(addLaporanPelajaran.this, message, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(addLaporanPelajaran.this, LaporanPelajaranSiswa.class);
                            startActivity(intent);
                        }
                        if (value.equals(false)) {
                            Toast.makeText(addLaporanPelajaran.this, message, Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<InsertLaporanPelajaranGuru> call, Throwable t) {

                    }
                });
            }
        });

        edTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(addLaporanPelajaran.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        edTanggal.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        apiUpload = RetrofitInstance.create().create(ApiUpload.class);
        selectedpdf=findViewById(R.id.tvselectedfile);
        pilihfile = findViewById(R.id.btnpilihfile);
        pilihfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");
                startActivityForResult(intent, 2);

            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == 2) && (resultCode == -1)) {
            Uri selectedfile = data.getData();
            String path = filePath.getPath(this, selectedfile);
            selectedpdf.setText(path);

            File file = new File(path);

            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), path);

            MultipartBody.Part multipartBody =MultipartBody.Part.createFormData("file",file.getName(),requestFile);


            Call<com.binainsanlesatari.aksis.model.SiswaModel.uploadimg> call = apiUpload.uploadpdf(multipartBody);
            call.enqueue(new Callback<uploadimg>() {
                @Override
                public void onResponse(Call<uploadimg> call, Response<uploadimg> response) {
                    int value = response.body().getValue();
                    progress.dismiss();
                    String message = response.body().getMessage();
                    if (value == 1) {
                        pdfpath = response.body().getPath();
                        Toast.makeText(com.binainsanlesatari.aksis.ViewGuru.LaporanPelajaran.addLaporanPelajaran.this, message, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(com.binainsanlesatari.aksis.ViewGuru.LaporanPelajaran.addLaporanPelajaran.this, message, Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<uploadimg> call, Throwable t) {
                    progress.dismiss();
                    Toast.makeText(com.binainsanlesatari.aksis.ViewGuru.LaporanPelajaran.addLaporanPelajaran.this, "Gagal Upload !", Toast.LENGTH_SHORT).show();
                }
            });


        }
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
                        adpKelas.clear();
                        adpKelas.addAll(string);
                    }

                    @Override
                    public void onFailure(Call<NilaiResponse> call, Throwable t) {

                    }
                });
        spKelas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    private void getMapel(String npsn) {
        ApiGuru apiGuru = RetrofitInstance.create().create(ApiGuru.class);

        apiGuru.getMyMapel(npsn, "duniamaya").enqueue(new Callback<MapelResponse>() {
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
}
