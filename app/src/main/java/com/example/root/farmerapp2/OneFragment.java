package com.example.root.farmerapp2;

/**
 * Created by root on 10/03/16.
 */


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
    public OneFragment() {


        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/picFolder/";
        final File newdir = new File(dir);

//        Log.d("Directory sasd" , dir);
        faActivity = (FragmentActivity) super.getActivity();
        Rlayout = (RelativeLayout) inflater.inflate(R.layout.fragment_one, container, false);
//        final SharedPreferences prefsFarmer = PreferenceManager.getDefaultSharedPreferences(getContext());
//        user_id = prefsFarmer.getInt("farmer_id", 400);
        SharedPreferences sp = this.getActivity().getSharedPreferences("sharedPreferences", Activity.MODE_PRIVATE);
        int myIntValue = sp.getInt("farmer_id", -1);
        Log.d("UserID", "" + myIntValue);



//        final SharedPreferences reg = PreferenceManager.getDefaultSharedPreferences(getActivity().getBaseContext());
//        reg_id= reg.getString("registerId", "");


        imgPLay = (ImageView) Rlayout.findViewById(R.id.playImg);

        imgPLay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.guitar);
                if (mp != null) {
                    mp.start();
                }

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
                            api.postProblem(resultUrl, "Problem", audioUrl, user_id, new Callback<Problem>()

                            {

                                public void success(Problem problem, Response response) {
                                    Log.d("Problem is:", "UPLOADED");

                                    //   Toast.makeText(getActivity().getApplicationContext(),user_id+"", Toast.LENGTH_LONG).show();
                                }

                                public void failure(RetrofitError error) {
                                    Log.d("Problem is:", "FAILING");
                                    Toast.makeText(getActivity().getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                                }
                            });
                            //Log.d("OBJECT",resultUrl.toString());
                            //imageArray =AddItemAndReturn(imageArray,resultUrl);
                            //audioArray =AddItemAndReturn(audioArray,resultUrl);


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
//                new Thread() {
//                    @Override
//                    public void run()
//                        //your code here
//
//                        Map config = new HashMap();
//                        config.put("cloud_name", "dsm9tcfpq");
//                        config.put("api_key", "433145311994214");
//                        config.put("api_secret", "5KIGzPsxpp5t1m3Z-djFk3pZV-w");
//                        Cloudinary cloudinary = new Cloudinary(config);
//                        try {
//
//                           // Map uploadResult= cloudinary.uploader().upload(newfile, ObjectUtils.emptyMap());
//                            //cloudinary.uploader().upload(stream, ObjectUtils.emptyMap());
//                            cloudinary.uploader().upload(audioFile, Cloudinary.asMap("resource_type", "audio"));
//                            //   Map uploadAudio= cloudinary.uploader().upload(outputFile, ObjectUtils.emptyMap());
//
//
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }   }
//                }.start();


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


    // Here, we are making a folder named picFolder to store
    // pics taken by the camera using this application.
//    public static void postImage(String nf){
//        RequestParams params = new RequestParams();
//        params.put("picture[name]","MyPictureName");
//        try {
//            Log.d("Fashel", nf);
//            params.put("picture[image]", new File(nf));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        AsyncHttpClient client = new AsyncHttpClient();
//        client.post("http://192.168.0.193:3000/pictures/", params, new AsyncHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//
//                Log.d("Success","ahooooo");
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                try {
//                    throw error;
//                } catch (Throwable throwable) {
//                    throwable.printStackTrace();
//                }
//                Log.d("Fashel", error.getMessage());
//
//            }
//
//        });
//    }
//
//
//public void postImage (TypedFile typedFile,String des){
//
//    MyApi service = ServiceGenerator.createService(MyApi.class, MyApi.BASE_URL);
//    typedFile = new TypedFile("multipart/form-data", newfile);
//    String description = "hello, this is description speaking";
//
//    service.uploadImage(typedFile, description, new Callback<Problem>() {
//
//        @Override
//        public void success(Problem problem, Response response) {
//            Log.e("Upload", "success");
//        }
//
//        @Override
//        public void failure(RetrofitError error) {
//            Log.e("Upload", "error");
//        }
//    });
//
//}


    //    public static void printMap(Map mp) {
//        Iterator it = mp.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry pair = (Map.Entry)it.next();
//            System.out.println(pair.getKey() + " = " + pair.getValue());
//            it.remove(); // avoids a ConcurrentModificationException
//        }
//    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TAKE_PHOTO_CODE && resultCode == 111) {
            Log.d("CameraDemo", "Pic saved");
        }
    }

//public void ConvertAudioToString(File file){
//     file = new File(Environment.getExternalStorageDirectory() + "/hello-4.wav");
//    byte[] bytes = FileUtils.readFileToByteArray(file);
//
//    String encoded = Base64.encodeToString(bytes, 0);
//    Utilities.log("~~~~~~~~ Encoded: ", encoded);
//
//    byte[] decoded = Base64.decode(encoded, 0);
//    Utilities.log("~~~~~~~~ Decoded: ", Arrays.toString(decoded));
//
//    try
//    {
//        File file2 = new File(Environment.getExternalStorageDirectory() + "/hello-5.wav");
//        FileOutputStream os = new FileOutputStream(file2, true);
//        os.write(decoded);
//        os.close();
//    }
//    catch (Exception e)
//    {
//        e.printStackTrace();
//    }
//}

    //    public void UploadFile(Uri fileUri){
//        UploadcareClient client = UploadcareClient.demoClient();
//
//    Context context = getActivity().getApplicationContext();
//
//    Uploader uploader = new FileUploader(client, fileUri, context)
//            .store(true);
//    uploader.uploadAsync(new UploadcareFileCallback() {
//        @Override
//        public void onFailure(UploadcareApiException e) {
//            Log.e("Error","File not uploaded");
//        }
//
//        @Override
//
//        public void onSuccess(UploadcareFile file) {
//            //successfully uploaded file to Uploadcare.
//            Log.e("Sucess","File uploaded correctly");
//        }
//    });}
    public ArrayList<Object> AddItemAndReturn(ArrayList<Object> array, Object o) {
        array.add(o);
        return array;
    }

}

