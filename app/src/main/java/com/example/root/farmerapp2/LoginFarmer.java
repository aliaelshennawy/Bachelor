package com.example.root.farmerapp2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    EditText name;
    String job;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_farmer);

        name = (EditText) findViewById(R.id.editName);;
      phone = (EditText) findViewById(R.id.editPhone);

;
    Button loginB = (Button) findViewById(R.id.submit_login);
        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getname=  name.getText().toString();
                String getphone= phone.getText().toString();

                RestAdapter adapter = new RestAdapter.Builder().setEndpoint(("http://192.168.1.101:3000/")).build();
                MyApi api = adapter.create(MyApi.class);
                api.Login(getname, getphone, new Callback<User>()

                {

                    public void success(User user, Response response) {
                        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(LoginFarmer.this);
                        SharedPreferences.Editor editor =prefs.edit();
                        editor.putInt("id",user.getId());
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




