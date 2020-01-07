package com.binainsanlesatari.aksis.network;

import com.binainsanlesatari.aksis.model.GuruModel.Login.LoginGuru.LoginModel;
import com.binainsanlesatari.aksis.model.GuruModel.Login.LoginSiswa.LoginModelSiswa;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiLogin {

    @FormUrlEncoded
    @POST("loginGuru.php")
    Call<LoginModel> loginGuru(
            @Field("no_hp") String noHp,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("loginSiswa.php")
    Call<LoginModelSiswa> loginSiswa(
            @Field("nisn")String nisn,
            @Field("password")String password
    );

    @FormUrlEncoded
    @POST("loginOrtu.php")
    Call<LoginModelSiswa> loginAyah(
            @Field("id_ayah")String idAyah,
            @Field("pass_ortu")String password
    );


}
