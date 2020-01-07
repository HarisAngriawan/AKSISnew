package com.binainsanlesatari.aksis.model.GuruModel.UPDATEGURU;

import com.google.gson.annotations.SerializedName;

public class VerifikasiIzin{

	@SerializedName("message")
	private String message;

	@SerializedName("value")
	private String value;

	public String getMessage(){
		return message;
	}

	public String getValue(){
		return value;
	}
}