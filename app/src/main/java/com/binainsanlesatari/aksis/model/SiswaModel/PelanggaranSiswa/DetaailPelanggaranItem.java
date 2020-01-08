package com.binainsanlesatari.aksis.model.SiswaModel.PelanggaranSiswa;

import com.google.gson.annotations.SerializedName;

public class DetaailPelanggaranItem {

    @SerializedName("tgl_pelanggaran")
    private String tglPelanggaran;

    @SerializedName("keterangan")
    private String keterangan;

    @SerializedName("th_ajaran")
    private String thAjaran;

    @SerializedName("nisn")
    private String nisn;

    @SerializedName("jam_pelanggaran")
    private String jamPelanggaran;

    @SerializedName("status_teguran")
    private String statusTeguran;

    @SerializedName("point")
    private String point;

    @SerializedName("npsn")
    private String npsn;

    @SerializedName("foto")
    private String foto;

    @SerializedName("kelas")
    private String kelas;

    @SerializedName("tempat_pelanggaran")
    private String tempatPelanggaran;

    @SerializedName("id")
    private String id;

    @SerializedName("deskripsi_pelanggaran")
    private String deskripsiPelanggaran;

    @SerializedName("guru_penginput")
    private String guruPenginput;

    @SerializedName("kategori_pelanggaran")
    private String kategoriPelanggaran;

    @SerializedName("status")
    private String status;

    @SerializedName("nama_guru")
    private String nama_guru;

    public String getTglPelanggaran() {
        return tglPelanggaran;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public String getThAjaran() {
        return thAjaran;
    }

    public String getNisn() {
        return nisn;
    }

    public String getJamPelanggaran() {
        return jamPelanggaran;
    }

    public String getStatusTeguran() {
        return statusTeguran;
    }

    public String getPoint() {
        return point;
    }

    public String getNpsn() {
        return npsn;
    }

    public String getFoto() {
        return foto;
    }

    public String getKelas() {
        return kelas;
    }

    public String getTempatPelanggaran() {
        return tempatPelanggaran;
    }

    public String getId() {
        return id;
    }

    public String getDeskripsiPelanggaran() {
        return deskripsiPelanggaran;
    }

    public String getGuruPenginput() {
        return guruPenginput;
    }

    public String getKategoriPelanggaran() {
        return kategoriPelanggaran;
    }

    public String getStatus() {
        return status;
    }

    public String getNama_guru() {
        return nama_guru;
    }
}