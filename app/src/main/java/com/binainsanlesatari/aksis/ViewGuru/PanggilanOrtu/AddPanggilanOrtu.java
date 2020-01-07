package com.binainsanlesatari.aksis.ViewGuru.PanggilanOrtu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.ViewGuru.TakeAllStudents;
import com.binainsanlesatari.aksis.utils.AppParams;
import com.binainsanlesatari.aksis.utils.PrefManagerGuru;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.textfield.TextInputEditText;

public class AddPanggilanOrtu extends AppCompatActivity {
    TextInputEditText edNama, edNisn, edKasus,edSurat;
    CardView cvSearchSiswa, cvFile;
    PrefManagerGuru prefManagerGuru;
    ProgressDialog progressDialog;
    Button addSurat;

    //TODO FILE UPLOAD / WA

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_panggilan_ortu);
        prefManagerGuru = new PrefManagerGuru(this);
        progressDialog = new ProgressDialog(this);

        edNisn = findViewById(R.id.edNisnPanggilan);
        edNama = findViewById(R.id.edNamaPanggilan);
        edKasus = findViewById(R.id.edKasusPanggilan);
        edSurat = findViewById(R.id.edFilePanggilan);
        cvSearchSiswa = findViewById(R.id.cvSearchSiswaPO);
        cvFile = findViewById(R.id.cvFileanggilan);

        cvSearchSiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddPanggilanOrtu.this, TakeAllStudents.class);
                startActivityForResult(intent, 11);
            }
        });
        cvFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");
                startActivityForResult(intent,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String nisn = data.getStringExtra(AppParams.NISN);
        String nama = data.getStringExtra(AppParams.NAMA);

        edNisn.setText(nisn);
        edNama.setText(nama);
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
