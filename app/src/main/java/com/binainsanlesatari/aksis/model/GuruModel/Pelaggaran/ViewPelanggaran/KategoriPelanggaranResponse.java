package com.binainsanlesatari.aksis.model.GuruModel.Pelaggaran.ViewPelanggaran;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class KategoriPelanggaranResponse{

	@SerializedName("data pelanggaran per-kategori")
	private List<DataPelanggaranPerKategoriItem> dataPelanggaranPerKategori;

	public void setDataPelanggaranPerKategori(List<DataPelanggaranPerKategoriItem> dataPelanggaranPerKategori){
		this.dataPelanggaranPerKategori = dataPelanggaranPerKategori;
	}

	public List<DataPelanggaranPerKategoriItem> getDataPelanggaranPerKategori(){
		return dataPelanggaranPerKategori;
	}
}