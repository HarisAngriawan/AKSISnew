package com.binainsanlesatari.aksis.model.GuruModel.CatatanKonseling;

import com.google.gson.annotations.SerializedName;

public class KonselingItem{

	@SerializedName("layanan")
	private String layanan;

	@SerializedName("nama")
	private String nama;

	@SerializedName("th_ajaran")
	private String thAjaran;

	@SerializedName("nisn")
	private String nisn;

	@SerializedName("tgl")
	private String tgl;

	@SerializedName("konselor")
	private String konselor;

	@SerializedName("id")
	private String id;

	@SerializedName("pendekatan")
	private String pendekatan;

	@SerializedName("masalah")
	private String masalah;

	@SerializedName("npsn")
	private String npsn;

	public void setLayanan(String layanan){
		this.layanan = layanan;
	}

	public String getLayanan(){
		return layanan;
	}

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

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setPendekatan(String pendekatan){
		this.pendekatan = pendekatan;
	}

	public String getPendekatan(){
		return pendekatan;
	}

	public void setMasalah(String masalah){
		this.masalah = masalah;
	}

	public String getMasalah(){
		return masalah;
	}

	public void setNpsn(String npsn){
		this.npsn = npsn;
	}

	public String getNpsn(){
		return npsn;
	}
}