package com.codepath.apps.twitterapp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.codepath.apps.twitterapp.R;
import com.codepath.apps.twitterapp.models.User;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ProfileHeaderFragment extends SherlockFragment {
	TextView tvProfileName;
	TextView tvTagline;
	TextView tvFollowers;
	TextView tvFollowing;
	ImageView ivProfileImage;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_profile_header, container, false);
		
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		_configureViews(getActivity());
		
	}
	
	public void _configureViews(Activity a) {
		tvProfileName = (TextView) a.findViewById(R.id.tvProfileName);
		tvTagline = (TextView) a.findViewById(R.id.tvTagLine);
		tvFollowers = (TextView) a.findViewById(R.id.tvFollowers);
		tvFollowing = (TextView) a.findViewById(R.id.tvFollowing);
		ivProfileImage = (ImageView) a.findViewById(R.id.ivProfileImage);
	}
	
	public void loadUserProfile(User u) {
		_configureViews(getActivity());
		tvProfileName.setText(u.getName());
		tvTagline.setText(u.getTagline());
		tvFollowers.setText(u.getFollowersCnt() + " Followers");
		tvFollowing.setText(u.getFollowingCnt() + " Following");
		ImageLoader.getInstance().displayImage(u.getProfilePic(), ivProfileImage);
	}

}
