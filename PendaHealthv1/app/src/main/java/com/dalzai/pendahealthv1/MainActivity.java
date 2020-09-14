package com.dalzai.pendahealthv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        //TabLayout implementation
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        //Removing Tab Selected Indicator
        tabLayout.setSelectedTabIndicator(0);
        //Retrieving the tab icons and texts
        final String[] tabLabels = getResources().getStringArray(R.array.navLabel);
        TypedArray tabIcons = getResources().obtainTypedArray(R.array.navIcon);
        TypedArray tabIconsSel = getResources().obtainTypedArray(R.array.navIconSel);
        //Setting the custom view for each tab and adding it to the tabLayout
        for (int i = 0; i < tabLabels.length; i++)
        {
            LinearLayout tab = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.nav_tab,null);
            TextView tab_label = tab.findViewById(R.id.nav_label);
            ImageView tab_icon = tab.findViewById(R.id.nav_icon);
            tab_label.setText(tabLabels[i]);
            if(i == 0)//For the homepage
            {
                tab_label.setTextColor(getResources().getColor(R.color.main_pink));
                tab_icon.setImageResource(tabIconsSel.getResourceId(i,0));
                getSupportActionBar().setTitle(tabLabels[i]);
            }
            else
            {
                tab_icon.setImageResource(tabIcons.getResourceId(i,0));
            }
            //Adding custom tab menu view to tabLayout
            tabLayout.addTab(tabLayout.newTab().setCustomView(tab));
        }
        final ViewPager viewPager = findViewById(R.id.view_pager);
        final pagerAdapter pageAdapter = new pagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                //Setting custom page titles
                getSupportActionBar().setTitle(tabLabels[tab.getPosition()]);
                //Highlighting selected tab
                View tabView = tab.getCustomView();
                TextView tab_label = tabView.findViewById(R.id.nav_label);
                ImageView tab_icon = tabView.findViewById(R.id.nav_icon);
                tab_label.setTextColor(getResources().getColor(R.color.main_pink));
                tab_icon.setImageResource(getResources().obtainTypedArray(R.array.navIconSel).getResourceId(tab.getPosition(),0));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //Unhighlighting selected tab
                View tabView = tab.getCustomView();
                TextView tab_label = tabView.findViewById(R.id.nav_label);
                ImageView tab_icon = tabView.findViewById(R.id.nav_icon);
                tab_label.setTextColor(getResources().getColor(R.color.main_white));
                tab_icon.setImageResource(getResources().obtainTypedArray(R.array.navIcon).getResourceId(tab.getPosition(),0));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
