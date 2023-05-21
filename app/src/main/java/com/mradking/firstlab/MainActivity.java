package com.mradking.firstlab;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;


import com.mradking.mylibrary.activity.Splash;
import com.mradking.mylibrary.other.Ad_SetUp;
import com.mradking.mylibrary.other.XUtils;


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



//         utils.get_book_name("https://www.ncrtsolutions.in/p/ncert-solutions-for-class-11.html"
//                 ,MainActivity.this);



//         utils.get_book_sol_cbse("english", "11th",
//                 "books/ncert-notes/english/class-11th/maths/1471",
//                 "Maths Notes"
//                 , MainActivity.this);

        Drawable drawable = getResources().getDrawable(R.drawable.my_exam); // Replace "my_image" with the actual name of your drawable resource

        utils.app_set_up("english"
                 ,"10th",
                "Science notes"
                ,"books/ncert-notes/english/class-10th/maths/1471"
                ,"10th Science Notes"
                ,"Notes || Books || Solutions"
                , drawable
                ,this
                );




    }






}