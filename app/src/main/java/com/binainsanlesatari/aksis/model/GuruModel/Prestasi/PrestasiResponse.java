package com.binainsanlesatari.aksis.model.GuruModel.Prestasi;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PrestasiResponse{

	@SerializedName("data Prestasi")
	private List<DataPrestasiItem> dataPrestasi;

	public void setDataPrestasi(List<DataPrestasiItem> dataPrestasi){
		this.dataPrestasi = dataPrestasi;
	}

	public List<DataPrestasiItem> getDataPrestasi(){
		return dataPrestasi;
	}
}