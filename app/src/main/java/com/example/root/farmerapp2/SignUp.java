package com.example.root.farmerapp2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import models.User;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by root on 26/03/16.
 */
public class SignUp extends Activity{

EditText phoneSignup;
EditText nameSignup;
ImageView engineerImg;
ImageView farmerImg;
Boolean clicked;
    String status;
    String registeration_id;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        clicked=false;

        nameSignup = (EditText) findViewById(R.id.editNameSignup);
        phoneSignup = (EditText) findViewById(R.id.editPhoneSignup);
        engineerImg =(ImageView) findViewById(R.id.engineerImg);
        farmerImg = (ImageView) findViewById(R.id.farmerImg);
        final Button submit_signup = (Button) findViewById(R.id.submit_signup);

        String myStringValue = PreferenceManager.getDefaultSharedPreferences(SignUp.this).getString("registerId", "defaultStringIfNothingFound");
        Log.d("Registeration id",myStringValue);

        final List <User> UserAccounts = null;
        submit_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getname = nameSignup.getText().toString();
                String getphone = phoneSignup.getText().toString();

                farmerImg.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                    status="farmer";
                    }
                });
                engineerImg.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        status="engineer";
                    }
                });


                RestAdapter adapter = new RestAdapter.Builder().setEndpoint(("http://192.168.1.109:3000/")).build();
                MyApi api = adapter.create(MyApi.class);
                api.SignUp(getname, getphone, getphone,status, registeration_id, new Callback<User>()

                {

                    public void success(User user, Response response) {
                       // Log.d("Useris:",status);
                        if (status=="farmer") {
                            Intent intent = new Intent(getApplicationContext(), LoginFarmer.class);
                            startActivity(intent);
                        }
                        else if(status=="engineer") {
                            Intent intent = new Intent(getApplicationContext(), LoginEngineer.class);
                            startActivity(intent);
                        }

                    }

                    public void failure(RetrofitError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }




    }
