package com.example.androidTV;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.*;
import android.webkit.WebView;
import android.widget.*;


public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                                  WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.main);

        final WebView webView = (WebView) findViewById(R.id.webView);
        webView.setFocusable(false);
        webView.setFocusableInTouchMode(false);

        final GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));
        gridview.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //To change body of implemented methods use File | Settings | File Templates.
                //Log.d("xyzhou", "adapterView.getId() = " + adapterView.getId());
                //Log.d("xyzhou", "view.tag = " + (Integer)view.getTag());
                //Log.d("xyzhou", "i = " + i);
                //Log.d("xyzhou", "l = " + l);

                webView.loadUrl("file:///android_asset/" + i + ".html");
                Log.d("xyzhou", "adapterView.getFocusedChild().getTag() = " + adapterView.getSelectedItem());
                Log.d("xyzhou", "focus ? = " + adapterView.getChildAt(0).hasWindowFocus());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.d("xyzhou", "onNothingSelected");
                //Log.d("xyzhou", "focus ? = " + adapterView.getChildAt(0).hasWindowFocus());
                //adapterView.getChildAt(0).setFocusable(true);
                //adapterView.getChildAt(0).setFocusableInTouchMode(true);
                //adapterView.getChildAt(0).requestFocus();
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(MyActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });


        //gridview.getOnItemSelectedListener().onItemSelected(gridview, gridview.getChildAt(0), 0, 0);

        //gridview.setSelected(false);
        gridview.setSelection(0);
        gridview.setSelected(true);

    }

    public class ImageAdapter extends BaseAdapter {

        private Context mContext;

        public ImageAdapter(Context c) {
            mContext = c;
        }
        @Override
        public int getCount() {
            //return 0;  //To change body of implemented methods use File | Settings | File Templates.
            return mThumbIds.length;
        }

        @Override
        public Object getItem(int i) {
            //return null;  //To change body of implemented methods use File | Settings | File Templates.
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            //return null;  //To change body of implemented methods use File | Settings | File Templates.
            ImageView imageView;
            if (convertView == null) {  // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(180, 100));
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setPadding(2, 2, 2, 2);
            } else {
                imageView = (ImageView) convertView;
            }

            imageView.setTag(position);
            imageView.setImageResource(mThumbIds[position]);
            return imageView;
        }

        private Integer[] mThumbIds = {
                R.drawable.movie, R.drawable.tv1,
                R.drawable.music, R.drawable.computer,
                R.drawable.settings, R.drawable.netflix,
                R.drawable.mlb, R.drawable.preview,
                R.drawable.youtube, R.drawable.vimeo,
                R.drawable.podcast, R.drawable.radio,
                R.drawable.photo_stream, R.drawable.flickr,
                R.drawable.wsj
        };
    }
}
