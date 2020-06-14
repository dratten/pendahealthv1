package com.dalzai.pendahealthv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    ViewPager mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TabLayout implementation
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.setupWithViewPager(mainView);
        for (int i = 0; i < tabLayout.getTabCount(); i++)
        {
            LinearLayout tab = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.nav_tab,null);
            TextView tab_label = tab.findViewById(R.id.nav_label);
            ImageView tab_icon = tab.findViewById(R.id.nav_icon);
            String[] tabLabels = getResources().getStringArray(R.array.navLabel);
            TypedArray tabIcons = getResources().obtainTypedArray(R.array.navIcon);
            TypedArray tabIconsSel = getResources().obtainTypedArray(R.array.navIconSel);
            tab_label.setText(tabLabels[i]);
            if(i == 0)
            {
                tab_label.setTextColor(getResources().getColor(R.color.main_pink));
                tab_icon.setImageResource(tabIconsSel.getResourceId(i,0));
            }
            else
            {
                tab_icon.setImageResource(tabIcons.getResourceId(i,0));
            }
            tabLayout.getTabAt(i).setCustomView(tab);
        }
        final ViewPager viewPager = findViewById(R.id.view_pager);
        final pagerAdapter pagerAdapter = new pagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
