package com.binainsanlesatari.aksis.model.GuruModel.Pelaggaran.inputPelanggaran;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DeskripsiPelanggaran{

	@SerializedName("Deskripsi Pelanggaran")
	private List<DeskripsiPelanggaranItem> deskripsiPelanggaran;

	public List<DeskripsiPelanggaranItem> getDeskripsiPelanggaran(){
		return deskripsiPelanggaran;
	}
}