package com.codepath.apps.restclienttemplate.models;

import android.text.format.DateUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by joh on 6/26/17.
 */

@Parcel
public class Tweet {
    // list out the attributes
    public String body;
    public long uid; // database ID for the tweet
    public User user;
    public String timestamp;
    public String date;
    public String createdAt;
    //public Integer likes;
    //public Integer retweets;

    // deserialize the JSON
    public static Tweet fromJSON(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();

        // extract the values from JSON
        tweet.body = jsonObject.getString("text");
        tweet.uid = jsonObject.getLong("id");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.timestamp = getRelativeTimeAgo(tweet.createdAt);
        tweet.date = tweet.createdAt.substring(8, 10) + " " + tweet.createdAt.substring(4, 8) + " " + tweet.createdAt.substring(15, 19);
        tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
        // tweet.likes = jsonObject.getInteger("favorites_count");
        // tweet.retweets = jsonObject.getInt("retweet_count");
        return tweet;
    }

    // getRelativeTimeAgo("Mon Apr 01 21:16:23 +0000 2014");
    public static String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }


}
