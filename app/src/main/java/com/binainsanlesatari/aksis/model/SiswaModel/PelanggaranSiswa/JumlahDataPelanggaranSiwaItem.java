package com.binainsanlesatari.aksis.model.SiswaModel.PelanggaranSiswa;

import com.google.gson.annotations.SerializedName;

public class JumlahDataPelanggaranSiwaItem{

	@SerializedName("pelanggaran")
	private String pelanggaran;

	@SerializedName("jumlah")
	private String jumlah;

	@SerializedName("kategori_pelanggaran")
	private String kategoriPelanggaran;

	public String getPelanggaran(){
		return pelanggaran;
	}

	public String getJumlah(){
		return jumlah;
	}

	public String getKategoriPelanggaran(){
		return kategoriPelanggaran;
	}
}