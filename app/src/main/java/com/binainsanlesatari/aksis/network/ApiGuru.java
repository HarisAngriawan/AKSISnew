package com.binainsanlesatari.aksis.network;

import com.binainsanlesatari.aksis.model.GuruModel.Alumni.Alumni;
import com.binainsanlesatari.aksis.model.GuruModel.CatatanKonseling.CatatanKonseling;
import com.binainsanlesatari.aksis.model.GuruModel.HomeVisit.HomeVisit;
import com.binainsanlesatari.aksis.model.GuruModel.Kehadiran.KehadiranPerKelas;
import com.binainsanlesatari.aksis.model.GuruModel.Ketidakhadiran.DataTidakHadir;
import com.binainsanlesatari.aksis.model.GuruModel.LaporanPelajaran.DataKelas;
import com.binainsanlesatari.aksis.model.GuruModel.LaporanPelajaran.LaporanPelajaran;
import com.binainsanlesatari.aksis.model.GuruModel.Nilai.GetTugas;
import com.binainsanlesatari.aksis.model.GuruModel.Nilai.GetUAS;
import com.binainsanlesatari.aksis.model.GuruModel.Nilai.GetUH;
import com.binainsanlesatari.aksis.model.GuruModel.Nilai.GetUTS;
import com.binainsanlesatari.aksis.model.GuruModel.Nilai.MapelResponse;
import com.binainsanlesatari.aksis.model.GuruModel.Nilai.NilaiResponse;
import com.binainsanlesatari.aksis.model.GuruModel.Nilai.Semester;
import com.binainsanlesatari.aksis.model.GuruModel.Nilai.TahunAjaran;
import com.binainsanlesatari.aksis.model.GuruModel.Pelaggaran.ViewPelanggaran.DetailPelanggaranResponse;
import com.binainsanlesatari.aksis.model.GuruModel.Pelaggaran.ViewPelanggaran.KategoriPelanggaranResponse;
import com.binainsanlesatari.aksis.model.GuruModel.Pelaggaran.inputPelanggaran.DeskripsiPelanggaran;
import com.binainsanlesatari.aksis.model.GuruModel.Pelaggaran.inputPelanggaran.PelanggaranKategori;
import com.binainsanlesatari.aksis.model.GuruModel.PermohonanIzin.Izin;
import com.binainsanlesatari.aksis.model.GuruModel.Prestasi.PrestasiResponse;
import com.binainsanlesatari.aksis.model.GuruModel.Rating.RatingResponse;
import com.binainsanlesatari.aksis.model.GuruModel.SuratPanggilanOrtu.ListPanggilan;
import com.binainsanlesatari.aksis.model.GuruModel.ViewSiswa.KelasResponse;
import com.binainsanlesatari.aksis.model.GuruModel.ViewSiswa.Siswa;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiGuru {
    //data siswa
    @GET("view_data_kelas_jurusan.php")
    Call<KelasResponse> getAllKelas(
            @Query("npsn") String npsn,
            @Query("pass") String password
    );

    @GET("getData_siswa_perkelas.php")
    Call<Siswa> getDataSiswaPerkelas(
            @Query("id_kelas") String idKelas,
            @Query("pass") String password
    );


    //input nilai
    @GET("getKelas.php")
    Call<NilaiResponse> getMyClass(
            @Query("id_guru") String idGuru,
            @Query("pass") String password
    );

    @GET("getMapel.php")
    Call<MapelResponse> getMyMapel(
            @Query("npsn") String npsn,
            @Query("pass") String password
    );

    @GET("getSemester.php")
    Call<Semester> getSemester(
            @Query("pass") String password
    );

    @GET("getTahunAjaran.php")
    Call<TahunAjaran> getTahunAjaran(
            @Query("pass") String password
    );

    @GET("getNilaiUH.php")
    Call<GetUH> getNilaiUh(
            @Query("npsn") String npsn,
            @Query("pass") String password,
            @Query("kelas") String kelas,
            @Query("mapel") String mapel,
            @Query("th_ajaran") String tahun_ajaran,
            @Query("semester") String semester
    );

    @GET("getNilaiUTS.php")
    Call<GetUTS> getNilaiUTS(
            @Query("npsn") String npsn,
            @Query("kelas") String kelas,
            @Query("mapel") String mapel,
            @Query("th_ajaran") String tahun_ajaran,
            @Query("semester") String semester
    );

    @GET("getNilaiTugas.php")
    Call<GetTugas> getNilaiTugas(
            @Query("npsn") String npsn,
            @Query("kelas") String kelas,
            @Query("mapel") String mapel,
            @Query("th_ajaran") String tahun_ajaran,
            @Query("semester") String semester
    );

    @GET("getNilaiUAS.php")
    Call<GetUAS> getNilaiUas(
            @Query("npsn") String npsn,
            @Query("kelas") String kelas,
            @Query("mapel") String mapel,
            @Query("th_ajaran") String tahun_ajaran,
            @Query("semester") String semester
    );

    //pelanggaran
    @GET("view_kategori.php")
    Call<KategoriPelanggaranResponse> getKategori(
            @Query("npsn") String npsn,
            @Query("pass") String password
    );

    @GET("getPelanggaran.php")
    Call<DetailPelanggaranResponse> getPelanggaran(
            @Query("kategori") String kategori,
            @Query("pass") String password
    );

    //prestasi
    @GET("getPrestasi.php")
    Call<PrestasiResponse> getPrestasi(
            @Query("npsn") String npsn,
            @Query("pass") String password
    );

    //rating
    @GET("getRating.php")
    Call<RatingResponse> getRating(
            @Query("npsn") String npsn,
            @Query("pass") String password
    );

    //input pelanggaran
    @GET("getKategoriPelanggaran.php")
    Call<PelanggaranKategori> getPelanggaranKat(
            @Query("npsn") String npsn,
            @Query("pass") String password
    );

    @GET("getDeskripsiPelanggaran.php")
    Call<DeskripsiPelanggaran> getPelanggaranDesc(
            @Query("npsn") String npsn,
            @Query("id_kategori") String idPelangaran,
            @Query("pass") String password
    );

    @GET("getKehadiran.php")
    Call<KehadiranPerKelas> getKehadiran(
            @Query("npsn") String npsn,
            @Query("pass") String password,
            @Query("tgl") String tgl
    );

    //show Alumni
    @GET("getAlumni.php")
    Call<Alumni> getAlumni(
            @Query("npsn") String npsn,
            @Query("pass") String password
    );

    //show catatan Koseling
    @GET("getCatatanKonseling.php")
    Call<CatatanKonseling> getCatatanKonseling(
            @Query("npsn") String npsn,
            @Query("pass") String password,
            @Query("status") String status
    );

    //show homew visit
    @GET("getCatatanHomeVisit.php")
    Call<HomeVisit> getHomeVisit(
            @Query("npsn") String npsn,
            @Query("pass") String password
    );

    //show ketidakhadiran
    @GET("getKetidakhadiran.php")
    Call<DataTidakHadir> getKetidakhadiran(
            @Query("npsn") String npsn,
            @Query("tgl") String tangal
    );

    //show permohonan izin
    @GET("getPermohonanIzin.php")
    Call<Izin> getPermohonanIzin(
            @Query("npsn") String npsn
    );
    //panggilan ortu

    @GET("getPanggilanOrtu.php")
    Call<ListPanggilan> getPanggilanOrtu(
            @Query("npsn") String npsn,
            @Query("status") String status
    );

    //Laporan pelajaran

    @GET("getLaporanPelajaran.php")
    Call<LaporanPelajaran> getLaporanPelajaran(
            @Query("npsn") String npsn,
            @Query("status") String status,
            @Query("id_pengguna") String id_pengguna,
            @Query("tgl") String tanggal
    );

    @GET("getNomorKelas.php")
    Call<DataKelas> getKelasLaporan(
            @Query("npsn") String npsn,
            @Query("id_guru") String id_guru,
            @Query("id_kelas") String id_kelas,
            @Query("pass") String pass
    );


}
