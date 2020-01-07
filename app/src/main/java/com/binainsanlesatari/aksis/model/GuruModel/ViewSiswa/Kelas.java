package com.binainsanlesatari.aksis.model.GuruModel.ViewSiswa;

import com.google.gson.annotations.SerializedName;

public class Kelas{

	@SerializedName("id_kelas_jurusan")
	private String idKelasJurusan;

	@SerializedName("nama")
	private String nama;

	@SerializedName("jumlah")
	private String jumlah;

	@SerializedName("npsn")
	private String npsn;

	@SerializedName("id_wali_kelas")
	private String idWaliKelas;

	@SerializedName("nama_kelas")
	private String namaKelas;

	public void setIdKelasJurusan(String idKelasJurusan){
		this.idKelasJurusan = idKelasJurusan;
	}

	public String getIdKelasJurusan(){
		return idKelasJurusan;
	}

	public String getNamaKelas() {
		return namaKelas;
	}

	public void setNamaKelas(String namaKelas) {
		this.namaKelas = namaKelas;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setJumlah(String jumlah){
		this.jumlah = jumlah;
	}

	public String getJumlah(){
		return jumlah;
	}

	public void setNpsn(String npsn){
		this.npsn = npsn;
	}

	public String getNpsn(){
		return npsn;
	}

	public void setIdWaliKelas(String idWaliKelas){
		this.idWaliKelas = idWaliKelas;
	}

	public String getIdWaliKelas(){
		return idWaliKelas;
	}
}