package com.codepath.apps.twitterapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.codepath.apps.twitterapp.fragments.HomeTimelineFragment;
import com.codepath.apps.twitterapp.fragments.MentionsTimelineFragment;
import com.codepath.apps.twitterapp.fragments.TweetsFragment;
import com.codepath.apps.twitterapp.models.User;


public class TimelineActivity extends SherlockFragmentActivity  {
	
	SherlockTabListener<HomeTimelineFragment> homeTabListener;
	SherlockTabListener<MentionsTimelineFragment> mentionsTabListener;
	
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getSupportMenuInflater().inflate(R.menu.timeline, menu);
			return true;
	 }
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		/*FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.frame_container, new HomeTimelineFragment());
		// or ft.add(R.id.your_placeholder, new FooFragment());
		ft.commit(); */
		setupTabs();

	}
	
	
	private void setupTabs() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setDisplayShowTitleEnabled(true);
		homeTabListener = new SherlockTabListener<HomeTimelineFragment>(R.id.frame_container, this, "HomeTimelineFragment",
				HomeTimelineFragment.class);
		mentionsTabListener = new SherlockTabListener<MentionsTimelineFragment>(R.id.frame_container, this, "MentionsFragment",
				MentionsTimelineFragment.class);
		
		Tab tabFirst = actionBar
			.newTab()
			.setText("HOME")
			.setIcon(R.drawable.ic_home)
			.setTabListener(homeTabListener);

		actionBar.addTab(tabFirst);
		actionBar.selectTab(tabFirst);
		
		Tab tabSecond = actionBar
			.newTab()
			.setText("MENTIONS")
			.setIcon(R.drawable.ic_mentions)
			.setTabListener(mentionsTabListener);
			

		actionBar.addTab(tabSecond);
	}
	

	public void onCompose(MenuItem m) {
		Intent I = new Intent(this, ComposeActivity.class);
		startActivityForResult(I, 1);
	}
	
	public void onProfile(MenuItem m) {
		Intent I = new Intent(this, ProfileActivity.class);
		startActivity(I);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if ( requestCode == 1) {
			 FragmentManager fragmentManager = getSupportFragmentManager();
			 TweetsFragment currFragment = (TweetsFragment) fragmentManager.findFragmentById(R.id.frame_container);
			 currFragment.loadNewTweets();
		}
	}
	
	public void onProfileShow(View v) {
		Intent I = new Intent(this, ProfileActivity.class);
	    I.putExtra("user", (User)v.getTag());
		startActivity(I);
	}
	

}
