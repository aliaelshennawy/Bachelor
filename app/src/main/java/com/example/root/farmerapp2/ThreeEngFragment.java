package com.example.root.farmerapp2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import models.Engineerlist;
import models.Farmerlist;
import models.Notification;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by root on 24/04/16.
 */
public class ThreeEngFragment extends Fragment {
    public static  NotificationAdapter engineer_adapter;
    ListView engineer_list;
    TextView empty_engineer;
    public static ArrayList<Notification> eng_notify_list = new ArrayList<Notification>();
    public ThreeEngFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentActivity faActivity  = (FragmentActivity)    super.getActivity();
        final LinearLayout rlLayout    = (LinearLayout)    inflater.inflate(R.layout.fragment_three_eng, container, false);

        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(("http://192.168.1.109:3000/")).build();
        MyApi api = adapter.create(MyApi.class);
        api.getAllEngineerNotify(new Callback<List<Engineerlist >> () {

            @Override

            public void success(final List<Engineerlist> engineer_notifications, Response response) {
                Log.d("Getting Farmer Notifications", "Success");
                //Log.d("Photo of reply",replies.get(0).getPhoto().toString());

                final ArrayList<Engineerlist> arrayListNotifications = new ArrayList<>();
                arrayListNotifications.clear();
                arrayListNotifications.addAll(engineer_notifications);


                engineerNotificationAdapter adapter = new engineerNotificationAdapter(getActivity(), arrayListNotifications);

                engineer_list = (ListView) rlLayout.findViewById(R.id.listEngineer);
                engineer_list.setAdapter(adapter);
                empty_engineer = (TextView)rlLayout.findViewById(R.id.empty_list_eng);
                engineer_list.setEmptyView(empty_engineer);
                adapter.notifyDataSetChanged();

                engineer_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {


                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if(arrayListNotifications.get(position).getIcon() == 0){
                            arrayListNotifications.get(position).setIcon(R.drawable.ic_bell_black);
                        }
                        else{
                            arrayListNotifications.get(position).setIcon(R.drawable.ic_bell);
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
