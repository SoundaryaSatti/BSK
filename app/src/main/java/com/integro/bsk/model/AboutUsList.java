package com.integro.bsk.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AboutUsList {

    @SerializedName("aboutus")
private ArrayList<AboutUs>aboutUsArrayList;
    private int success;

    private String message;

    public ArrayList<AboutUs> getAboutUsArrayList ()
    {
        return aboutUsArrayList;
    }

    public void setAboutus (ArrayList<AboutUs> aboutUsArrayList)
    {
        this.aboutUsArrayList = aboutUsArrayList;
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
