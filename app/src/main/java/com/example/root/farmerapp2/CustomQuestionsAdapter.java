//package com.example.root.farmerapp2;
//
//import android.app.Activity;
//import android.content.Context;
//import android.media.AudioManager;
//import android.media.MediaPlayer;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.squareup.picasso.Picasso;
//
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//import models.Problem;
//
///**
// * Created by root on 03/05/16.
// */
//public class CustomQuestionsAdapter extends ArrayAdapter<Problem>{
//    private final Activity context;
//ImageView img;
//
//
//    // private final Object[] imagesUrl;
//    //private final Object[] audioUrl;
//
//    ImageView playQues;
//    ImageView imgQues;
//    List<Problem> problem;
//
//    private LayoutInflater inflater;
//    public CustomQuestionsAdapter(Activity context,List<Problem> problem) {
//        super(context,R.layout.question_item, (List<Problem>) problem);
//
//
//        this.context=context;
//        //this.photos=photos;
//        //this.audio=audio;
//
//
//       // this.imagesUrl=imagesUrl;
//        //this.audioUrl=audioUrl;
//
//    }
//
//    @Override
//    public View getView (int position, View convertView, ViewGroup parent){
//        View itemView=convertView;
//        if( itemView == null ){
//            //We must create a View:
//            itemView = inflater.inflate(R.layout.question_item, parent, false);
//            imgQues = (ImageView) itemView.findViewById(R.id.returnImage);
//        }
//        String imgUrl = problem.get(position).getPhoto();
//        Picasso.with(context)
//                .load(imgUrl)
//                .fit() // will explain later
//                .into((ImageView) imgQues);
//        return convertView;
//    }
////        playQues.setOnClickListener(new View.OnClickListener() {
////            public void onClick(View v) {
////                try {
////                    MediaPlayer player = new MediaPlayer();
////                    player.setAudioStreamType(AudioManager.STREAM_MUSIC);
////                    player.setDataSource(String.valueOf(audio.get(position)));
////                    player.prepare();
////                    player.start();
////                } catch (Exception e) {
////                    // TODO: handle exception
////                }
////
////
////
////            }   });
//
//
//}
