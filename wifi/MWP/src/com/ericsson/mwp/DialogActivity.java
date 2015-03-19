package com.ericsson.mwp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class DialogActivity extends Activity {

	private int iod_rds_ = 0;
	
	private String NotifyService = "com.ericsson.mwp.NOTIFY";
	private Intent notify_intent_ = new Intent(NotifyService);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//		setContentView(R.layout.activity_dialog);
		
		Bundle extras = getIntent().getExtras();
		
		
		if (extras != null) {
			iod_rds_ = extras.getInt("iod_rds");
		}
		
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				this);
 
		// set title
		alertDialogBuilder.setTitle("You are close to ...");
		
		ImageView img = new ImageView(this);
		
		if( iod_rds_==0 )
			img.setImageResource(R.drawable.demo_pop_iod);
		else
			img.setImageResource(R.drawable.demo_pop_rds);
 
		// set dialog message
		alertDialogBuilder
//			.setMessage("Click yes to exit!")
			.setView(img)
			.setCancelable(false)
			.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					// if this button is clicked, close
					// current activity
					
					Intent intent = null;
			    	
			    	switch( iod_rds_ )
			    	{
			    	case 0:
			    		intent = new Intent(DialogActivity.this, IODActivity.class);
			    		break;
			    		
			    	case 1:
			    		intent = new Intent(DialogActivity.this, RDSActivity.class);
			    		break;
			    	};
			    	
//			    	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			    	startActivity(intent);
			    	
//			    	DialogActivity.this.setResult(RESULT_OK);
			    	DialogActivity.this.finish();
			    	
			    	notify_intent_.putExtra("CUR_IOD_RDS", iod_rds_); 
			        sendBroadcast(notify_intent_);  
				}
			  })
			.setNegativeButton("No",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					// if this button is clicked, just close
					// the dialog box and do nothing
					
//					Intent returnIntent = new Intent();
//					DialogActivity.this.setResult(RESULT_CANCELED);
					
					DialogActivity.this.finish();
				}
			});
 
			// create alert dialog
			AlertDialog alertDialog = alertDialogBuilder.create();
 
				// show it
			alertDialog.show();
		}
}
