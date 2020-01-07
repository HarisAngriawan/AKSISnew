package com.binainsanlesatari.aksis.ViewSiswa;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.model.SiswaModel.InputPermohonan;
import com.binainsanlesatari.aksis.model.SiswaModel.uploadimg;
import com.binainsanlesatari.aksis.network.ApiSiswa;
import com.binainsanlesatari.aksis.network.RetrofitInstance;
import com.binainsanlesatari.aksis.utils.PrefManagerSiswa;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Suratpermohonan extends AppCompatActivity {

    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView, alasan, kelastv;
    private Button kirim;
    private int year, month, day;
    private ImageButton showkalender, pilihgambar, ambilgambar;
    private ApiSiswa apiinsertpermohonan;
    private ProgressDialog progress;
    private ImageView loadimg;
    private Bitmap bitmap;
    private String imgpath, nisnSiswa, namaSiswa, nama_kelas, npsn, no_kelas, kelas;
    private PrefManagerSiswa prefManagerSiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suratpermohonan);
        dateView = (TextView) findViewById(R.id.tanggalpermohonan);
        calendar = Calendar.getInstance();
        showkalender = (ImageButton) findViewById(R.id.btnPilihtanggal);
        alasan = (TextView) findViewById(R.id.alasanpermohonan);
        kirim = (Button) findViewById(R.id.kirimpermohonan);
        pilihgambar = (ImageButton) findViewById(R.id.btnPilihgambar);
        loadimg = (ImageView) findViewById(R.id.loadimg);
        ambilgambar = (ImageButton) findViewById(R.id.btnAmbilgambar);
        kelastv = (TextView) findViewById(R.id.kelassiswa);

        prefManagerSiswa = new PrefManagerSiswa(this);
        namaSiswa = prefManagerSiswa.getSiswa().getNamaLengkap();
        nisnSiswa = prefManagerSiswa.getSiswa().getNisn();
        nama_kelas = prefManagerSiswa.getSiswa().getNamaKelasJurusan();
        no_kelas = prefManagerSiswa.getSiswa().getNomorKelas();
        kelas = nama_kelas+"-"+no_kelas;
        npsn = prefManagerSiswa.getSiswa().getNpsn();

        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);


        kirim.setEnabled(false);
        pilihgambar.setEnabled(false);
        ambilgambar.setEnabled(false);

        apiinsertpermohonan = RetrofitInstance.create().create(ApiSiswa.class);
        progress = new ProgressDialog(this);

        showkalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Suratpermohonan.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                dateView.setText(year + "-" + (month + 1) + "-" + day);
                                pilihgambar.setEnabled(true);
                                ambilgambar.setEnabled(true);
                            }
                        }, year, month, day);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();

            }
        });

        ambilgambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
                }
                else {
                    Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 1);
                }
            }
        });

// tampilkan galeri
        pilihgambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);

            }
        });

        kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String alasanpermohonan = alasan.getText().toString();
                String tanggalpermohonan = dateView.getText().toString();


                if (alasanpermohonan.isEmpty()) {
                    alasan.setError("Masukkan Alasan ");
                    alasan.requestFocus();
                    return;
                }
                progress.setCancelable(false);
                progress.setMessage("Loading.....");
                progress.show();
//masukkan data ke mysql
                apiinsertpermohonan.insertpermohonan(alasanpermohonan, tanggalpermohonan, imgpath, namaSiswa, nisnSiswa, npsn, kelas)
                        .enqueue(new Callback<InputPermohonan>() {
                            @Override
                            public void onResponse(Call<InputPermohonan> call, Response<InputPermohonan> response) {
                                int value = response.body().getValue();
                                String message = response.body().getMessage();

                                progress.dismiss();
                                if (value == 1) {
                                    Toast.makeText(com.binainsanlesatari.aksis.ViewSiswa.Suratpermohonan.this, message, Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(com.binainsanlesatari.aksis.ViewSiswa.Suratpermohonan.this, message, Toast.LENGTH_SHORT).show();

                                }

                            }


                            public void onFailure(Call<com.binainsanlesatari.aksis.model.SiswaModel.InputPermohonan> call, Throwable t) {
                                progress.dismiss();
                                Toast.makeText(com.binainsanlesatari.aksis.ViewSiswa.Suratpermohonan.this, "Jaringan Error !", Toast.LENGTH_SHORT).show();

                            }
                        });

            }
        });


    }

    //pilih gambar dan upload gambar
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            //cek result
            progress.setCancelable(false);
            progress.setMessage("Loading.....");
            progress.show();
            //cek request code
            if (requestCode == 1) {

                Uri selectedImage = data.getData();
                bitmap = null;

                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                    loadimg.setImageBitmap(bitmap);
                    InputStream imgstream = getContentResolver().openInputStream(data.getData());
                    ByteArrayOutputStream byteBuff = new ByteArrayOutputStream();

                    int buffSize = 1024;
                    byte[] buff = new byte[buffSize];

                    int len = 0;
                    while ((len = imgstream.read(buff)) != -1) {
                        byteBuff.write(buff, 0, len);
                    }

                    byte[] imgbyte = byteBuff.toByteArray();
                    RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), imgbyte);
                    MultipartBody.Part body = MultipartBody.Part.createFormData("image", nisnSiswa + "_" + dateView.getText().toString() + ".jpg", requestFile);
                    Call<com.binainsanlesatari.aksis.model.SiswaModel.uploadimg> call = apiinsertpermohonan.uploadImage(body);
                    call.enqueue(new Callback<uploadimg>() {
                        @Override
                        public void onResponse(Call<uploadimg> call, Response<uploadimg> response) {
                            int value = response.body().getValue();
                            progress.dismiss();
                            kirim.setEnabled(true);
                            String message = response.body().getMessage();
                            if (value == 1) {
                                imgpath = response.body().getPath();
                                Toast.makeText(com.binainsanlesatari.aksis.ViewSiswa.Suratpermohonan.this, message, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(com.binainsanlesatari.aksis.ViewSiswa.Suratpermohonan.this, message, Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<uploadimg> call, Throwable t) {
                            progress.dismiss();
                            Toast.makeText(com.binainsanlesatari.aksis.ViewSiswa.Suratpermohonan.this, "Gagal Upload !", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 1);
            }
            else
            {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }


}

