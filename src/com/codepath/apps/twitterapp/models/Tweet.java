package com.codepath.apps.twitterapp.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Tweet {
	private User user;
	private String text;
	private Long id;
		
	public Long getId() {
		return id;
	}
	
	public String getText() {
		return text;
	}
	
	public User getUser() {
		return user;
	}
	
	public static Tweet fromJSON(JSONObject jsonTweet) {
		Tweet t = new Tweet();
		
		try {
			t.id = jsonTweet.getLong("id");
			t.text = jsonTweet.getString("text");
			t.user = User.fromJSON(jsonTweet.getJSONObject("user"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return t;
		
	}
	
	public static ArrayList<Tweet> fromJSONArray(JSONArray jsonTweets) {
		ArrayList<Tweet> tweets = new ArrayList<Tweet> ();
		for (int i=0; i<jsonTweets.length(); i++) {
			try {
				Tweet t = Tweet.fromJSON(jsonTweets.getJSONObject(i));
				if (t!= null) {
					tweets.add(t);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return tweets;
	}

}
