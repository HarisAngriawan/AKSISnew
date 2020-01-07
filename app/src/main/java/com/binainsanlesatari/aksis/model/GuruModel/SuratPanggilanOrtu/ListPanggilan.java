package com.binainsanlesatari.aksis.model.GuruModel.SuratPanggilanOrtu;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListPanggilan{

	@SerializedName("ListPanggilanOrtu")
	private List<ListPanggilanOrtuItem> listPanggilanOrtu;

	public List<ListPanggilanOrtuItem> getListPanggilanOrtu(){
		return listPanggilanOrtu;
	}
}