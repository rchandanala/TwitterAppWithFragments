package com.codepath.apps.twitterapp;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

public class ComposeActivity extends Activity {
	EditText etNewTweet;
	TextView tvRemainingChar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compose);
		_setViews();
	}

	private void _setViews() {
		etNewTweet = (EditText) findViewById(R.id.etNewTweet);
		tvRemainingChar = (TextView) findViewById(R.id.tvRemainingChar);
		etNewTweet.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				int i = s.length();
				tvRemainingChar.setText("Entered Character Count:" + i);
				
			}

	
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}


			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compose, menu);
		return true;
	}

	public void onSave(View v){
		String newTweet = etNewTweet.getText().toString();
		Log.d("DEBUG", "here " + newTweet);
		if (newTweet.isEmpty()) {
			Toast.makeText(this, "Tweet cannot be empty", Toast.LENGTH_SHORT).show();
			return;
		}
		
		TwitterClientApp.getRestClient().postTweet(new JsonHttpResponseHandler() {
			public void onSuccess(JSONObject tweet) {
				Log.d("DEBUG", "created " + tweet);
				Toast.makeText(getApplicationContext(), "Tweet posted successfully!", Toast.LENGTH_SHORT).show();
				Intent I = new Intent();
				setResult(RESULT_OK, I);
				finish();
			}
			
			@Override
			public void onFailure(Throwable e, JSONObject responseObject) {
				Log.d("DEBUG", "error " + responseObject);
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Failed to post", Toast.LENGTH_SHORT).show();
			}
			
		}, newTweet);
		Log.d("DEBUG", "here after " + newTweet);
		
	}
	
	public void onCancel(View v) {
		Intent I = new Intent();
		setResult(RESULT_CANCELED, I);
		finish();
	}
}
