/**
 * 
 */
package com.example.bodyspeedchecker.api;

/**
 * @author eigo
 *
 */
public interface WebAPIListener {

	public void onFailture(WetherAPIResponse response);
	public void onSuccess(WetherAPIResponse response);
	
}
