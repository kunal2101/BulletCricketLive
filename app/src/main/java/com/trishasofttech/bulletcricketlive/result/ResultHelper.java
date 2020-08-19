package com.trishasofttech.bulletcricketlive.result;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ResultHelper {

    public static String[] title;
    public static String[] team1;
    public static String[] team2;
    public static String[] team1score;
    public static String[] team2score;
    public static String[] team1logo;
    public static String[] team2logo;
    public static String[] result;
    public static long[] id;
    private static String json;


    public ResultHelper(String json) {
        this.json = json;
    }

    public static void upcominghelper(){
        JSONObject noteObject=null;
        try {
            noteObject=new JSONObject(json);
            JSONArray jsonArray=noteObject.getJSONArray("data");
            id=new long[jsonArray.length()];
            title=new String[jsonArray.length()];
            team1=new String[jsonArray.length()];
            team2=new String[jsonArray.length()];
            team1score=new String[jsonArray.length()];
            team2score=new String[jsonArray.length()];
            team1logo=new String[jsonArray.length()];
            team2logo=new String[jsonArray.length()];
            result=new String[jsonArray.length()];

            for (int i = 0; i <jsonArray.length() ; i++) {
                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                //id[i]=jsonObject1.getLong("unique_id");
                title[i]=jsonObject1.getString("title");
                team1[i]=jsonObject1.getString("team1");
                team2[i]=jsonObject1.getString("team2");
                team1score[i]=jsonObject1.getString("team1score");
                team2score[i]=jsonObject1.getString("team2score");
                team1logo[i]=jsonObject1.getString("team1logo");
                team2logo[i]=jsonObject1.getString("team2logo");
                result[i]= jsonObject1.getString("result");

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
