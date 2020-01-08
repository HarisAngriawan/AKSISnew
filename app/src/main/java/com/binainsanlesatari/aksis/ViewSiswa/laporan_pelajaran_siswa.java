package com.binainsanlesatari.aksis.ViewSiswa;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.ViewGuru.Konseling.InputKonseling;
import com.binainsanlesatari.aksis.adapter.adapterSiswa.Adplaporanpelajaransiswa;
import com.binainsanlesatari.aksis.model.SiswaModel.DataLaporanPelajaranItem;
import com.binainsanlesatari.aksis.model.SiswaModel.Laporanpelajaransiswa;
import com.binainsanlesatari.aksis.network.ApiSiswa;
import com.binainsanlesatari.aksis.network.RetrofitInstance;
import com.binainsanlesatari.aksis.utils.PrefManagerSiswa;

import java.util.Calendar;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class laporan_pelajaran_siswa extends AppCompatActivity {

    DatePickerDialog datePickerDialogpicker;
    private PrefManagerSiswa prefManagerSiswa;
    private ApiSiswa apigetlaporan;
    private ImageButton showkalender;
    private TextView dateView;
    private Adplaporanpelajaransiswa adplaporanpelajaransiswa;
    private RecyclerView laporanpelajaransiswa;
    private String tgl, namaSiswa, nisnSiswa, npsnSiswa, kelas;
    private Button tampilkan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan_pelajaran_siswa);
        datePickerDialogpicker = new DatePickerDialog(this);
        adplaporanpelajaransiswa = new Adplaporanpelajaransiswa(this);
        prefManagerSiswa = new PrefManagerSiswa(this);

        namaSiswa = prefManagerSiswa.getSiswa().getNamaLengkap();
        nisnSiswa = prefManagerSiswa.getSiswa().getNisn();
        npsnSiswa = prefManagerSiswa.getSiswa().getNpsn();
        kelas = prefManagerSiswa.getSiswa().getKelasSaatIni();

        dateView=findViewById(R.id.tvtanggallaporansiswa);

        laporanpelajaransiswa = findViewById(R.id.rvlaporanpelajaransiswa);
        laporanpelajaransiswa.setLayoutManager(new LinearLayoutManager(this));
        laporanpelajaransiswa.setAdapter(adplaporanpelajaransiswa);

        showkalender = findViewById(R.id.kalender);
        showkalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialogpicker = new DatePickerDialog(laporan_pelajaran_siswa.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        dateView.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, year, month, day);
                datePickerDialogpicker.show();
            }
        });

        tampilkan=findViewById(R.id.tampilkan);
        tampilkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getlaporansiswa();
            }
        });

    }

    public void getlaporansiswa(){
        tgl = dateView.getText().toString();
        apigetlaporan = RetrofitInstance.create().create(ApiSiswa.class);
        apigetlaporan.getlaporansiswa(tgl,npsnSiswa,kelas).enqueue(new Callback<Laporanpelajaransiswa>() {
            @Override
            public void onResponse(Call<Laporanpelajaransiswa> call, Response<Laporanpelajaransiswa> response) {
                List<DataLaporanPelajaranItem> pelajaranItem = response.body().getDataLaporanPelajaran();
                adplaporanpelajaransiswa.setData(pelajaranItem);
            }

            @Override
            public void onFailure(Call<Laporanpelajaransiswa> call, Throwable t) {

            }
        });
    }
}
