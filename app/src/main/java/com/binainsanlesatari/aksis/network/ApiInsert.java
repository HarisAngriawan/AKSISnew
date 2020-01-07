package com.binainsanlesatari.aksis.network;

import com.binainsanlesatari.aksis.model.GuruModel.Inputguru.InsertLaporanPelajaranGuru;
import com.binainsanlesatari.aksis.model.GuruModel.InsertGuru.InsertAlumni;
import com.binainsanlesatari.aksis.model.GuruModel.InsertGuru.InsertCatatanKonseling;
import com.binainsanlesatari.aksis.model.GuruModel.InsertGuru.InsertHomeVisit;
import com.binainsanlesatari.aksis.model.GuruModel.InsertGuru.InsertPelanggaran;
import com.binainsanlesatari.aksis.model.GuruModel.InsertGuru.InsertPrestasi;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInsert {
    @FormUrlEncoded
    @POST("insertAlumni.php")
    Call<InsertAlumni> getAlumniInsert(
            @Field("angkatan") String angkatan,
            @Field("jumlah_lulus") String jumlah_lulus,
            @Field("swasta") String swasta,
            @Field("negeri") String negeri,
            @Field("kerja") String kerja,
            @Field("lain") String lain,
            @Field("npsn") String npsn);

    @FormUrlEncoded
    @POST("inputPelanggaran.php")
    Call<InsertPelanggaran> getPelanggaranInsert(
            @Field("nisn") String nisn,
            @Field("kelas") String kelas,
            @Field("kategori_pelanggaran") String kategori,
            @Field("deskripsi_pelanggaran") String deskripsi,
            @Field("tgl_pelanggaran") String tgl_pelanggaran,
            @Field("jam_pelanggaran") String jam,
            @Field("tempat_pelanggaran") String tempat_pelanggaran,
            @Field("guru_penginput") String guru_penginput,
            @Field("npsn") String npsn,
            @Field("point") String point
    );

    @FormUrlEncoded
    @POST("insertHomeVisit.php")
    Call<InsertHomeVisit> getHomeVisitInsert(
            @Field("tgl") String tgl,
            @Field("tujuan") String tujuan,
            @Field("nisn") String nisn,
            @Field("nama") String nama,
            @Field("tanggapan") String tanggapan,
            @Field("konselor") String konselor,
            @Field("th_ajaran") String th_ajaran,
            @Field("npsn") String npsn
    );

    @FormUrlEncoded
    @POST("insertPrestasi.php")
    Call<InsertPrestasi> getPrestasiInsert(
            @Field("tgl") String tgl,
            @Field("kategori") String kategori,
            @Field("nisn") String nisn,
            @Field("nama") String nama,
            @Field("kelas") String kelas,
            @Field("deskripsi") String deksripsi,
            @Field("npsn") String npsn,
            @Field("th_ajaran") String th_ajaran
    );

    @FormUrlEncoded
    @POST("insertCatatanKonseling.php")
    Call<InsertCatatanKonseling> getCatatanKonseling(
            @Field("tgl") String tgl,
            @Field("masalah") String masalah,
            @Field("nisn") String nisn,
            @Field("nama") String nama,
            @Field("pendekatan") String pendekatan,
            @Field("layanan") String layanan,
            @Field("konselor") String konselor,
            @Field("npsn") String npsn,
            @Field("th_ajaran") String th_ajaran
    );

    @FormUrlEncoded
    @POST("insertLaporanPelajaran.php")
    Call<InsertLaporanPelajaranGuru> postLaporanPelajaran(
            @Field("tgl") String tgl,
            @Field("mapel") String mapel,
            @Field("materi") String jdl_materi,
            @Field("id_kelas") String kelas,
            @Field("detail") String detail_materi,
            @Field("id_guru") String id_guru,
            @Field("file") String file,
            @Field("npsn") String npsn,
            @Field("th_ajaran") String th_ajaran

    );
}
