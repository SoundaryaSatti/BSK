package com.integro.bsk.api;

import com.integro.bsk.model.AboutUsList;
import com.integro.bsk.model.ActivitiesList;
import com.integro.bsk.model.CollaborationList;
import com.integro.bsk.model.InitiativesList;
import com.integro.bsk.model.NewsImagesList;
import com.integro.bsk.model.NewsLetterList;
import com.integro.bsk.model.NewsList;
import com.integro.bsk.model.NotificationList;
import com.integro.bsk.model.VolunteerList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiServices {

    @FormUrlEncoded
    @POST("bosco_news.php")
    Call<NewsList> getNewsList(@Field("updated_at")String updated_at);

    @FormUrlEncoded
    @POST("bosco_notification.php")
    Call<NotificationList> getNotificationList(@Field("updated_at")String updated_at);

    @FormUrlEncoded
    @POST("bosco_newsletter.php")
    Call<NewsLetterList> getNewsLetterList(@Field("Updated_at")String updated_at);

   @FormUrlEncoded
    @POST("bosco_about.php")
    Call<AboutUsList> getAbouUsList(@Field("Updated_at") String updated_at);

    @FormUrlEncoded
    @POST("bosco_activities.php")
    Call<ActivitiesList> getActivitiesList(@Field("Updated_at") String updated_at);

    @FormUrlEncoded
    @POST("bosco_volunteer.php")
    Call<VolunteerList> getVolunteerList(@Field("Updated_at") String updated_at);

    @FormUrlEncoded
    @POST("bosco_collaboration.php")
    Call<CollaborationList> getCollaborationList(@Field("Updated_at") String updated_at);


    @FormUrlEncoded
    @POST("bosco_initiatives.php")
    Call<InitiativesList>getInitiativesList(@Field("Updated_at")String updated_at);

    @FormUrlEncoded
    @POST("bosco_newsimages.php")
    Call<NewsImagesList> getNewsImagesList(@Field("news_id") String itemId);

}

