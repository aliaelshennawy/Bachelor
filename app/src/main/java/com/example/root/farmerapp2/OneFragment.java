package com.example.root.farmerapp2;

/**
 * Created by root on 10/03/16.
 */


import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import models.Problem;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class OneFragment extends Fragment {

    public static int count = 0;
    int TAKE_PHOTO_CODE = 0;
    File newfile;
    int i = 0;
    Uri outputFileUri;
    Map uploadResult;
    File audioFile;
    int user_id;
    InputStream stream;
    Map uploadAudio;
    private RelativeLayout Rlayout;
    private FragmentActivity faActivity;
    private ImageView ivImage = null;
    private ImageView imgPLay = null;
    private ImageView imgRecord = null;
    private MediaRecorder myAudioRecorder;
    private String outputFile = null;
    private String outPutImage = null;
    private Button Adkhel = null;
    int problem_id;
    SeekBar seekBarFrag1;
    public OneFragment() {


        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/picFolder/";
        final File newdir = new File(dir);
        faActivity = (FragmentActivity) super.getActivity();
        Rlayout = (RelativeLayout) inflater.inflate(R.layout.fragment_one, container, false);
       final SharedPreferences sp = this.getActivity().getSharedPreferences("sharedPreferences", Activity.MODE_PRIVATE);
        final int myIntValue = sp.getInt("farmer_id", -1);
        Log.d("UserID", "" + myIntValue);



//        final SharedPreferences reg = PreferenceManager.getDefaultSharedPreferences(getActivity().getBaseContext());
//        reg_id= reg.getString("registerId", "");
        seekBarFrag1 = (SeekBar) Rlayout.findViewById(R.id.seekFragment1);
        seekBarFrag1.setRotation(180);


        imgPLay = (ImageView) Rlayout.findViewById(R.id.playImg);

        imgPLay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.frag1);
                if (mp != null) {
                    mp.start();
                }
                ObjectAnimator progressAnimator = ObjectAnimator.ofInt(seekBarFrag1, "progress", 0, 100);
                progressAnimator.setDuration(1900);
                progressAnimator.setInterpolator(new LinearInterpolator());
                progressAnimator.start();

            }
        });

        ivImage = (ImageView) Rlayout.findViewById(R.id.CameraImg);
        ivImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //postImage(dir + "1.jpg");
                // Here, the counter will be incremented each time, and the
                // picture taken by camera will be stored as 1.jpg,2.jpg
                // and likewise.

                count++;
                String file = dir + count + ".jpg";
                newfile = new File(file);
                try {
                    newfile.createNewFile();
                    //TypedFile TypedFileFinal = new TypedFile("image/jpg",newfile);
                    //postImage(TypedFileFinal , "Tagrobaa");


                } catch (IOException e) {
                }
                outputFileUri = Uri.fromFile(newfile);
                outPutImage = newfile.toString();
                //Log.v("HTTPGet", "NewFile.toString == " + newfile.toString());


                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                //postImage(outputFileUri);
                startActivityForResult(cameraIntent, TAKE_PHOTO_CODE);

            }
        });
        newdir.mkdirs();
        Adkhel = (Button) Rlayout.findViewById(R.id.SubmitProblem);
        Adkhel.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                new Thread() {
                    @Override
                    public void run() {
                        //your code here

                        Map config = new HashMap();
                        // Map  audio = new HashMap();

                        config.put("cloud_name", "dsm9tcfpq");
                        config.put("api_key", "433145311994214");
                        config.put("api_secret", "5KIGzPsxpp5t1m3Z-djFk3pZV-w");
;
                        Cloudinary cloudinary = new Cloudinary(config);
                        Cloudinary cloudinary1 = new Cloudinary(config);
                        try {

                            uploadAudio = cloudinary.uploader().upload(audioFile, Cloudinary.asMap("resource_type", "video"));
                            uploadResult = cloudinary1.uploader().upload(newfile, ObjectUtils.asMap());

                            String resultUrl = (String) uploadResult.get("url");
                            Log.d("UrlToString", resultUrl);
                            String audioUrl = (String) uploadAudio.get("url");
                            Log.d("UrlToString", audioUrl);


                            RestAdapter adapter = new RestAdapter.Builder().setEndpoint(("http://192.168.1.109:3000/")).build();
                            MyApi api = adapter.create(MyApi.class);
                            api.postProblem(resultUrl, "Problem", audioUrl, myIntValue, new Callback<Problem>()

                            {

                                public void success(Problem problem, Response response) {
                                    Log.d("Problem is:", "UPLOADED");
                                    Log.d("User creating problem",myIntValue+"");




                                }

                                public void failure(RetrofitError error) {
                                    Log.d("Problem is:", "FAILING");
                                    Toast.makeText(getActivity().getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                                }
                            });

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();


            }
        });


        //File fileFinal=new File(outputFileUri.getPath());
        //Log.v("HTTPGet", "Uri.toString == " + fileFinal.toString());

        //TypedFile TypedFileFinal = new TypedFile("image/jpg",fileFinal);
        //Log.v("HTTPGet", "Uri.toString == " + TypedFileFinal.toString());
        //postImage(TypedFileFinal,"YARAB HAWENHAA");


        imgRecord = (ImageView) Rlayout.findViewById(R.id.MicImg);
        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.mp4";
        audioFile = new File(outputFile);
        ;

        myAudioRecorder = new MediaRecorder();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        // myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        myAudioRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        myAudioRecorder.setOutputFile(outputFile);
        //audioFile = new File(outputFile,"Audio");
        // File audioFile = context.getCacheDir();
        try {
            stream = new ByteArrayInputStream(outputFile.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        imgRecord.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (i == 0) {


                    try {
                        myAudioRecorder.prepare();
                        myAudioRecorder.start();
                    } catch (IllegalStateException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }


                    Toast.makeText(getActivity().getApplicationContext(), "Recording started", Toast.LENGTH_LONG).show();
                    i = 1;
                    // uploadAudio(selectedPath);

                } else {

                    myAudioRecorder.stop();
                    //myAudioRecorder.reset();
                    myAudioRecorder.release();
                    myAudioRecorder = null;
                    //  doAudioFileUpload(myFileName);
                    // audioUri = Uri.parse(outputFile);
                    // UploadFile(audioUri);


                    Toast.makeText(getActivity().getApplicationContext(), "Audio recorded successfully", Toast.LENGTH_LONG).show();


                }


            }
        });

        return Rlayout;

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TAKE_PHOTO_CODE && resultCode == 111) {
            Log.d("CameraDemo", "Pic saved");
        }
    }


    public ArrayList<Object> AddItemAndReturn(ArrayList<Object> array, Object o) {
        array.add(o);
        return array;
    }

}

