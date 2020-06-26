package com.integro.bsk.model;

import java.io.Serializable;

public class NewsImages implements Serializable {

    private String images;
    private String updated_at;
    private String id;
    private String news_id;

    public String getImages ()
    {
        return images;
    }

    public void setImages (String images)
    {
        this.images = images;
    }

    public String getUpdated_at ()
    {
        return updated_at;
    }

    public void setUpdated_at (String updated_at)
    {
        this.updated_at = updated_at;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getNews_id ()
    {
        return news_id;
    }

    public void setNews_id (String news_id)
    {
        this.news_id = news_id;
    }

}
