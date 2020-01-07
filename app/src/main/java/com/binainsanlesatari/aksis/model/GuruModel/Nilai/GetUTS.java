package com.binainsanlesatari.aksis.model.GuruModel.Nilai;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class GetUTS{

	@SerializedName("Nilai UTS")
	private List<NilaiUTSItem> nilaiUTS;

	public List<NilaiUTSItem> getNilaiUTS(){
		return nilaiUTS;
	}
}