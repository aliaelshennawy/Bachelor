package com.example.root.farmerapp2;

/**
 * Created by root on 10/03/16.
 */


import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import models.Notification;


public class ThreeFragment extends Fragment{
    ImageView playNotific;
    SeekBar seekBarFrag3;
   ArrayList<String> all_notifications = new ArrayList<String>();
   ArrayList<Integer> icons =new ArrayList<Integer>();
    String content;
    public static ArrayList<Notification> notify = new ArrayList<Notification>();
    models.Notification notification_item = new Notification();
    public static  NotificationAdapter adapter;


    public ThreeFragment() {
        // Required empty public constructor
    }

    ListView farmer_notify;

    //
    //@Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentActivity faActivity  = (FragmentActivity)    super.getActivity();
        // Replace LinearLayout by the type of the root element of the layout you're trying to load
        RelativeLayout rlLayout    = (RelativeLayout)    inflater.inflate(R.layout.fragment_three, container, false);
        //Adding sound to the command in fragment 2
        playNotific = (ImageView) rlLayout.findViewById(R.id.playNotfications);
        seekBarFrag3 =(SeekBar) rlLayout.findViewById(R.id.seekFragment3);
        seekBarFrag3.setRotation(180);


        playNotific.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.frag3);
                if (mp != null) {
                    mp.start();
                }
                ObjectAnimator progressAnimator = ObjectAnimator.ofInt(seekBarFrag3, "progress", 0, 100);
                progressAnimator.setDuration(6000);
                progressAnimator.setInterpolator(new LinearInterpolator());
                progressAnimator.start();

            }
        });






 adapter = new NotificationAdapter(getActivity(),notify);
        farmer_notify = (ListView) (rlLayout).findViewById(R.id.listNotify);
        farmer_notify.setAdapter(adapter);



        farmer_notify.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
               // String Slecteditem= itemname[+position];

            }
        });

        return rlLayout;
    }







    // private String isCheckedOrNot(CheckBox checkbox) {
//        if(checkbox.isChecked())
//            return "is checked";
//        else
//            return "is not checked";
//    }
    //ADDED TO TRIAL

//
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_main);
//
//
//        }

}


