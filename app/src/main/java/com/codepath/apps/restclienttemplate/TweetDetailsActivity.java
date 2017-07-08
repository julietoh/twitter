package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

public class TweetDetailsActivity extends AppCompatActivity {
public static final String TWEET = "TWEET";
        private TextView ptTweet;
        private ImageView ivDetailsProfile;
        private TextView ptUsername;
        private TextView ptHandle;
        private TextView ptDetailsTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_details);

        // get the intent
        // load all your fields
        Intent i = getIntent();
        Tweet tweet = Parcels.unwrap(i.getParcelableExtra(TWEET));

        ptTweet = (TextView) findViewById(R.id.ptTweet);
        ivDetailsProfile = (ImageView) findViewById(R.id.ivDetailsProfile);
        ptUsername = (TextView) findViewById(R.id.ptUsername);
        ptHandle = (TextView) findViewById(R.id.ptHandle);
        ptDetailsTime = (TextView) findViewById(R.id.ptDetailsTime);

        Glide.with(this).load(tweet.user.profileImageUrl).into(ivDetailsProfile);
        ptTweet.setText(tweet.body);
        ptUsername.setText(tweet.user.name);
        ptHandle.setText("@" + tweet.user.screenName);
        ptDetailsTime.setText(tweet.date);


    }
}
