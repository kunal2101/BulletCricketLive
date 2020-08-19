package com.trishasofttech.bulletcricketlive.upcoming;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UpcomingHelper {

    public static String[] name;
    public static String[] date;
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

            for (int i = 0; i <jsonArray.length() ; i++) {
                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                //id[i]=jsonObject1.getLong("unique_id");
                name[i]=jsonObject1.getString("name");
                date[i]=jsonObject1.getString("date");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
