package com.integro.bsk.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CollaborationList {

    @SerializedName("collaboration")
    private ArrayList<Collaboration>collaborationArrayList;

    private String success;
    private String message;

    public ArrayList<Collaboration> getCollaborationArrayList() {
        return collaborationArrayList;
    }

    public void setCollaboration (ArrayList<Collaboration> collaborationArrayList)
    {
        this.collaborationArrayList = collaborationArrayList;
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
