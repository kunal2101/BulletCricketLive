package com.trishasofttech.bulletcricketlive;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SeriesHelper {

    public static String[] name;
    public static String[] date;
    public static String[] gname;
    public static String[] place;
    public static String[] matchtime;
    public static String[] series;
    public static int[] id;
    private static String json;


    public SeriesHelper(String json) {
        this.json = json;
    }

    public static void serieshelper() {
        JSONObject noteObject = null;
        try {
            noteObject = new JSONObject(json);
            JSONArray jsonArray = noteObject.getJSONArray("data");
            id = new int[jsonArray.length()];
            name = new String[jsonArray.length()];
            date = new String[jsonArray.length()];
            matchtime = new String[jsonArray.length()];
            gname = new String[jsonArray.length()];
            place = new String[jsonArray.length()];
            series = new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                id[i] = jsonObject1.getInt("series");
                name[i] = jsonObject1.getString("name");
                date[i] = jsonObject1.getString("date");
                matchtime[i] = jsonObject1.getString("matchtime");
                gname[i] = jsonObject1.getString("Groundname");
                place[i] = jsonObject1.getString("place");
                series[i] = jsonObject1.getString("unique_id");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
