package com.example.root.farmerapp2;
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

public interface MyApi {
    @FormUrlEncoded
    @POST("/login")
    void Login(@Field("session[name]") String name , @Field("session[password]")String password, Callback<User> callback);



}