package com.mradking.firstlab;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.mradking.mylibrary.activity.Splash;
import com.mradking.mylibrary.other.Ad_SetUp;
import com.mradking.mylibrary.other.XUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//
        XUtils utils=new XUtils();
        Ad_SetUp adSetUp=new Ad_SetUp();


        adSetUp.set_ad_values(MainActivity.this,"ca-app-pub-3940256099942544/6300978111",
                "ca-app-pub-3940256099942544/1033173712");



         utils.get_book_name("https://www.ncrtsolutions.in/p/ncert-solutions-for-class-10.html"
                 ,MainActivity.this);




    }






}