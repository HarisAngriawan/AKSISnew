package com.binainsanlesatari.aksis;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.binainsanlesatari.aksis.ViewGuru.MainFragment;
import com.binainsanlesatari.aksis.ViewSiswa.MainFragmentSiswa;
import com.binainsanlesatari.aksis.model.GuruModel.Login.LoginGuru.Guru;
import com.binainsanlesatari.aksis.model.GuruModel.Login.LoginSiswa.Siswa;
import com.binainsanlesatari.aksis.utils.PrefManagerGuru;
import com.binainsanlesatari.aksis.utils.PrefManagerSiswa;

import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    ProgressBar progressBar;
    TextView textView;
    int i = 50;
    private PrefManagerGuru prefManagerGuru;
    private PrefManagerSiswa prefManagerSiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        progressBar = findViewById(R.id.progress_screen);
        textView = findViewById(R.id.tvLoading);
        textView.setText("");
        progressBar.setScaleY(3f);
//        Drawable progressDrawable = progressBar.getProgressDrawable().mutate();
//        progressDrawable.setColorFilter(Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
//        progressBar.setProgressDrawable(progressDrawable);

        long period = 100;
        final Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                if (i<100){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(String.valueOf(i)+"%");
                        }
                    });
                    progressBar.setProgress(i);
                    i++;
                }else {
                    t.cancel();
                    finish();
                }
            }
        },0,period);
        prefManagerGuru = new PrefManagerGuru(this);
        prefManagerSiswa = new PrefManagerSiswa(this);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                closeSplashGuru();
            }
        }, 5000);
    }


//
//    private void progressAnimation() {
//        ProgressBarAnimation anim = new ProgressBarAnimation(this, progressBar, textView, 0f, 100f);
//        anim.setDuration(8000);
//        progressBar.setAnimation(anim);
//    }

    private void closeSplashGuru() {
        Guru guru = prefManagerGuru.getUser();
        Siswa siswa = prefManagerSiswa.getSiswa();

        if (guru != null) {
            Intent intent = new Intent(SplashScreen.this, MainFragment.class);
            startActivity(intent);
        } else if (siswa != null) {
            Intent intent = new Intent(SplashScreen.this, MainFragmentSiswa.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(SplashScreen.this, IntroApps.class);
            startActivity(intent);
        }

        finish();
    }
}

