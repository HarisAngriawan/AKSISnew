package com.binainsanlesatari.aksis.model.GuruModel.Pelaggaran.ViewPelanggaran;

import com.google.gson.annotations.SerializedName;

public class DetailPelanggaranItem{

	@SerializedName("tgl_pelanggaran")
	private String tglPelanggaran;

	@SerializedName("jam_pelanggaran")
	private String jamPelanggaran;

	@SerializedName("nisn")
	private String nisn;

	@SerializedName("kelas")
	private String kelas;

	@SerializedName("nama_lengkap")
	private String namaLengkap;

	@SerializedName("kategori_pelanggaran")
	private String kategoriPelanggaran;

	@SerializedName("deskripsi_pelanggaran")
	private String deskripsiPelanggaran;

	public void setTglPelanggaran(String tglPelanggaran){
		this.tglPelanggaran = tglPelanggaran;
	}

	public String getTglPelanggaran(){
		return tglPelanggaran;
	}

	public void setKategoriPelanggaran(String kategoriPelanggaran) {
		this.kategoriPelanggaran = kategoriPelanggaran;
	}

	public String getKategoriPelanggaran() {
		return kategoriPelanggaran;
	}

	public void setJamPelanggaran(String jamPelanggaran){
		this.jamPelanggaran = jamPelanggaran;
	}

	public String getJamPelanggaran(){
		return jamPelanggaran;
	}

	public void setNisn(String nisn){
		this.nisn = nisn;
	}

	public String getNisn(){
		return nisn;
	}

	public void setKelas(String kelas){
		this.kelas = kelas;
	}

	public String getKelas(){
		return kelas;
	}

	public void setNamaLengkap(String namaLengkap){
		this.namaLengkap = namaLengkap;
	}

	public String getNamaLengkap(){
		return namaLengkap;
	}

	public void setDeskripsiPelanggaran(String deskripsiPelanggaran){
		this.deskripsiPelanggaran = deskripsiPelanggaran;
	}

	public String getDeskripsiPelanggaran(){
		return deskripsiPelanggaran;
	}
}