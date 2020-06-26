package com.integro.bsk.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NewsList {
    @SerializedName("news")
    private ArrayList<News> newsArrayList;
    @SerializedName("news_images")
    private ArrayList<NewsImages> newsImagesArrayList;

    private int success;

    private String message;

    public ArrayList<News> getNewsArrayList() {
        return newsArrayList;
    }

    public void setNewsArrayList(ArrayList<News> newsArrayList) {
        this.newsArrayList = newsArrayList;
    }

    public ArrayList<NewsImages> getNewsImagesArrayList() {
        return newsImagesArrayList;
    }

    public void setNewsImagesArrayList(ArrayList<NewsImages> newsImagesArrayList) {
        this.newsImagesArrayList = newsImagesArrayList;
    }

    public int getSuccess()
    { return success;
    }

    public void setSuccess(int success)
    {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
