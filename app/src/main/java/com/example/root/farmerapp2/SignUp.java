package com.example.root.farmerapp2;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
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
    EditText phoneSignup2;

EditText phoneSignup;
EditText nameSignup;
ImageView engineerImg;
ImageView farmerImg;
Boolean clicked;
    String status;
    ImageView signupCommand;
    String registeration_id;
    SeekBar seekBarSignup;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        clicked=false;

        nameSignup = (EditText) findViewById(R.id.editNameSignup);
        phoneSignup = (EditText) findViewById(R.id.editPhoneSignup);
        phoneSignup2 =(EditText) findViewById(R.id.resubmitPhone);
        phoneSignup.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        phoneSignup2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        engineerImg =(ImageView) findViewById(R.id.engineerImg);
        farmerImg = (ImageView) findViewById(R.id.farmerImg);
        signupCommand=(ImageView) findViewById(R.id.playSignup);
        seekBarSignup = (SeekBar) findViewById(R.id.seekSignup);
        seekBarSignup.setRotation(180);
        signupCommand.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.login);
                if (mp != null) {

                    mp.start();

                }

                ObjectAnimator progressAnimator = ObjectAnimator.ofInt(seekBarSignup, "progress", 0, 100);
                progressAnimator.setDuration(1000);
                progressAnimator.setInterpolator(new LinearInterpolator());
                progressAnimator.start();
            }
        });

        final Button submit_signup = (Button) findViewById(R.id.submit_signup);

        registeration_id = PreferenceManager.getDefaultSharedPreferences(SignUp.this).getString("registerId", "defaultStringIfNothingFound");
        Log.d("Registeration id",registeration_id);

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

                        status = "engineer";
                    }
                });


                RestAdapter adapter = new RestAdapter.Builder().setEndpoint(("http://192.168.1.109:3000/")).build();
                MyApi api = adapter.create(MyApi.class);
                api.SignUp(getname, getphone, getphone, status, registeration_id, new Callback<User>()

                {

                    public void success(User user, Response response) {
                        // Log.d("Useris:",status);
                        if (status == "farmer") {


                            SharedPreferences sp = getSharedPreferences("", Activity.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("status", user.getStatus());
                            editor.commit();
                            Intent intent = new Intent(getApplicationContext(), LoginFarmer.class);
                            startActivity(intent);
                        } else if (status == "engineer") {
                            SharedPreferences sp = getSharedPreferences("sharedPreferences", Activity.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("status", user.getStatus());
                            editor.commit();
                            String myStringValue2 = PreferenceManager.getDefaultSharedPreferences(SignUp.this).getString("registerId", "defaultStringIfNothingFound");
                            Log.d("Registeration id", myStringValue2);
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
