package com.binainsanlesatari.aksis.model.GuruModel.LaporanPelajaran;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataKelas{

	@SerializedName("dataKelas")
	private List<DataKelasItem> dataKelas;

	public List<DataKelasItem> getDataKelas(){
		return dataKelas;
	}
}