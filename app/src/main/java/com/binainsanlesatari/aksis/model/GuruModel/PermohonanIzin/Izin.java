package com.binainsanlesatari.aksis.model.GuruModel.PermohonanIzin;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Izin{

	@SerializedName("izinItems")
	private List<IzinItemsItem> izinItems;

	public List<IzinItemsItem> getIzinItems(){
		return izinItems;
	}
}