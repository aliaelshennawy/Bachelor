package com.example.root.farmerapp2;
import com.google.gson.annotations.SerializedName;

import java.util.List;


import models.User;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;


import retrofit.Callback;
//import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by root on 17/03/16.
 */
class AccessToken { @SerializedName("access_token") private String accessToken; }

public interface MyApi {
    @FormUrlEncoded
    @POST("/login")
    void Login(@Field("session[name]") String name , @Field("session[password]")String password, Callback<User> callback);



}