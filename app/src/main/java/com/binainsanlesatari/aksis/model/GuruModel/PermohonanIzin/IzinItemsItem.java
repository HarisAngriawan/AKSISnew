package com.binainsanlesatari.aksis.model.GuruModel.PermohonanIzin;

import com.google.gson.annotations.SerializedName;

public class IzinItemsItem {

    @SerializedName("keterangan")
    private String keterangan;

    @SerializedName("nama")
    private String nama;

    @SerializedName("foto")
    private String foto;

    @SerializedName("nisn")
    private String nisn;

    @SerializedName("kelas")
    private String kelas;

    @SerializedName("tgl")
    private String tgl;

    @SerializedName("pelapor")
    private String pelapor;

    @SerializedName("id")
    private String id;

    @SerializedName("npsn")
    private String npsn;

    @SerializedName("status")
    private String status;

    @SerializedName("foto_profil")
    private String fotoprofil;

    public String getKeterangan() {
        return keterangan;
    }

    public String getFotoprofil() {
        return fotoprofil;
    }

    public String getNama() {
        return nama;
    }

    public String getFoto() {
        return foto;
    }

    public String getNisn() {
        return nisn;
    }

    public String getKelas() {
        return kelas;
    }

    public String getTgl() {
        return tgl;
    }

    public String getPelapor() {
        return pelapor;
    }

    public String getId() {
        return id;
    }

    public String getNpsn() {
        return npsn;
    }

    public String getStatus() {
        return status;
    }
}