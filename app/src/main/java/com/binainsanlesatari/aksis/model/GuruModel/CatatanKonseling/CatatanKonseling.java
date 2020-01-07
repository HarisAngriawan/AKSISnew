package com.binainsanlesatari.aksis.model.GuruModel.CatatanKonseling;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CatatanKonseling{

	@SerializedName("Konseling")
	private List<KonselingItem> konseling;

	public void setKonseling(List<KonselingItem> konseling){
		this.konseling = konseling;
	}

	public List<KonselingItem> getKonseling(){
		return konseling;
	}
}