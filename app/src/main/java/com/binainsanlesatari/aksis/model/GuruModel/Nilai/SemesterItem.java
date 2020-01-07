package com.binainsanlesatari.aksis.model.GuruModel.Nilai;

import com.google.gson.annotations.SerializedName;

public class SemesterItem{

	@SerializedName("semester")
	private String semester;

	@SerializedName("id")
	private String id;

	public void setSemester(String semester){
		this.semester = semester;
	}

	public String getSemester(){
		return semester;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}
}