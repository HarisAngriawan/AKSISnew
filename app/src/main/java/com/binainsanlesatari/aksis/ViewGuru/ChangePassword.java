package com.binainsanlesatari.aksis.ViewGuru;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.WelcomeLogin;
import com.binainsanlesatari.aksis.model.GuruModel.UPDATEGURU.UpdatePassGuru;
import com.binainsanlesatari.aksis.network.ApiUpdate;
import com.binainsanlesatari.aksis.network.RetrofitInstance;
import com.binainsanlesatari.aksis.utils.PrefManagerGuru;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassword extends AppCompatActivity {
    TextInputEditText edPasswordBaru, edConfirmPassword;
    Button savePass;
    private PrefManagerGuru prefManagerGuru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        prefManagerGuru = new PrefManagerGuru(this);
        Toolbar tbCommone = findViewById(R.id.tbCommon);
        tbCommone.setTitle("Ganti Password");
        setSupportActionBar(tbCommone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        LinearLayout tbMenuContainer = findViewById(R.id.tbMenuContainer);
        savePass = new Button(this);
        savePass.setText("Simpan");
        savePass.setBackgroundColor(getResources().getColor(R.color.gradient));
        savePass.setBackgroundResource(R.drawable.ripple_effect);
        savePass.setTextSize(TypedValue.COMPLEX_UNIT_PX, 30);
        savePass.setPadding(2, 2, 2, 2);
        tbMenuContainer.addView(savePass);

        edPasswordBaru = findViewById(R.id.edPassBaru);
        edConfirmPassword = findViewById(R.id.edKonfirmPass);

        savePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    private void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        alertDialogBuilder.setTitle("Apakah Anda Yakin Ingin Mengganti Password ?");

        alertDialogBuilder
                .setMessage("Klik Ya untuk Ganti!")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        gantiPass();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
    }

    private void gantiPass() {
        String pass_lama = prefManagerGuru.getUser().getPass();
        final String pass_baru = edPasswordBaru.getText().toString().trim();
        final String konfirm_pass = edConfirmPassword.getText().toString().trim();
        String id_pengguna = prefManagerGuru.getUser().getIdPengguna();
        String npsn = prefManagerGuru.getUser().getNpsn();

        ApiUpdate apiUpdate = RetrofitInstance.create().create(ApiUpdate.class);

        apiUpdate.updatePassGuru(pass_lama, pass_baru, konfirm_pass, id_pengguna, npsn)
                .enqueue(new Callback<UpdatePassGuru>() {
                    @Override
                    public void onResponse(Call<UpdatePassGuru> call, Response<UpdatePassGuru> response) {
                        String value = response.body().getValue();
                        String message = response.body().getMessage();
                        if (pass_baru != konfirm_pass) {
                            Toast.makeText(ChangePassword.this, "Password Tidak Sama", Toast.LENGTH_SHORT).show();
                        }
                        if (pass_baru.isEmpty()) {
                            edPasswordBaru.setError("Masukkan Password Baru");
                            edPasswordBaru.requestFocus();
                            return;
                        }
                        if (konfirm_pass.isEmpty()) {
                            edConfirmPassword.setError("Masukkan Konfirmasi Password");
                            edConfirmPassword.requestFocus();
                            return;
                        }
                        if (value.equals("true")) {
                            Toast.makeText(ChangePassword.this,message,Toast.LENGTH_SHORT).show();
                            prefManagerGuru.deleteGuru();
                            Intent intent = new Intent(ChangePassword.this, WelcomeLogin.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(ChangePassword.this, message, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UpdatePassGuru> call, Throwable t) {

                    }
                });

    }

    @Override
    public void onBackPressed() {
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
