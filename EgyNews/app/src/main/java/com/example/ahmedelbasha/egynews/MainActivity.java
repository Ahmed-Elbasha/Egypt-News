package com.example.ahmedelbasha.egynews;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewpager);

        NewsArticleFragmentStatePagerAdapter adapter = new NewsArticleFragmentStatePagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.sliding_tabs);

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setTabTextColors(Color.parseColor("#80ffffff"), Color.parseColor("#FFFFFF"));

        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FFFFFF"));
    }
}
