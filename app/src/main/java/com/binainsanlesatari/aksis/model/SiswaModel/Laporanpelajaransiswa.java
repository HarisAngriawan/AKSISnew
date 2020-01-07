package com.binainsanlesatari.aksis.model.SiswaModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Laporanpelajaransiswa{

	@SerializedName("Data Laporan Pelajaran")
	private List<DataLaporanPelajaranItem> dataLaporanPelajaran;

	public List<DataLaporanPelajaranItem> getDataLaporanPelajaran(){
		return dataLaporanPelajaran;
	}
}