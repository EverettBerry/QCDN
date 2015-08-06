package ndejaco.qcdn;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;




public class SingleVideoActivity extends ActionBarActivity {

    private ProgressDialog pDialog;
    private VideoView videoView;
    private TextView mTextView;
    private MediaController mediaController;

    private Playlist myPlaylist;
    private long startTime;
    private long endTime;
    private int videoIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_video);

        if (getIntent().getStringExtra("Network Type").equals("qcdn")) {
            myPlaylist = new Playlist("qcdn");
        }

        else {
            myPlaylist = new Playlist("cell network");
        }

        videoIndex = 0;



        mTextView = (TextView) findViewById(R.id.textView);
        videoView = (VideoView) findViewById(R.id.videoView);
        pDialog = new ProgressDialog(SingleVideoActivity.this);

        pDialog.setTitle("Android Video Streaming Tutorial");
        pDialog.setMessage("Buffering...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);

        startTime = System.currentTimeMillis();
        pDialog.show();

        try {
            mediaController = new MediaController(SingleVideoActivity.this);
            mediaController.setAnchorView(videoView);
            Uri video = Uri.parse(myPlaylist.getVideo(videoIndex));
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
                endTime = System.currentTimeMillis();
                videoView.start();
                mTextView.setText("Video loaded in " + ((endTime - startTime) / 1000.00) + " seconds");

            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                videoIndex++;
                pDialog.setTitle("Android Video Streaming Tutorial");
                pDialog.setMessage("Buffering...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(false);

                if (videoIndex >= myPlaylist.getSize())
                    videoIndex = 0;

                startTime = System.currentTimeMillis();
                pDialog.show();

                try {
                    Uri video = Uri.parse(myPlaylist.getVideo(videoIndex));
                    videoView.setVideoURI(video);

                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }
                videoView.requestFocus();
            }
        });


    }
}
