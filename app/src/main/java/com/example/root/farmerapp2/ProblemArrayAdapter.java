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

/**
 * Created by root on 07/05/16.
 */
public class ProblemArrayAdapter extends ArrayAdapter<Problem>{
    private Context context;
    private LayoutInflater inflater;
    ImageView imgQues;
    ImageView playQues;
    ImageView reply;


    ArrayAdapter<Problem> problems;


    public ProblemArrayAdapter(Context context,ArrayList<Problem> problems) {
        super(context, R.layout.question_item, problems);

        this.context = context;




        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Problem problem = getItem(position);
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.question_item, parent, false);

            reply = (ImageView) convertView.findViewById(R.id.replyImg);
            reply.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent myIntent = new Intent(v.getContext(), ReplyActivity.class);
                    v.getContext().startActivity(myIntent);

                }
            });
            // playQues =(ImageView) convertView.findViewById(R.id.returnAudio);
        }
        imgQues = (ImageView) convertView.findViewById(R.id.returnImage);
        Picasso
                .with(context)
                .load(problem.getPhoto())
                .fit()
                .error(R.drawable.error)
                .into(imgQues);
//playQues.setTag(position);
//      playQues.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//
//               try {
//                   MediaPlayer player = new MediaPlayer();
//                    player.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                    player.setDataSource(String.valueOf(audioUrls[position]));
//                    player.prepare();
//                   player.start();
//               } catch (Exception e) {
//                   // TODO: handle exception
//                }
//
//
//
//           }   });
        return convertView;
    }
}