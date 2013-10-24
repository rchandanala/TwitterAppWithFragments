package com.codepath.apps.twitterapp.models;

import org.json.JSONException;
import org.json.JSONObject;

public class BaseModel {
	protected JSONObject jsonObj;
	
	public String getString(String name) {
		try {
			return jsonObj.getString(name);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public int getInt(String name) {
		try {
			return jsonObj.getInt(name);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public boolean getBoolean(String name) {
		try {
			return jsonObj.getBoolean(name);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public double getDouble(String name) {
		try {
			return jsonObj.getDouble(name);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public Long getLong(String name) {
		try {
			return jsonObj.getLong(name);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0L;
		}
	}
	

}
