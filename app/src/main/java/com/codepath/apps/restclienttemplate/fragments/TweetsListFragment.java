package com.codepath.apps.restclienttemplate.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.TweetAdapter;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.ArrayList;

/**
 * Created by joh on 7/3/17.
 */

public class TweetsListFragment extends Fragment implements TweetAdapter.TweetAdapterListener {

    public interface TweetSelectedListener {
        // handle tweet selection
        public void onTweetSelected(Tweet tweet);
    }
    public ArrayList<Tweet> tweets;
    public TweetAdapter tweetAdapter;
    public RecyclerView rvTweets;
    // inflation happens inside onCreate view

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // inflate the layout
        View v = inflater.inflate(R.layout.fragments_tweets_list, container, false);

        // find the RecyclerView
        rvTweets = (RecyclerView) v.findViewById(R.id.rvTweet);
        // RecyclerView setup (layout manager, user adapter)
        tweets = new ArrayList<>();
        tweetAdapter = new TweetAdapter(tweets, this);
        rvTweets.setLayoutManager(new LinearLayoutManager(getContext()));
        // set adapter
        // LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        // rvTweets.setLayoutManager(linearLayoutManager);
        rvTweets.setLayoutManager(new LinearLayoutManager(getContext()));
        // set the adapter
        rvTweets.setAdapter(tweetAdapter);

        return v;
    }

    @Override
    public void onItemSelected(View view, int position) {
        Tweet tweet = tweets.get(position);
        ((TweetSelectedListener) getActivity()).onTweetSelected(tweet);
    }
    // step 5: TweetsListFragment to recycler view
    public void onTweetsAvailable(Tweet tweet) {
        tweets.add(0, tweet);
        tweetAdapter.notifyItemInserted(0);
        rvTweets.scrollToPosition(0);
    }
}
