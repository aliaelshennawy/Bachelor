package com.example.root.farmerapp2;

/**
 * Created by root on 27/03/16.
 */
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

class CustomCropsAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname;
    private final Integer[] imgid;


    public CustomCropsAdapter(Activity context, String[] itemname, Integer[] imgid ) {
        super(context, R.layout.mylist, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;

    }


    public View getView(int position,View view,ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist, null, true);

        CheckBox checkBox = (CheckBox) rowView.findViewById(R.id.check);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        //checkBox.setText(checkBox[position]);
        imageView.setImageResource(imgid[position]);
        //extratxt.setText("Description "+itemname[position]);


        return rowView;

    }


}
