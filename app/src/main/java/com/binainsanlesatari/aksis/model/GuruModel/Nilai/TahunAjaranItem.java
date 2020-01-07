package com.binainsanlesatari.aksis.model.GuruModel.Nilai;

import com.google.gson.annotations.SerializedName;

public class TahunAjaranItem{

	@SerializedName("th_ajaran")
	private String thAjaran;

	@SerializedName("id")
	private String id;

	@SerializedName("status")
	private String status;

	public void setThAjaran(String thAjaran){
		this.thAjaran = thAjaran;
	}

	public String getThAjaran(){
		return thAjaran;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}