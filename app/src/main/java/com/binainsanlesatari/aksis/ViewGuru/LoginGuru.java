package com.binainsanlesatari.aksis.ViewGuru;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.model.GuruModel.Login.LoginGuru.LoginModel;
import com.binainsanlesatari.aksis.network.ApiLogin;
import com.binainsanlesatari.aksis.network.RetrofitInstance;
import com.binainsanlesatari.aksis.utils.PrefManagerGuru;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginGuru extends AppCompatActivity {

    private EditText edNoHp, edPassword;
    private ProgressBar pbLoading;
    private Button btnLoginGuru;

    private PrefManagerGuru prefManagerGuru;
    private Toolbar Tb_LoginGuru;

    Retrofit retrofit = RetrofitInstance.create();
    ApiLogin apiLogin = retrofit.create(ApiLogin.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_guru);
        Toolbar tbCommone = findViewById(R.id.tbCommon);
        tbCommone.setTitle("Login");
        setSupportActionBar(tbCommone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        bindView();
        prefManagerGuru = new PrefManagerGuru(this);
        btnLoginGuru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });

    }

    private void doLogin() {
        pbLoading.setVisibility(View.VISIBLE);
        btnLoginGuru.setVisibility(View.GONE);
        final String noHp = edNoHp.getText().toString().trim();
        final String password = edPassword.getText().toString().trim();

        apiLogin.loginGuru(noHp, password)
                .enqueue(new Callback<LoginModel>() {
                    @Override
                    public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                        pbLoading.setVisibility(View.GONE);
                        String status = response.body().getStatus();
                        if (noHp.isEmpty()) {
                            btnLoginGuru.setVisibility(View.VISIBLE);
                            edNoHp.setError("Masukkan No.Handphone Anda");
                            edNoHp.requestFocus();
                            return;
                        }
                        if (password.isEmpty()) {
                            btnLoginGuru.setVisibility(View.VISIBLE);
                            edPassword.setError("Masukkan Password Anda");
                            edPassword.requestFocus();
                            return;
                        }
                        if (status.equals("true")) {
//                            String role = response.body().getData().get(0).getLevel();
//                            showForm(role);
                            prefManagerGuru.saveUser(response.body().getData().get(0));
                            Toast.makeText(LoginGuru.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                            Log.i("LOGIN", response.body().toString());
                            Intent intent = new Intent(LoginGuru.this, MainFragment.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                        if (status.equals("false")) {
                            btnLoginGuru.setVisibility(View.VISIBLE);
                            Toast.makeText(LoginGuru.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginModel> call, Throwable t) {
                        pbLoading.setVisibility(View.VISIBLE);
                        String error = t.getMessage();
                        Toast.makeText(LoginGuru.this, "Login Gagal" + t, Toast.LENGTH_SHORT).show();
                    }
                });

    }

//    private void showForm(String role) {
//        if (role = equals("Kepsek")){
//            gridMenu.removeView(btnGuruPres);
//        }
//    }


    private void bindView() {
        edNoHp = findViewById(R.id.guru_no_hp);
        edPassword = findViewById(R.id.guru_password);
        pbLoading = findViewById(R.id.pbLogin);
        btnLoginGuru = findViewById(R.id.mGuru);

//        gridMenu = findViewById(R.id.gridMenuGuru);

//        btnGuruP = findViewById(R.id.guruPelanggaran);
//        btnGuruPres = findViewById(R.id.presetasi);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
