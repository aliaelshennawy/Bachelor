package com.example.root.farmerapp2;

/**
 * Created by root on 10/03/16.
 */
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.root.farmerapp2.R;

import models.Problem;
import models.Reply;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class TwoFragment extends Fragment {
    ListView rListView;
    ImageView playReplies;
    SeekBar seekBarFrag2;
    public TwoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
        FragmentActivity faActivity  = (FragmentActivity)    super.getActivity();
        // Replace LinearLayout by the type of the root element of the layout you're trying to load
        final SharedPreferences sp = this.getActivity().getSharedPreferences("sharedPreferences", Activity.MODE_PRIVATE);
        final RelativeLayout ilLayout    = (RelativeLayout)    inflater.inflate(R.layout.fragment_two, container, false);
        int myProblemId = sp.getInt("problem_id", -1);
        Log.d("problem_id", "" + myProblemId+"");
        //Adding sound to the command in fragment 2
        playReplies = (ImageView) ilLayout.findViewById(R.id.playImgReply);
        seekBarFrag2 = (SeekBar) ilLayout.findViewById(R.id.seekBarReply);
        seekBarFrag2.setRotation(180);

        playReplies.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.frag2);
                if (mp != null) {
                    mp.start();
                }
                ObjectAnimator progressAnimator = ObjectAnimator.ofInt(seekBarFrag2, "progress", 0, 100);
                progressAnimator.setDuration(1850);
                progressAnimator.setInterpolator(new LinearInterpolator());
                progressAnimator.start();

            }
        });



        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(("http://192.168.1.109:3000/")).build();
        MyApi api = adapter.create(MyApi.class);
        api.getAllReplies(myProblemId,new Callback<List<Reply>>() {

            @Override

            public void success(final List<Reply> replies, Response response) {
                Log.d("Getting replies", "Success");
                //Log.d("Photo of reply",replies.get(0).getPhoto().toString());

                final ArrayList<Reply> arrayListReplies = new ArrayList<Reply>();
                arrayListReplies.clear();
                arrayListReplies.addAll(replies);


                ReplyArrayAdapter replyAdapter = new ReplyArrayAdapter(getActivity(),arrayListReplies);

               rListView = (ListView) ilLayout.findViewById(R.id.listReplies);
                rListView.setAdapter(replyAdapter);
                replyAdapter.notifyDataSetChanged();

                rListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {

                        // TODO Auto-generated method stub
                        try {
                            MediaPlayer player = new MediaPlayer();
                            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                            player.setDataSource(arrayListReplies.get(position).getAudio());
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
                Log.d("Getting Replies", "Failed");
            }
        });


        return ilLayout;
    }



    }
