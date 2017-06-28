package com.codepath.apps.restclienttemplate.models;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.TimelineActivity;
import com.codepath.apps.restclienttemplate.TwitterClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import cz.msebera.android.httpclient.Header;


public class ComposeActivity extends AppCompatActivity {

    TwitterClient client;
    public void onSubmit(View v) {
        client = new TwitterClient(this);
        EditText ETtweet = (EditText)findViewById(R.id.ETtweet);

//        // Prepare data intent
//        Intent data = new Intent();
//        // Pass relevant data back as a result
//        data.putExtra("tweet", ETtweet.getText().toString());
//        // Activity finished ok, return the data
//        setResult(RESULT_OK, data); // set result code and bundle data for response
//        finish(); // closes the activity, pass data to parent

        client.sendTweet(ETtweet.getText().toString(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    Tweet tweet = Tweet.fromJSON(response);
                    Intent i = new Intent(ComposeActivity.this, TimelineActivity.class);
                    i.putExtra("tweet", Parcels.wrap(tweet));
                    setResult(RESULT_OK, i);
                    finish();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
    }

}
