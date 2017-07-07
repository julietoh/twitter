package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import cz.msebera.android.httpclient.Header;

public class ReplyActivity extends AppCompatActivity {
    private Tweet replyTweet;
    public static final String TWEET = "TWEET";
    TwitterClient client;
    private EditText ptComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);

        Intent i = getIntent();
        replyTweet = Parcels.unwrap(i.getParcelableExtra(TWEET));

        ptComment = (EditText) findViewById(R.id.ptComment);
        ptComment.setText("@" + replyTweet.user.screenName + " ");

    }
    public void onReply(View view) {
        client = new TwitterClient(this);
        Log.i("reply", "reply");

        client.sendTweet(ptComment.getText().toString(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    Tweet tweet = Tweet.fromJSON(response);
                    Intent i = new Intent(ReplyActivity.this, TimelineActivity.class);
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
}
