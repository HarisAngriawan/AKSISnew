package com.binainsanlesatari.aksis.model.GuruModel.Kehadiran;

import com.google.gson.annotations.SerializedName;

public class DataKehadiranItem{

	@SerializedName("id_kelas_jurusan")
	private String idKelasJurusan;

	@SerializedName("nama_kelas_jurusan")
	private String namaKelasJurusan;

	@SerializedName("jumlah_masuk")
	private String jumlahMasuk;

	@SerializedName("nomor_kelas")
	private String nomorKelas;

	@SerializedName("jumlah_siswa")
	private String jumlahSiswa;

	@SerializedName("tidak_masuk")
	private String tidakMasuk;

	public void setIdKelasJurusan(String idKelasJurusan){
		this.idKelasJurusan = idKelasJurusan;
	}

	public String getIdKelasJurusan(){
		return idKelasJurusan;
	}

	public void setNamaKelasJurusan(String namaKelasJurusan){
		this.namaKelasJurusan = namaKelasJurusan;
	}

	public String getNamaKelasJurusan(){
		return namaKelasJurusan;
	}

	public void setJumlahMasuk(String jumlahMasuk){
		this.jumlahMasuk = jumlahMasuk;
	}

	public String getJumlahMasuk(){
		return jumlahMasuk;
	}

	public void setNomorKelas(String nomorKelas){
		this.nomorKelas = nomorKelas;
	}

	public String getNomorKelas(){
		return nomorKelas;
	}

	public void setJumlahSiswa(String jumlahSiswa){
		this.jumlahSiswa = jumlahSiswa;
	}

	public String getJumlahSiswa(){
		return jumlahSiswa;
	}

	public void setTidakMasuk(String tidakMasuk){
		this.tidakMasuk = tidakMasuk;
	}

	public String getTidakMasuk(){
		return tidakMasuk;
	}
}