package com.codepath.apps.twitterapp;

import java.util.ArrayList;

import org.json.JSONArray;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.codepath.apps.twitterapp.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import eu.erikw.PullToRefreshListView;
import eu.erikw.PullToRefreshListView.OnRefreshListener;

public class TimelineActivity extends Activity {
	ListView lvTweets;
    TweetsArrayAdapter tweetAdapter;
    ArrayList<Tweet> tweetArray = new ArrayList<Tweet>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		_configureViews();

	}
	
	private void _configureViews() {
		
		lvTweets = (ListView) findViewById(R.id.lvTweets);
		tweetAdapter = new TweetsArrayAdapter(this, tweetArray);
		lvTweets.setAdapter(tweetAdapter);
		loadMoreTweets(null);
		/*lvTweets.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				tweetArray.clear();
				loadMoreTweets(null);
			}
			
		}); */
		lvTweets.setOnScrollListener(new EndlessScrollListener() {

			@Override
			public void onLoadMore(int page, int totalItemsCount) {
				// TODO Auto-generated method stub
				//if (tweetArray.isEmpty()) { 
					//loadMoreTweets(null);
				//} else {
				Tweet lastTweet = tweetArray.get(tweetArray.size()-1);
				loadMoreTweets(lastTweet.getId()-1);
				//}
			}
			
		});
	}
	
	public void onCompose(MenuItem m) {
		Intent I = new Intent(this, ComposeActivity.class);
		startActivityForResult(I, 1);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if ( requestCode == 1) {
			tweetArray.clear();
			loadMoreTweets(null);
		}
	}
	
	public void loadMoreTweets(Long maxId) {
		Log.d("LOADING", "in Load More");
		TwitterClientApp.getRestClient().getTweets(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray jsonTweets) {
				// TODO Auto-generated method stub
				Log.d("DEBUG", jsonTweets.toString());
				ArrayList<Tweet> tweets = Tweet.fromJSONArray(jsonTweets);
				tweetArray.addAll(tweets);
				tweetAdapter.notifyDataSetChanged();
				Log.d("DEBUG", tweets.toString());
			}
		}, maxId); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.timeline, menu);
		return true;
	}

}
