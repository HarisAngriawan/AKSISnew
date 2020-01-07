package com.binainsanlesatari.aksis.model.SiswaModel;

import com.google.gson.annotations.SerializedName;

public class DataLaporanPelajaranItem{

	@SerializedName("materi")
	private String materi;

	@SerializedName("file")
	private String file;

	@SerializedName("th_ajaran")
	private String thAjaran;

	@SerializedName("nama")
	private String nama;

	@SerializedName("kelas")
	private String kelas;

	@SerializedName("tgl")
	private String tgl;

	@SerializedName("detail")
	private String detail;

	@SerializedName("mapel")
	private String mapel;

	@SerializedName("npsn")
	private String npsn;

	@SerializedName("status")
	private String status;

	public String getMateri(){
		return materi;
	}

	public String getFile(){
		return file;
	}

	public String getThAjaran(){
		return thAjaran;
	}

	public String getNama(){
		return nama;
	}

	public String getKelas(){
		return kelas;
	}

	public String getTgl(){
		return tgl;
	}

	public String getDetail(){
		return detail;
	}

	public String getMapel(){
		return mapel;
	}

	public String getNpsn(){
		return npsn;
	}

	public String getStatus(){
		return status;
	}
}