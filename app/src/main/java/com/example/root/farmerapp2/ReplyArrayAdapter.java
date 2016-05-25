package com.example.root.farmerapp2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import models.Problem;
import models.Reply;

/**
 * Created by root on 07/05/16.
 */
public class ReplyArrayAdapter extends ArrayAdapter<Reply>{
    private Context context;
    private LayoutInflater inflater;
    ImageView replyImage;


    ArrayAdapter<Reply> replies;


    public ReplyArrayAdapter(Context context,ArrayList<Reply> replies) {
        super(context, R.layout.reply_item, replies);

        this.context = context;




        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Reply reply = getItem(position);
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.reply_item, parent, false);


            // playQues =(ImageView) convertView.findViewById(R.id.returnAudio);
        }
        replyImage= (ImageView) convertView.findViewById(R.id.returnReply);
        Picasso
                .with(context)
                .load(reply.getPhoto())
                .fit()
                .error(R.drawable.error)
                .into(replyImage);

        return convertView;
    }
}