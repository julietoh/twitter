package com.codepath.apps.restclienttemplate.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by joh on 7/3/17.
 */

public class TweetsPagerAdapter extends FragmentPagerAdapter {
    private String tabTitles[] = new String[] {"Home", "Mentions"};
    private Context context;
    public ArrayList<TweetsListFragment> tweetsList = new ArrayList<>();

    public TweetsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }
    // return the total # of fragment

    @Override
    public int getCount() {
        return 2;
    }

    // return the fragment to use depending on the position

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            HomeTimelineFragment homeTimelineFragment = new HomeTimelineFragment();
            tweetsList.add(position, homeTimelineFragment);
            return homeTimelineFragment;
        } else if (position == 1) {
            MentionsTimelineFragment mentionsTimelineFragment = new MentionsTimelineFragment();
            tweetsList.add(position, mentionsTimelineFragment);
            return mentionsTimelineFragment;
        } else {
            return null;
        }
    }

    // return title
    public CharSequence getPageTitle(int position) {
        // generate title based on item position
        return tabTitles[position];
    }
}
