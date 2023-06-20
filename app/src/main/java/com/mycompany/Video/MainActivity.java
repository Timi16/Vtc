package com.mycompany.Video;

import android.app.*;
import android.net.*;
import android.os.*;
import android.widget.*;
import android.content.*;
import android.provider.MediaStore;
import android.media.MediaPlayer;
public class MainActivity extends Activity {
	private static final int REQUEST_VIDEO_SELECTION = 1;
	VideoView videoView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        videoView =(VideoView)findViewById(R.id.videoView);
		
        //Set MediaController  to enable play, pause, forward, etc options.
        MediaController mediaController= new MediaController(this);
        videoView.setMediaController(mediaController);
		openVideoSelection();
    }
	private void openVideoSelection() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        intent.setType("video/*");
        startActivityForResult(intent, REQUEST_VIDEO_SELECTION);
    }
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_VIDEO_SELECTION && resultCode == RESULT_OK) {
            Uri videoUri = data.getData();
            playVideo(videoUri);
        }
    }
	private void playVideo(Uri videoUri) {
        videoView.setVideoURI(videoUri);
        videoView.start();
    }
}
			

