package com.ericsson.mwp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;


public class MainActivity extends Activity {

	private String LOG = "com.ericsson.mwp.MainActivity";
	
	private String NotifyService = "com.ericsson.mwp.NOTIFY";
	
	private Intent notify_intent_;  
	private NotifyReceiver notify_receiver_;  
	
	private NotificationService.NotifyBinder binder_ = null;  
	
	private WifiAdmin wifi_admin_ = null;
	
	Preferences prefs_;
	
	private boolean nearest_clicked = false;
	  
    private ServiceConnection connection_ = new ServiceConnection() {  
  
        @Override  
        public void onServiceDisconnected(ComponentName name) {  
        	Log.d(LOG, "onServiceDisconnected");
        	
        	binder_ = null;
        }  
  
        @Override  
        public void onServiceConnected(ComponentName name, IBinder service) { 
        	Log.d(LOG, "onServiceConnected");
        	
        	binder_ = (NotificationService.NotifyBinder) service;
        	
        	binder_.triggerStart();
        }  
    };  
	
	private int old_id_ = -1;
	private int cur_id_ = -1;
	
	private int DIALOG_REQUEST = 1;
	
	
	private GuiHandler gui_handler_ = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
//        register broadcast
        notify_receiver_ = new NotifyReceiver();  
        IntentFilter intent_filter = new IntentFilter();
        
        intent_filter.addAction(NotifyService);
        intent_filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        registerReceiver(notify_receiver_, intent_filter); 
        
        notify_intent_ = new Intent(NotifyService);
        startService(notify_intent_);
        
        wifi_admin_ = WifiAdmin.getInstance(this);
        
        gui_handler_ = new GuiHandler(this);
        
        prefs_ = new Preferences( getApplicationContext() );
        
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy); 
    }


    @Override
    protected void onDestroy(){
        super.onDestroy();
        
        if( NotificationService.isRunning() )
        {
        	unbindService(connection_);
        	
        	// stop the notification service
    		Intent stopIntent = new Intent(this, NotificationService.class);  
            stopService(stopIntent);  
        }
        
        stopService(notify_intent_);
        unregisterReceiver(notify_receiver_);  
    }
    
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//    	
//    	Log.d(LOG, "onActivityResult");
//    	
//        if (requestCode == DIALOG_REQUEST) {
//            
//            if (resultCode == RESULT_OK) {
//            	
//            	Log.d(LOG, "result ok");
//            	old_id_ = cur_id_;	
//            }
//            
//            Log.d(LOG, "the old id is " + String.valueOf(old_id_));
//        }
//    }
    
    /** Called when the user clicks the Nearest button */
    public void onClickNearestButton(View view) {
        
    	Log.d(LOG, "click Nearest button");
    	
    	if( nearest_clicked )
    		return ;
    	
    	nearest_clicked = true;
    	
    	wifi_admin_.startScan();
    	
//    	new Thread(){
//    	    @Override
//    	    public void run()
//    	    {
//    	    	int iod_rds = wifi_admin_.findNearest();
//
//    	    	Log.d(LOG, "iod_rds is " + String.valueOf(iod_rds));
//    	    	
//    	    	startNearestPlatform(iod_rds);
//    	    }
//    	}.start();
    	
    }
    
    public void onClickIODButton(View view) {
    	
    	Log.d(LOG, "click info on demand button");
        
    	Intent intent = new Intent(this, IODActivity.class);
    	
    	startActivity(intent);
    }
    
    
    public void onClickRDSButton(View view) {
        
    	Log.d(LOG, "click radio dot system button");
    	
    	Intent intent = new Intent(this, RDSActivity.class);
    	
    	startActivity(intent);
    }
    
    
    public void onClickSettingButton(View view) {
        
    	Intent intent = new Intent(this, SettingActivity.class);
    	
    	startActivity(intent);
    }
    
    public class NotifyReceiver extends BroadcastReceiver{  
    	  
        @Override  
        public void onReceive(Context context, Intent intent) {   
        	Log.d(LOG, "notify message received ...");      
        	
        	if (intent.getAction().equals(NotifyService))
        	{
        		Log.d(LOG, "receiver broadcast message is " + NotifyService + "...");
        		
	        	Bundle bundle = intent.getExtras();
	        	
	        	if( bundle.containsKey("notification_off") )
	        	{
	        		Log.d(LOG, "notification off ...");
	        		
	        		old_id_ = -1; // reset the currrent id
	        		
	        		boolean notification_off = intent.getBooleanExtra("notification_off", false);
	        		
	        		if( notification_off   )
	        		{
	        			if( NotificationService.isRunning() )
	        			{
		        			binder_.triggerStop();
		        			
		        			unbindService(connection_);
		        			
		    	    		// stop the notification service
		    	    		Intent stopIntent = new Intent(context, NotificationService.class);  
		    	            stopService(stopIntent); 
	        			}
	        		}
	        		else
	        		{
	        			if( !NotificationService.isRunning() )
	            		{
	        	    		// start notification service
	        	    		Intent startIntent = new Intent(context, NotificationService.class);  
	        	            startService(startIntent);  
	        	            
	        	            // bind service
	                        Intent bindIntent = new Intent(context, NotificationService.class);  
	                        bindService(bindIntent, connection_, BIND_AUTO_CREATE); 
	            		}
	        		}
	        	}
	        	
	        	if( bundle.containsKey("CUR_IOD_RDS") )
	        	{
	        		old_id_ = intent.getIntExtra("CUR_IOD_RDS", -1);
	        		
	        		Log.d(LOG, "CUR_IOD_RDS is " + String.valueOf(old_id_));
	        	}
	        	
//	        	if( bundle.containsKey("IOD_RDS") )
//	        	{
//		        	cur_id_ = intent.getIntExtra("IOD_RDS", -1);
//		        	
//		        	Log.d(LOG, "IOD_RDS received " + String.valueOf(cur_id_));
//	        	
//		        	if( cur_id_!=-1 && cur_id_!=old_id_ )
//		        	{	
//		        		Intent dialog_intent = new Intent(MainActivity.this, DialogActivity.class);
//		        		dialog_intent.putExtra("iod_rds", cur_id_);
//		        		startActivityForResult(dialog_intent, DIALOG_REQUEST);
//		        	}
//	        	}
        	}
        	
        	
        	if (intent.getAction().equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION))
        	{
        		Log.d(LOG, "receiver broadcast message is Wifi scan result ...");
        		
        		if( !nearest_clicked && prefs_.getNotificationOff() )
        		{
        			Log.d(LOG, "not triggered by this mwp, just ignore ...");
        			
        			return ;
        		}
        		
            	new Thread(){
	        	    @Override
	        	    public void run()
	        	    {
	        	    	int iod_rds = wifi_admin_.findNearest();
	    
	        	    	Log.d(LOG, "iod_rds is " + String.valueOf(iod_rds));
	        	    	
	//        	    	startNearestPlatform(iod_rds);
	        	    	
	        	    	if( prefs_.getNotificationOff() )
	        	    	{
	        	    		nearest_clicked = false;
	        	    		startNearestPlatform(iod_rds);
	        	    	}
	        	    	else
	        	    	{	
	//        	    		if( bundle.containsKey("IOD_RDS") )
	        	        	{
	//        		        	cur_id_ = intent.getIntExtra("IOD_RDS", -1);
	        	        		cur_id_ = iod_rds;
	        		        	
	        		        	Log.d(LOG, "IOD_RDS received " + String.valueOf(cur_id_));
	        	        	
	        		        	if( cur_id_!=-1 && cur_id_!=old_id_ )
	        		        	{	
	        		        		Intent dialog_intent = new Intent(MainActivity.this, DialogActivity.class);
	        		        		dialog_intent.putExtra("iod_rds", cur_id_);
	        		        		startActivityForResult(dialog_intent, DIALOG_REQUEST);
	        		        	}
	        	        	}
	                    	
	        	    	}
	        	    }
	        	}.start();
        		
//        		int iod_rds = wifi_admin_.findNearest();
        		
//    	    	Log.d(LOG, "iod_rds is " + String.valueOf(iod_rds));
    	    	
        	}
        }  
    }
    
    private void startNearestPlatform(int id)
    {
    	Intent intent = null;
	    	
    	switch( id )
    	{
    	case 0:
    		intent = new Intent(this, IODActivity.class);
    		break;
    		
    	case 1:
    		intent = new Intent(this, RDSActivity.class);
    		break;
    	};
    	
    	if( intent!=null )
    		startActivity(intent);
    	else
    	{
    		Message message = new Message();   
            message.what = GuiHandler.NO_CLOSING_PLATFORM;
            this.gui_handler_.sendMessage(message);
    	}
    }
    
}
