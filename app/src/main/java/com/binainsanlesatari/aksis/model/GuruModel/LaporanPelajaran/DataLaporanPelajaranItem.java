package com.binainsanlesatari.aksis.model.GuruModel.LaporanPelajaran;

import com.google.gson.annotations.SerializedName;

public class DataLaporanPelajaranItem{

	@SerializedName("th_ajaran")
	private String thAjaran;

	@SerializedName("id_guru")
	private String idGuru;

	@SerializedName("nomor_kelas")
	private String nomorKelas;

	@SerializedName("mapel")
	private String mapel;

	@SerializedName("npsn")
	private String npsn;

	@SerializedName("materi")
	private String materi;

	@SerializedName("file")
	private String file;

	@SerializedName("nama")
	private String nama;

	@SerializedName("kelas")
	private String kelas;

	@SerializedName("nama_kelas_jurusan")
	private String namaKelasJurusan;

	@SerializedName("tgl")
	private String tgl;

	@SerializedName("detail")
	private String detail;

	@SerializedName("status")
	private String status;

	public String getThAjaran(){
		return thAjaran;
	}

	public String getIdGuru(){
		return idGuru;
	}

	public String getNomorKelas(){
		return nomorKelas;
	}

	public String getMapel(){
		return mapel;
	}

	public String getNpsn(){
		return npsn;
	}

	public String getMateri(){
		return materi;
	}

	public String getFile(){
		return file;
	}

	public String getNama(){
		return nama;
	}

	public String getKelas(){
		return kelas;
	}

	public String getNamaKelasJurusan(){
		return namaKelasJurusan;
	}

	public String getTgl(){
		return tgl;
	}

	public String getDetail(){
		return detail;
	}

	public String getStatus(){
		return status;
	}
}