package com.binainsanlesatari.aksis.model.SiswaModel.PelanggaranSiswa;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class JumlahPelanggaran{

	@SerializedName("JumlahDataPelanggaranSiwa")
	private List<JumlahDataPelanggaranSiwaItem> jumlahDataPelanggaranSiwa;

	public List<JumlahDataPelanggaranSiwaItem> getJumlahDataPelanggaranSiwa(){
		return jumlahDataPelanggaranSiwa;
	}
}