package com.binainsanlesatari.aksis.network;

import com.binainsanlesatari.aksis.model.SiswaModel.InputPermohonan;
import com.binainsanlesatari.aksis.model.SiswaModel.Laporanpelajaransiswa;
import com.binainsanlesatari.aksis.model.SiswaModel.PelanggaranSiswa.DetaailPelanggaranItem;
import com.binainsanlesatari.aksis.model.SiswaModel.PelanggaranSiswa.DetailPelanggaran;
import com.binainsanlesatari.aksis.model.SiswaModel.PelanggaranSiswa.JumlahPelanggaran;
import com.binainsanlesatari.aksis.model.SiswaModel.uploadimg;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiSiswa {
    @FormUrlEncoded
    @POST("insertsuratpermohonan.php")
    Call<InputPermohonan> insertpermohonan(
            @Field("alasan") String alasan,
            @Field("tanggal") String tgl,
            @Field("url") String imgpath,
            @Field("nama") String nama,
            @Field("nisn") String nisn,
            @Field("npsn") String npsn,
            @Field("kelas") String kelas

    );

    @Multipart
    @POST("uploadimgpermohonan.php")
    Call<uploadimg> uploadImage(
            @Part MultipartBody.Part image
    );

    @FormUrlEncoded
    @POST("updateprofilsiswa.php")
    Call<InputPermohonan> updateprofil(
            @Field("foto") String foto,
            @Field("nisn") String nisn

    );

    @FormUrlEncoded
    @POST("getprofilsiswa.php")
    Call<uploadimg> getprofilsiswa(
            @Field("nisn") String nisn

    );

    @GET("getlaporanpelajaransiswa.php")
    Call<Laporanpelajaransiswa> getlaporansiswa(
            @Query("tgl") String tgl,
            @Query("npsn") String npsn,
            @Query("kelas") String kls

    );

    @GET("getJumlahPelanggaranSiswa.php")
    Call<JumlahPelanggaran> getJumlahPelanggaran(
            @Query("pass") String pass,
            @Query("npsn") String npsn,
            @Query("nisn") String nisn
    );

    @GET("getDetailPelaggaranSiswa.php")
    Call<DetailPelanggaran> getDetailPelanggaran(
            @Query("pass") String pass,
            @Query("npsn") String npsn,
            @Query("nisn") String nisn,
            @Query("kategori_pelanggaran") String kategori
    );
}
