package com.example.root.farmerapp2;


import android.content.Intent;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import models.Advice;
import models.Problem;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by root on 24/04/16.
 */
public class TwoEngFragment extends Fragment {
    ImageView imgCam;
    ImageView imgRec;
    private MediaRecorder myAudioRecorder;
    private String outputFile = null;
    InputStream stream;
    int TAKE_PHOTO_CODE = 0;
    Button submitAdvice;
    private String outPutImage = null;
    public static int count = 0;
    int i = 0;
    File newfile;
    File audioFile;
    Uri outputFileUri;
    Map uploadResult;
    Map uploadAudio;
    public TwoEngFragment() {
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentActivity faActivity  = (FragmentActivity)    super.getActivity();
        // Replace LinearLayout by the type of the root element of the layout you're trying to load
        RelativeLayout rlLayout    = (RelativeLayout)    inflater.inflate(R.layout.advice, container, false);
        //Initializing the advice view
        imgCam = (ImageView) rlLayout.findViewById(R.id.adviceCam);
        imgRec = (ImageView) rlLayout.findViewById(R.id.adviceRec);
        submitAdvice = (Button) rlLayout.findViewById(R.id.SubmitAdvice);
        //Setting up the directory and making the format to be saved as mp3
        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.mp4";
        audioFile = new File(outputFile);
        //Setting up the audio recorder to start recording recording on button click stopping recording when button clicked again
        myAudioRecorder = new MediaRecorder();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        myAudioRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        myAudioRecorder.setOutputFile(outputFile);
        try {
            stream = new ByteArrayInputStream(outputFile.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

       imgRec.setOnClickListener(new View.OnClickListener() {
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
                final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/picFolder/";
                final File newdir = new File(dir);
                //starting camera intent on camera button clicked
                imgCam.setOnClickListener(new View.OnClickListener() {
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



            }
        });
        submitAdvice.setOnClickListener(new Button.OnClickListener() {
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
                            api.postAdvice(resultUrl, audioUrl, new Callback<Advice>()

                            {

                                public void success(Advice advice, Response response) {
                                    Log.d("Advice is:", "UPLOADED");
                                }

                                public void failure(RetrofitError error) {
                                    Log.d("Advice is:", "FAILING");
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

        return rlLayout;

    }
}
