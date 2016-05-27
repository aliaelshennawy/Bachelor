package com.example.root.farmerapp2;

/**
 * Created by root on 10/03/16.
 */


import android.animation.ObjectAnimator;
import android.app.TabActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import models.Farmerlist;
import models.Notification;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ThreeFragment extends Fragment{
    ImageView playNotific;
    SeekBar seekBarFrag3;
   ArrayList<String> all_notifications = new ArrayList<String>();
   ArrayList<Integer> icons =new ArrayList<Integer>();
    String content;
    public static ArrayList<Notification> notify = new ArrayList<Notification>();
    models.Notification notification_item = new Notification();

    ListView farmer_list;
TextView empty_farmer ;

    public ThreeFragment() {
        // Required empty public constructor
    }

    ListView farmer_notify;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentActivity faActivity  = (FragmentActivity)    super.getActivity();
        // Replace LinearLayout by the type of the root element of the layout you're trying to load
        final RelativeLayout rlLayout    = (RelativeLayout)    inflater.inflate(R.layout.fragment_three, container, false);
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

        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(("http://192.168.1.109:3000/")).build();
        MyApi api = adapter.create(MyApi.class);
        api.getAllFarmerNotify(new Callback<List<Farmerlist>>() {

            @Override

            public void success(final List<Farmerlist> farmer_notifications, Response response) {
                Log.d("Getting Farmer Notifications", "Success");

                final ArrayList<Farmerlist> arrayListNotifications = new ArrayList<>();
                arrayListNotifications.clear();
                arrayListNotifications.addAll(farmer_notifications);


                farmerNotificationAdapter adapter = new farmerNotificationAdapter(getActivity(), arrayListNotifications);

                farmer_list = (ListView) rlLayout.findViewById(R.id.listNotify);
                empty_farmer = (TextView)rlLayout.findViewById(R.id.empty_list_farmer);
                farmer_list.setEmptyView(empty_farmer);

                farmer_list.setAdapter(adapter);

                adapter.notifyDataSetChanged();

                farmer_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {


                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if(arrayListNotifications.get(position).getIcon()==0){
                            Intent intent = new Intent(getActivity(), getAdvice.class);
                            startActivity(intent);
                        }
                        else {

MainActivity.viewPager.setCurrentItem(1);
                        }


                    }
                });

            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("Getting Farmer Notifications", "Failed");
            }
        });


        return rlLayout;
    }



}


