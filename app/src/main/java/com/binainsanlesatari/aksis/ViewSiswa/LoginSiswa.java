package com.binainsanlesatari.aksis.ViewSiswa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.model.GuruModel.Login.LoginSiswa.LoginModelSiswa;
import com.binainsanlesatari.aksis.network.ApiLogin;
import com.binainsanlesatari.aksis.network.RetrofitInstance;
import com.binainsanlesatari.aksis.utils.PrefManagerSiswa;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginSiswa extends AppCompatActivity {
    private EditText edNisn, edPassword;
    private ProgressBar pbLoginsiswa;
    private Button loginSiswa;
    private PrefManagerSiswa prefManagerSiswa;


    Retrofit retrofit = RetrofitInstance.create();
    ApiLogin apiLogin = retrofit.create(ApiLogin.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_siswa);

        bindView();
        prefManagerSiswa = new PrefManagerSiswa(this);
        loginSiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    doLoginSiswa();
            }
        });


    }

    private void doLoginSiswa() {
        pbLoginsiswa.setVisibility(View.VISIBLE);
        loginSiswa.setVisibility(View.GONE);

        final String nisn = edNisn.getText().toString().trim();
        final String password = edPassword.getText().toString().trim();
        apiLogin.loginSiswa(nisn, password)
                .enqueue(new Callback<LoginModelSiswa>() {
                    @Override
                    public void onResponse(Call<LoginModelSiswa> call, Response<LoginModelSiswa> response) {
                        pbLoginsiswa.setVisibility(View.GONE);
                        String status = response.body().getStatus();
                        if (nisn.isEmpty()){
                            loginSiswa.setVisibility(View.VISIBLE);
                            edNisn.setError("Masukkan Nisn Anda");
                            edNisn.requestFocus();
                            return;
                        }
                        if (password.isEmpty()){
                            loginSiswa.setVisibility(View.VISIBLE);
                            edPassword.setError("Masukkan Password Anda");
                            edPassword.requestFocus();
                            return;
                        }
                        if (status.equals("true")) {
                            prefManagerSiswa.saveSiswa(response.body().getData().get(0));
                            Toast.makeText(LoginSiswa.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginSiswa.this, MainFragmentSiswa.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                        if(status.equals("false")){
                            loginSiswa.setVisibility(View.VISIBLE);
                            Toast.makeText(LoginSiswa.this,"Login Gagal",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginModelSiswa> call, Throwable t) {
                    }
                });
    }


    private void bindView() {
        edNisn = findViewById(R.id.siswa_nisn);
        edPassword = findViewById(R.id.siswa_password);
        pbLoginsiswa = findViewById(R.id.pbLoginSiswa);
        loginSiswa = findViewById(R.id.mSiswa);
    }
}
