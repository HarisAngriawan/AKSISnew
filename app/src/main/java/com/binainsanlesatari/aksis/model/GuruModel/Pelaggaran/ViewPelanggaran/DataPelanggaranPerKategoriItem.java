package com.binainsanlesatari.aksis.model.GuruModel.Pelaggaran.ViewPelanggaran;

import com.google.gson.annotations.SerializedName;

public class DataPelanggaranPerKategoriItem{
	@SerializedName("deskripsi_pelanggaran")
	private String deskripsi_Pelaggaran;

	@SerializedName("jumlah")
	private String jumlah;

	@SerializedName("kategori_pelanggaran")
	private String kategoriPelanggaran;

	public void setJumlah(String jumlah){
		this.jumlah = jumlah;
	}

	public String getJumlah(){
		return jumlah;
	}

	public void setDeskripsi_Pelaggaran(String deskripsi_Pelaggaran) {
		this.deskripsi_Pelaggaran = deskripsi_Pelaggaran;
	}

	public String getDeskripsi_Pelaggaran() {
		return deskripsi_Pelaggaran;
	}

	public void setKategoriPelanggaran(String kategoriPelanggaran){
		this.kategoriPelanggaran = kategoriPelanggaran;
	}

	public String getKategoriPelanggaran(){
		return kategoriPelanggaran;
	}
}