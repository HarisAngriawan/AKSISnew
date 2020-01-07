package com.binainsanlesatari.aksis.model.GuruModel.Login.LoginSiswa;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class LoginModelSiswa {

	@SerializedName("data")
	private List<Siswa> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

	public void setData(List<Siswa> data){
		this.data = data;
	}

	public List<Siswa> getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}