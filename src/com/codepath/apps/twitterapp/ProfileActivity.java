package com.codepath.apps.twitterapp;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.codepath.apps.twitterapp.fragments.ProfileHeaderFragment;
import com.codepath.apps.twitterapp.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;

public class ProfileActivity extends SherlockFragmentActivity {
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		Bundle extrasMap = getIntent().getExtras();
		User u;
		if (extrasMap!=null && extrasMap.containsKey("user")) {
			u = (User) extrasMap.get("user");
			_loadUserProfile(u);
		} else {
			loadCurrentProfile();
		}	
		
	}
	
	public void onProfileShow(View v) {
		return;
	}
	
	private void _loadUserProfile(User u) {
		Log.d("DEBUG", "in Here " + u.getScreenName());
		getSupportActionBar().setTitle("@" + u.getScreenName());
		//actionbar.setDisplayShowTitleEnabled(true);
		 FragmentManager fragmentManager = getSupportFragmentManager();
		 ProfileHeaderFragment currFragment = (ProfileHeaderFragment) fragmentManager.findFragmentById(R.id.fargmentProfileHeader);
		 Log.d("DEBUG", "trying to load " + u);
		 currFragment.loadUserProfile(u);
	}
	
	/*private void loadUserProfile(User u) {
		if ( u== null ) {
			loadCurrentProfile();
		} else {
			Log.d("DEBUG", "u is " + u);
			_loadUserProfile(u);
		}
	}*/
	
	private void loadCurrentProfile() {
		TwitterClientApp.getRestClient().getMyInfo(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject jsonUser) {
				// TODO Auto-generated method stub
				User u = User.fromJSON(jsonUser);
				_loadUserProfile(u);				
			}
			
			@Override
			public void onFailure(Throwable e, JSONObject arg1) {
				// TODO Auto-generated method stub
				Log.d("ERROR", "failed " + e);
			}
		}); 
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}

}
