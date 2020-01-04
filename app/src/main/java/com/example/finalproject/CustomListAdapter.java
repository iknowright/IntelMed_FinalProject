package com.example.finalproject;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> videoTitle;
    private final ArrayList<String> videoImage;
    private final ArrayList<String> videoID;

    public CustomListAdapter(Activity context, ArrayList<String> videoTitle, ArrayList<String> videoImage, ArrayList<String> videoID) {
        super(context, R.layout.result_list, videoTitle);

        this.context=context;
        this.videoTitle=videoTitle;
        this.videoID=videoID;
        this.videoImage=videoImage;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.result_list, null,true);

        TextView txtTitle = rowView.findViewById(R.id.item);
        ImageView imageView = rowView.findViewById(R.id.icon);
        TextView txtOpt = rowView.findViewById(R.id.textView1);

        txtTitle.setText(videoTitle.get(position));
        new DownloadImageTask(imageView)
                .execute(videoImage.get(position));
        txtOpt.setText("Description "+ videoID.get(position));
        return rowView;

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urlDisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urlDisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}