package com.binainsanlesatari.aksis.model.GuruModel.Prestasi;

import com.google.gson.annotations.SerializedName;

public class DataPrestasiItem{

	@SerializedName("nama")
	private String nama;

	@SerializedName("th_ajaran")
	private String thAjaran;

	@SerializedName("nisn")
	private String nisn;

	@SerializedName("kelas")
	private String kelas;

	@SerializedName("tgl")
	private String tgl;

	@SerializedName("kategori")
	private String kategori;

	@SerializedName("id")
	private String id;

	@SerializedName("deskripsi")
	private String deskripsi;

	@SerializedName("npsn")
	private String npsn;

	@SerializedName("status")
	private String status;

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setThAjaran(String thAjaran){
		this.thAjaran = thAjaran;
	}

	public String getThAjaran(){
		return thAjaran;
	}

	public void setNisn(String nisn){
		this.nisn = nisn;
	}

	public String getNisn(){
		return nisn;
	}

	public void setKelas(String kelas){
		this.kelas = kelas;
	}

	public String getKelas(){
		return kelas;
	}

	public void setTgl(String tgl){
		this.tgl = tgl;
	}

	public String getTgl(){
		return tgl;
	}

	public void setKategori(String kategori){
		this.kategori = kategori;
	}

	public String getKategori(){
		return kategori;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setDeskripsi(String deskripsi){
		this.deskripsi = deskripsi;
	}

	public String getDeskripsi(){
		return deskripsi;
	}

	public void setNpsn(String npsn){
		this.npsn = npsn;
	}

	public String getNpsn(){
		return npsn;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}