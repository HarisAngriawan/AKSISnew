package com.binainsanlesatari.aksis.model.GuruModel.ViewSiswa;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Siswa{

	@SerializedName("siswa")
	private List<SiswaItem> siswa;

	public List<SiswaItem> getSiswa(){
		return siswa;
	}
}