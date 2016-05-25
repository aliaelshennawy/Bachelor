package com.example.root.farmerapp2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
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
public class LoginEngineer extends Activity {

    EditText phone;
    EditText name;
//    String reg_id;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_engineer);


        name = (EditText) findViewById(R.id.editNameEng);;
      phone = (EditText) findViewById(R.id.editPhoneEng);
;
    Button loginB = (Button) findViewById(R.id.submit_loginEng);
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
                      editor.putInt("engineer_id", user.getId());
                        editor.commit();
                        Log.d("Engineer_id",user.getId()+"");
                        Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
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




