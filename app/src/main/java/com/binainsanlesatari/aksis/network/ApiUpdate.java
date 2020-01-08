package com.binainsanlesatari.aksis.network;

import com.binainsanlesatari.aksis.model.GuruModel.UPDATEGURU.UpdatePassGuru;
import com.binainsanlesatari.aksis.model.GuruModel.UPDATEGURU.UpdateProfilGuru;
import com.binainsanlesatari.aksis.model.GuruModel.UPDATEGURU.VerifikasiIzin;
import com.binainsanlesatari.aksis.model.SiswaModel.InputPermohonan;
import com.binainsanlesatari.aksis.model.SiswaModel.uploadimg;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiUpdate {

    @FormUrlEncoded
    @POST("updatePassGuru.php")
    Call<UpdatePassGuru> updatePassGuru(
            @Field("pass_lama") String passLama,
            @Field("pass_baru") String passBaru,
            @Field("konfirm_pass") String konfirmPass,
            @Field("id_pengguna") String idPengguna,
            @Field("npsn") String npsn
    );

    @FormUrlEncoded
    @POST("verifikasiIzin.php")
    Call<VerifikasiIzin> verifikasiIzin(
            @Field("id") String id

    );


    //fotoProfile
    @Multipart
    @POST("uploadimgpermohonan.php")
    Call<uploadimg> uploadImage(
            @Part MultipartBody.Part image
    );

    @FormUrlEncoded
    @POST("uploadFotoGuru.php")
    Call<InputPermohonan> updateFotoGuru(
            @Field("foto")String foto,
            @Field("no_hp")String no_hp

    );
    @FormUrlEncoded
    @POST("getFotoGuru.php")
    Call<uploadimg> getFotoGuru(
            @Field("no_hp")String no_hp
    );

    //Update Edit Profil Guru
    @FormUrlEncoded
    @POST("updateProfilGuru.php")
    Call<UpdateProfilGuru> updateProfilGuru(
            @Field("id")String id,
            @Field("nama")String nama,
            @Field("no_hp")String no_hp,
            @Field("alamat")String alamat
    );
}
