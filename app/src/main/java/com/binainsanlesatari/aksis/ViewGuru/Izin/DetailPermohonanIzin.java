package com.binainsanlesatari.aksis.ViewGuru.Izin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.adapter.adapterGuru.AdpSiswa;
import com.binainsanlesatari.aksis.model.GuruModel.UPDATEGURU.VerifikasiIzin;
import com.binainsanlesatari.aksis.network.ApiUpdate;
import com.binainsanlesatari.aksis.network.RetrofitInstance;
import com.binainsanlesatari.aksis.utils.PrefManagerGuru;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import java.sql.Struct;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.binainsanlesatari.aksis.utils.AppParams.AGAMA;
import static com.binainsanlesatari.aksis.utils.AppParams.ALAMAT;
import static com.binainsanlesatari.aksis.utils.AppParams.ASALSKLH;
import static com.binainsanlesatari.aksis.utils.AppParams.EMAIL;
import static com.binainsanlesatari.aksis.utils.AppParams.IZINFOTO;
import static com.binainsanlesatari.aksis.utils.AppParams.IZINID;
import static com.binainsanlesatari.aksis.utils.AppParams.IZINKELAS;
import static com.binainsanlesatari.aksis.utils.AppParams.IZINKETERANGAN;
import static com.binainsanlesatari.aksis.utils.AppParams.IZINNAMA;
import static com.binainsanlesatari.aksis.utils.AppParams.IZINNISN;
import static com.binainsanlesatari.aksis.utils.AppParams.IZINNPSN;
import static com.binainsanlesatari.aksis.utils.AppParams.IZINSTATUS;
import static com.binainsanlesatari.aksis.utils.AppParams.IZINTGL;
import static com.binainsanlesatari.aksis.utils.AppParams.JK;
import static com.binainsanlesatari.aksis.utils.AppParams.KELAS_SISWA;
import static com.binainsanlesatari.aksis.utils.AppParams.NAMALENGKAP;
import static com.binainsanlesatari.aksis.utils.AppParams.NAMAPANGGILAN;
import static com.binainsanlesatari.aksis.utils.AppParams.NISNSISWA;
import static com.binainsanlesatari.aksis.utils.AppParams.NISSISWA;
import static com.binainsanlesatari.aksis.utils.AppParams.NOHP;
import static com.binainsanlesatari.aksis.utils.AppParams.NPSN;
import static com.binainsanlesatari.aksis.utils.AppParams.SUKU;
import static com.binainsanlesatari.aksis.utils.AppParams.TGLDGN;
import static com.binainsanlesatari.aksis.utils.AppParams.TGLMASUK;
import static com.binainsanlesatari.aksis.utils.AppParams.TTL;

public class DetailPermohonanIzin extends AppCompatActivity {
    PrefManagerGuru prefManagerGuru;
    ShimmerFrameLayout shimmerFrameLayout;
    ProgressBar pbPlaceHolder;

    AppCompatImageView imgIzin;
    TextView tvNama, tvNisn, tvKelas, tvStatus;
    TextInputEditText edKeterangan;
    CardView cvVerifikasi, cvHapus;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_permohonan_izin);
        Toolbar tbCommone = findViewById(R.id.tbCommon);
        tbCommone.setTitle("Detail Izin Siswa");
        setSupportActionBar(tbCommone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tvNama = findViewById(R.id.detailIzinnNama);
        tvNisn = findViewById(R.id.detailIzinnKelas);
        tvKelas = findViewById(R.id.detailIzinnNisn);
        tvStatus = findViewById(R.id.detailIzinnStatus);
        edKeterangan = findViewById(R.id.edKeterangan);
        cvVerifikasi = findViewById(R.id.cvVerifikasi);
        cvHapus = findViewById(R.id.cvDelete);
        imgIzin = findViewById(R.id.izinPicture);

        String tgl = getIntent().getStringExtra(IZINTGL);
        String npsn = getIntent().getStringExtra(IZINNPSN);
        String nisn = getIntent().getStringExtra(IZINNISN);
        String nama = getIntent().getStringExtra(IZINNAMA);
        String kelas = getIntent().getStringExtra(IZINKELAS);
        String keterangan = getIntent().getStringExtra(IZINKETERANGAN);
        String status = getIntent().getStringExtra(IZINSTATUS);
        String foto = getIntent().getStringExtra(IZINFOTO);
        String url = "http://aksis.binainsanlestari.com/android_AKSIS/" + foto;



        tvNama.setText(nama);
        tvNisn.setText(nisn);
        tvKelas.setText(kelas);
        tvStatus.setText(status);
        edKeterangan.setText(keterangan);
        Picasso.with(DetailPermohonanIzin.this)
                .load(url)
                .placeholder(R.mipmap.ic_launcher_round)
                .into(imgIzin);
        
        cvVerifikasi.setOnClickListener(new View.OnClickListener() {
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
                        verifikasi();
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

    private void verifikasi() {
        String id = getIntent().getStringExtra(IZINID);

        ApiUpdate apiUpdate = RetrofitInstance.create().create(ApiUpdate.class);

        apiUpdate.verifikasiIzin(id).enqueue(new Callback<VerifikasiIzin>() {
            @Override
            public void onResponse(Call<VerifikasiIzin> call, Response<VerifikasiIzin> response) {
                String value = response.body().getValue();
                String message = response.body().getMessage();

                if (value.equals("true")){
                    Toast.makeText(DetailPermohonanIzin.this,message,Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(DetailPermohonanIzin.this,message,Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<VerifikasiIzin> call, Throwable t) {

            }
        });

    }
}
