package com.binainsanlesatari.aksis.model.GuruModel.Nilai;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Semester{

	@SerializedName("Semester")
	private List<SemesterItem> semester;

	public void setSemester(List<SemesterItem> semester){
		this.semester = semester;
	}

	public List<SemesterItem> getSemester(){
		return semester;
	}
}