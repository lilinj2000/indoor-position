package com.river.mwp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.util.Log;

public class Preferences {

	private String LOG = "com.river.mwp.Preference";
	
	SharedPreferences prefs_;
	
	private static final String PREF_DEV_OPTION = "PREF_DEV_OPTION";
	private static final String PREF_DEMO = "PREF_DEMO";
	private static final String PREF_BASIC_URL = "PREF_BASIC_URL";
	private static final String PREF_IOD_LEFT_TOP_X = "PREF_IOD_LEFT_TOP_X";
	private static final String PREF_IOD_LEFT_TOP_Y = "PREF_IOD_LEFT_TOP_Y";
	private static final String PREF_IOD_RIGHT_BOTTOM_X = "PREF_IOD_RIGHT_BUTTOM_X";
	private static final String PREF_IOD_RIGHT_BOTTOM_Y = "PREF_IOD_RIGHT_BUTTOM_Y";
	private static final String PREF_RDS_LEFT_TOP_X = "PREF_RDS_LEFT_TOP_X";
	private static final String PREF_RDS_LEFT_TOP_Y = "PREF_RDS_LEFT_TOP_Y";
	private static final String PREF_RDS_RIGHT_BOTTOM_X = "PREF_RDS_RIGHT_BUTTOM_X";
	private static final String PREF_RDS_RIGHT_BOTTOM_Y = "PREF_RDS_RIGHT_BUTTOM_Y";
	private static final String PREF_NOTIFICATION_OFF = "PREF_NOTIFICATION_OFF";
	private static final String PREF_NOTIFICATION_INTERVAL = "PREF_NOTIFICATION_INTERVAL";
		
	public Preferences(Context context)
	{
//		Context context = getApplicationContext();
		prefs_ = PreferenceManager.getDefaultSharedPreferences(context);
	}
	
	
	public void setBasicURL(String basic_url)
	{
		Editor edit = prefs_.edit();
		edit.putString(PREF_BASIC_URL, basic_url);
		edit.commit();
	}
	
	public void setDevOption(boolean checked)
	{
		Editor edit = prefs_.edit();
		edit.putBoolean(PREF_DEV_OPTION, checked);
		edit.commit();
	}
	
	public void setDEMO(boolean checked)
	{
		Editor edit = prefs_.edit();
		edit.putBoolean(PREF_DEMO, checked);
		edit.commit();
	}
	
	public boolean getDevOption()
	{
		return prefs_.getBoolean(PREF_DEV_OPTION, true);
	}
	
	public boolean getDemo()
	{
		return prefs_.getBoolean(PREF_DEMO, true);
	}
	
	public String getBasicURL()
	{
		return prefs_.getString(PREF_BASIC_URL, "http://192.168.1.100:8080");
	}
	
	public int getIODLeftTopX()
	{
		return prefs_.getInt(PREF_IOD_LEFT_TOP_X, 0);
	}
	
	public int getIODLeftTopY()
	{
		return prefs_.getInt(PREF_IOD_LEFT_TOP_Y, 0);
	}
	
	public int getIODRightButtomX()
	{
		return prefs_.getInt(PREF_IOD_RIGHT_BOTTOM_X, 8);
	}
	
	public int getIODRightButtomY()
	{
		return prefs_.getInt(PREF_IOD_RIGHT_BOTTOM_Y, 5);
	}
	
	public String getIODArea()
	{
		int x1 =  getIODLeftTopX();
		int y1 =  getIODLeftTopY();
		
		int x2 =  getIODRightButtomX();
		int y2 =  getIODRightButtomY();
		
		
		return "(" + String.valueOf(x1)+"," + String.valueOf(y1) + ";" + String.valueOf(x2) + "," + String.valueOf(y2) + ")";
	}
	
	public int getRDSLeftTopX()
	{
		return prefs_.getInt(PREF_RDS_LEFT_TOP_X, 9);
	}
	
	public int getRDSLeftTopY()
	{
		return prefs_.getInt(PREF_RDS_LEFT_TOP_Y, 6);
	}
	
	public int getRDSRightButtomX()
	{
		return prefs_.getInt(PREF_RDS_RIGHT_BOTTOM_X, 14);
	}
	
	public int getRDSRightButtomY()
	{
		return prefs_.getInt(PREF_RDS_RIGHT_BOTTOM_Y, 10);
	}
	
	public String getRDSArea()
	{
		int x1 =  getRDSLeftTopX();
		int y1 =  getRDSLeftTopY();
		
		int x2 =  getRDSRightButtomX();
		int y2 =  getRDSRightButtomY();
		
		
		return "(" + String.valueOf(x1)+"," + String.valueOf(y1) + ";" + String.valueOf(x2) + "," + String.valueOf(y2) + ")";
	}
	
	public boolean getNotificationOff()
	{
		return prefs_.getBoolean(PREF_NOTIFICATION_OFF, true);
	}
	
	public void setNotifiationOff(boolean notification_off)
	{
		Editor editor = prefs_.edit();
		editor.putBoolean(PREF_NOTIFICATION_OFF, notification_off);
		editor.commit();
	}
	
	public int getNotificationInterval()
	{
		return prefs_.getInt(PREF_NOTIFICATION_INTERVAL, 10);
	}
	
	public void setNotificationInterval(int notification_interval)
	{
		Editor editor = prefs_.edit();
		editor.putInt(PREF_NOTIFICATION_INTERVAL, notification_interval);
		editor.commit();
	}
	
	public void setArea(int flag, String area)
	{
		Pattern pattern = Pattern.compile("^\\((\\d+),(\\d+);(\\d+),(\\d+)\\)$");
	    
	    Matcher matcher = pattern.matcher(area);
	    
	    if( matcher.find() )
	    {
	    	Log.d(LOG, matcher.group());
	    	Log.d(LOG,  String.valueOf(matcher.groupCount()));
	    	
	    	Log.d(LOG, matcher.group(1));
	    	Log.d(LOG, matcher.group(2));
	    	Log.d(LOG, matcher.group(3));
	    	Log.d(LOG, matcher.group(4));
	    	
	        int x1 = Integer.valueOf(matcher.group(1));
	        int y1 = Integer.valueOf(matcher.group(2));
	        int x2 = Integer.valueOf(matcher.group(3));
	        int y2 = Integer.valueOf(matcher.group(4));
	        
	        Editor edit = prefs_.edit();
	        
	        if( flag==1 )
	        {
				edit.putInt(PREF_IOD_LEFT_TOP_X, x1);
				edit.putInt(PREF_IOD_LEFT_TOP_Y, y1);
				edit.putInt(PREF_IOD_RIGHT_BOTTOM_X, x2);
				edit.putInt(PREF_IOD_RIGHT_BOTTOM_Y, y2);
	        }
	        else
	        {
	        	edit.putInt(PREF_RDS_LEFT_TOP_X, x1);
				edit.putInt(PREF_RDS_LEFT_TOP_Y, y1);
				edit.putInt(PREF_RDS_RIGHT_BOTTOM_X, x2);
				edit.putInt(PREF_RDS_RIGHT_BOTTOM_Y, y2);
	        }
	        edit.commit();
	    }
	}
	
}
