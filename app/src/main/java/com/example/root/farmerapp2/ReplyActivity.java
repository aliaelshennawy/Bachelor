package com.example.root.farmerapp2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import models.Problem;
import models.Reply;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ReplyActivity extends AppCompatActivity {
   android.support.v7.widget.Toolbar toolbar;
    ImageView replyImg;
    ImageView replyRecord;
    private MediaRecorder myAudioRecorder;
    private String outputFile = null;
    private InputStream stream;
    private int TAKE_PHOTO_CODE = 0;
    Button submitReply;
    public static int count = 0;
    private int i = 0;
    private File newfile;
    private File audioFile;
    private Uri outputFileUri;
    private Map uploadResult;
    private Map uploadAudio;

    private String outPutImage = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reply_question);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbarReplay);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" ");
        SharedPreferences sp = getSharedPreferences("sharedPreferences", Activity.MODE_PRIVATE);
        final int problem_id = sp.getInt("problem_id", -1);
        Log.d("Problem_id", "" + problem_id);
        //Initialising the reply view buttons
        replyRecord = (ImageView) findViewById(R.id.replyRec);
        replyImg = (ImageView) findViewById(R.id.replyCam);
        submitReply =(Button) findViewById(R.id.submitReply);
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

        replyRecord.setOnClickListener(new View.OnClickListener() {
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


                    Toast.makeText(getApplicationContext(), "Recording started", Toast.LENGTH_LONG).show();
                    i ++;
                    // uploadAudio(selectedPath);

                } else if(i==1){

                    myAudioRecorder.stop();
                    //myAudioRecorder.reset();
                    myAudioRecorder.release();
                    myAudioRecorder = null;
                    //  doAudioFileUpload(myFileName);
                    // audioUri = Uri.parse(outputFile);
                    // UploadFile(audioUri);


                    Toast.makeText(getApplicationContext(), "Audio recorded successfully", Toast.LENGTH_LONG).show();
                    i=0;
                }
                final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/picFolder/";
                final File newdir = new File(dir);
                //starting camera intent on camera button clicked
                replyImg.setOnClickListener(new View.OnClickListener() {
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
        //Adding an action to the submitReply butoon to add the image taken and the audio recorded to cloud
        submitReply.setOnClickListener(new Button.OnClickListener() {
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
//                        audio.put("cloud_name", "dsm9tcfpq");
//                        audio.put("api_key", "433145311994214");
//                        audio.put("api_secret", "5KIGzPsxpp5t1m3Z-djFk3pZV-w");
                        Cloudinary cloudinary = new Cloudinary(config);
                        Cloudinary cloudinary1 = new Cloudinary(config);
                        // Cloudinary cloud = new Cloudinary(audio);
                        try {


                            //cloudinary.uploader().upload(stream, ObjectUtils.emptyMap());
                            uploadAudio = cloudinary.uploader().upload(audioFile, Cloudinary.asMap("resource_type", "video"));
                            uploadResult = cloudinary1.uploader().upload(newfile, ObjectUtils.asMap());

                            String resultUrl = (String) uploadResult.get("url");
                            Log.d("UrlToString", resultUrl);
                            // Object audioUrl = uploadAudio.get("url");
                            String audioUrl = (String) uploadAudio.get("url");
                            Log.d("UrlToString", audioUrl);


                            RestAdapter adapter = new RestAdapter.Builder().setEndpoint(("http://192.168.1.109:3000/")).build();
                            MyApi api = adapter.create(MyApi.class);
                          api.createReply(resultUrl,audioUrl,problem_id, new Callback<Reply>() {
                              @Override
                              public void success(Reply reply, Response response) {
                                  Log.d("Reply","SUCCESS");

                              }

                              @Override
                              public void failure(RetrofitError error) {
                                  Log.d("Reply","FAILURE");

                              }
                          });
                            //Log.d("OBJECT",resultUrl.toString());
                            //imageArray =AddItemAndReturn(imageArray,resultUrl);
                            //audioArray =AddItemAndReturn(audiArray,resultUrl);


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();


            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
