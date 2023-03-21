package com.mradking.mylibrary.activity;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.mradking.mylibrary.R;
import com.mradking.mylibrary.other.Ad_SetUp;
import com.mradking.mylibrary.other.XUtils;

public class Splash extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);



        XUtils utils=new XUtils();


        ImageView imageView=findViewById(R.id.imageView2);



        utils.one_book(getIntent().getExtras().getString("url"),Splash.this);


    }

    public void data_set(Context context){


    }

}
