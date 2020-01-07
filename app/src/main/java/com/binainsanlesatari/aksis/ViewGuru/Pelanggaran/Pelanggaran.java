package com.binainsanlesatari.aksis.ViewGuru.Pelanggaran;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.ViewGuru.Pelanggaran.dataPelanggaran.ViewPelanggaran;
import com.binainsanlesatari.aksis.ViewGuru.Pelanggaran.inputPelanggaran.mainInputPelanggaran;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class Pelanggaran extends AppCompatActivity {

    private CardView cvDetailPelanggaran,cvInputPelanggaran;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelanggaran);

        Toolbar tbCommone = findViewById(R.id.tbCommon);
        tbCommone.setTitle("Pelanggaran");
        setSupportActionBar(tbCommone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        bindView();

        cvDetailPelanggaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pelanggaran.this, ViewPelanggaran.class);
                startActivity(intent);
            }
        });

        cvInputPelanggaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pelanggaran.this, mainInputPelanggaran.class);
                startActivity(intent);
            }
        }); 
    }

    private void bindView() {
        cvDetailPelanggaran = findViewById(R.id.view_pelanggaran);
        cvInputPelanggaran = findViewById(R.id.input_pelanggaran);
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
