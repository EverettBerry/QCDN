package ndejaco.qcdn;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;


public class SingleVideoActivity extends ActionBarActivity {

    ProgressDialog pDialog;
    VideoView videoView;

    String VideoURL = "http://www.androidbegin.com/tutorial/AndroidCommercial.3gp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_video);

        videoView = (VideoView) findViewById(R.id.videoView);

        pDialog = new ProgressDialog(SingleVideoActivity.this);

        pDialog.setTitle("Android Video Streaming Tutorial");
        pDialog.setMessage("Buffering...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);

        pDialog.show();

        try {
            MediaController mediaController = new MediaController(SingleVideoActivity.this);
            mediaController.setAnchorView(videoView);
            Uri video = Uri.parse(VideoURL);
            videoView.setMediaController(mediaController);
            videoView.setVideoURI(video);

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                pDialog.dismiss();
                videoView.start();
            }
        });



    }
}
