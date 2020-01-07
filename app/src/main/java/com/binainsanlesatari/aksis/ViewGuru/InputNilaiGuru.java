package com.binainsanlesatari.aksis.ViewGuru;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.ViewGuru.InputNilai.InputNilai_Tugas;
import com.binainsanlesatari.aksis.ViewGuru.InputNilai.InputNilai_UAS;
import com.binainsanlesatari.aksis.ViewGuru.InputNilai.InputNilai_UH;
import com.binainsanlesatari.aksis.ViewGuru.InputNilai.InputNilai_UTS;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class InputNilaiGuru extends AppCompatActivity {

    private CardView btnInputUH, btnInputTugas, btnInputUAS, btnInputUTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_nilai_guru);
        Toolbar tbCommone = findViewById(R.id.tbCommon);
        tbCommone.setTitle("Pilih Input");
        setSupportActionBar(tbCommone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        BindBtnInput();

        btnInputUH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InputNilaiGuru.this, InputNilai_UH.class);
                startActivity(intent);
            }
        });
        btnInputTugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InputNilaiGuru.this, InputNilai_Tugas.class);
                startActivity(intent);
            }
        });
        btnInputUAS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InputNilaiGuru.this, InputNilai_UAS.class);
                startActivity(intent);
            }
        });
        btnInputUTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InputNilaiGuru.this, InputNilai_UTS.class);
                startActivity(intent);
            }
        });
    }

    private void BindBtnInput() {
        btnInputUH = findViewById(R.id.input_UH);
        btnInputTugas = findViewById(R.id.input_tugas);
        btnInputUAS = findViewById(R.id.input_UAS);
        btnInputUTS = findViewById(R.id.input_UTS);
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
