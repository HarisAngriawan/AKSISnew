package com.binainsanlesatari.aksis.model.GuruModel.Kehadiran;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KehadiranPerKelas{

	@SerializedName("data kehadiran")
	private List<DataKehadiranItem> dataKehadiran;

	public void setDataKehadiran(List<DataKehadiranItem> dataKehadiran){
		this.dataKehadiran = dataKehadiran;
	}

	public List<DataKehadiranItem> getDataKehadiran(){
		return dataKehadiran;
	}
}