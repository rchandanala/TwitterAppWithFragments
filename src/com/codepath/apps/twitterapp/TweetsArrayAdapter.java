package com.codepath.apps.twitterapp;

import java.util.List;

import android.content.Context;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.twitterapp.models.Tweet;
import com.nostra13.universalimageloader.core.ImageLoader;

public class TweetsArrayAdapter extends ArrayAdapter<Tweet> {

	public TweetsArrayAdapter(Context context, List<Tweet> tweets) {
		super(context, R.layout.item_tweet, tweets);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Tweet t= this.getItem(position);
		// TODO Auto-generated method stub
		View view = convertView;
		if (view == null) {
			LayoutInflater inflater = LayoutInflater.from(getContext());
			//LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.item_tweet, null);
		}
		ImageView ivProfile = (ImageView) view.findViewById(R.id.ivProfile);
		ImageLoader.getInstance().displayImage(t.getUser().getProfilePic(), ivProfile);
		ivProfile.setTag(t.getUser());
		TextView tvText = (TextView) view.findViewById(R.id.tvText);
		String tweetDetails =  t.getText()  + "<small><font color='#777777'>   " + t.getTimestamp() +
				 "</font></small>";
 		tvText.setText(Html.fromHtml(tweetDetails));
		TextView tvName = (TextView) view.findViewById(R.id.tvName);
		String userDetials = "<b>" + t.getUser().getName() + "</b>" + " <small><font color='#777777'>@" + t.getUser().getScreenName() +
				 "</font></small>";
		tvName.setText(Html.fromHtml(userDetials));
		return view;
	}

}
