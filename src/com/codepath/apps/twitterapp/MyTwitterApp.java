package com.codepath.apps.twitterapp;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.FlickrApi;
import org.scribe.builder.api.TwitterApi;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/*
 * 
 * This is the object responsible for communicating with a REST API. 
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes: 
 *   https://github.com/fernandezpablo85/scribe-java/tree/master/src/main/java/org/scribe/builder/api
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 * 
 * NOTE: You may want to rename this object based on the service i.e TwitterClient or FlickrClient
 * 
 */
public class MyTwitterApp extends OAuthBaseClient {
    public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class; // Change this
    public static final String REST_URL = "https://api.twitter.com/1.1/"; // Change this, base API URL
    public static final String REST_CONSUMER_KEY = "5SbDT72GyzcRC6d3UQDBcw";       // Change this
    public static final String REST_CONSUMER_SECRET = "aOpU5n53SPOQJ1FCkrcZ156QFMOzvZo1aNpOmJVzto"; // Change this
    public static final String REST_CALLBACK_URL = "oauth://twitterapp"; // Change this (here and in manifest)
    
    public MyTwitterApp(Context context) {
        super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
    }
    
    // CHANGE THIS
    // DEFINE METHODS for different API endpoints here
    public void getInterestingnessList(AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("?nojsoncallback=1&method=flickr.interestingness.getList");
        // Can specify query string params directly or through RequestParams.
        RequestParams params = new RequestParams();
        params.put("format", "json");
        client.get(apiUrl, params, handler);
    }
    
    public void getTweets(AsyncHttpResponseHandler handler, RequestParams params) {
    	String apiUrl = getApiUrl("statuses/home_timeline.json");
    	//RequestParams params = new RequestParams();
    	params.put("count", "25");
    	client.get(apiUrl, params, handler);
    }
    
    public void getMentionTweets(AsyncHttpResponseHandler handler, RequestParams params) {
    	String apiUrl = getApiUrl("statuses/mentions_timeline.json");
    	//RequestParams params = new RequestParams();
    	params.put("count", "25");
    	client.get(apiUrl, params, handler);    	
    }
    
    public void getProfileTimeline(AsyncHttpResponseHandler handler, RequestParams params) {
    	String apiUrl = getApiUrl("statuses/user_timeline.json");
    	//RequestParams params = new RequestParams();
    	params.put("count", "25");
    	client.get(apiUrl, params, handler);    	
    }
    
    public void postTweet(AsyncHttpResponseHandler handler, String status) {
    	String apiUrl = getApiUrl("statuses/update.json");
    	RequestParams params = new RequestParams();
    	params.put("status", status);
    	
    	client.post(apiUrl, params, handler);
    }
    
    public void getMyInfo(AsyncHttpResponseHandler handler) {
    	String apiUrl = getApiUrl("account/verify_credentials.json");
    	client.get(apiUrl, null, handler);
    }
    
    /* 1. Define the endpoint URL with getApiUrl and pass a relative path to the endpoint
     * 	  i.e getApiUrl("statuses/home_timeline.json");
     * 2. Define the parameters to pass to the request (query or body)
     *    i.e RequestParams params = new RequestParams("foo", "bar");
     * 3. Define the request method and make a call to the client
     *    i.e client.get(apiUrl, params, handler);
     *    i.e client.post(apiUrl, params, handler);
     */
}