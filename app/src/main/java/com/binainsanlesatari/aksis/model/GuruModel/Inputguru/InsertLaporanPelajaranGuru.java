package com.binainsanlesatari.aksis.model.GuruModel.Inputguru;

import com.google.gson.annotations.SerializedName;

public class InsertLaporanPelajaranGuru{

	@SerializedName("message")
	private String message;

	@SerializedName("value")
	private boolean value;

	public String getMessage(){
		return message;
	}

	public boolean isValue(){
		return value;
	}
}