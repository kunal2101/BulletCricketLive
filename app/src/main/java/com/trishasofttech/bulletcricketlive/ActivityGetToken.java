package com.trishasofttech.bulletcricketlive;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.TextView;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class ActivityGetToken extends AppCompatActivity {
    static String LZ_API_URL = "https://rest.cricketapi.com/rest/";

    static String LZ_APP_ID = "com.trishasofttech.bulletcricketlive";
    static String ACCESS_KEY = "0b4493705446403556093735ef542914";
    static String SECRET_KEY = "bb57041e85ab5c279d62b00f085d526b";

    String ACCESS_TOKEN = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_token);

        String DEVICE_ID = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);;

        List<NameValuePair> authParams = new ArrayList<> (4);
        authParams.add(new BasicNameValuePair ("access_key", ACCESS_KEY));
        authParams.add(new BasicNameValuePair("secret_key", SECRET_KEY));
        authParams.add(new BasicNameValuePair("app_id", LZ_APP_ID));
        authParams.add(new BasicNameValuePair("device_id", DEVICE_ID));

        new HttpAsyncTask(new OnAPIResponse() {
            @Override
            public void onResponse( JSONObject response) {
                try {
                    Log.v("LZ-API", "Got Auth Response:" + response);
                    boolean status = response.getBoolean("status");
                    if(!status){
                        TextView v = ( TextView ) findViewById(R.id.textview);
                        v.setText("Auth Failed, Give correct APP Details");
                    }else{

                        ACCESS_TOKEN = response.getJSONObject("auth").getString("access_token");

                        List<NameValuePair> apiParams = new ArrayList<>(4);
                        apiParams.add(new BasicNameValuePair("access_token", ACCESS_TOKEN));

                        new HttpAsyncTask(new OnAPIResponse() {
                            @Override
                            public void onResponse(JSONObject matchResponse) {
                                Log.v("LZ-API", "Got Match Response:" + matchResponse);
                                try {
                                    String matchName = matchResponse.getJSONObject("data").getJSONObject("card").getString("name");
                                    Log.v("LZ-API", "Got Match Name:" + matchName);
                                    TextView v = (TextView) findViewById(R.id.textview);
                                    v.setText("Got Match Response for: " + matchName);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, apiParams, false).execute("match/iplt20_2013_g30/");

                    }



                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, authParams).execute("auth/");

    }


/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    // List<NameValuePair> params
    public static JSONObject API_RESPONSE(String url){
        return API_RESPONSE(url, new ArrayList<NameValuePair>(0), false);
    }

    public static JSONObject API_RESPONSE(String url, List<NameValuePair> params){
        return API_RESPONSE(url, params, true);
    }

    public static JSONObject API_RESPONSE(String url, List<NameValuePair> params, boolean isPost){
        InputStream inputStream = null;
        String result = "";
        try {

            String newUrl = LZ_API_URL +  url;
            HttpClient httpclient = new DefaultHttpClient ();
            HttpUriRequest request;

            if(!isPost){
                String paramString = URLEncodedUtils.format(params, "utf-8");
                request = new HttpGet (newUrl + '?' + paramString);
            }else{
                HttpPost postRequest = new HttpPost (newUrl);
                HttpEntity requestParams = new UrlEncodedFormEntity (params);

                postRequest.setEntity(requestParams);
                request = postRequest;
            }

            request.addHeader("Accept-Encoding", "gzip");
            HttpResponse httpResponse = httpclient.execute(request);

            inputStream = httpResponse.getEntity().getContent();
            Header contentEncoding = httpResponse.getFirstHeader("Content-Encoding");

            if (contentEncoding != null && contentEncoding.getValue().equalsIgnoreCase("gzip")) {
                inputStream = new GZIPInputStream (inputStream);
            }

            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "{\"status\":false, \"msg\": \"Error Reading\"}";

        } catch (Exception e) {
            Log.e("InputStream", e.toString());
        }

        JSONObject response = null;
        try {
            response = new JSONObject(result);
        } catch (JSONException e) {
            response = new JSONObject();
            try {
                response.put("status", false);
                response.put("msg", "Error reading JSON");
            } catch (JSONException e1) {
                e1.printStackTrace();
            }

            Log.v("LZ-API", "Error reading: " + result);
        }

        return response;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader (inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    public interface OnAPIResponse{
        void onResponse(JSONObject response);
    }


    public boolean isInternetConnected(){
        ConnectivityManager connMgr = ( ConnectivityManager ) getSystemService( Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }
    private class HttpAsyncTask extends AsyncTask<String, Void, JSONObject> {

        boolean isPost;
        List<NameValuePair> params;
        OnAPIResponse listener;

        public HttpAsyncTask(OnAPIResponse listener){
            this.listener = listener;
            this.params = new ArrayList<>(0);
            this.isPost = false;
        }

        public HttpAsyncTask(OnAPIResponse listener, List<NameValuePair> params){
            this.listener = listener;
            this.params = params;
            this.isPost = true;
        }

        public HttpAsyncTask(OnAPIResponse listener, List<NameValuePair> params, boolean isPost){
            this.listener = listener;
            this.params = params;
            this.isPost = isPost;
        }

        @Override
        protected JSONObject doInBackground(String... urls) {

            return API_RESPONSE(urls[0], params, isPost);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(JSONObject result) {
            listener.onResponse(result);
        }
    }


}