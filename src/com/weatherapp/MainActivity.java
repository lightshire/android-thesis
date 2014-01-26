package com.weatherapp;

import android.app.Activity;
import android.app.ActivityGroup;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends ActivityGroup {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.activity_main);
        initComponents();
    }
    private void initTabHost() {
    	 final TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
    
         Intent locationIntent = new Intent().setClass(this, LocationActivity.class);
       
         tabHost.setup(this.getLocalActivityManager());    
        
         
         
         TabSpec spec1 = tabHost.newTabSpec("location");
         TabSpec spec2 = tabHost.newTabSpec("weather");
         TabSpec spec3 = tabHost.newTabSpec("aboutus");
         
        
         spec1.setIndicator("Location");
         spec1.setContent(locationIntent);
         spec2.setIndicator("Weather");
         spec2.setContent(R.id.weather);
         spec3.setIndicator("About Us");
         spec3.setContent(R.id.about_us);
         
         tabHost.addTab(spec1);	
         tabHost.addTab(spec2);	
         tabHost.addTab(spec3);	
    }

    private void initComponents() {
    	 initTabHost();
    
    }
    @Override
    public void onBackPressed() {
    	android.os.Process.killProcess(android.os.Process.myPid());
    }
}
