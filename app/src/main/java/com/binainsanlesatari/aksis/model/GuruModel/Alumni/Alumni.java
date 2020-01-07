package com.binainsanlesatari.aksis.model.GuruModel.Alumni;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Alumni{

	@SerializedName("Alumni")
	private List<AlumniItem> alumni;

	public void setAlumni(List<AlumniItem> alumni){
		this.alumni = alumni;
	}

	public List<AlumniItem> getAlumni(){
		return alumni;
	}
}