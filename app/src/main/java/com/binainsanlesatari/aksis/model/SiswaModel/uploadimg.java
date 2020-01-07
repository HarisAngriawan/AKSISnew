package com.binainsanlesatari.aksis.model.SiswaModel;

import com.google.gson.annotations.SerializedName;

public class uploadimg {
    @SerializedName("message")
    private String message;

    @SerializedName("path")
    private String path;

    @SerializedName("value")
    private int value;

    public String getMessage(){
        return message;
    }
    public String getPath(){
        return path;
    }
    public int getValue(){
        return value;
    }
}
