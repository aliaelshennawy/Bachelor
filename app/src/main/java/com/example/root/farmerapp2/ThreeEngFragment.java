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
import android.widget.RelativeLayout;

import retrofit.RestAdapter;

/**
 * Created by root on 24/04/16.
 */
public class ThreeEngFragment extends Fragment {
    public ThreeEngFragment() {
    }
    ListView listNotify;
    String[] itemname ={
            "المهندس احمد جاوب عن سؤالك",
            "المهندس احمد جاوب عن سؤالك",
            "المهندس احمد جاوب عن سؤالك",
            "المهندس احمد جاوب عن سؤالك",
            "المهندس احمد جاوب عن سؤالك",
            "المهندس احمد جاوب عن سؤالك",
            "المهندس احمد جاوب عن سؤالك",


    };

    Integer[] imgid={
            R.drawable.ic_bell,
            R.drawable.ic_bell,
            R.drawable.ic_bell,
            R.drawable.ic_bell,
            R.drawable.ic_bell,
            R.drawable.ic_bell,
            R.drawable.ic_bell,




    };
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentActivity faActivity  = (FragmentActivity)    super.getActivity();
        // Replace LinearLayout by the type of the root element of the layout you're trying to load
        LinearLayout rlLayout    = (LinearLayout)    inflater.inflate(R.layout.fragment_three_eng, container, false);
        CustomNotificationsAdapter adapter=new CustomNotificationsAdapter(super.getActivity(), itemname, imgid);
        listNotify=(ListView) rlLayout.findViewById(R.id.listEngineer);
        listNotify.setAdapter(adapter);

        listNotify.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem= itemname[+position];

            }
        });
        return rlLayout;

    }

}
