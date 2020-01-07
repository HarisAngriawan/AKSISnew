package com.binainsanlesatari.aksis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.binainsanlesatari.aksis.adapter.IntroAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class IntroApps extends AppCompatActivity {

    private ViewPager screenPager;
    private IntroAdapter introAdapter;
    private TabLayout tabIndicator;
    private Button btnNext,btnMulai;
    int position = 0;
    private Animation btnAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_apps);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        tabIndicator = findViewById(R.id.tab_indicator);

//        getSupportActionBar().hide();
        final List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("PelanggaranItem","Memudahkan para dewan pengajar untuk input data pelanggaran siswa, monitoring dan menegur siswa yagn bermasalah dalam sekolah.", R.drawable.data_pelaggaran));
        mList.add(new ScreenItem("Nilai","Memberikan kemudahan bagi guru untuk memberikan nilai melalui aplikasi dalam sekolah. Memudahkan untuk menginput secara menyeluruh dalam memberikan nilai", R.drawable.input));
        mList.add(new ScreenItem("Surat","Memudahkan Guru atau siswa dan wali murid dalam mengetahui informasi tentang siswa apa saja yang terjadi terhadap murid disekolah maupun di rumah, memudahkan wali kelas atau wali murid dalam mengirimkan surat panggilan ataupun surat izin sekolah yang dapat di akses oleh wali murid", R.drawable.surat_pangillan));

        screenPager = findViewById(R.id.screenviewpager);
        introAdapter = new IntroAdapter(this, mList);
        screenPager.setAdapter(introAdapter);

        tabIndicator.setupWithViewPager(screenPager);
        btnNext = findViewById(R.id.btnNext);
        btnMulai = findViewById(R.id.btnMulai);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position=screenPager.getCurrentItem();
                if (position<mList.size()){
                    position++;
                    screenPager.setCurrentItem(position);
                }
                if (position == mList.size()-1){
                    loadLastScreen();
                }
            }
        });

        btnMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroApps.this, WelcomeLogin.class);
                startActivity(intent);
            }
        });

        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == mList.size()){
                    loadLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    private void loadLastScreen() {
        btnNext.setVisibility(View.INVISIBLE);
        btnMulai.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.VISIBLE);


        btnMulai.setAnimation(btnAnim);
    }
}
