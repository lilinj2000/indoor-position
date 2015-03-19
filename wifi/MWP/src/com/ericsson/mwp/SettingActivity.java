package com.ericsson.mwp;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;


public class SettingActivity extends Activity {

	private String LOG = "com.ericsson.mwp.SettingActivity";
	
	Button notification_button;
	Button devoption_button;
	
	private String NotifyService = "com.ericsson.mwp.NOTIFY";
	private Intent notify_intent_ = new Intent(NotifyService);
	
	Preferences prefs_;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		
		notification_button = (Button) findViewById(R.id.button_notification);
		devoption_button = (Button) findViewById(R.id.button_devoption);
		
		prefs_ = new Preferences( getApplicationContext() );
	}
	
	@Override
    protected void onStart(){
        super.onDestroy();
        
        updateUI();
        
        setNotification();
    }
	
	@Override
    protected void onDestroy(){
        super.onDestroy();
    }
	
	/** Called when the user clicks the Setting button */
    public void onClickAboutButton(View view) {
        
    	Intent intent = new Intent(this, AboutActivity.class);
    	
    	startActivity(intent);
    }
    
    public void onClickNotificationButton(View view){
    	
    	Log.d(LOG, "onClickNotificationButton");
    	    	
    	boolean notification_off = !prefs_.getNotificationOff();
    	
    	prefs_.setNotifiationOff(notification_off);
    	
    	setNotification();
    }
    
    public void setNotification()
    {
    	Log.d(LOG, "setNotification");
    	
		boolean notification_off = prefs_.getNotificationOff();
    	
    	if( notification_off )
    	{
    		Log.d(LOG, "notification off");
    		
    		notification_button.setCompoundDrawablesWithIntrinsicBounds(R.drawable.setting_item1_icon, 0, R.drawable.setting_swtich_off, 0);            
    	}
    	else
    	{
    		Log.d(LOG, "notification on");
    		
    		notification_button.setCompoundDrawablesWithIntrinsicBounds(R.drawable.setting_item1_icon, 0, R.drawable.setting_swtich_on, 0);
    	}
    	
    	notify_intent_.putExtra("notification_off", notification_off); 
        sendBroadcast(notify_intent_);  
        
    }
    
    public void onClickDevOptionButton(View view){
    	
    	Intent intent = new Intent(this, DevOptionActivity.class);
    	
    	startActivity(intent);
    }
    
    public void updateUI()
    {
    	Log.d(LOG, "updateUI");
    	
    	boolean dev_option_enabled = prefs_.getDevOption();
    	devoption_button.setEnabled(dev_option_enabled);
    	
    	if( dev_option_enabled )
    	{
    		Log.d(LOG, "dev option enabled.");
    		
    		devoption_button.setVisibility(View.VISIBLE);
    	}
    	else
    	{
    		Log.d(LOG, "dev option disabled.");
    		devoption_button.setVisibility(View.INVISIBLE);
    	}
    }
    
}
