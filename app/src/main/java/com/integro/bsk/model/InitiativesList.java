package com.integro.bsk.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class InitiativesList {

    @SerializedName("initiatives")
    private ArrayList<Initiatives>initiativesArrayList;

    private int success;
    private String message;

    public ArrayList<Initiatives> getInitiativesArrayList() {
        return initiativesArrayList;
    }

    public void setInitiatives (ArrayList<Initiatives>initiativesArrayList)
    {
        this.initiativesArrayList = initiativesArrayList;
    }

    public int getSuccess ()
    {
        return success;
    }

    public void setSuccess (int success)
    {
        this.success = success;
    }

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

}
