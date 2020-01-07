package com.binainsanlesatari.aksis.model.GuruModel.Nilai;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class GetTugas{

	@SerializedName("Nilai Tugas")
	private List<NilaiTugasItem> nilaiTugas;

	public List<NilaiTugasItem> getNilaiTugas(){
		return nilaiTugas;
	}
}