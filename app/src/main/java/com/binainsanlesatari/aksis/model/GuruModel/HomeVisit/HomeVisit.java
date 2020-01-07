package com.binainsanlesatari.aksis.model.GuruModel.HomeVisit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeVisit{

	@SerializedName("HomeVisit")
	private List<HomeVisitItem> homeVisit;

	public void setHomeVisit(List<HomeVisitItem> homeVisit){
		this.homeVisit = homeVisit;
	}

	public List<HomeVisitItem> getHomeVisit(){
		return homeVisit;
	}
}