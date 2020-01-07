package com.binainsanlesatari.aksis.model.GuruModel.Nilai;

import com.google.gson.annotations.SerializedName;

public class NilaiUTSItem{

	@SerializedName("nisn")
	private String nisn;

	@SerializedName("nilai")
	private String nilai;

	@SerializedName("nama_lengkap")
	private String namaLengkap;

	public String getNisn(){
		return nisn;
	}

	public String getNilai(){
		return nilai;
	}

	public String getNamaLengkap(){
		return namaLengkap;
	}
}