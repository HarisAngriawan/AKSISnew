package com.binainsanlesatari.aksis.model.GuruModel.Rating;

import com.google.gson.annotations.SerializedName;

public class RatingItem{

	@SerializedName("nama")
	private String nama;

	@SerializedName("jumlah")
	private String jumlah;

	@SerializedName("id_guru")
	private String idGuru;

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

	public void setIdGuru(String idGuru){
		this.idGuru = idGuru;
	}

	public String getIdGuru(){
		return idGuru;
	}
}