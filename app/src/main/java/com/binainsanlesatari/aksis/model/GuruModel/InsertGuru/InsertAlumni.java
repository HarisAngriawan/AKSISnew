package com.binainsanlesatari.aksis.model.GuruModel.InsertGuru;

import com.google.gson.annotations.SerializedName;

public class InsertAlumni{

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

	@Override
 	public String toString(){
		return 
			"InsertAlumni{" + 
			"message = '" + message + '\'' + 
			",value = '" + value + '\'' + 
			"}";
		}
}