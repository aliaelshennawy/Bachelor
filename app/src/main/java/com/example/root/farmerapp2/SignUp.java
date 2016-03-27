package com.example.root.farmerapp2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        nameSignup = (EditText) findViewById(R.id.editNameSignup);
        phoneSignup = (EditText) findViewById(R.id.editPhoneSignup);
        final Button submit_signup = (Button) findViewById(R.id.submit_signup);
        final List <User> UserAccounts = null;
        submit_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getname = nameSignup.getText().toString();
                String getphone = phoneSignup.getText().toString();

                RestAdapter adapter = new RestAdapter.Builder().setEndpoint(("http://192.168.1.111:3000/")).build();
                MyApi api = adapter.create(MyApi.class);
                api.SignUp(getname, getphone, getphone, new Callback<User>()

                {

                    public void success(User user, Response response) {

                        Intent intent = new Intent(getApplicationContext(), Login.class);
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
