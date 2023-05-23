package com.mradking.mylibrary.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.mradking.mylibrary.R;
import com.mradking.mylibrary.other.XUtils;

public class Splash extends Activity {

    TextView app_name_tv,tag_line_tv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.splash);
            ImageView imageView2 =findViewById(R.id.imageView2);
               app_name_tv=findViewById(R.id.app_name);
                tag_line_tv=findViewById(R.id.tag_line);

        Bitmap bitmap = getIntent().getParcelableExtra("bitmap");
            String lanuga=getIntent().getExtras().getString("lanuga");
            String clas=getIntent().getExtras().getString("class");
            String home_page_url=getIntent().getExtras().getString("home_page_url");
            String app_name=getIntent().getExtras().getString("app_name");
            String tag_line=getIntent().getExtras().getString("tag_line");
            String home_page_name=getIntent().getExtras().getString("home_page_name");


        Intent intent=new Intent(Splash.this, Pdf_show_from_assets.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);


        app_name_tv.setText(app_name);
            tag_line_tv.setText(tag_line);

            imageView2.setImageBitmap(bitmap);
            XUtils utils=new XUtils();



//             utils.get_book_sol_cbse(lanuga, clas,
//                     home_page_url,
//                     home_page_name, this, new open_call() {
//                         @Override
//                         public void open() {
//
//                             Intent intent=new Intent(Splash.this, main_act.class);
//                             intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                startActivity(intent);
//                                finish();
//                         }
//
//                         @Override
//                         public void error(String message) {
//
//                         }
//                     });



    }



}
