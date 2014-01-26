package com.weatherapp;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.WindowManager.LayoutParams;

import com.classes.Location;
import com.classes.LocationAdapter;
import com.classes.LocationListener;
import com.classes.LocationPassedListener;

public class LocationPopUpActivity extends ListActivity {

	LocationAdapter dataAdapter = null;
	ArrayList<Location> _locationList = new ArrayList<Location>();
	ArrayList<LocationPassedListener> listeners = new ArrayList<LocationPassedListener>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("Select Location..");
		setContentView(R.layout.activity_location_pop_up);
		getWindow().setLayout (LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		
		initListView();
	}

	private void initListView() {
		ArrayList<Location> locationList = new ArrayList<Location>();
		String[] initialLocation = {"Abra","Benguet","Baguio", "Badjao", "Badjao"};
		
		for(int i = 0; i < initialLocation.length; i++) {
			locationList.add(new Location(i, initialLocation[i]));
		}
		
		dataAdapter = new LocationAdapter(this, R.layout.location_info, locationList);
		dataAdapter.addListener(new LocationListener() {
			
			@Override
			public void locationListRefreshed(ArrayList<Location> loc) {
				_locationList.clear();
				for(Location _l : loc) {
					_locationList.add(_l);
				}
			}
		});
		this.getListView().setAdapter(dataAdapter);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.location_pop_up, menu);
		return true;
	}
	
	public void closeActivity() {
		for(LocationPassedListener ls : listeners) {
			ls.passedLocation(_locationList);
		}
		this.finish();
	}
	
	@Override
	public void onBackPressed() {
		Intent intent = new Intent();
		String locations = "";
		int ctr = 0;
		
		for(int i = 0; i < _locationList.size(); i++) {
			if(i + 1 >= _locationList.size()) {
				locations += _locationList.get(i).getLocationName();
			}else {
				locations += _locationList.get(i).getLocationName()+", ";
			}
		}
		
	
		intent.putExtra("locationList", locations);
		setResult(RESULT_OK, intent);
		this.dataAdapter.clearSelected();
		_locationList.clear();
		this.finish();
	}
	
	

}
