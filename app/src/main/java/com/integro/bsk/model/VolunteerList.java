package com.integro.bsk.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class VolunteerList {
    @SerializedName("volunteer")

    private ArrayList<Volunteer>volunteerArrayList;
    private String success;
    private String message;

    public ArrayList<Volunteer> getVolunteerArrayList() {
        return volunteerArrayList;
    }

    public void setVolunteer (ArrayList<Volunteer>volunteerArrayList)
    {
        this.volunteerArrayList = volunteerArrayList;
    }

    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
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
