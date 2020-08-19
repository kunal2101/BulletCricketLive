package com.trishasofttech.bulletcricketlive;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UpcomingHelper {

    public static String[] name;
    public static String[] date;
    public static String[] time;
    public static String[] ground;
    public static String[] iv_team1;
    public static String[] location;
    public static String[] iv_team2;
    public static long[] id;
    private static String json;


    public UpcomingHelper(String json) {
        this.json = json;
    }

    public static void upcominghelper(){
        JSONObject noteObject=null;
        try {
            noteObject=new JSONObject(json);
            JSONArray jsonArray=noteObject.getJSONArray("data");
            id=new long[jsonArray.length()];
            name=new String[jsonArray.length()];
            date=new String[jsonArray.length()];
            ground=new String[jsonArray.length()];
            location=new String[jsonArray.length()];
            time=new String[jsonArray.length()];
            iv_team1=new String[jsonArray.length()];
            iv_team2=new String[jsonArray.length()];

            for (int i = 0; i <jsonArray.length() ; i++) {
                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                //id[i]=jsonObject1.getLong("unique_id");
                name[i]=jsonObject1.getString("name");
                date[i]=jsonObject1.getString("date");
                iv_team1[i]=jsonObject1.getString("team1logo");
                iv_team2[i]=jsonObject1.getString("team2logo");
                location[i]=jsonObject1.getString("place");
                ground[i]=jsonObject1.getString("groundname");
                time[i]=jsonObject1.getString("matchtime");

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
