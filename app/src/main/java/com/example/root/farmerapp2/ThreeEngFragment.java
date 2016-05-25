package com.example.root.farmerapp2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import models.Notification;

/**
 * Created by root on 24/04/16.
 */
public class ThreeEngFragment extends Fragment {
    public static  NotificationAdapter engineer_adapter;
    ListView engineer_noitfy;
    public static ArrayList<Notification> eng_notify_list = new ArrayList<Notification>();
    public ThreeEngFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentActivity faActivity  = (FragmentActivity)    super.getActivity();
        LinearLayout rlLayout    = (LinearLayout)    inflater.inflate(R.layout.fragment_three_eng, container, false);
        engineer_adapter = new NotificationAdapter(getActivity(),eng_notify_list);
        engineer_noitfy = (ListView) (rlLayout).findViewById(R.id.listEngineer);
        engineer_noitfy.setAdapter(engineer_adapter);



        engineer_noitfy.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                // String Slecteditem= itemname[+position];

            }
        });


//        });
        return rlLayout;

    }

}
