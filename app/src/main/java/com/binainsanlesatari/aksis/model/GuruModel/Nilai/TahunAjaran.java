package com.binainsanlesatari.aksis.model.GuruModel.Nilai;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TahunAjaran{

	@SerializedName("TahunAjaran")
	private List<TahunAjaranItem> tahunAjaran;

	public void setTahunAjaran(List<TahunAjaranItem> tahunAjaran){
		this.tahunAjaran = tahunAjaran;
	}

	public List<TahunAjaranItem> getTahunAjaran(){
		return tahunAjaran;
	}
}