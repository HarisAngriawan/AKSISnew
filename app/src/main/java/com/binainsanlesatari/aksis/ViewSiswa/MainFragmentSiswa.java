package com.binainsanlesatari.aksis.ViewSiswa;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.ViewGuru.FRAGMENT.FragmentHome;
import com.binainsanlesatari.aksis.ViewGuru.FRAGMENT.FragmentProfile;
import com.binainsanlesatari.aksis.ViewGuru.MainFragment;
import com.binainsanlesatari.aksis.ViewSiswa.FRAGMENT.FragmentHomeSiswa;
import com.binainsanlesatari.aksis.ViewSiswa.FRAGMENT.FragmentProfileSiswa;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainFragmentSiswa extends AppCompatActivity {

    private AlertDialog.Builder alertDialog;
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selected = null;

                    switch (menuItem.getItemId()) {
                        case R.id.home:
                            selected = new FragmentHomeSiswa();
                            break;
                        case R.id.profile:
                            selected = new FragmentProfileSiswa();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, selected).commit();
                    return true;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment_siswa);
        alertDialog = new AlertDialog.Builder(this);
        BottomNavigationView bottomNav = findViewById(R.id.navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new
                FragmentHomeSiswa()).commit();
    }
    @Override
    public void onBackPressed() {

        alertDialog.setTitle("Tutup Aplikasi");
        alertDialog.setMessage("Apakah anda yakin ingin menutup Aplikasi ?")
                .setCancelable(false)
                .setPositiveButton("Ya",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.cancel();
                                MainFragmentSiswa.super.onBackPressed();
                                finish();
                            }
                        }).setNegativeButton("Tidak",
                    new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                    }
                }).create().show();

    }
}
