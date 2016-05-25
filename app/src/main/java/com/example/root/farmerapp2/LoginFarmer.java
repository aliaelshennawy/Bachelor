package com.example.root.farmerapp2;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import models.User;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by root on 17/03/16.
 */
public class LoginFarmer extends Activity {

    EditText phone;
    SeekBar seekBarLogin;
    EditText name;
    String job;
    ImageView playInfo;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_farmer);
        seekBarLogin = (SeekBar) findViewById(R.id.seekLogin);
       seekBarLogin.setRotation(180);

        name = (EditText) findViewById(R.id.editName);;
      phone = (EditText) findViewById(R.id.editPhone);

        //Adding sound to the command in Login Farmer
        playInfo = (ImageView) findViewById(R.id.playLogin);

        playInfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.login);
                if (mp != null) {

                    mp.start();

                }

                ObjectAnimator progressAnimator = ObjectAnimator.ofInt(seekBarLogin, "progress", 0, 100);
                progressAnimator.setDuration(2000);
                progressAnimator.setInterpolator(new LinearInterpolator());
                progressAnimator.start();
            }
        });

    Button loginB = (Button) findViewById(R.id.submit_login);
        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getname=  name.getText().toString();
                String getphone= phone.getText().toString();


                RestAdapter adapter = new RestAdapter.Builder().setEndpoint(("http://192.168.1.109:3000/")).build();
                MyApi api = adapter.create(MyApi.class);
                api.Login(getname, getphone, new Callback<User>()

                {

                    public void success(User user, Response response) {

                        SharedPreferences sp = getSharedPreferences("sharedPreferences", Activity.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putInt("farmer_id", user.getId());
                        editor.commit();
                        Log.d("UserLoginId",user.getId()+"");


                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }

                    public void failure(RetrofitError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });





}

}




