package com.binainsanlesatari.aksis.ViewSiswa.PelanggaranSiswa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.adapter.adapterSiswa.AdpPelanggaranSiswa;
import com.binainsanlesatari.aksis.model.SiswaModel.PelanggaranSiswa.JumlahDataPelanggaranSiwaItem;
import com.binainsanlesatari.aksis.model.SiswaModel.PelanggaranSiswa.JumlahPelanggaran;
import com.binainsanlesatari.aksis.network.ApiSiswa;
import com.binainsanlesatari.aksis.network.RetrofitInstance;
import com.binainsanlesatari.aksis.utils.PrefManagerSiswa;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardPelanggaranSiswa extends AppCompatActivity {
    private RecyclerView rvDataPelanggaran;
    private AdpPelanggaranSiswa adpPelanggaranSiswa;
    private PrefManagerSiswa prefManagerSiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_pelanggaran_siswa);

        prefManagerSiswa = new PrefManagerSiswa(this);
        adpPelanggaranSiswa = new AdpPelanggaranSiswa(this);

        String namaSiswa = prefManagerSiswa.getSiswa().getNamaLengkap();
        String kelas = prefManagerSiswa.getSiswa().getNamaKelasJurusan();
        String nomorkelas = prefManagerSiswa.getSiswa().getNomorKelas();
        Toolbar tbCommone = findViewById(R.id.tbCommon);
        tbCommone.setTitle("Data Pelanggaran Siwa");
        tbCommone.setSubtitle(namaSiswa + " | " + kelas + " "+ nomorkelas);
        setSupportActionBar(tbCommone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        rvDataPelanggaran = findViewById(R.id.rvPelanggaranSiswa);
        rvDataPelanggaran.setLayoutManager(new LinearLayoutManager(this));
        rvDataPelanggaran.setAdapter(adpPelanggaranSiswa);

        getDataPelanggaran();

    }

    private void getDataPelanggaran() {
        ApiSiswa apiSiswa = RetrofitInstance.create().create(ApiSiswa.class);
        String npsn = prefManagerSiswa.getSiswa().getNpsn();
        String nisn = prefManagerSiswa.getSiswa().getNisn();

        apiSiswa.getJumlahPelanggaran("duniamaya",npsn,nisn).enqueue(new Callback<JumlahPelanggaran>() {
            @Override
            public void onResponse(Call<JumlahPelanggaran> call, Response<JumlahPelanggaran> response) {
                List<JumlahDataPelanggaranSiwaItem> data = response.body().getJumlahDataPelanggaranSiwa();
                adpPelanggaranSiswa.setData(data);
            }

            @Override
            public void onFailure(Call<JumlahPelanggaran> call, Throwable t) {

            }
        });
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
