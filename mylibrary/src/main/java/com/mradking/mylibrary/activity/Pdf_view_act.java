package com.mradking.mylibrary.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mradking.mylibrary.R;
import com.mradking.mylibrary.other.Ad_SetUp;

import java.io.File;


public class Pdf_view_act extends AppCompatActivity {
    PDFView pdfView;
    FloatingActionButton fab;
    int mode_check;
    Toolbar toolbar;
    String pdf_uri;

    LinearLayout adView;

    @Override
    public void onBackPressed() {
        super.onBackPressed();






    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdf_show_act);

        pdfView = (PDFView) findViewById(R.id.pdfView);
        fab=(FloatingActionButton)findViewById(R.id.fab);
        mode_check=1;
        pdf_uri=getIntent().getExtras().getString("key");


        adView=findViewById(R.id.adView);
        Ad_SetUp.load_banner_ad(this,adView);
        Ad_SetUp.loadInterstitialAd(Pdf_view_act.this);








        pdfView.fromFile(new File(pdf_uri))

                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .password(null)
                .pageFitPolicy(FitPolicy.WIDTH)

                .scrollHandle(new DefaultScrollHandle(this))
                .fitEachPage(true) // fit each page to the view, else smaller pages are scaled relative to largest page.
                .pageSnap(true) // snap pages to screen boundaries
                .pageFling(true) // make a fling change only a single page like ViewPager
                .nightMode(false)
                .load();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mode_check==1){

                    pdfView.fromFile(new File(pdf_uri))

                            .enableSwipe(true) // allows to block changing pages using swipe
                            .swipeHorizontal(false)
                            .enableDoubletap(true)
                            .password(null)
                            .pageFitPolicy(FitPolicy.WIDTH)

                            .scrollHandle(new DefaultScrollHandle(Pdf_view_act.this))
                            .fitEachPage(true) // fit each page to the view, else smaller pages are scaled relative to largest page.
                            .pageSnap(true) // snap pages to screen boundaries
                            .pageFling(true) // make a fling change only a single page like ViewPager
                            .nightMode(true)
                            .load();

                    fab.setImageResource(R.drawable.sun);

                    mode_check++;

                }else if(mode_check==2){



                    pdfView.fromFile(new File(pdf_uri))

                            .enableSwipe(true) // allows to block changing pages using swipe
                            .swipeHorizontal(false)
                            .enableDoubletap(true)
                            .password(null)
                            .pageFitPolicy(FitPolicy.WIDTH)
                            .scrollHandle(new DefaultScrollHandle(Pdf_view_act.this))
                            // fit each page to the view, else smaller pages are scaled relative to largest page.
                            // snap pages to screen boundaries
                            .pageFling(true) // make a fling change only a single page like ViewPager
                            .nightMode(false)
                            .load();
                    --mode_check;

                    fab.setImageResource(R.drawable.night_mode);

                }else {

                    mode_check=1;

                }

            }
        });






    }



}
