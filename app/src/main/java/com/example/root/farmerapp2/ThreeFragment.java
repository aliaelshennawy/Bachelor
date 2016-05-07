package com.example.root.farmerapp2;

/**
 * Created by root on 10/03/16.
 */


import android.app.Activity;
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
import android.widget.Toast;


import java.util.List;


public class ThreeFragment extends Fragment{

    public ThreeFragment() {
        // Required empty public constructor
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

        // Inflate the layout for this fragment
        CustomNotificationsAdapter adapter=new CustomNotificationsAdapter(super.getActivity(), itemname, imgid);
        listNotify=(ListView) rlLayout.findViewById(R.id.listNotify);
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


