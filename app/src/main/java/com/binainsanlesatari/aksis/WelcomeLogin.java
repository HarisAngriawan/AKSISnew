package com.binainsanlesatari.aksis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.binainsanlesatari.aksis.ViewGuru.LoginGuru;
import com.binainsanlesatari.aksis.ViewSiswa.LoginSiswa;

public class WelcomeLogin extends AppCompatActivity {

    private Button btnGuru, btnSiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_login);
//        getSupportActionBar().hide();
        BindView();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        btnGuru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeLogin.this, LoginGuru.class);
                startActivity(intent);
            }
        });
        btnSiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeLogin.this, LoginSiswa.class);
                startActivity(intent);
            }
        });

    }

    private void BindView() {
        btnGuru = findViewById(R.id.masukGuru);
        btnSiswa = findViewById(R.id.masukSiswa);
    }
}
