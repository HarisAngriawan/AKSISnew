package com.binainsanlesatari.aksis.model.GuruModel.Alumni;

import com.google.gson.annotations.SerializedName;

public class AlumniItem{

	@SerializedName("jumlah_lulus")
	private String jumlahLulus;

	@SerializedName("angkatan")
	private String angkatan;

	@SerializedName("negeri")
	private String negeri;

	@SerializedName("lain")
	private String lain;

	@SerializedName("id")
	private String id;

	@SerializedName("swasta")
	private String swasta;

	@SerializedName("kerja")
	private String kerja;

	@SerializedName("npsn")
	private String npsn;

	@SerializedName("status")
	private String status;

	public void setJumlahLulus(String jumlahLulus){
		this.jumlahLulus = jumlahLulus;
	}

	public String getJumlahLulus(){
		return jumlahLulus;
	}

	public void setAngkatan(String angkatan){
		this.angkatan = angkatan;
	}

	public String getAngkatan(){
		return angkatan;
	}

	public void setNegeri(String negeri){
		this.negeri = negeri;
	}

	public String getNegeri(){
		return negeri;
	}

	public void setLain(String lain){
		this.lain = lain;
	}

	public String getLain(){
		return lain;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setSwasta(String swasta){
		this.swasta = swasta;
	}

	public String getSwasta(){
		return swasta;
	}

	public void setKerja(String kerja){
		this.kerja = kerja;
	}

	public String getKerja(){
		return kerja;
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