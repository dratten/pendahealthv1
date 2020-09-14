package com.dalzai.pendahealthv1;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class notificationFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    public notificationAdapter notificationadapter;
    public ArrayList<notification> notificationData;
    public RecyclerView notificationRecyclerView;
    SwipeRefreshLayout swipe;

    public notificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_notification, container, false);
        notificationRecyclerView = rootView.findViewById(R.id.notification_recycler);
        Toast toast = Toast.makeText(getActivity(),"Loading.....",Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.LEFT,0,0);
        toast.show();
        swipe = rootView.findViewById(R.id.swipe);
        swipe.setOnRefreshListener(this);
        notificationRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        notificationData = new ArrayList<>();
        notificationadapter = new notificationAdapter(notificationData, getContext());
        initiateData();
        notificationRecyclerView.setAdapter(notificationadapter);
        return rootView;
    }

    public void initiateData() {
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            String url = "https://penda-healthv1.herokuapp.com/api/notifications";
            notificationData.clear();
            final TypedArray notificationImage = getResources().obtainTypedArray(R.array.notification_image);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Log.e(" result", (String.valueOf(response)));
                        JSONArray notifications = response.getJSONArray("notifications");
                        for (int i = 0; i < notifications.length(); i++) {
                            JSONObject rec = notifications.getJSONObject(i);
                            String title = rec.getString("title");
                            String description = rec.getString("description");
                            String time = rec.getString("created_at");
                            String link = rec.getString("link");
                            int category = rec.getInt("category_id");
                            notificationData.add(new notification(notificationImage.getResourceId(category, 0), title, description, time, link));
                        }
                        Log.d("list", notificationData.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("notificationFragment", "onErrorResponse: " + error.getMessage());
                }
            });
            requestQueue.add(request);
            notificationadapter.notifyDataSetChanged();
            notificationRecyclerView.scrollBy(0,0);
        }

    @Override
    public void onRefresh() {
        initiateData();
        swipe.setRefreshing(false);
    }
}


