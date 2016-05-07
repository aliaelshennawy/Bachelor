package com.example.root.farmerapp2;

/**
 * Created by root on 10/03/16.
 */
import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.root.farmerapp2.R;


public class TwoFragment extends Fragment {
    public TwoFragment() {
        // Required empty public constructor
    }

    private static final String TAG = "ChatActivity";

    private ChatArrayAdapter chatArrayAdapter;
    private ListView listView;
    private EditText chatText;
    private ImageView buttonSend;
    private ImageView btnMic;
    private ImageView btnPic;
    int TAKE_PHOTO_CODE = 0;
    public static int count = 0;
    final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1;


    Uri imageUri   = null;
   // CameraPhotoCapture CameraActivity = null;
    private boolean side = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
        FragmentActivity faActivity  = (FragmentActivity)    super.getActivity();
        // Replace LinearLayout by the type of the root element of the layout you're trying to load
        RelativeLayout ilLayout    = (RelativeLayout)    inflater.inflate(R.layout.fragment_two, container, false);

        buttonSend = (ImageView) ilLayout.findViewById(R.id.send);
        btnMic = (ImageView) ilLayout.findViewById(R.id.sendVoice);
        btnPic = (ImageView) ilLayout.findViewById(R.id.sendPic);

        listView = (ListView) ilLayout.findViewById(R.id.msgview);
        final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/picFolder/";
        File newdir = new File(dir);

        chatArrayAdapter = new ChatArrayAdapter(super.getActivity(), R.layout.right);
        listView.setAdapter(chatArrayAdapter);

        chatText = (EditText) ilLayout.findViewById(R.id.msg);
        chatText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    return sendChatMessage();
                }
                return false;
            }
        });
        newdir.mkdirs();
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                sendChatMessage();
            }
        });
        btnPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
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

        listView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        listView.setAdapter(chatArrayAdapter);

        //to scroll the list view to bottom on data change
        chatArrayAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(chatArrayAdapter.getCount() - 1);
            }
        });
        return ilLayout;
    }

    private boolean sendChatMessage() {
        chatArrayAdapter.add(new ChatMessage(side, chatText.getText().toString()));
        chatText.setText("");
        side = !side;
        return true;
    }

    }
