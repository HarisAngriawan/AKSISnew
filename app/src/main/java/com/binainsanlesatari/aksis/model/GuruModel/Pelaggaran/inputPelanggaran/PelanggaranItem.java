package com.binainsanlesatari.aksis.model.GuruModel.Pelaggaran.inputPelanggaran;

import com.google.gson.annotations.SerializedName;

public class PelanggaranItem {

	@SerializedName("idd")
	private String idd;

	@SerializedName("id")
	private String id;

	@SerializedName("npsn")
	private String npsn;

	@SerializedName("kategori_pelanggaran")
	private String kategoriPelanggaran;

	@SerializedName("status")
	private String status;

	public String getIdd(){
		return idd;
	}

	public String getId(){
		return id;
	}

	public String getNpsn(){
		return npsn;
	}

	public String getKategoriPelanggaran(){
		return kategoriPelanggaran;
	}

	public String getStatus(){
		return status;
	}
}