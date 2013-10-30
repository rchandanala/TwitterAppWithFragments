package com.codepath.apps.twitterapp.fragments;

import java.util.ArrayList;

import org.json.JSONArray;

import android.util.Log;

import com.codepath.apps.twitterapp.TwitterClientApp;
import com.codepath.apps.twitterapp.models.Tweet;
import com.codepath.apps.twitterapp.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class UserTimelineFragment extends TweetsFragment {

	public void actualLoadMoreTweets(RequestParams params, final boolean prepend) {
		Log.d("LOADING", "in Load user profile");
		User u;
		if (getActivity().getIntent().hasExtra("user")) {
			u = (User) getActivity().getIntent().getExtras().get("user");
			params.put("user_id", String.valueOf(u.getId()));
			params.put("screen_name", u.getScreenName());
		}
		TwitterClientApp.getRestClient().getProfileTimeline(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray jsonTweets) {
				// TODO Auto-generated method stub
				Log.d("DEBUG", jsonTweets.toString());
				ArrayList<Tweet> tweets = Tweet.fromJSONArray(jsonTweets);
				if (prepend) {
					tweetArray.addAll(0, tweets); }
				else {
					tweetArray.addAll(tweets);
				}
				tweetAdapter.notifyDataSetChanged();
				Log.d("DEBUG", tweets.toString());
				
			}
		}, params); 
	}

}
