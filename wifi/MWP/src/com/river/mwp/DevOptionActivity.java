package com.river.mwp;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class DevOptionActivity extends Activity {

	private String LOG = "com.river.mwp.DevOptionActivity";
	
	private CheckBox checkBoxDevOption;
	private CheckBox checkBoxDEMO;
	private EditText editTextBasicURL;
	private EditText editTextIODArea;
	private EditText editTextRDSArea;
	private EditText editTextNotificationInterval;
	
	Preferences prefs_ ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dev_option);
		
		checkBoxDevOption = (CheckBox) findViewById(R.id.checkBoxDevOption);
		checkBoxDEMO = (CheckBox) findViewById(R.id.checkBoxDEMO);
		editTextBasicURL = (EditText) findViewById(R.id.editTextBasicURL);
		editTextIODArea = (EditText) findViewById(R.id.editTextIODArea);
		editTextRDSArea = (EditText) findViewById(R.id.editTextRDSArea);
		editTextNotificationInterval = (EditText) findViewById(R.id.editTextNotificationInterval);
		
		editTextIODArea.setVisibility(View.GONE);
		editTextRDSArea.setVisibility(View.GONE);
		
		prefs_ = new Preferences( getApplicationContext() );
		
		updateUI();
		
		editTextBasicURL.addTextChangedListener(new TextWatcher(){
	        public void afterTextChanged(Editable s) {
	            
	        	prefs_.setBasicURL(s.toString());
	        	
	        }
	        public void beforeTextChanged(CharSequence s, int start, int count, int after){}
	        public void onTextChanged(CharSequence s, int start, int before, int count){}
	    }); 
		
		editTextIODArea.addTextChangedListener(new TextWatcher(){
	        public void afterTextChanged(Editable s) {
	            String area = s.toString();
	            
	            Log.d(LOG, area);
	            
	            prefs_.setArea(1, area);
	            
	            Log.d(LOG, prefs_.getIODArea());
	            
	        }
	        public void beforeTextChanged(CharSequence s, int start, int count, int after){}
	        public void onTextChanged(CharSequence s, int start, int before, int count){}
	    }); 
		
		editTextRDSArea.addTextChangedListener(new TextWatcher(){
	        public void afterTextChanged(Editable s) {
	            String area = s.toString();
	            
	            Log.d(LOG, area);
	            
	            prefs_.setArea(2, area);
	            
	            Log.d(LOG, prefs_.getRDSArea());
	            
	        }
	        public void beforeTextChanged(CharSequence s, int start, int count, int after){}
	        public void onTextChanged(CharSequence s, int start, int before, int count){}
	    }); 
		
		editTextNotificationInterval.addTextChangedListener(new TextWatcher(){
	        public void afterTextChanged(Editable s) {
	            
	        	prefs_.setNotificationInterval(Integer.valueOf(s.toString()));
	        	
	        }
	        public void beforeTextChanged(CharSequence s, int start, int count, int after){}
	        public void onTextChanged(CharSequence s, int start, int before, int count){}
	    }); 
	}
	
	public void onClickDevOption(View view)
	{
		boolean checked = checkBoxDevOption.isChecked();
		
		prefs_.setDevOption(checked);
				
		checkBoxDEMO.setEnabled(checked);
		editTextBasicURL.setEnabled(checked);
		editTextIODArea.setEnabled(checked);
		editTextRDSArea.setEnabled(checked);
		editTextNotificationInterval.setEnabled(checked);
	}
	
	public void onClickDEMO(View view)
	{
		boolean checked = checkBoxDEMO.isChecked();
		
		prefs_.setDEMO(checked);
	}
	
	private void updateUI()
	{	
		checkBoxDevOption.setChecked(prefs_.getDevOption());
		checkBoxDEMO.setChecked(prefs_.getDemo());
		
		editTextBasicURL.setText(prefs_.getBasicURL());
		editTextIODArea.setText(prefs_.getIODArea());
		editTextRDSArea.setText(prefs_.getRDSArea());
		
		editTextNotificationInterval.setText(String.valueOf(prefs_.getNotificationInterval()));
	}
	
}
