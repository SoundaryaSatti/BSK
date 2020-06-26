package com.integro.bsk.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NewsLetterList {

    @SerializedName("newsletter")

    private ArrayList<NewsLetter>newsLetterArrayList;

    private String success;

    private String message;

    public ArrayList<NewsLetter>getNewsLetterArrayList()
    {
        return newsLetterArrayList;
    }

    public void setNewsLetterArrayList(ArrayList<NewsLetter>newsLetterArrayList)
    {
        this.newsLetterArrayList = newsLetterArrayList;
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
