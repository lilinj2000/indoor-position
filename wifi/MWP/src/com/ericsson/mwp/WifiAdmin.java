package com.ericsson.mwp;

import java.util.List;
import java.util.Random;

import org.apache.http.HttpResponse;
//import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.SystemClock;
import android.util.Log;

public class WifiAdmin {

	private String LOG = "com.ericsson.mwp.WifiAdmin";
	
	private String service = Context.WIFI_SERVICE;
	private WifiManager wifi = null;
	
//	Context context_ = null;
	
	Preferences prefs_ ;
	
//	private WifiInfo wifiInfo = null;
//	private List<ScanResult> wifiList = null;
	
	private static  WifiAdmin wifiAdmin = null;
	
//	private boolean DEMO = true;
//	private String basic_url = "http://192.168.1.101:8080";
//	private String basic_url = "http://192.168.56.10:8080";
	
	
	public static WifiAdmin getInstance(Context context) {
        if(wifiAdmin == null) {
            wifiAdmin = new WifiAdmin(context);
            return wifiAdmin;
        }
        return wifiAdmin;
    }
	
	public void startScan()
	{
		Log.d(LOG, "start scan ...  ");
				
		wifi.startScan();
	}
	
	
	public int findNearest()
	{
		Log.d(LOG, "find nearest");
		
		if( prefs_.getDemo() )
		{
			Log.d(LOG, "it's demo.");
			final Random random = new Random();
			return random.nextInt(2);
		}
	
		try
		{
//			get location
			JSONObject loc = this.location();
			
			if( loc!=null )
			{
//				int x = loc.getInt("x");
//				int y = loc.getInt("y");
				
				String plat = loc.getString("Location");
				
				if( plat.equalsIgnoreCase("TV") )
					return 0;
				
				if( plat.equalsIgnoreCase("RDS") )
					return 1;
				
//				if( prefs_.getIODLeftTopX()<=x && x<=prefs_.getIODRightButtomX() 
//						&& prefs_.getIODLeftTopY()<=y && y<=prefs_.getIODRightButtomY() )
//					return 0;
//				
//				if( prefs_.getRDSLeftTopX()<=x && x<=prefs_.getRDSRightButtomX()
//						&& prefs_.getRDSLeftTopY()<=y && y<=prefs_.getRDSRightButtomY() )
//					return 1;
				
				Log.d(LOG, "loc is not match any:  " + loc.toString());
				
				return -1;
			}
			
			Log.d(LOG, "loc is null");
			
//			else
//				return 1;
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			Log.d(LOG, e.getMessage());
		}
		
		
		return 0;
	}
	
	private WifiAdmin(Context context) {
        this.wifi = (WifiManager) context.getSystemService(service);
        
//        context_ = context;
        
        prefs_ = new Preferences( context );
        
    }
	
	private void openWifi() {
		Log.d(LOG, "open wifi");
		
        if(!this.wifi.isWifiEnabled()){
            this.wifi.setWifiEnabled(true);
            
            while( !this.wifi.isWifiEnabled() )
            {
            	SystemClock.sleep(1000);
            	Log.d(LOG, "sleep 1s");
            }
        }
        
    }
	
	private JSONObject location()
	{
		Log.d(LOG, "location ...");
		
//		open wifi
		openWifi();
		
		try
		{
//			scan & gen the req json pkg
//			this.wifi.startScan();
			
			JSONObject json_req = jsonObject(this.wifi.getScanResults());
			
			DefaultHttpClient httpClient = new DefaultHttpClient();
			
			String url = "/location";
			
			String basic_url = prefs_.getBasicURL();
			Log.d(LOG, "url is " + basic_url + url);
			HttpPost httpPost = new HttpPost(basic_url + url);
//			HttpGet httpGet = new HttpGet(basic_url + url );
			
			StringEntity se = new StringEntity(json_req.toString());
	        se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
	        httpPost.setEntity(se);
	        
	        HttpResponse httpResponse = httpClient.execute(httpPost);
//	        
//			Log.d(LOG, "start http client execute ...");
//			HttpResponse httpResponse = httpClient.execute(httpGet);
//			Log.d(LOG, "start http client done.");
			
	        if( httpResponse!=null && httpResponse.getStatusLine().getStatusCode()==200)
	        {
	        	String content = EntityUtils.toString(httpResponse.getEntity());
	        	
	        	Log.d(LOG, "the receive message is: " + content);
	        	
	        	JSONObject loc = new JSONObject(content);
	        	
	        	Log.d(LOG, "the loc is: " + loc.toString());
	        	
	        	return loc;
	        }
		}
		catch(Exception e)
		{
			Log.d(LOG, e.getMessage());
		}
		
		return null;
	}
    
//    private void closeWifi() {
//        if(wifi.isWifiEnabled()) {
//        	wifi.setWifiEnabled(false);
//        }
//    }
//    
//    private String getBSSID() {
//        if (this.wifiInfo == null)
//            return "NULL";
//        
//        return this.wifiInfo.getBSSID();
//    }
//    
//    private int getIPAddress() {
//        return (wifiInfo == null) ? 0 : wifiInfo.getIpAddress();
//    }
// 
//    private String getMacAddress() {
//         return (wifiInfo == null) ? "NULL" : wifiInfo.getMacAddress();
//    }  
//                                                                                                                                                             
//    private int getNetworkId() {
//         return (wifiInfo == null) ? 0 : wifiInfo.getNetworkId();
//    }
    
//    private void scanWifi(){
//    	Log.d(LOG, "scan wifi");
//    	
//    	this.wifiList = this.wifi.getScanResults();
//    }
    
    private JSONObject jsonObject(List<ScanResult> wifiList)
    {
    	Log.d(LOG, "jsonObject");
    	
    	JSONObject req = new JSONObject();
    	
    	try
    	{
	    	JSONArray aps = new JSONArray();
	    	for (int i = 0; i < wifiList.size(); i++)
	    	{
	    		JSONObject ap = new JSONObject();
	    		ap.put("SSID", wifiList.get(i).SSID);
	    		ap.put("BSSID", wifiList.get(i).BSSID);
	    		ap.put("capabilities", wifiList.get(i).capabilities);
	    		ap.put("level", wifiList.get(i).level);
	    		ap.put("frequency", wifiList.get(i).frequency);
//	    		param.put("timestamp", wifiList.get(i).timestamp);
	    		
	    		Log.d(LOG, ap.toString());
	    		
	    		aps.put(ap);
	    		
	    	}
	    	
	    	
	    	req.put("APInfo", aps);
	    	Log.d(LOG, req.toString());
	    	
	    	
    	}
    	catch( Exception e)
    	{
    		Log.d(LOG, e.toString());
    	}
    	
    	return req;
    }
    
//    private StringBuilder lookUpScan() {
//    	Log.d(LOG, "look up scan");
//    	
//        StringBuilder localStringBuilder = new StringBuilder();
//        for (int i = 0; i < wifiList.size(); i++)
//        {
//            localStringBuilder.append("Index_");
//            localStringBuilder.append(i+1);
//            localStringBuilder.append(":");
//            localStringBuilder.append((wifiList.get(i)).toString());
//            localStringBuilder.append("\n");
//            
//            Log.d(LOG, wifiList.get(i).toString());
//        }
//        
//        Log.d(LOG, localStringBuilder.toString());
//        
//        return localStringBuilder;
//    }
}
