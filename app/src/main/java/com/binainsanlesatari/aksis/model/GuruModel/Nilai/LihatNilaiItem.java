package com.binainsanlesatari.aksis.model.GuruModel.Nilai;

import com.google.gson.annotations.SerializedName;

public class LihatNilaiItem{

	@SerializedName("n_uh_1")
	private String nUh1 ;

	@SerializedName("n_uh_2")
	private String nUh2 ;

	@SerializedName("uh_3")
	private String uh3 ;

	@SerializedName("nisn")
	private String nisn;

	@SerializedName("nama_lengkap")
	private String namaLengkap;

	public void setNUh1(String nUh1){
		this.nUh1 = nUh1;
	}

	public String getNUh1(){
		return nUh1;
	}

	public void setNUh2(String nUh2){
		this.nUh2 = nUh2;
	}

	public String getNUh2(){
		return nUh2;
	}

	public void setUh3(String uh3){
		this.uh3 = uh3;
	}

	public String getUh3(){
		return uh3;
	}

	public void setNisn(String nisn){
		this.nisn = nisn;
	}

	public String getNisn(){
		return nisn;
	}

	public void setNamaLengkap(String namaLengkap){
		this.namaLengkap = namaLengkap;
	}

	public String getNamaLengkap(){
		return namaLengkap;
	}
}