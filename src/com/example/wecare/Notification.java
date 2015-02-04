package com.example.wecare;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.os.Build;

public class Notification extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alert);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}

	}
	
	public void alert(View view) {
		Vibrator vibe = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE) ;
		vibe.vibrate(100);
		
		Intent callIntent = new Intent(Intent.ACTION_CALL);
		callIntent.setData(Uri.parse("tel:96547197"));
		startActivity(callIntent);
		
		MyLocationListener gps;

		gps = new MyLocationListener(Notification.this);
		double latitude;
		double longitude;

		// check if GPS enabled
		if (gps.canGetLocation()) {

			latitude = gps.getLatitude();
			longitude = gps.getLongitude();

			String myMsg = "Help, I am in trouble! I am at: \n"
					+ "http://maps.google.com/maps?q="
					+ String.valueOf(latitude)
					+ "+"
					+ String.valueOf(longitude);

			String theNumber = "96547197";
			Sender sender = new Sender();
			sender.sendMsg(theNumber, myMsg);
			
			
			

			//updatePositionIfLocationMoves(latitude, longitude);

		} else {
			// can't get location
			// GPS or Network is not enabled
			// Ask user to enable GPS/network in settings
			gps.showSettingsAlert();

			String myMsg = "I am lost and cannot track the location I am at!";
			String theNumber = "96547197";
			Sender sender = new Sender();
			sender.sendMsg(theNumber, myMsg);

		}
		
		
		
	}
	
	public void checkin(View view) {
		//every hour you are supposed to check if with your family, or else it will go off...
		Vibrator vibe = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE) ;
		vibe.vibrate(100);
		
		MyLocationListener gps;

		gps = new MyLocationListener(Notification.this);
		double latitude;
		double longitude;

		// check if GPS enabled
		if (gps.canGetLocation()) {

			latitude = gps.getLatitude();
			longitude = gps.getLongitude();

			String myMsg = "Just checking in! I am at: \n"
					+ "http://maps.google.com/maps?q="
					+ String.valueOf(latitude)
					+ "+"
					+ String.valueOf(longitude);;

			String theNumber = "96547197";
			Sender sender = new Sender();
			sender.sendMsg(theNumber, myMsg);

			//updatePositionIfLocationMoves(latitude, longitude);

		} else {
			// can't get location
			// GPS or Network is not enabled
			// Ask user to enable GPS/network in settings
			gps.showSettingsAlert();

			String myMsg = "I am lost and cannot track the location I am at!";
			String theNumber = "96547197";
			Sender sender = new Sender();
			sender.sendMsg(theNumber, myMsg);

		}
	}
	
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alert, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_notification,
					container, false);
			return rootView;
		}
	}
//	private void updatePositionIfLocationMoves(double latitude, double longitude) {
//
//		MyLocationListener gps = null;
//		
//		Location location = gps.getLocation();
//		while (gps.canGetLocation()) {
//			if (Math.sqrt(Math.pow(gps.getLatitude() - latitude, 2)
//					+ Math.pow(gps.getLongitude() - longitude, 2)) > 1) {
//				latitude = gps.getLatitude();
//				longitude = gps.getLongitude();
//			}
//
//		}
//
//	}
}
