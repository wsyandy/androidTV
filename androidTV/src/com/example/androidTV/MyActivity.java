package com.example.androidTV;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.util.Pair;
import android.view.*;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.*;

import java.util.HashMap;
import java.util.Map;


public class MyActivity extends Activity {
    //final WebView webView = (WebView) findViewById(R.id.webView);
    final private Pair<Integer, String> movie = new Pair<Integer, String>(R.drawable.movie, "热门电影");
    final private Pair<Integer, String> tv1 = new Pair<Integer, String>(R.drawable.tv1, "电视");
    final private Pair<Integer, String> music = new Pair<Integer, String>(R.drawable.music, "音乐");
    final private Pair<Integer, String> computer = new Pair<Integer, String>(R.drawable.computer, "家庭应用");
    final private Pair<Integer, String> settings = new Pair<Integer, String>(R.drawable.settings, "系统设置");
    final private Pair<Integer, String> netflix = new Pair<Integer, String>(R.drawable.netflix, "netflix");
    final private Pair<Integer, String> mlb = new Pair<Integer, String>(R.drawable.mlb, "mlb");
    final private Pair<Integer, String> preview = new Pair<Integer, String>(R.drawable.preview, "电影预览");
    final private Pair<Integer, String> youtube = new Pair<Integer, String>(R.drawable.youtube, "youtube");
    final private Pair<Integer, String> vimeo = new Pair<Integer, String>(R.drawable.vimeo, "vimeo");
    final private Pair<Integer, String> podcast = new Pair<Integer, String>(R.drawable.podcast, "podcast");
    final private Pair<Integer, String> radio = new Pair<Integer, String>(R.drawable.radio, "广播");
    final private Pair<Integer, String> photo_stream = new Pair<Integer, String>(R.drawable.photo_stream, "相册");
    final private Pair<Integer, String> flickr = new Pair<Integer, String>(R.drawable.flickr, "flickr");
    final private Pair<Integer, String> wsj = new Pair<Integer, String>(R.drawable.wsj, "wsj");

    final private Pair<Integer, String>[] items = new Pair[] {
            movie,
            tv1,
            music,
            computer,
            settings,
            netflix,
            mlb,
            preview,
            youtube,
            vimeo,
            //podcast,
            //radio,
            //photo_stream,
            //flickr,
            //wsj
    };

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
        /*gridview.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //To change body of implemented methods use File | Settings | File Templates.
                //Log.d("xyzhou", "adapterView.getId() = " + adapterView.getId());
                //Log.d("xyzhou", "view.tag = " + (Integer)view.getTag());
                //Log.d("xyzhou", "i = " + i);
                //Log.d("xyzhou", "l = " + l);

                webView.loadUrl("file:///android_asset/" + i + ".html");
                //Log.d("xyzhou", "adapterView.getFocusedChild().getTag() = " + adapterView.getSelectedItem());
                Log.d("xyzhou", "focus ? = " + adapterView.getChildAt(0).hasWindowFocus());
                //getCurrentFocus().getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(MyActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    public class ImageAdapter extends BaseAdapter {

        private Context mContext;

        public ImageAdapter(Context c) {
            mContext = c;
        }
        @Override
        public int getCount() {
            //return 0;  //To change body of implemented methods use File | Settings | File Templates.
            return items.length;
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
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);

            if (convertView == null) {  // if it's not recycled, initialize some attributes
                convertView = layoutInflater.inflate(R.layout.myitem, null);
            }

            ImageView imageView = (ImageView)convertView.findViewById(R.id.imageView);
            imageView.setImageResource(items[position].first);
            imageView.setFocusable(true);
            imageView.setFocusableInTouchMode(true);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            //imageView.setAlpha(0);
            //imageView.setPadding(1, 1, 1, 1);
            //imageView.setBackgroundColor(Color.BLACK);
            imageView.setTag(position);

            imageView.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                private ImageView mRectangle = new ImageView(mContext);
                @Override
                public void onFocusChange(View view, boolean b) {
                    //To change body of implemented methods use File | Settings | File Templates.
                    if (b) {

                        //view.setBackgroundColor(Color.BLUE);
                        Resources res = getResources();
                        Drawable shape = res.getDrawable(R.drawable.rectangle);

                        view.setBackground(shape);

                        WebView webView = (WebView)findViewById(R.id.webView);
                        webView.loadUrl("file:///android_asset/" + view.getTag() + ".html");

                        Animation hyperspaceJump = AnimationUtils.loadAnimation(view.getContext(), R.anim.hyperspace_jump);
                        view.startAnimation(hyperspaceJump);


                        //view.setBackgroundResource(R.anim.rocket);

                        //AnimationDrawable rocketAnimation = (AnimationDrawable) view.getBackground();
                        //rocketAnimation.start();
                    }
                    else
                        view.setBackgroundColor(Color.BLACK);
                }
            });

            TextView textView = (TextView)convertView.findViewById(R.id.textView);
            textView.setText(items[position].second);


            return convertView;
        }
    }
}
