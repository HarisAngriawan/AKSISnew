package com.binainsanlesatari.aksis.model.GuruModel.LaporanPelajaran;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class LaporanPelajaran{

	@SerializedName("Data Laporan Pelajaran")
	private List<DataLaporanPelajaranItem> dataLaporanPelajaran;

	public List<DataLaporanPelajaranItem> getDataLaporanPelajaran(){
		return dataLaporanPelajaran;
	}
}