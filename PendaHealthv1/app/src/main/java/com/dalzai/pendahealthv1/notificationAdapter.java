package com.dalzai.pendahealthv1;

import android.app.Notification;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class notificationAdapter extends RecyclerView.Adapter<notificationAdapter.ViewHolder>
{
    private ArrayList<notification> notificationData;
    private Context myContext;

    notificationAdapter(ArrayList<notification> mnotificationData, Context context)
    {
        this.notificationData = mnotificationData;
        this.myContext = context;
    }
    @NonNull
    @Override
    public notificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(myContext).inflate(R.layout.notification_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull notificationAdapter.ViewHolder holder, int position) {
        notification currentNotification = notificationData.get(position);
        holder.bindTo(currentNotification);
    }

    @Override
    public int getItemCount() {
        return notificationData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView myNotificationImage;
        private TextView myNotificationTitle;
        private  TextView myNotificationTime;
        private TextView myNotificationDescription;
        private TextView myNotificationLink;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myNotificationImage = itemView.findViewById(R.id.notification_image);
            myNotificationTitle = itemView.findViewById(R.id.notification_title);
            myNotificationTime = itemView.findViewById(R.id.notification_time);
            myNotificationDescription = itemView.findViewById(R.id.notification_description);
            myNotificationLink = itemView.findViewById(R.id.notification_link);
        }

        public void bindTo(notification currentNotification) {
            Glide.with(myContext).load(currentNotification.getNotificationImage()).into(myNotificationImage);
            myNotificationTitle.setText(currentNotification.getTitle());
            myNotificationTime.setText(currentNotification.getTime());
            myNotificationDescription.setText(currentNotification.getDescription());
            myNotificationLink.setText(currentNotification.getLink());
        }
    }
}
