package com.example.root.farmerapp2;

import android.content.Intent;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class ReplyActivity extends AppCompatActivity {
   android.support.v7.widget.Toolbar toolbar;
    ImageView replyImg;
    ImageView replyRecord;
    private MediaRecorder myAudioRecorder;
    private String outputFile = null;
    InputStream stream;
    int TAKE_PHOTO_CODE = 0;
    Button submitReply;
    public static int count = 0;
    int i = 0;
    File newfile;
    Uri outputFileUri;
    private String outPutImage = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reply_question);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbarReplay);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" ");
        //Initialising the reply view buttons
        replyRecord = (ImageView) findViewById(R.id.replyRec);
        replyImg = (ImageView) findViewById(R.id.replyCam);
        submitReply =(Button) findViewById(R.id.submitReply);
        //Setting up the directory and making the format to be saved as mp3
        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.mp4";
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


                    Toast.makeText(getApplicationContext(), "Audio recorded successfully", Toast.LENGTH_LONG).show();
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
