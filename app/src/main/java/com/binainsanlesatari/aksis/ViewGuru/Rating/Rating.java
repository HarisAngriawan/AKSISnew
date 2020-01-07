package com.binainsanlesatari.aksis.ViewGuru.Rating;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.binainsanlesatari.aksis.R;
import com.binainsanlesatari.aksis.adapter.adapterGuru.AdpRating;
import com.binainsanlesatari.aksis.model.GuruModel.Rating.RatingItem;
import com.binainsanlesatari.aksis.model.GuruModel.Rating.RatingResponse;
import com.binainsanlesatari.aksis.network.ApiGuru;
import com.binainsanlesatari.aksis.network.RetrofitInstance;
import com.binainsanlesatari.aksis.utils.PrefManagerGuru;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Rating extends AppCompatActivity {
    private PrefManagerGuru prefManagerGuru;
    private AdpRating adpRating;
    private RecyclerView rvRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        Toolbar tbCommone = findViewById(R.id.tbCommon);
            tbCommone.setTitle("Rating Guru");
        setSupportActionBar(tbCommone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        prefManagerGuru = new PrefManagerGuru(this);
        adpRating = new AdpRating(this);

        rvRating = findViewById(R.id.rvRating);
        rvRating.setLayoutManager(new LinearLayoutManager(this));
        rvRating.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
        rvRating.setAdapter(adpRating);

        getRating();

    }

    private void getRating() {
        Retrofit retrofit = new RetrofitInstance().create();
        ApiGuru apiGuru = retrofit.create(ApiGuru.class);
        String npsn = prefManagerGuru.getUser().getNpsn();
        String password = "duniamaya";

        apiGuru.getRating(npsn, password).enqueue(new Callback<RatingResponse>() {
            @Override
            public void onResponse(Call<RatingResponse> call, Response<RatingResponse> response) {
                List<RatingItem> dataRating = response.body().getRating();
                adpRating.setRating(dataRating);
            }

            @Override
            public void onFailure(Call<RatingResponse> call, Throwable t) {

            }
        });
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
