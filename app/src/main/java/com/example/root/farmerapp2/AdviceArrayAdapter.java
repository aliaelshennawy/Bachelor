package com.example.root.farmerapp2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import models.Advice;
import models.Reply;

/**
 * Created by root on 26/05/16.
 */
public class AdviceArrayAdapter extends ArrayAdapter<Advice> {
    private Context context;
    private LayoutInflater inflater;
    ImageView adviceImage;


    ArrayAdapter<Reply> advices;


    public AdviceArrayAdapter(Context context,ArrayList<Advice> advices) {
        super(context, R.layout.advice_item, advices);

        this.context = context;




        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Advice advice = getItem(position);
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.advice_item, parent, false);


            // playQues =(ImageView) convertView.findViewById(R.id.returnAudio);
        }
        adviceImage = (ImageView) convertView.findViewById(R.id.returnAdvice);
        Picasso
                .with(context)
                .load(advice.getPhoto())
                .fit()
                .error(R.drawable.error)
                .into(adviceImage);

        return convertView;
    }

}
