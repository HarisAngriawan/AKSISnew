package com.binainsanlesatari.aksis.model.GuruModel.ViewSiswa;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class InsertSiswa{

	@SerializedName("Insert Siswa")
	private List<InsertSiswaItem> insertSiswa;

	public List<InsertSiswaItem> getInsertSiswa(){
		return insertSiswa;
	}
}