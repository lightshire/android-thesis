package com.classes;

import java.util.ArrayList;

import com.weatherapp.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

public class LocationAdapter extends ArrayAdapter<Location> {
	private ArrayList<Location> locationList = new ArrayList<Location>();
	private ArrayList<Location> selectedLocations = new ArrayList<Location>();
	private ArrayList<LocationListener> listeners = new ArrayList<LocationListener>();
	
	public void addListener(LocationListener listener) {
		listeners.add(listener);
	}
	
	public LocationAdapter(Context context, int textViewResourceId, ArrayList<Location> locationList) {
		super(context, textViewResourceId, locationList);
		this.locationList = new ArrayList<Location>();
		this.locationList.addAll(locationList);
	}
	
	private class ViewHolder {
		CheckBox name;
	}
	public void clearSelected() {
		selectedLocations.clear();
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		Log.v("ConvertView", String.valueOf(position));
		
		if(convertView == null) {
			LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = vi.inflate(R.layout.location_info, null);
			
			holder = new ViewHolder();
			holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
			holder.name.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					CheckBox cb  = (CheckBox) arg0;
					Location location = (Location)cb.getTag();
					if(cb.isChecked() && !selectedLocations.contains(location)) {
						selectedLocations.add(location);
					}else if(!cb.isChecked() && selectedLocations.contains(location)) {
						int index = selectedLocations.indexOf(location);
						selectedLocations.remove(index);
					}
					cb.setSelected(cb.isChecked());
					
					for(LocationListener ls : listeners) {
						ls.locationListRefreshed(selectedLocations);
					}
				
				}
			});
			convertView.setTag(holder);
			

		}else {
			holder = (ViewHolder) convertView.getTag();
		}

		Location location = locationList.get(position);
		holder.name.setText(location.getLocationName());
		holder.name.setTag(location);
		
		return convertView;
	}
}
