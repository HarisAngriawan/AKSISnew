package com.binainsanlesatari.aksis.model.GuruModel.Login.LoginGuru;

import com.google.gson.annotations.SerializedName;

public class Guru {

	@SerializedName("jk")
	private String jk;

	@SerializedName("wali_kelas")
	private String waliKelas;

	@SerializedName("no_hp")
	private String noHp;

	@SerializedName("pass")
	private String pass;

	@SerializedName("npsn")
	private String npsn;

	@SerializedName("mapel")
	private String mapel;

	@SerializedName("alamat")
	private String alamat;

	@SerializedName("nama")
	private String nama;

	@SerializedName("nip")
	private String nip;

	@SerializedName("foto")
	private String foto;

	@SerializedName("piket")
	private String piket;

	@SerializedName("status_aktif")
	private String statusAktif;

	@SerializedName("id")
	private String id;

	@SerializedName("id_pengguna")
	private String idPengguna;

	@SerializedName("status")
	private String status;

	public String getJk(){
		return jk;
	}

	public String getWaliKelas(){
		return waliKelas;
	}

	public String getNoHp(){
		return noHp;
	}

	public String getPass(){
		return pass;
	}

	public String getNpsn(){
		return npsn;
	}

	public String getMapel(){
		return mapel;
	}

	public String getAlamat(){
		return alamat;
	}

	public String getNama(){
		return nama;
	}

	public String getNip(){
		return nip;
	}

	public String getFoto(){
		return foto;
	}

	public String getPiket(){
		return piket;
	}

	public String getStatusAktif(){
		return statusAktif;
	}

	public String getId(){
		return id;
	}

	public String getIdPengguna(){
		return idPengguna;
	}

	public String getStatus(){
		return status;
	}
}