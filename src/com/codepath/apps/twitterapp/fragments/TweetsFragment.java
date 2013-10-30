package com.codepath.apps.twitterapp.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.actionbarsherlock.app.SherlockFragment;
import com.codepath.apps.twitterapp.EndlessScrollListener;
import com.codepath.apps.twitterapp.R;
import com.codepath.apps.twitterapp.TweetsArrayAdapter;
import com.codepath.apps.twitterapp.models.Tweet;
import com.loopj.android.http.RequestParams;

import eu.erikw.PullToRefreshListView;
import eu.erikw.PullToRefreshListView.OnRefreshListener;

public abstract class TweetsFragment extends SherlockFragment {
	PullToRefreshListView lvTweets;
	//ListView lvTweets;
    protected TweetsArrayAdapter tweetAdapter;
    protected ArrayList<Tweet> tweetArray = new ArrayList<Tweet>();
    protected ProgressBar pb;
    
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		_configureViews();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_tweets, container, false);
		
	}
	
	public void loadNewTweets() {
		RequestParams params = new RequestParams();
		if (!tweetArray.isEmpty()) {
			Tweet firstTweet = tweetArray.get(0);
		    params.put("since_id", firstTweet.getId().toString());
		}
		loadMoreTweets(params, true);	
	}
	
	abstract public void actualLoadMoreTweets(RequestParams params, final boolean prepend);
	
	public void loadMoreTweets(RequestParams params, final boolean prepend) {
		pb.setVisibility(ProgressBar.VISIBLE);
		actualLoadMoreTweets(params, prepend);
		pb.setVisibility(ProgressBar.INVISIBLE);

	}

	private void _refresh() {
		loadNewTweets();
		lvTweets.onRefreshComplete();
	} 
	
	
private void _configureViews() {
	    pb = (ProgressBar) getActivity().findViewById(R.id.pbLoading);
		lvTweets = (PullToRefreshListView) getActivity().findViewById(R.id.lvTweets);
	    //lvTweets = (ListView) getActivity().findViewById(R.id.lvTweets);
		tweetAdapter = new TweetsArrayAdapter(getActivity(), tweetArray);
		lvTweets.setAdapter(tweetAdapter);
		/*RequestParams params = new RequestParams();
		loadMoreTweets(params, false);*/
		lvTweets.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				//tweetArray.clear();
				_refresh();
			}
			
		}); 
		lvTweets.setOnScrollListener(new EndlessScrollListener() {

			@Override
			public void onLoadMore(int page, int totalItemsCount) {
				// TODO Auto-generated method stub
				RequestParams params = new RequestParams();
				
				if (tweetArray.size() >0 ) {
					
				Tweet lastTweet = tweetArray.get(tweetArray.size()-1);
				
				Long id= lastTweet.getId()-1;
				params.put("max_id", id.toString());
				}
				loadMoreTweets(params, false);
				
			}
			
		});
	}
}
