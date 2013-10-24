package com.codepath.apps.twitterapp.models;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
	private String name;
	private String profilePic;
	private String screenName;
	
	public String getName() {
		return name;
	}
	
	public String getProfilePic() {
		return profilePic;
	}
	
	public String getScreenName() {
		return screenName;
	}
	
	public static User fromJSON(JSONObject jsonUser) {
		User u = new User();
		try {
			u.name = jsonUser.getString("name");
			u.profilePic = jsonUser.getString("profile_image_url");
			u.screenName = jsonUser.getString("screen_name");
			
		} catch(JSONException e) {
			e.printStackTrace();
			return null;
		}
		
		return u;
	}
}
