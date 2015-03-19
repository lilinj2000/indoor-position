package com.river.mwp;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class NotificationService extends Service {
	
//	private boolean DEMO = true;
	
	public static final String LOG = "com.river.mwp.NotificationService";
	
//	private String NotifyService = "com.river.mwp.NOTIFY";
	
//	private Intent notify_intent_ = new Intent(NotifyService);
	
	private NotifyBinder binder_ = new NotifyBinder();  
	
	public static NotificationService instance_ = null;
	
	private Preferences prefs_ ;
	
	private WifiAdmin wifi_admin_ = null;
	
	
	@Override  
    public void onCreate() {  
        super.onCreate(); 
        
        instance_ = this;
        
        prefs_ = new Preferences( getApplicationContext() );
        
        wifi_admin_ = WifiAdmin.getInstance(this);
        
        Log.d(LOG, "onCreate() executed");  
    }  
  
    @Override  
    public int onStartCommand(Intent intent, int flags, int startId) {  
        Log.d(LOG, "onStartCommand() executed");  
        
        return super.onStartCommand(intent, flags, startId);  
    }  
      
    @Override  
    public void onDestroy() {  
        super.onDestroy();
        
        instance_ = null;
        
        binder_.triggerStop();
        
        Log.d(LOG, "onDestroy() executed");  
    }  
  
    @Override  
    public IBinder onBind(Intent intent) {  
        return binder_;  
    }  
    
    public static boolean isRunning()
    {
    	return instance_!=null;
    }
    
    
    class NotifyBinder extends Binder {  
    	
    	ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);
    	
    	ScheduledFuture<?> future_ = null;
    	
        public void startDownload() {  
            Log.d(LOG, "startDownload() executed");  
        }  
        
        public void triggerStart()
        {
        	Log.d(LOG,  "triggerStart");
        	

        	
        	if( future_==null )
        		future_ = scheduledThreadPool.scheduleWithFixedDelay(new Runnable() {
	                
        			@Override
	                public void run() {
	                	Log.d(LOG, "running ...");

	                	wifi_admin_.startScan();
	                	
//	                	int iod_rds = -1;
//	                	
//	                	if( prefs_.getDemo() )
//	            		{
//	                		final Random random = new Random();
//	                		
////	                		handler_.post(new AlertDialogRunnable(random.nextInt(2)));
//	                		
//	            			iod_rds = random.nextInt(2); 
//	            		}
//	                	else
//	                	{
//	                		iod_rds = wifi_admin_.findNearest();
//	                	}
//	                	
//	                	notify_intent_.putExtra("IOD_RDS", iod_rds);
//	                	
//	                    sendBroadcast(notify_intent_);  
	//                    System.out.println("delay 3 seconds");
	                	
	//                	Toast.makeText(getApplication(), "you are closing iod platforam ...", Toast.LENGTH_LONG).show();
	                	
	                }
	            }, 1, prefs_.getNotificationInterval(), TimeUnit.SECONDS);
        }
        
        
        public void triggerStop()
        {
        	Log.d(LOG, "triggerStop");
        	
        	scheduledThreadPool.shutdown();
        }
        
        public void triggerNearest()
        {
        	
        }
  
    }  
    

}
