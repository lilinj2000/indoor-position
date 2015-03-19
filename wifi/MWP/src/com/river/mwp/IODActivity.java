package com.river.mwp;


import android.app.Activity;
import android.widget.MediaController;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

public class IODActivity extends Activity {

	VideoView iod_videoview;
	MediaController media_controller;
	MediaPlayer media_player = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_iod);
		
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
		
//		prepare video play
		iod_videoview = (VideoView) findViewById(R.id.iod_des_video);
		
		media_controller = new MediaController(this);
		media_controller.setAnchorView(iod_videoview);
		
		iod_videoview.setMediaController(media_controller);
		
		Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.demo_iod_video);
		
//		iod_videoview.setVideoPath("/sdcard/video.mp4");
		
		iod_videoview.setVideoURI(video);
		
//		prepare mp3 play
		try
		{
			media_player = MediaPlayer.create(this, R.raw.demo_iod);
//			media_player.prepare();
			media_player.start();
			
	//		on completion the MP3, start the vedio play
			media_player.setOnCompletionListener(new OnCompletionListener() {
				@Override  
	            public void onCompletion(MediaPlayer mp) {  
	//                Uri uri = Uri.parse(url);  
	//                videoview.setVideoURI(uri);  
	            	iod_videoview.start();  
	  
	            }
			});
		}
		catch( Exception e)
		{
			e.printStackTrace();
		}
					
//		video start
//		iod_videoview.start();
		
		iod_videoview.setOnCompletionListener(new OnCompletionListener() {  
  
            @Override  
            public void onCompletion(MediaPlayer mp) {  
//                Uri uri = Uri.parse(url);  
//                videoview.setVideoURI(uri);  
            	iod_videoview.start();  
  
            }
		});
		
//		iod_videoview.seekTo(6000);
		
//		media_controller.hide();
		
		media_controller.setVisibility(View.GONE);
		
//		iod_videoview.start(); 
		
		}
}
