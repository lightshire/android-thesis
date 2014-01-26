package com.weatherapp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
 
public class LocationActivity extends ListActivity {
	
	int currentIndex = -1;
	TextView currentTab = null;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.locationactivity_layout);
        initListView();
        fixListViewEvents();

    }
    
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		//super.onCreateContextMenu(menu, v, menuInfo);
		getMenuInflater().inflate(R.menu.main, menu);
	}
	private void fixListViewEvents() {
		getListView().setOnItemClickListener(new OnItemClickListener() {
			 @Override
	            public void onItemClick(AdapterView<?> parent, View view, int position,
	                    long id) {
				 Map entry = (HashMap) parent.getAdapter().getItem(position);
				 int index = position;
				 String header = (String) entry.get("text1");
				 switchIntent(index, header, view);
			}
			
		});
	}
	private void switchIntent(int index, String header, View view) {
		switch(index) {
			case 0: //location
				initiateIntentLocation(header, view);
				break;
			case 1: //from
				initateDatePickerActivity(header, view);
				break;
			case 2: //to
				initateDatePickerActivity(header, view);
				break;
			case 3: //map
				initiateMapIntent(header);
				break;
		}
	}
	private void initateDatePickerActivity(String header, View view) {
		Intent intent = new Intent(this, DateTimePickerActivity.class);
		startActivityForResult(intent, 2);
		currentTab =  (TextView)view.findViewById(android.R.id.text2);
		currentIndex = 1;
	}
	private void initiateMapIntent(String header) {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(LocationActivity.this);
		alertDialog.setTitle(header);
		alertDialog.setMessage("The Map is Coming Soon!");
		alertDialog.setCancelable(true);
		alertDialog.setPositiveButton("OK",  new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			}
		});
		alertDialog.create();
		alertDialog.show();
	}
	private void initiateIntentLocation(String header, View view) {
		Intent intent = new Intent(this, LocationPopUpActivity.class);
		startActivityForResult(intent, 1);
		currentTab =  (TextView)view.findViewById(android.R.id.text2);
		currentIndex = 0;
	}
	
	private void launchLocationResult(int requestCode, int resultCode, Intent data) {
		String locations = data.getStringExtra("locationList");
		currentTab.setText(locations);
	}
	
	public void launchDateResult(int requestCode, int resultCode, Intent data) {
		String date = data.getStringExtra("dateSelected");
		currentTab.setText(date);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode == RESULT_OK) {
			switch(requestCode) {
			case 1:
				launchLocationResult(requestCode, resultCode, data);
				break;
			case 2:
				launchDateResult(requestCode, resultCode, data);
				break;
			case 3:
				launchDateResult(requestCode, resultCode, data);
				break;
			}
		}
	}
	
    private void initListView() {
    	//get current date
    	Calendar c = Calendar.getInstance();
    	SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
    	String formattedDate = df.format(c.getTime());
    
    	//fix string arrays
    	String[] mapKeys = {"text1","text2"};
    	String[] settingHeader 	= {"Location","From","To","Map"};
    	String[] settingItem 	= {"Baguio", formattedDate, formattedDate,"Coming Soon.."}; 
    	
    	//layout
    	int[] layouts = new int[]{android.R.id.text1, android.R.id.text2};
    	
    	//hashmap
    	List<Map<String, String>> list = new ArrayList<Map<String, String>>();
    	
    	for(int i = 0; i < settingHeader.length; i++) {
    		Map<String, String> map = new HashMap<String, String>();
    		map.put(mapKeys[0], settingHeader[i]);
    		map.put(mapKeys[1], settingItem[i]);
    		list.add(map);
    	}
    	
    	ListAdapter adapter = new SimpleAdapter(this, list, android.R.layout.simple_list_item_2, mapKeys, layouts);
    	setListAdapter(adapter);
    	
    }

}
