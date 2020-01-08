package com.binainsanlesatari.aksis.ViewGuru;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.binainsanlesatari.aksis.BuildConfig;
import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.WelcomeLogin;
import com.binainsanlesatari.aksis.model.GuruModel.UPDATEGURU.UpdateProfilGuru;
import com.binainsanlesatari.aksis.model.SiswaModel.InputPermohonan;
import com.binainsanlesatari.aksis.model.SiswaModel.uploadimg;
import com.binainsanlesatari.aksis.network.ApiUpdate;
import com.binainsanlesatari.aksis.network.RetrofitInstance;
import com.binainsanlesatari.aksis.utils.PrefManagerGuru;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileGuru extends AppCompatActivity {
    private TextInputEditText edNip, edNoHp,edJenisKelamin, edJabatan, edAlamat,edNamaSekolah,edNamaGuru;

    private FloatingActionButton gantiFoto;
    private ProgressDialog progress;
    private Bitmap bitmap;
    private String imgpath, imgpathprofil;
    private TextView tvNama, tvNPSN;
    private CircleImageView fotoGuru;

    private PrefManagerGuru prefManagerGuru;
    private Button saveDataGuru;
    private ApiUpdate uploadFoto;

    private String nama,nip,jabatan,alamat,no_hp,npsn,nama_sekolah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_guru);
        progress = new ProgressDialog(this);
        gantiFoto = findViewById(R.id.fabChoosePic);
        fotoGuru = findViewById(R.id.pictureGuru);

        Toolbar tbCommone = findViewById(R.id.tbCommon);
        tbCommone.setTitle("Edit Profile");
        setSupportActionBar(tbCommone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        LinearLayout tbMenuContainer = findViewById(R.id.tbMenuContainer);

        saveDataGuru = new Button(this);
        saveDataGuru.setText("Simpan");
        saveDataGuru.setBackgroundColor(getResources().getColor(R.color.gradient));
        saveDataGuru.setBackgroundResource(R.drawable.ripple_effect);
        saveDataGuru.setTextSize(TypedValue.COMPLEX_UNIT_PX, 30);
        saveDataGuru.setPadding(2, 2, 2, 2);
        tbMenuContainer.addView(saveDataGuru);

        edNamaGuru = findViewById(R.id.edEditNamaGuru);
        edNip = findViewById(R.id.edEditNipGuru);
        edNamaSekolah = findViewById(R.id.edEditNamaSekolah);
        edNoHp = findViewById(R.id.edEditNo_hp);
        edJenisKelamin = findViewById(R.id.edEditJk);
        edJabatan = findViewById(R.id.edEditJabatan);
        edAlamat = findViewById(R.id.edEditAlamat);
        tvNama = findViewById(R.id.tvEditNamaGuru);
        tvNPSN = findViewById(R.id.tvEditNpsnSekolah);

        prefManagerGuru = new PrefManagerGuru(this);
        no_hp = prefManagerGuru.getUser().getNoHp();
        nama = prefManagerGuru.getUser().getNama();
        npsn= prefManagerGuru.getUser().getNpsn();
        nip = prefManagerGuru.getUser().getNip();
        jabatan = prefManagerGuru.getUser().getStatus();
        alamat = prefManagerGuru.getUser().getAlamat();
        nama_sekolah = prefManagerGuru.getUser().getNama_sekolah();

        edNamaGuru.setText(nama);
        edNip.setText(nip);
        edNoHp.setText(no_hp);
        edJabatan.setText(jabatan);
        edAlamat.setText(alamat);
        edNamaSekolah.setText(nama_sekolah);
        tvNama.setText(nama.toUpperCase());
        tvNPSN.setText(npsn);

        String gender;
        if (prefManagerGuru.getUser().getJk().equals("P")) {
            gender = "Perempuan";
        } else {
            gender = "Laki-Laki";
        }
        edJenisKelamin.setText(gender);

        uploadFoto = RetrofitInstance.create().create(ApiUpdate.class);

        uploadFoto.getFotoGuru(no_hp).enqueue(new Callback<uploadimg>() {
            @Override
            public void onResponse(Call<uploadimg> call, Response<uploadimg> response) {
                int value = response.body().getValue();
                String message = response.body().getMessage();
                if (value == 1) {
                    imgpathprofil = response.body().getPath();
                    Picasso.with(EditProfileGuru.this).load(BuildConfig.BASE_URL+imgpathprofil).fit().centerCrop()
                            .placeholder(R.drawable.teacher)
                            .into(fotoGuru);
                } else {
                    Toast.makeText(EditProfileGuru.this, message, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<uploadimg> call, Throwable t) {

            }
        });

        gantiFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tampilpilihan();

            }
        });
        saveDataGuru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             showDialog();
            }
        });
    }
    private void showDialog() {
        android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(
                this);

        alertDialogBuilder.setTitle("Apakah Anda Yakin ingin merubah Data?");

        alertDialogBuilder
                .setMessage("Jika Yakin setelah, klik ya. Anda akan dikembalikan ke halaman login untuk login kembali.")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        updateData();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        android.app.AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
    }
    private void updateData() {
        ApiUpdate apiUpdate = RetrofitInstance.create().create(ApiUpdate.class);
        String id = prefManagerGuru.getUser().getId();
        String nama = edNamaGuru.getText().toString();
        String no_hp = edNoHp.getText().toString();
        String alamat = edAlamat.getText().toString();

        apiUpdate.updateProfilGuru(id,nama,no_hp,alamat).enqueue(new Callback<UpdateProfilGuru>() {
            @Override
            public void onResponse(Call<UpdateProfilGuru> call, Response<UpdateProfilGuru> response) {
                Boolean value = response.body().isValue();
                String message = response.body().getMessage();

                if (value.equals(true)){
                    Toast.makeText(EditProfileGuru.this,message,Toast.LENGTH_SHORT).show();
                    prefManagerGuru.deleteGuru();
                    Intent intent = new Intent(EditProfileGuru.this, WelcomeLogin.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(EditProfileGuru.this,message,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UpdateProfilGuru> call, Throwable t) {

            }
        });
    }

    public void tampilpilihan(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Pilih Metode");
        String[] pictureDialogItems = {
                "Pilih Dari Gallery",
                "Ambil Dengan Camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent;
                        switch (which) {
                            case 0:{
                                intent = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(intent, 1);
                                break;
                            }
                            case 1: {
                                intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent, 1);
                                break;
                            }
                        }
                    }
                });
        pictureDialog.show();
    }
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
                    fotoGuru.setImageBitmap(bitmap);
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
                    MultipartBody.Part body = MultipartBody.Part.createFormData("image", no_hp + "_profil.jpg", requestFile);
                    Call<uploadimg> call = uploadFoto.uploadImage(body);
                    call.enqueue(new Callback<uploadimg>() {
                        @Override
                        public void onResponse(Call<uploadimg> call, Response<uploadimg> response) {
                            int value = response.body().getValue();
                            progress.dismiss();
                            String message = response.body().getMessage();
                            if (value == 1) {
                                imgpath = response.body().getPath();
                                Toast.makeText(EditProfileGuru.this, message, Toast.LENGTH_SHORT).show();
                                updatedb_profil();
                            } else {
                                Toast.makeText(EditProfileGuru.this, message, Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<uploadimg> call, Throwable t) {
                            progress.dismiss();
                            Toast.makeText(EditProfileGuru.this, "Gagal Upload !", Toast.LENGTH_SHORT).show();
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

    public void updatedb_profil() {
        uploadFoto.updateFotoGuru(imgpath, no_hp)
                .enqueue(new Callback<InputPermohonan>() {
                    @Override
                    public void onResponse(Call<InputPermohonan> call, Response<InputPermohonan> response) {
                        int value = response.body().getValue();
                        String message = response.body().getMessage();

                        progress.dismiss();
                        if (value == 1) {
                            Toast.makeText(EditProfileGuru.this, message, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(EditProfileGuru.this, message, Toast.LENGTH_SHORT).show();

                        }

                    }

                    public void onFailure(Call<com.binainsanlesatari.aksis.model.SiswaModel.InputPermohonan> call, Throwable t) {
                        progress.dismiss();
                        Toast.makeText(EditProfileGuru.this, "Jaringan Error !", Toast.LENGTH_SHORT).show();

                    }
                });
    }
    
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Animatoo.animateSlideRight(this);
    }
}
