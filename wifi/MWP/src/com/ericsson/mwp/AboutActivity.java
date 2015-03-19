package com.ericsson.mwp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class AboutActivity extends Activity {

	private String LOG = "com.ericsson.mwp.AboutActivity";
	private int count_;
	private long timestamp_;
	
	Preferences prefs_ ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		
		count_ = 0;
		timestamp_ = System.currentTimeMillis()/1000;
		
		prefs_ = new Preferences( getApplicationContext() );
	}

	public void onClickCopyright(View view)
	{
		Log.d(LOG, "onClickCopyright");
		
		if( prefs_.getDevOption() )
		{
			Log.d(LOG, "dev option already enabled.");
			return ;
		}
		
		long cur_stamp = System.currentTimeMillis()/1000;
		
		if( cur_stamp>timestamp_+5 )
		{
			count_ = 1;
			timestamp_ = cur_stamp;
		}
		else
		{
			if( ++count_>=5 )
			{
				prefs_.setDevOption(true);
				Toast.makeText(getApplicationContext(), "the dev option is opened!!!", Toast.LENGTH_SHORT).show();
			}
		}
		
		Log.d(LOG, "[" + String.valueOf(timestamp_) + "," + String.valueOf(cur_stamp) + "]" + " " + String.valueOf(count_));
	}
}
