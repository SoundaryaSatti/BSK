package com.integro.bsk.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ActivitiesList {
    @SerializedName("activities")
    private ArrayList<Activities>activitiesArrayList;

    private String success;
    private String message;

    public  ArrayList<Activities>getActivitiesArrayList()
    {
        return activitiesArrayList;
    }

    public void setActivities (ArrayList<Activities>activitiesArrayList)
    {
        this.activitiesArrayList = activitiesArrayList;
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
