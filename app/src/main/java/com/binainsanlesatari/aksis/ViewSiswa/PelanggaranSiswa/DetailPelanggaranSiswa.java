package com.binainsanlesatari.aksis.ViewSiswa.PelanggaranSiswa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.adapter.adapterSiswa.AdpDetailPelanggaran;
import com.binainsanlesatari.aksis.model.SiswaModel.PelanggaranSiswa.DetaailPelanggaranItem;
import com.binainsanlesatari.aksis.model.SiswaModel.PelanggaranSiswa.DetailPelanggaran;
import com.binainsanlesatari.aksis.network.ApiSiswa;
import com.binainsanlesatari.aksis.network.RetrofitInstance;
import com.binainsanlesatari.aksis.utils.AppParamsSiswa;
import com.binainsanlesatari.aksis.utils.PrefManagerSiswa;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPelanggaranSiswa extends AppCompatActivity {
    private AdpDetailPelanggaran adpDetailPelanggaran;
    private RecyclerView rvDetailPelanggaran;
    private PrefManagerSiswa prefManagerSiswa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pelanggaran_siswa);
        prefManagerSiswa = new PrefManagerSiswa(this);
        adpDetailPelanggaran = new AdpDetailPelanggaran(this);
        String kodepelanggaran = getIntent().getStringExtra(AppParamsSiswa.KODEPELANGGARANSISWA);

        rvDetailPelanggaran = findViewById(R.id.rvDetailSiswaPelanggaran);
        rvDetailPelanggaran.setLayoutManager(new LinearLayoutManager(this));
        rvDetailPelanggaran.setAdapter(adpDetailPelanggaran);

        getDetailPelanggaranSiswa(kodepelanggaran);

    }

    private void getDetailPelanggaranSiswa(String kodepelanggaran) {
        ApiSiswa apiSiswa = RetrofitInstance.create().create(ApiSiswa.class);
        String npsn = prefManagerSiswa.getSiswa().getNpsn();
        String nisn = prefManagerSiswa.getSiswa().getNisn();

        apiSiswa.getDetailPelanggaran("duniamaya",npsn,nisn,kodepelanggaran)
                .enqueue(new Callback<DetailPelanggaran>() {
                    @Override
                    public void onResponse(Call<DetailPelanggaran> call, Response<DetailPelanggaran> response) {
                        List<DetaailPelanggaranItem> data = response.body().getDetaailPelanggaran();
                        adpDetailPelanggaran.setData(data);
                    }

                    @Override
                    public void onFailure(Call<DetailPelanggaran> call, Throwable t) {

                    }
                });
    }
}
