package com.codepath.apps.twitterapp.fragments;

import java.util.ArrayList;

import org.json.JSONArray;

import android.os.Bundle;
import android.util.Log;

import com.codepath.apps.twitterapp.TwitterClientApp;
import com.codepath.apps.twitterapp.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class MentionsTimelineFragment extends TweetsFragment{
	
	
	public void actualLoadMoreTweets(RequestParams params, final boolean prepend) {
		
		TwitterClientApp.getRestClient().getMentionTweets(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray jsonTweets) {
				// TODO Auto-generated method stub
				
				ArrayList<Tweet> tweets = Tweet.fromJSONArray(jsonTweets);
				if (prepend) {
					tweetArray.addAll(0, tweets); }
				else {
					tweetArray.addAll(tweets);
				}
				tweetAdapter.notifyDataSetChanged();
				
				
			}
		}, params); 
	}

}
