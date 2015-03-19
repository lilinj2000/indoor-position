package com.river.mwp;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class RDSActivity extends Activity {

	VideoView rds_videoview;
	MediaController media_controller;
	MediaPlayer media_player;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rds);
		
		getInit();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		try
		{
			if( media_player!=null )
			{
				media_player.stop();
			}
		}
		catch(  Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void getInit() { 
		
		rds_videoview = (VideoView) findViewById(R.id.rds_des_video);
		
		media_controller = new MediaController(this);
		media_controller.setAnchorView(rds_videoview);
		
		rds_videoview.setMediaController(media_controller);
		
		Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.demo_rds_video);
		
//		iod_videoview.setVideoPath("/sdcard/video.mp4");
		
		rds_videoview.setVideoURI(video);
		
//		rds_videoview.seekTo(11000);
		
//		media_controller.hide();
		
		media_controller.setVisibility(View.GONE);
		
		media_player = MediaPlayer.create(this, R.raw.demo_rds);
//		media_player.prepare();
		media_player.start();
		
//		on completion the MP3, start the vedio play
		media_player.setOnCompletionListener(new OnCompletionListener() {
			@Override  
            public void onCompletion(MediaPlayer mp) {  
//                Uri uri = Uri.parse(url);  
//                videoview.setVideoURI(uri);  
				rds_videoview.start();  
  
            }
		});
	
		
//		rds_videoview.start(); 
		
		rds_videoview.setOnCompletionListener(new OnCompletionListener() {  
			  
            @Override  
            public void onCompletion(MediaPlayer mp) {  
//                Uri uri = Uri.parse(url);  
//                videoview.setVideoURI(uri);  
            	rds_videoview.start();  
  
            }
		});
		
		}

}
