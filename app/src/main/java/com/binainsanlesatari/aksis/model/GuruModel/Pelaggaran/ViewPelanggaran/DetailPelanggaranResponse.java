package com.binainsanlesatari.aksis.model.GuruModel.Pelaggaran.ViewPelanggaran;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DetailPelanggaranResponse{

	@SerializedName("detail pelanggaran")
	private List<DetailPelanggaranItem> detailPelanggaran;

	public void setDetailPelanggaran(List<DetailPelanggaranItem> detailPelanggaran){
		this.detailPelanggaran = detailPelanggaran;
	}

	public List<DetailPelanggaranItem> getDetailPelanggaran(){
		return detailPelanggaran;
	}
}