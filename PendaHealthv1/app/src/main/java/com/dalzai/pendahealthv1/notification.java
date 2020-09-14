package com.dalzai.pendahealthv1;


import java.io.Serializable;
import java.sql.Time;

public class notification implements Serializable
{
    private final int notificationImage;
    private String title;
    private String description;
    private String time;
    private String link;

    notification (int notificationimage, String title, String description, String time, String link)
    {
        this.notificationImage = notificationimage;
        this.title = title;
        this.description = description;
        this.time = time;
        this.link = link;
    }

    public int getNotificationImage()
    {
        return notificationImage;
    }
    public String getTitle()
    {
        return title;
    }
    public String getDescription()
    {
        return description;
    }
    public String getTime()
    {
        return time;
    }
    public String getLink()
    {
        return link;
    }
}
