package com.binainsanlesatari.aksis.model.SiswaModel.PelanggaranSiswa;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DetailPelanggaran{

	@SerializedName("DetaailPelanggaran")
	private List<DetaailPelanggaranItem> detaailPelanggaran;

	public List<DetaailPelanggaranItem> getDetaailPelanggaran(){
		return detaailPelanggaran;
	}
}