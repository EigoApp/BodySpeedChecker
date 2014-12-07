/**
 * 
 */
package com.example.bodyspeedchecker.api;

import android.os.AsyncTask;

/**
 * @author eigo
 *
 */
public class WetherAPITask extends AsyncTask<String, Integer, WetherAPIResponse> {
	
	private WebAPIListener mListener;
	
	public WetherAPITask(WebAPIListener lisener) {
		mListener = lisener;
	}

	@Override
	protected WetherAPIResponse doInBackground(String... arg0) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	protected void onPostExecute(WetherAPIResponse result) {
		// TODO 自動生成されたメソッド・スタブ
		super.onPostExecute(result);
		
		
	}

}
