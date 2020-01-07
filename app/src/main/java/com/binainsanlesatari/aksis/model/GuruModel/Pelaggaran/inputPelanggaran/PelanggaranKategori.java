package com.binainsanlesatari.aksis.model.GuruModel.Pelaggaran.inputPelanggaran;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PelanggaranKategori{

	@SerializedName("list kategori")
	private List<PelanggaranItem> listKategori;

	public List<PelanggaranItem> getListKategori(){
		return listKategori;
	}
}