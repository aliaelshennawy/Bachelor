package com.example.root.farmerapp2;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by root on 07/05/16.
 */
public class ImageListAdapter extends ArrayAdapter {
    private Context context;
    private LayoutInflater inflater;
    ImageView imgQues;
    ImageView playQues;

    private String[] imageUrls;
    private  String[] audioUrls;

    public ImageListAdapter(Context context, String[] imageUrls ,String []audioUrls) {
        super(context, R.layout.question_item, imageUrls);

        this.context = context;
        this.imageUrls = imageUrls;
        this.audioUrls=audioUrls;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.question_item, parent, false);

            imgQues = (ImageView) convertView.findViewById(R.id.returnImage);
            playQues =(ImageView) convertView.findViewById(R.id.returnAudio);
        }

        Picasso
                .with(context)
                .load(imageUrls[position])
                .fit()
                .error(R.drawable.error)
                .into((ImageView) imgQues);

      playQues.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               try {
                   MediaPlayer player = new MediaPlayer();
                    player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    player.setDataSource(String.valueOf(audioUrls[position]));
                    player.prepare();
                   player.start();
               } catch (Exception e) {
                   // TODO: handle exception
                }



           }   });
        return convertView;
    }
}