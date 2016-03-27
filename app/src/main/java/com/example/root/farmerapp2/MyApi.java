package com.example.root.farmerapp2;
import com.google.gson.annotations.SerializedName;

import java.util.List;


import models.User;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
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
    @FormUrlEncoded
    @POST("/users/")
    void SignUp(@Field("user[name]") String name , @Field("user[password]")String password,@Field("user[password_confirmation]")String password_confirmation, Callback<User> callback);



}