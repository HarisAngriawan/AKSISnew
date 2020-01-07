package com.binainsanlesatari.aksis.model.SiswaModel;


import com.google.gson.annotations.SerializedName;

public class InputPermohonan {
    @SerializedName("message")
    private String message;

    @SerializedName("value")
    private int value;

    public String getMessage(){
        return message;
    }

    public int getValue(){
        return value;
    }
}
