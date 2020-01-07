package com.binainsanlesatari.aksis.ViewSiswa;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.binainsanlesatari.aksis.BuildConfig;
import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.model.SiswaModel.InputPermohonan;
import com.binainsanlesatari.aksis.model.SiswaModel.uploadimg;
import com.binainsanlesatari.aksis.network.ApiSiswa;
import com.binainsanlesatari.aksis.network.RetrofitInstance;
import com.binainsanlesatari.aksis.utils.PrefManagerSiswa;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class edit_profil_siswa extends AppCompatActivity {

    private FloatingActionButton pilihfoto;
    private ProgressDialog progress;
    private Bitmap bitmap;
    private CircleImageView fotoprofil;
    private String imgpath, nisnSiswa, imgpathprofil;
    private PrefManagerSiswa prefManagerSiswa;
    private ApiSiswa uploadprofil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil_siswa);
        progress = new ProgressDialog(this);
        pilihfoto = findViewById(R.id.fabChoosePic);
        fotoprofil = findViewById(R.id.picture);

        prefManagerSiswa = new PrefManagerSiswa(this);
        nisnSiswa = prefManagerSiswa.getSiswa().getNisn();

        uploadprofil = RetrofitInstance.create().create(ApiSiswa.class);

        uploadprofil.getprofilsiswa(nisnSiswa).enqueue(new Callback<uploadimg>() {
            @Override
            public void onResponse(Call<uploadimg> call, Response<uploadimg> response) {
                int value = response.body().getValue();
                String message = response.body().getMessage();
                if (value == 1) {
                    imgpathprofil = response.body().getPath();
                    Picasso.with(com.binainsanlesatari.aksis.ViewSiswa.edit_profil_siswa.this).load(BuildConfig.BASE_URL+imgpathprofil).fit().centerCrop()
                            .placeholder(R.drawable.student)
                            .error(R.drawable.student)
                            .into(fotoprofil);
                } else {
                    Toast.makeText(com.binainsanlesatari.aksis.ViewSiswa.edit_profil_siswa.this, message, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<uploadimg> call, Throwable t) {

            }
        });

        pilihfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tampilpilihan();

            }
        });
    }

    public void tampilpilihan(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
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
                    fotoprofil.setImageBitmap(bitmap);
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
                    MultipartBody.Part body = MultipartBody.Part.createFormData("image", nisnSiswa + "_profil.jpg", requestFile);
                    Call<uploadimg> call = uploadprofil.uploadImage(body);
                    call.enqueue(new Callback<uploadimg>() {
                        @Override
                        public void onResponse(Call<uploadimg> call, Response<uploadimg> response) {
                            int value = response.body().getValue();
                            progress.dismiss();
                            String message = response.body().getMessage();
                            if (value == 1) {
                                imgpath = response.body().getPath();
                                Toast.makeText(com.binainsanlesatari.aksis.ViewSiswa.edit_profil_siswa.this, message, Toast.LENGTH_SHORT).show();
                                updatedb_profil();
                            } else {
                                Toast.makeText(com.binainsanlesatari.aksis.ViewSiswa.edit_profil_siswa.this, message, Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<uploadimg> call, Throwable t) {
                            progress.dismiss();
                            Toast.makeText(com.binainsanlesatari.aksis.ViewSiswa.edit_profil_siswa.this, "Gagal Upload !", Toast.LENGTH_SHORT).show();
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
        uploadprofil.updateprofil(imgpath, nisnSiswa)
                .enqueue(new Callback<InputPermohonan>() {
                    @Override
                    public void onResponse(Call<InputPermohonan> call, Response<InputPermohonan> response) {
                        int value = response.body().getValue();
                        String message = response.body().getMessage();

                        progress.dismiss();
                        if (value == 1) {
                            Toast.makeText(com.binainsanlesatari.aksis.ViewSiswa.edit_profil_siswa.this, message, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(com.binainsanlesatari.aksis.ViewSiswa.edit_profil_siswa.this, message, Toast.LENGTH_SHORT).show();

                        }

                    }

                    public void onFailure(Call<com.binainsanlesatari.aksis.model.SiswaModel.InputPermohonan> call, Throwable t) {
                        progress.dismiss();
                        Toast.makeText(com.binainsanlesatari.aksis.ViewSiswa.edit_profil_siswa.this, "Jaringan Error !", Toast.LENGTH_SHORT).show();

                    }
                });
    }
}
