package com.binainsanlesatari.aksis.utils;


import android.content.Context;
import android.content.SharedPreferences;

import com.binainsanlesatari.aksis.model.GuruModel.Login.LoginSiswa.Siswa;
import com.google.gson.Gson;

public class PrefManagerSiswa implements PrefRuleSiswa {

    private Context context;
    private SharedPreferences preferences;

    private static final String SISWA = "SISWA";

    public PrefManagerSiswa(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences("AKSIS_SISWA", Context.MODE_PRIVATE);
    }

    @Override
    public void saveSiswa(Siswa siswa) {
        String json = new Gson().toJson(siswa);
        preferences.edit().putString(SISWA,json).apply();
    }

    @Override
    public Siswa getSiswa() {
        String json = preferences.getString(SISWA, "");
        Siswa siswa = new Gson().fromJson(json, Siswa.class);
        return siswa;
    }
    public void deleteSiswa(){
        preferences.edit().remove(SISWA).apply();
    }
}
