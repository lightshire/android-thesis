package com.weatherapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;

public class DateTimePickerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_date_time_picker);
		setTitle("Select Date..");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.date_time_picker, menu);
		return true;
	}
	
	public void closeActivity(View v) {
		this.finish();
	}
	
	@Override
	public void onBackPressed() {
		Intent intent = new Intent();
		DatePicker picker = (DatePicker) findViewById(R.id.datePicker1);
		String date = picker.getMonth()+"/"+picker.getDayOfMonth()+"/"+picker.getYear();
		intent.putExtra("dateSelected", date);
		setResult(RESULT_OK, intent);
		this.finish();
	}
}
