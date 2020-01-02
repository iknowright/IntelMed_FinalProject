package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class result extends AppCompatActivity {

    private ArrayAdapter listAdapter;
    private ArrayList<String> totalListViewData = new ArrayList<String>();
    private ArrayList<videoEntry> myVideoList = new ArrayList<videoEntry>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        SetInit();
    }

    private void SetInit() {

        this.myVideoList.addAll(GetTotalResultInfo());
        this.totalListViewData = this.GetTotalDataSource();

        ListView videoList = (ListView) findViewById(R.id.result_sheet_list_view);
        videoList.setOnItemClickListener(new ListView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int indexOfClickItem = parent.getPositionForView(view);
                videoEntry video = myVideoList.get(indexOfClickItem);

                String videoId = video.GetVideoID();
                Log.d("Response", videoId);

                try
                {
                    Intent intent = new Intent();
                    intent.setClass(result.this, youtube.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("videoID", videoId);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                catch (Exception e)
                {
                    Toast.makeText(result.this, "please enter completly1", Toast.LENGTH_SHORT).show();
                }
            }
        });

        this.listAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, this.totalListViewData);
        videoList.setAdapter(this.listAdapter);
    }

    private  ArrayList<String> GetTotalDataSource() {
        ArrayList<String> totalListViewData = new ArrayList<String>();

        for (int index = 0; index < this.myVideoList.size(); index++) {
            videoEntry video = this.myVideoList.get(index);
            String perListViewData = "[" + video.GetVideoID() + "] " + video.GetVideoTitle();

            totalListViewData.add(perListViewData);
        }
        return totalListViewData;
    }

    public ArrayList<videoEntry> GetTotalResultInfo() {
        ArrayList<videoEntry> totalResultInfo = new ArrayList<videoEntry>();

        //Some url endpoint that you may have
        String myUrl = "https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=25&q=surfing&key=AIzaSyBb_QZULAFWE8scL9MDMmhjArUez4uTfuw\n";
        //String to place our result in
        String result;
        //Instantiate new instance of our class
        HttpGetRequest getRequest = new HttpGetRequest();
        //Perform the doInBackground method, passing in our url
        try {
            result = getRequest.execute(myUrl).get();
            Log.d("Response", result);
            JSONObject reader = new JSONObject(result);

            JSONArray items = reader.getJSONArray("items");
            Log.d("Response", items.toString());
            for (int i = 0; i < items.length(); i++) {
                JSONObject row = items.getJSONObject(i);
                try {
                    String video_title = row.getJSONObject("snippet").getString("title");
                    String video_id = row.getJSONObject("id").getString("videoId");
                    Log.d("Response", "index = " + i);
                    Log.d("Response", video_id);
                    Log.d("Response", video_title);

                    videoEntry perVideoEntry = new videoEntry();
                    perVideoEntry.init(video_id, video_title);
                    totalResultInfo.add(perVideoEntry);
                } catch (Exception e) {
                    continue;
                }
            }
        }
        catch(Exception e) {
            Log.d("Response", "failed");
        }

        return totalResultInfo;
    }

    public class HttpGetRequest extends AsyncTask<String, Void, String> {
        public static final String REQUEST_METHOD = "GET";
        public static final int READ_TIMEOUT = 15000;
        public static final int CONNECTION_TIMEOUT = 15000;
        @Override
        protected String doInBackground(String... params){
            String stringUrl = params[0];
            String result;
            String inputLine;
            try {
                //Create a URL object holding our url
                URL myUrl = new URL(stringUrl);
                //Create a connection
                HttpURLConnection connection =(HttpURLConnection)
                        myUrl.openConnection();
                //Set methods and timeouts
                connection.setRequestMethod(REQUEST_METHOD);
                connection.setReadTimeout(READ_TIMEOUT);
                connection.setConnectTimeout(CONNECTION_TIMEOUT);

                //Connect to our url
                connection.connect();
                //Create a new InputStreamReader
                InputStreamReader streamReader = new
                        InputStreamReader(connection.getInputStream());
                //Create a new buffered reader and String Builder
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();
                //Check if the line we are reading is not null
                while((inputLine = reader.readLine()) != null){
                    stringBuilder.append(inputLine);
                }
                //Close our InputStream and Buffered reader
                reader.close();
                streamReader.close();
                //Set our result equal to our stringBuilder
                result = stringBuilder.toString();
            }
            catch(IOException e){
                e.printStackTrace();
                result = null;
            }
            return result;
        }
        protected void onPostExecute(String result){
            super.onPostExecute(result);
        }
    }
}
