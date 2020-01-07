package com.binainsanlesatari.aksis.model.GuruModel.Nilai;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class MapelResponse{

	@SerializedName("data_mapel")
	private List<DataMapelItem> dataMapel;

	public void setDataMapel(List<DataMapelItem> dataMapel){
		this.dataMapel = dataMapel;
	}

	public List<DataMapelItem> getDataMapel(){
		return dataMapel;
	}
}