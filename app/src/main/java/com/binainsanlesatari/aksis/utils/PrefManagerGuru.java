package com.binainsanlesatari.aksis.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.binainsanlesatari.aksis.model.GuruModel.Login.LoginGuru.Guru;
import com.google.gson.Gson;

public class PrefManagerGuru implements PrefRuleGuru {

    private Context context;
    private SharedPreferences sharedPreferences;

    private static final String GURU = "GURU";


    public PrefManagerGuru(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("AKSIS", Context.MODE_PRIVATE);
    }

    @Override
    public void saveUser(Guru guru) {
        String json = new Gson().toJson(guru);
        sharedPreferences.edit().putString(GURU, json).apply();
    }

    @Override
    public Guru getUser() {
        String json = sharedPreferences.getString(GURU, "");
        Guru guru = new Gson().fromJson(json, Guru.class);
        return guru;
    }

    public void deleteGuru(){
        sharedPreferences.edit().remove(GURU).apply();
    }

}
