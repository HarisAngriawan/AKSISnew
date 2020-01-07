package com.binainsanlesatari.aksis.network;

import com.binainsanlesatari.aksis.model.SiswaModel.uploadimg;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiUpload {
    @Multipart
    @POST("uploadpdf.php")
    Call<uploadimg> uploadpdf(
            @Part MultipartBody.Part file
    );
}
