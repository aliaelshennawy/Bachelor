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


public class FourFragment extends Fragment{

    public FourFragment() {
        // Required empty public constructor
    }
    ListView list;
    String[] itemname ={
            "Apple",
            "Carrot",
            "Cucumber",
            "Grapes",
            "Paprika",
            "Tomato",
            "Onion",
            "Apple",
            "Carrot",
            "Cucumber",
            "Grapes",
            "Paprika",
            "Tomato",
            "Onion"

    };

    Integer[] imgid={
            R.drawable.apple,
            R.drawable.carrot,
            R.drawable.cucumber,
            R.drawable.grapes,
            R.drawable.paprika,
            R.drawable.tomato,
            R.drawable.onion,
            R.drawable.apple,
            R.drawable.carrot,
            R.drawable.cucumber,
            R.drawable.grapes,
            R.drawable.paprika,
            R.drawable.tomato,
            R.drawable.onion,




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
        RelativeLayout rlLayout    = (RelativeLayout)    inflater.inflate(R.layout.fragment_four, container, false);

        // Inflate the layout for this fragment
        CustomCropsAdapter adapter=new CustomCropsAdapter(super.getActivity(), itemname, imgid);
        list=(ListView) rlLayout.findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

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


