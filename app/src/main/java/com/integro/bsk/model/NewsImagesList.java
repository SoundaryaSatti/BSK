package com.integro.bsk.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NewsImagesList
{
@SerializedName("news_images")

private ArrayList<NewsImages>newsImagesArrayList;

    private String success;
    private String message;

    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
    {
        this.success = success;
    }

    public ArrayList<NewsImages> getNewsImagesArrayList() {
        return newsImagesArrayList;
    }


    public void setNews_images (ArrayList<NewsImages>newsImagesArrayList)
    {
        this.newsImagesArrayList = newsImagesArrayList;
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
