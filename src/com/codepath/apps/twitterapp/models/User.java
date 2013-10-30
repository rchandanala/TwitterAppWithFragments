package com.codepath.apps.twitterapp.models;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String profilePic;
	private String description;
	private String screenName;
	private int followersCnt;
	private int followingCnt;
	private long id;
	
	public long getId() {
		return id;
	}
	public int getFollowersCnt() {
		return followersCnt;
	}
	
	public int getFollowingCnt() {
		return followingCnt;
	}
	
	public String getName() {
		return name;
	}
	
	public String getTagline() {
		return description;
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
			u.followersCnt = jsonUser.getInt("followers_count");
			u.followingCnt = jsonUser.getInt("friends_count");
			u.description = jsonUser.getString("description");
			u.id = jsonUser.getLong("id");
		} catch(JSONException e) {
			e.printStackTrace();
			return null;
		}
		
		return u;
	}
	
	public String toString() {
	     return "Name " + name;	
	}
}
