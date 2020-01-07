package com.binainsanlesatari.aksis.model.GuruModel.HomeVisit;

import com.google.gson.annotations.SerializedName;

public class HomeVisitItem{

	@SerializedName("tanggapan")
	private String tanggapan;

	@SerializedName("nama")
	private String nama;

	@SerializedName("nama_kelas")
	private String namaKelas;

	@SerializedName("nisn")
	private String nisn;

	@SerializedName("tgl")
	private String tgl;

	@SerializedName("konselor")
	private String konselor;

	@SerializedName("nama_konselor")
	private String namaKonselor;

	@SerializedName("kelas_saat_ini")
	private String kelasSaatIni;

	@SerializedName("tujuan")
	private String tujuan;

	public void setTanggapan(String tanggapan){
		this.tanggapan = tanggapan;
	}

	public String getTanggapan(){
		return tanggapan;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setNamaKelas(String namaKelas){
		this.namaKelas = namaKelas;
	}

	public String getNamaKelas(){
		return namaKelas;
	}

	public void setNisn(String nisn){
		this.nisn = nisn;
	}

	public String getNisn(){
		return nisn;
	}

	public void setTgl(String tgl){
		this.tgl = tgl;
	}

	public String getTgl(){
		return tgl;
	}

	public void setKonselor(String konselor){
		this.konselor = konselor;
	}

	public String getKonselor(){
		return konselor;
	}

	public void setNamaKonselor(String namaKonselor){
		this.namaKonselor = namaKonselor;
	}

	public String getNamaKonselor(){
		return namaKonselor;
	}

	public void setKelasSaatIni(String kelasSaatIni){
		this.kelasSaatIni = kelasSaatIni;
	}

	public String getKelasSaatIni(){
		return kelasSaatIni;
	}

	public void setTujuan(String tujuan){
		this.tujuan = tujuan;
	}

	public String getTujuan(){
		return tujuan;
	}
}