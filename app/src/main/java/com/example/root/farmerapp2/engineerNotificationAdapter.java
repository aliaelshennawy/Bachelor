package com.example.root.farmerapp2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import models.Engineerlist;
import models.Farmerlist;

/**
 * Created by root on 25/05/16.
 *
 package com.example.root.farmerapp2;

 import android.app.Activity;
 import android.content.Context;
 import android.content.Intent;
 import android.support.v7.widget.RecyclerView;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;
 import android.widget.ArrayAdapter;
 import android.widget.ImageView;
 import android.widget.TextView;

 import com.squareup.picasso.Picasso;

 import java.util.ArrayList;
 import java.util.List;

 import models.Farmerlist;
 import models.Notification;
 import models.Problem;

 /**
 * Created by root on 24/05/16.
 */
public class engineerNotificationAdapter extends ArrayAdapter<Engineerlist> {
    private Context context;
    private LayoutInflater inflater;

    ArrayAdapter<Engineerlist> notify;
    public engineerNotificationAdapter(Context context, ArrayList<Engineerlist> notify) {
        super(context,R.layout.notify_item,notify);


        this.context=context;
        inflater = LayoutInflater.from(context);
    }
    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Engineerlist notify = getItem(position);
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.notify_item_engineer, parent, false);
            holder = new ViewHolder();
            holder.txtTitle = (TextView) convertView.findViewById(R.id.itemEng);
            holder.imageView = (ImageView) convertView.findViewById(R.id.iconEng);
            convertView.setTag(holder);

        } else{
            holder = (ViewHolder) convertView.getTag();


    holder.txtTitle.setText(notify.getTitle());



            holder.imageView.setImageResource(R.drawable.ic_bell);
        }



        return convertView;
    }



}
