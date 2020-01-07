package com.binainsanlesatari.aksis.network;

import com.binainsanlesatari.aksis.model.GuruModel.ViewSiswa.InsertSiswa;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiSearch {

    @GET("getSiswa.php")
    Call<InsertSiswa> searchSiswaByName(
            @Query("npsn") String npsn,
            @Query("nama") String nama
    );



}
