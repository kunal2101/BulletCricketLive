package com.trishasofttech.bulletcricketlive;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AllNewsHelper {

    public static String[] title;
    public static String[] date;
    public static String[] url;
    public static String[] desc;
    public static long[] id;
    private static String json;


    public AllNewsHelper(String json) {
        this.json = json;
    }

    public static void upcominghelper(){
        JSONObject noteObject=null;
        try {
            noteObject=new JSONObject(json);
            JSONArray jsonArray=noteObject.getJSONArray("data");
            id=new long[jsonArray.length()];
            title=new String[jsonArray.length()];
            date=new String[jsonArray.length()];
            url=new String[jsonArray.length()];
            desc=new String[jsonArray.length()];

            for (int i = 0; i <jsonArray.length() ; i++) {
                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                //id[i]=jsonObject1.getLong("unique_id");
                title[i]=jsonObject1.getString("title");
                desc[i]=jsonObject1.getString("description");
                date[i]=jsonObject1.getString("date");
                url[i]=jsonObject1.getString("url");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
