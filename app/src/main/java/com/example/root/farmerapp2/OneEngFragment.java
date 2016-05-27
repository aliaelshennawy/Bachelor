package com.example.root.farmerapp2;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.cloudinary.Cloudinary;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import models.Problem;
import models.User;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by root on 24/04/16.
 */
public class OneEngFragment extends Fragment {

    int itemPos;
    ListView pListView; //Problems list view in engineer side
    public OneEngFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentActivity faActivity  = (FragmentActivity)    super.getActivity();
        // Replace LinearLayout by the type of the root element of the layout you're trying to load
        final LinearLayout lLayout    = (LinearLayout)    inflater.inflate(R.layout.questions, container, false);
        final SharedPreferences sp = this.getActivity().getSharedPreferences("sharedPreferences", Activity.MODE_PRIVATE);
        int myIntValue = sp.getInt("engineer_id", -1);
        Log.d("UserEngID", "" + myIntValue);

       // CustomQuestionsAdapter questionsAdapter = new CustomQuestionsAdapter(getActivity().getApplicationContext(),S)
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint((MyApi.BASE_URL)).build();
        MyApi api = adapter.create(MyApi.class);
     api.getAllProblems(new Callback<List<Problem>>() {

         @Override

         public void success(final List<Problem> problems, Response response) {
Log.d("Problem Get", "Success");

final ArrayList<Problem> arrayListProblems = new ArrayList<Problem>();
             arrayListProblems.clear();
             arrayListProblems.addAll(problems);


             ProblemArrayAdapter quesAdapter = new ProblemArrayAdapter(getActivity(),arrayListProblems);

                 pListView = (ListView) lLayout.findViewById(R.id.questionsList);
                 pListView.setAdapter(quesAdapter);
             quesAdapter.notifyDataSetChanged();

                 pListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                     @Override
                     public void onItemClick(AdapterView<?> parent, View view,
                                             int position, long id) {
                      int problem_id=   arrayListProblems.get(position).getId();
                         SharedPreferences.Editor editor = sp.edit();
                         editor.putInt("problem_id",problem_id);
                         editor.commit();
                         Log.d("Problem_id",problem_id+"");
                         Object item = pListView.getAdapter().getItem(position);
                         itemPos = pListView.getSelectedItemPosition();


                         // TODO Auto-generated method stub
                         try {
                             MediaPlayer player = new MediaPlayer();
                             player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                             player.setDataSource(arrayListProblems.get(position).getAudio());
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
             Log.d("GET PROBLEMS", "Failed");
         }
     });



        return lLayout;

    }


}