/**
 * 
 */
package com.example.bodyspeedchecker.view.fragment;

import android.app.Fragment;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bodyspeedchecker.R;

/**
 * @author eigo
 *
 */
public class PlaceholderFragment extends Fragment implements OnClickListener, LocationListener {

	private static final String TAG = PlaceholderFragment.class.getName();

	private TextView mTVTaikanSpeed;
	private TextView mTVTemperature;
	private TextView mTVShitsudo;
	private TextView mTVJisoku;
	private Button mBtnInit;
	private Button mBtnClear;
	
	private LocationManager mLocationManager;

	private boolean status = false;

	/**
	 * 
	 */
	public PlaceholderFragment() {
		super();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate");

		super.onCreate(savedInstanceState);
		// SensorManagerのインスタンスを取得する
		mLocationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d(TAG, "onCreateView");
		View rootView = inflater.inflate(R.layout.fragment_main, container, false);

		initView(rootView);

		return rootView;
	}

	@Override
	public void onResume() {
		Log.d(TAG, "onResume");
		if (mLocationManager != null) {
			mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
		}

		super.onResume();
	}

	@Override
	public void onPause() {
		Log.d(TAG, "onPause");
		if (mLocationManager != null) {
			mLocationManager.removeUpdates(this);
		}

		super.onPause();
	}

	private void initView(View view) {

		mTVTaikanSpeed = (TextView)view.findViewById(R.id.taikansokudo_value);
		mTVTemperature = (TextView)view.findViewById(R.id.temperature_value);
		mTVShitsudo = (TextView)view.findViewById(R.id.shitsudo_value);
		mTVJisoku = (TextView)view.findViewById(R.id.jisoku_value);

		mBtnInit = (Button)view.findViewById(R.id.btn_init);
		mBtnInit.setOnClickListener(this);
		mBtnClear = (Button)view.findViewById(R.id.btn_clear);
		mBtnClear.setOnClickListener(this);

		clear();
	}

	@Override
	public void onClick(View view) {
		if (view == mBtnInit) {
			start();
		} else if (view == mBtnClear) {
			clear();
		}
	}

	private void start() {
		status = true;
		this.showToast("計測を開始します。");
	}

	private void clear() {
		mTVTaikanSpeed.setText(R.string.init_float);
		mTVTemperature.setText(R.string.init_float);
		mTVShitsudo.setText(R.string.init_int);
		mTVJisoku.setText(R.string.init_int);
		status = false;
	}

	@Override
	public void onLocationChanged(Location location) {
		Log.d(TAG, "onLocationChanged");

		if (status) {
			// 時速を反映
			float jisoku = location.getSpeed() * 60 *60;
			mTVJisoku.setText(Float.toString(jisoku));
		}
	}

	@Override
	public void onProviderDisabled(String provider) {
		Log.d(TAG, "onProviderDisabled provider:" + provider);

	}

	@Override
	public void onProviderEnabled(String provider) {
		Log.d(TAG, "onProviderEnabled provider:" + provider);

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle bundle) {
		Log.d(TAG, "onStatusChanged provider:" + provider + " status:" + Integer.toString(status));
		switch (status) {
		case LocationProvider.AVAILABLE:
			Log.v("Status", "AVAILABLE");
			showToast("Status AVAILABLE");
			break;
		case LocationProvider.OUT_OF_SERVICE:
			Log.v("Status", "OUT_OF_SERVICE");
			showToast("Status OUT_OF_SERVICE");
			break;
		case LocationProvider.TEMPORARILY_UNAVAILABLE:
			Log.v("Status", "TEMPORARILY_UNAVAILABLE");
			showToast("Status TEMPORARILY_UNAVAILABLE");
			break;
		}

	}

	private void showToast(String str) {
		Toast.makeText(getActivity(), str, Toast.LENGTH_LONG).show();
	}

}
