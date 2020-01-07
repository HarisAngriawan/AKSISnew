package com.binainsanlesatari.aksis.model.GuruModel.Nilai;

import com.google.gson.annotations.SerializedName;

public class NilaiTugasItem{

	@SerializedName("n_uh_1")
	private String nUh1;

	@SerializedName("n_uh_2")
	private String nUh2;

	@SerializedName("uh_3")
	private String uh3;

	@SerializedName("nisn")
	private String nisn;

	@SerializedName("nama_lengkap")
	private String namaLengkap;

	public String getNUh1(){
		return nUh1;
	}

	public String getNUh2(){
		return nUh2;
	}

	public String getUh3(){
		return uh3;
	}

	public String getNisn(){
		return nisn;
	}

	public String getNamaLengkap(){
		return namaLengkap;
	}
}