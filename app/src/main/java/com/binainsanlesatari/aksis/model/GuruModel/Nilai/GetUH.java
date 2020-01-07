package com.binainsanlesatari.aksis.model.GuruModel.Nilai;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetUH{

	@SerializedName("Lihat Nilai")
	private List<LihatNilaiItem> lihatNilai;

	public void setLihatNilai(List<LihatNilaiItem> lihatNilai){
		this.lihatNilai = lihatNilai;
	}

	public List<LihatNilaiItem> getLihatNilai(){
		return lihatNilai;
	}
}