package com.binainsanlesatari.aksis.model.GuruModel.SuratPanggilanOrtu;

import com.google.gson.annotations.SerializedName;

public class ListPanggilanOrtuItem{

	@SerializedName("nama")
	private String nama;

	@SerializedName("nisn")
	private String nisn;

	@SerializedName("surat")
	private String surat;

	@SerializedName("kasus")
	private String kasus;

	@SerializedName("id")
	private String id;

	@SerializedName("npsn")
	private String npsn;

	@SerializedName("status")
	private String status;

	public String getNama(){
		return nama;
	}

	public String getNisn(){
		return nisn;
	}

	public String getSurat(){
		return surat;
	}

	public String getKasus(){
		return kasus;
	}

	public String getId(){
		return id;
	}

	public String getNpsn(){
		return npsn;
	}

	public String getStatus(){
		return status;
	}
}