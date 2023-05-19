package com.mradking.mylibrary.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.mradking.mylibrary.R;
import com.mradking.mylibrary.database.DatabaseHelper;
import com.mradking.mylibrary.interf.show_intertails_ad_call;
import com.mradking.mylibrary.other.Ad_SetUp;
import com.mradking.mylibrary.other.Download_file;
import com.mradking.mylibrary.other.sharePrefX;

public class download_read_act extends AppCompatActivity {

    Button bt;
    String key;
    LinearLayout adView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_file);


        key= getIntent().getExtras().getString("key");
        adView=findViewById(R.id.adView);
        Ad_SetUp.load__big_banner_ad(this,adView);

        bt=findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Ad_SetUp.loadInterstitialAd(download_read_act.this, new show_intertails_ad_call() {
                    @Override
                    public void show(InterstitialAd interstitialAd, Context context) {

                          if(interstitialAd!=null){

                            interstitialAd.show((Activity) context);
                        }else {
                              Intent intent=new Intent(download_read_act.this, Pdf_view_act.class);
                              intent.putExtra("key", key);
                                startActivity(intent);;
                                finish();
                        }
                    }




                    @Override
                    public void close(String done) {
                        Intent intent=new Intent(download_read_act.this, Pdf_view_act.class);
                        intent.putExtra("key", key);
                        startActivity(intent);;
                        finish();
                    }

                    @Override
                    public void error(String message) {
                        Intent intent=new Intent(download_read_act.this, Pdf_view_act.class);
                        intent.putExtra("key", key);
                        startActivity(intent);
                        finish();
                    }
                });

            }
        });


    }
}
