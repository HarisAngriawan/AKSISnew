package com.binainsanlesatari.aksis.ViewGuru.Siswa;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.binainsanlesatari.aksis.R;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import static com.binainsanlesatari.aksis.utils.AppParams.AGAMA;
import static com.binainsanlesatari.aksis.utils.AppParams.ALAMAT;
import static com.binainsanlesatari.aksis.utils.AppParams.ASALSKLH;
import static com.binainsanlesatari.aksis.utils.AppParams.EMAIL;
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

public class ProfileSiswa extends AppCompatActivity {
    TextView tvNisn,tvNis,tvNamaLengkap,tvNamaPangglan,tvTTL,tvJK,tvSuku,tvAgama,tvNohp,tvAlamat,tvEmail,
            tvAsalSklh,tvTglMasuk,tvTglDengan,tvNpsn,tvNamaSiswa,tvKelasSiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_siswa);
        Toolbar tbCommone = findViewById(R.id.tbCommon);
        tbCommone.setTitle("Profile Siswa");
        setSupportActionBar(tbCommone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        bindView();


        String nisn = getIntent().getStringExtra(NISNSISWA);
        String nis = getIntent().getStringExtra(NISSISWA);
        String namalengkap = getIntent().getStringExtra(NAMALENGKAP);
        String namapanggilan = getIntent().getStringExtra(NAMAPANGGILAN);
        String ttl = getIntent().getStringExtra(TTL);
        String jk = getIntent().getStringExtra(JK);
        String suku = getIntent().getStringExtra(SUKU);
        String agama = getIntent().getStringExtra(AGAMA);
        String nohp = getIntent().getStringExtra(NOHP);
        String alamat = getIntent().getStringExtra(ALAMAT);
        String email = getIntent().getStringExtra(EMAIL);
        String asalsklh = getIntent().getStringExtra(ASALSKLH);
        String tglmasuk = getIntent().getStringExtra(TGLMASUK);
        String tgldgn = getIntent().getStringExtra(TGLDGN);
        String npsn = getIntent().getStringExtra(NPSN);
        String kelas = getIntent().getStringExtra(KELAS_SISWA);

        String gender;
        assert jk != null;
        if (jk.equals("P")) {
            gender = "Perempuan";
        } else {
            gender = "Laki-Laki";
        }
        setView(nisn, nis, namalengkap, namapanggilan, ttl, suku, agama, nohp, alamat, email, asalsklh, tglmasuk, tgldgn, npsn, kelas, gender);

    }

    private void setView(String nisn, String nis, String namalengkap, String namapanggilan, String ttl, String suku, String agama, String nohp, String alamat, String email, String asalsklh, String tglmasuk, String tgldgn, String npsn, String kelas, String gender) {
        tvNisn.setText(nisn);
        tvNis.setText(nis);
        tvNamaSiswa.setText(namalengkap);
        tvNamaLengkap.setText(namalengkap);
        tvNamaPangglan.setText(namapanggilan);
        tvTTL.setText(ttl);
        tvJK.setText(gender);
        tvSuku.setText(suku);
        tvAgama.setText(agama);
        tvNohp.setText(nohp);
        tvAlamat.setText(alamat);
        tvEmail.setText(email);
        tvAsalSklh.setText(asalsklh);
        tvTglMasuk.setText(tglmasuk);
        tvTglDengan.setText(tgldgn);
        tvNpsn.setText(npsn);
        tvKelasSiswa.setText(kelas);
    }

    private void bindView() {
        tvNamaSiswa = findViewById(R.id.tvStudentNama);
        tvKelasSiswa = findViewById(R.id.tvStudentClass);
        tvNisn = findViewById(R.id.tvProfileNisn);
        tvNis = findViewById(R.id.tvProfileNis);
        tvNamaLengkap = findViewById(R.id.tvProfileNama);
        tvNamaPangglan = findViewById(R.id.tvProfilePanggilan);
        tvTTL = findViewById(R.id.tvProfileTTL);
        tvJK = findViewById(R.id.tvProfileJK);
        tvSuku = findViewById(R.id.tvProfileSuku);
        tvAgama = findViewById(R.id.tvProfileAgama);
        tvNohp = findViewById(R.id.tvProfilenoHp);
        tvAlamat = findViewById(R.id.tvProfileAlamat);
        tvEmail = findViewById(R.id.tvProfileEmai);
        tvAsalSklh = findViewById(R.id.tvProfileAsalSD);
        tvTglMasuk = findViewById(R.id.tvProfileTglMasuk);
        tvTglDengan = findViewById(R.id.tvProfileTinggalDengan);
        tvNpsn = findViewById(R.id.tvProfileNpsn);
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
