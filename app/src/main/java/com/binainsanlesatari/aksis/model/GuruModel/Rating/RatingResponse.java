package com.binainsanlesatari.aksis.model.GuruModel.Rating;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class RatingResponse{

	@SerializedName("Rating")
	private List<RatingItem> rating;

	public void setRating(List<RatingItem> rating){
		this.rating = rating;
	}

	public List<RatingItem> getRating(){
		return rating;
	}
}