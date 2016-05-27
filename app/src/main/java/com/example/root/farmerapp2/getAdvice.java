package com.example.root.farmerapp2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Advice;
import models.Engineerlist;
import models.Farmerlist;
import models.Reply;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import models.Problem;
import models.Reply;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by root on 26/05/16.
 */
public class getAdvice extends AppCompatActivity{

        android.support.v7.widget.Toolbar toolbar;
    ListView advice_list;
TextView empty_advice;
        private String outPutImage = null;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.advice_list);
            toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbarAdvice);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(" ");

                                RestAdapter adapter = new RestAdapter.Builder().setEndpoint((MyApi.BASE_URL)).build();
                                MyApi api = adapter.create(MyApi.class);
                                api.getAllAdvices(new Callback<List<Advice>>() {
                                    public void success(List<Advice> advices, Response response) {
                                        Log.d("Getting Advice", "FAILURE");
                                        final ArrayList<Advice> arrayListAdvices = new ArrayList<>();
                                        arrayListAdvices.clear();
                                        arrayListAdvices.addAll(advices);


                                        AdviceArrayAdapter adapter = new AdviceArrayAdapter(getApplicationContext(), arrayListAdvices);

                                       advice_list  = (ListView) findViewById(R.id.listAdvice);
                                        advice_list.setAdapter(adapter);
                                        empty_advice = (TextView) findViewById(R.id.empty_list_advice);
                                        advice_list.setEmptyView(empty_advice);
                                        adapter.notifyDataSetChanged();

                                        advice_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {


                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                try {
                                                    MediaPlayer player = new MediaPlayer();
                                                    player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                                                    player.setDataSource(arrayListAdvices.get(position).getAudio());
                                                    player.prepare();
                                                    player.start();
                                                } catch (Exception e) {
                                                    // TODO: handle exception
                                                }


                                            }
                                        });

                                    }


                                    @Override
                                    public void failure(RetrofitError error) {
                                        Log.d("Getting Advice","FAILURE");

                                    }
                                });}
                                //Log.d("OBJECT",resultUrl.toString());
                                //imageArray =AddItemAndReturn(imageArray,resultUrl);
                                //audioArray =AddItemAndReturn(audiArray,resultUrl);



        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case android.R.id.home:
                    onBackPressed();
                    return true;
            }

            return super.onOptionsItemSelected(item);
        }


    }


