package com.binainsanlesatari.aksis.model.GuruModel.Nilai;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class GetUAS{

	@SerializedName("Nilai UAS")
	private List<NilaiUASItem> nilaiUAS;

	public List<NilaiUASItem> getNilaiUAS(){
		return nilaiUAS;
	}
}