package com.binainsanlesatari.aksis.ViewSiswa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.binainsanlesatari.aksis.BuildConfig;
import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.WelcomeLogin;
import com.binainsanlesatari.aksis.model.SiswaModel.uploadimg;
import com.binainsanlesatari.aksis.network.ApiSiswa;
import com.binainsanlesatari.aksis.network.RetrofitInstance;
import com.binainsanlesatari.aksis.utils.PrefManagerSiswa;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardSiswa extends AppCompatActivity {

    private CardView btnLogoutSiswa, btnSuratpermohonan, btnlaporansiswa;
    private TextView tvNama, tvNisn;
    private PrefManagerSiswa prefManagerSiswa;
    private Button editprofil;
    private CircleImageView fotoprofilsiswa;
    private ApiSiswa cekfotoprofil;
    private String imgpathprofil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_siswa);
        prefManagerSiswa = new PrefManagerSiswa(this);
        //TODO DASHBOARD GURU

        tvNama = findViewById(R.id.DBnama_siswa);
        tvNisn = findViewById(R.id.DBnisn_siswa);

        fotoprofilsiswa=findViewById(R.id.DBprofile_image_siswa);

        editprofil= findViewById(R.id.DBedit_profile_siswa);

        String namaSiswa = prefManagerSiswa.getSiswa().getNamaLengkap();
        tvNama.setText(namaSiswa);
        String nisnSiswa = prefManagerSiswa.getSiswa().getNisn();
        tvNisn.setText(nisnSiswa);

        cekfotoprofil = RetrofitInstance.create().create(ApiSiswa.class);
        cekfotoprofil.getprofilsiswa(nisnSiswa).enqueue(new Callback<uploadimg>() {
            @Override
            public void onResponse(Call<uploadimg> call, Response<uploadimg> response) {
                int value = response.body().getValue();
                String message = response.body().getMessage();
                if (value == 1) {
                    imgpathprofil = response.body().getPath();
                    Picasso.with(com.binainsanlesatari.aksis.ViewSiswa.DashboardSiswa.this).load(BuildConfig.BASE_URL+imgpathprofil).fit().centerCrop()
                            .placeholder(R.drawable.student)
                            .error(R.drawable.student)
                            .into(fotoprofilsiswa);
                } else {
                    Toast.makeText(com.binainsanlesatari.aksis.ViewSiswa.DashboardSiswa.this, message, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<uploadimg> call, Throwable t) {

            }
        });


        btnLogoutSiswa = findViewById(R.id.logout_siswa);
        btnLogoutSiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefManagerSiswa.deleteSiswa();
                Intent intent = new Intent(DashboardSiswa.this, WelcomeLogin.class);
                startActivity(intent);
                finish();
            }
        });

        btnSuratpermohonan = findViewById(R.id.DBsurat_permohonan_izin);
        btnSuratpermohonan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardSiswa.this, Suratpermohonan.class);
                startActivity(intent);

            }
        });

        editprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardSiswa.this, edit_profil_siswa.class);
                startActivity(intent);
            }
        });
        btnlaporansiswa=findViewById(R.id.DBlaporan_pelajaran);
        btnlaporansiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardSiswa.this, laporan_pelajaran_siswa.class);
                startActivity(intent);
            }
        });
    }
}
