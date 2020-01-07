package com.binainsanlesatari.aksis.model.GuruModel.Ketidakhadiran;

import com.google.gson.annotations.SerializedName;

public class DataTidakHadirItem{

	@SerializedName("nama")
	private String nama;

	@SerializedName("nama_kelas")
	private String namaKelas;

	@SerializedName("nisn")
	private String nisn;

	@SerializedName("kelas")
	private String kelas;

	@SerializedName("tgl")
	private String tgl;

	@SerializedName("id")
	private String id;

	@SerializedName("npsn")
	private String npsn;

	@SerializedName("alasan")
	private String alasan;

	@SerializedName("id_wali_kelas")
	private String idWaliKelas;

	public String getNama(){
		return nama;
	}

	public String getNamaKelas(){
		return namaKelas;
	}

	public String getNisn(){
		return nisn;
	}

	public String getKelas(){
		return kelas;
	}

	public String getTgl(){
		return tgl;
	}

	public String getId(){
		return id;
	}

	public String getNpsn(){
		return npsn;
	}

	public String getAlasan(){
		return alasan;
	}

	public String getIdWaliKelas(){
		return idWaliKelas;
	}
}