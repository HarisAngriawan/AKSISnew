package com.binainsanlesatari.aksis.model.GuruModel.Ketidakhadiran;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DataTidakHadir{

	@SerializedName("Data Tidak Hadir")
	private List<DataTidakHadirItem> dataTidakHadir;

	public List<DataTidakHadirItem> getDataTidakHadir(){
		return dataTidakHadir;
	}
}