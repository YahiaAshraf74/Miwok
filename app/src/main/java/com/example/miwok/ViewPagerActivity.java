package com.example.miwok;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;

public class ViewPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        int mainPosition = getIntent().getIntExtra(MainActivity.KEY_POSITION, 4);
        ViewPager viewPager = findViewById(R.id.view_pager);
        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return NumbersFragment.getInstance();
                    case 1:
                        return FamilyMembersFragment.getInstance();
                    case 2:
                        return ColorsFragment.getInstance();
                    case 3:
                        return PhrasesFragment.getInstance();
                    default:
                        return TestFragment.getInstance();
                }
            }

            @Override
            public int getCount() {
                return DummyDate.listItemMenu.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return DummyDate.listItemMenu.get(position);
            }
        };
        viewPager.setAdapter(fragmentPagerAdapter);
        viewPager.setCurrentItem(mainPosition);
        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
}
