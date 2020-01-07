package com.binainsanlesatari.aksis.model.GuruModel.InsertGuru;

import com.google.gson.annotations.SerializedName;

public class InsertCatatanKonseling{

	@SerializedName("message")
	private String message;

	@SerializedName("value")
	private int value;

	public String getMessage(){
		return message;
	}

	public int getValue(){
		return value;
	}
}