package com.binainsanlesatari.aksis.model.GuruModel.Nilai;

import com.google.gson.annotations.SerializedName;

public class DataMapelItem{

	@SerializedName("mapel")
	private String mapel;

	public void setMapel(String mapel){
		this.mapel = mapel;
	}

	public String getMapel(){
		return mapel;
	}
}