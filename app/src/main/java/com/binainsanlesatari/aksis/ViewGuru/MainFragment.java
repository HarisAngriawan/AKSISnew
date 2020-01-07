package com.binainsanlesatari.aksis.ViewGuru;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.ViewGuru.FRAGMENT.FragmentHome;
import com.binainsanlesatari.aksis.ViewGuru.FRAGMENT.FragmentProfile;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainFragment extends AppCompatActivity {

    private AlertDialog.Builder alertDialog;
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selected = null;

                    switch (menuItem.getItemId()) {
                        case R.id.home:
                            selected = new FragmentHome();
                            break;
                        case R.id.profile:
                            selected = new FragmentProfile();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, selected).commit();
                    return true;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment_guru);
        alertDialog = new AlertDialog.Builder(this);
        BottomNavigationView bottomNav = findViewById(R.id.navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new
                FragmentHome()).commit();
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
                                MainFragment.super.onBackPressed();
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
