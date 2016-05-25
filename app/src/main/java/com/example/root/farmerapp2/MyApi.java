package com.example.root.farmerapp2;
import com.google.gson.annotations.SerializedName;

import java.util.List;


import models.Advice;
import models.Problem;
import models.Reply;
import models.User;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;


import retrofit.Callback;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.mime.TypedFile;
//import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by root on 17/03/16.
 */
class AccessToken { @SerializedName("access_token") private String accessToken; }

public interface MyApi {
    public static final String BASE_URL = "";
    @FormUrlEncoded
    @POST("/login")
    void Login(@Field("session[name]") String name , @Field("session[password]")String password, Callback<User> callback);
    @FormUrlEncoded
    @POST("/users/")
void SignUp(@Field("user[name]") String name, @Field("user[password]") String password, @Field("user[password_confirmation]") String password_confirmation, @Field("user[status]") String status, @Field("user[registeration_id]") String registeration_id, Callback<User> callback);
//@POST("/problems")
//void Problem(@Field(p))
@FormUrlEncoded
@POST("/imgupload")
void uploadImage(@Field("problem[photo]") String photo, @Field("problem[title]") String title, Callback<Problem> cb);
@GET("/problems/get_photo")
void storeImage(@Path("id") int id ,Callback<Problem> cb);
@FormUrlEncoded
@POST("/problems/{user_id}")
void postProblem(@Field("problem[photo]") String photo , @Field("problem[title]")String title,@Field("problem[audio]") String audio,@Field("problem[user_id]") int user_id, Callback<Problem> callback);
@GET("/problems")
void getAllProblems(Callback<List<models.Problem>> callback);
@FormUrlEncoded
@POST("/replyCreate")
void createReply(@Field("reply[photo]") String photo ,@Field("reply[audio]") String audio,@Field("reply[problem_id]") int problem_id, Callback<Reply> callback);
@GET("/replies/{problem_id}")
void getAllReplies(@Path("problem_id") int problem_id,Callback<List<models.Reply>> callback);
@FormUrlEncoded
@POST("/adviceCreate")
void postAdvice(@Field("advice[photo]") String photo,@Field("advice[audio]") String audio,Callback<Advice> callback);
@GET("/farmerNotificationCreate")
void getAllFarmerNotify(Callback<List<models.Farmerlist>> callback);
@GET("/engineerNotificationCreate")
void getAllEngineerNotify(Callback<List<models.Engineerlist>> callback);

}