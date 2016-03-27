package com.example.root.farmerapp2;

/**
 * Created by root on 10/03/16.
 */


        import android.content.Intent;
        import android.media.MediaPlayer;
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
        import android.widget.ImageView;
        import android.widget.RelativeLayout;
        import android.widget.Toast;

        import com.example.root.farmerapp2.R;

        import java.io.File;
        import java.io.IOException;


public class OneFragment extends Fragment{

    public OneFragment() {
        // Required empty public constructor
    }
    int TAKE_PHOTO_CODE = 0;
    public static int count = 0;
    private RelativeLayout Rlayout;
    private FragmentActivity faActivity;
    private ImageView ivImage = null;
    private ImageView imgPLay = null;
    private ImageView imgRecord =null;
    int i=0;
    private MediaRecorder myAudioRecorder;
    private String outputFile = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/picFolder/";
        File newdir = new File(dir);
        faActivity  = (FragmentActivity)    super.getActivity();
        Rlayout    = (RelativeLayout)    inflater.inflate(R.layout.fragment_one, container, false);
        imgPLay=(ImageView)Rlayout.findViewById(R.id.playImg);
        imgPLay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.guitar);
                if(mp != null){
                    mp.start();
                }

            }   });

        ivImage = (ImageView) Rlayout.findViewById(R.id.CameraImg);
        ivImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Here, the counter will be incremented each time, and the
                // picture taken by camera will be stored as 1.jpg,2.jpg
                // and likewise.
                count++;
                String file = dir + count + ".jpg";
                File newfile = new File(file);
                try {
                    newfile.createNewFile();
                } catch (IOException e) {
                }

                Uri outputFileUri = Uri.fromFile(newfile);

                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

                startActivityForResult(cameraIntent, TAKE_PHOTO_CODE);
            }
        });
        newdir.mkdirs();

        imgRecord=(ImageView)Rlayout.findViewById(R.id.MicImg);
        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.3gp";;

        myAudioRecorder=new MediaRecorder();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        myAudioRecorder.setOutputFile(outputFile);

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
                    i=1;

                } else{

                    myAudioRecorder.stop();
                    myAudioRecorder.release();
                    myAudioRecorder  = null;


                    Toast.makeText(getActivity().getApplicationContext(), "Audio recorded successfully",Toast.LENGTH_LONG).show();


                }


            }
        });

        return Rlayout;
    }


    // Here, we are making a folder named picFolder to store
    // pics taken by the camera using this application.







    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        onActivityResult(requestCode, resultCode, data);

        if (requestCode == TAKE_PHOTO_CODE && resultCode == 111) {
            Log.d("CameraDemo", "Pic saved");
        }
    }

}