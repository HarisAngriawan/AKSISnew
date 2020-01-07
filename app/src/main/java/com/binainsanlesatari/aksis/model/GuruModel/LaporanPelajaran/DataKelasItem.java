package com.binainsanlesatari.aksis.model.GuruModel.LaporanPelajaran;

import com.google.gson.annotations.SerializedName;

public class DataKelasItem{

	@SerializedName("nama_kelas")
	private String namaKelas;

	@SerializedName("th_ajaran")
	private String thAjaran;

	@SerializedName("id_kelas_diajar")
	private String idKelasDiajar;

	@SerializedName("id")
	private String id;

	@SerializedName("id_guru")
	private String idGuru;

	@SerializedName("npsn")
	private String npsn;

	@SerializedName("status")
	private String status;

	public String getNamaKelas(){
		return namaKelas;
	}

	public String getThAjaran(){
		return thAjaran;
	}

	public String getIdKelasDiajar(){
		return idKelasDiajar;
	}

	public String getId(){
		return id;
	}

	public String getIdGuru(){
		return idGuru;
	}

	public String getNpsn(){
		return npsn;
	}

	public String getStatus(){
		return status;
	}
}