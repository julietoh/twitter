package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.codepath.apps.restclienttemplate.fragments.TweetsListFragment;
import com.codepath.apps.restclienttemplate.fragments.TweetsPagerAdapter;
import com.codepath.apps.restclienttemplate.models.ComposeActivity;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

import java.util.ArrayList;

public class TimelineActivity extends AppCompatActivity implements TweetsListFragment.TweetSelectedListener {
    private SwipeRefreshLayout swipeContainer;
    // Store a member variable for the listener
    private String TAG = getClass().getName();
    private TwitterClient client;
    ArrayList<Tweet> tweets = new ArrayList<>();
    //TweetAdapter tweetAdapter;
    RecyclerView rvTweets;
    private EndlessRecyclerViewScrollListener scrollListener;
//    TweetsListFragment fragmentTweetsList;
    ViewPager vpPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        // get the view pager
        vpPager = (ViewPager) findViewById(R.id.viewpager);
        // set the adapter for the pager
        vpPager.setAdapter(new TweetsPagerAdapter(getSupportFragmentManager(), this));
        //setup the TabLayout to use the view pager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(vpPager);
//        client = TwitterApp.getRestClient();
//        fragmentTweetsList = (TweetsListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_timeline);
//        populateTimeline();

//        Log.i(TAG, fragmentTweetsList.rvTweets.toString());

        // Retain an instance so that you can call `resetState()` for fresh searches
//        scrollListener = new EndlessRecyclerViewScrollListener(new LinearLayoutManager(this)) {
//            @Override
//            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
//                // Triggered only when new data needs to be appended to the list
//                // Add whatever code is needed to append new items to the bottom of the list
//                Long maxId = tweets.get(tweets.size() - 1).uid;
//                loadNextDataFromApi(maxId);
//            }
//        };
    }
    // Adds the scroll listener to RecyclerView
    //  rvTweets.addOnScrollListener(scrollListener);

    // Lookup the swipe container view

//        // Setup refresh listener which triggers new data loading
//        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
//        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                fetchTimelineAsync(0);
//            }
//        });
//
//        // Configure the refreshing colors
//        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
//                android.R.color.holo_green_light,
//                android.R.color.holo_orange_light,
//                android.R.color.holo_red_light);
//    }

    // Append the next page of data into the adapter
    // This method probably sends out a network request and appends new data items to your adapter.
    // Send an API request to retrieve appropriate paginated data
    //  --> Send the request including an offset value (i.e `page`) as a query parameter.
    //  --> Deserialize and construct new model objects from the API response
    //  --> Append the new data objects to the existing set of items inside the array of items
    //  --> Notify the adapter of the new items made with `notifyItemRangeInserted()`

    // convert each object to a Tweet model
    // add the Tweet model to our data source
    // notify the adapter that we've added an item
//    public void loadNextDataFromApi(Long maxId) {
//
//        client.getNextTweets(maxId, new JsonHttpResponseHandler() {
//
//        @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//            for (int i = 0; i < response.length(); i++) {
//                try {
//                    Tweet tweet = Tweet.fromJSON(response.getJSONObject(i));
//                    tweets.add(tweet);
//                    tweetAdapter.notifyItemInserted(tweets.size() - 1);
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                //fragmentTweetsList.addItems(response);
//            }
//        }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                super.onFailure(statusCode, headers, responseString, throwable);
//            }
//        });
//    }

//   public void fetchTimelineAsync(int page) {
//        // Send the network request to fetch the updated data
//        // `client` here is an instance of Android Async HTTP
//        // getHomeTimeline is an example endpoint.
//
//        client.getHomeTimeline(new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//                // Remember to CLEAR OUT old items before appending in the new ones
//                //tweetAdapter.clear();
//                // ...the data has come back, add new items to your adapter...
//                // tweets.clear();
//                // loop through json objects in array and convert each to a Tweet object
//                for (int i = 0; i < response.length(); i++) {
//                    try {
//                        Tweet tweet = Tweet.fromJSON(response.getJSONObject(i));
//                        // tweets.add(i, tweet);
//                        //tweetAdapter.notifyItemInserted(tweets.size() - 1);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//                // Now we call setRefreshing(false) to signal refresh has finished
//                swipeContainer.setRefreshing(false);
//            }
//
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                super.onFailure(statusCode, headers, responseString, throwable);
//                Log.d("DEBUG", "Fetch timeline error: " + responseString);
//            }
//        });
//    }


        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_timeline, menu);
            return true;
        }

        // REQUEST_CODE can be any value we like, used to determine the result type later
    private final int REQUEST_CODE = 20;

    public void onComposeAction(MenuItem mi) {
        // Handle click
        Intent i = new Intent(TimelineActivity.this, ComposeActivity.class);
        i.putExtra("mode", 2); // pass arbitrary data to launched activity
        startActivityForResult(i, REQUEST_CODE);
    }


     // FirstActivity, launching an activity for a result
     // ActivityOne.java, time to handle the result of the sub-activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // Extract name value from result extras
            // String tweet = data.getExtras().getString("tweet");
            Tweet tweet = Parcels.unwrap(data.getParcelableExtra("tweet"));
            // tweets.add(0, tweet);
            //tweetAdapter.notifyItemInserted(0);
            // rvTweets.scrollToPosition(0);

            TweetsPagerAdapter pagerAdapter = (TweetsPagerAdapter) vpPager.getAdapter();
            pagerAdapter.tweetsList.get(0).onTweetsAvailable(tweet);
        }
    }
//    private void populateTimeline() {
//        client.getHomeTimeline(new JsonHttpResponseHandler() {
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                Log.d("TwitterClient", response.toString());
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//                // Log.d("TwitterClient", response.toString());
//                // iterate through the JSON array
//                // for each entry, deserialize the JSON object
//
//
//                for (int i = 0; i < response.length(); i++) {
//                    // convert each object to a Tweet model
//                    // add the Tweet model to our data source
//                    // notify the adapter that we've added an item
//                    try {
//
//                        Tweet tweet = Tweet.fromJSON(response.getJSONObject(i));
//                        fragmentTweetsList.tweets.add(tweet);
//                        fragmentTweetsList.tweetAdapter.notifyItemInserted(fragmentTweetsList.tweets.size() - 1);
//                        } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                Log.d("TwitterClient", responseString);
//                throwable.printStackTrace();
//
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                Log.d("TwitterClient", errorResponse.toString());
//                throwable.printStackTrace();
//
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
//                Log.d("TwitterClient", errorResponse.toString());
//                throwable.printStackTrace();
//            }
//        });
//    }

    public void onProfileView(MenuItem item) {
        // launch the profile view
        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);

    }

    public void onTweetSelected(Tweet tweet) {
        Toast.makeText(this, tweet.body, Toast.LENGTH_SHORT).show();
    }
}

