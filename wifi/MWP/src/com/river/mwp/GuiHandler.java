package com.river.mwp;

import java.lang.ref.WeakReference;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class GuiHandler extends Handler {
	
	public static final int NO_CLOSING_PLATFORM = 100;
	
	WeakReference<MainActivity> activity_;
	
	GuiHandler(MainActivity activity) {
		activity_ = new WeakReference<MainActivity>(activity);
	}
	
	public void handleMessage(Message msg) {   
			if ( activity_.get()==null )
			{
				return ;
			}
			
            switch (msg.what) {   
                 case NO_CLOSING_PLATFORM:   
                      Toast.makeText(activity_.get(), "No Closing Platform !!!", Toast.LENGTH_SHORT).show();
                      break;   
            }   
            super.handleMessage(msg);   
       }   

}
