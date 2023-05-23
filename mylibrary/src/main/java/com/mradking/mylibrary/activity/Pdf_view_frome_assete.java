package com.mradking.mylibrary.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.codemybrainsout.ratingdialog.RatingDialog;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mradking.mylibrary.R;
import com.mradking.mylibrary.interf.show_intertails_ad_call;
import com.mradking.mylibrary.other.Ad_SetUp;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Pdf_view_frome_assete extends AppCompatActivity {
    PDFView pdfView;
    FloatingActionButton fab;
    int mode_check;
    Toolbar toolbar;
    String pdf_uri;
    private Handler mHandler;
    LinearLayout adView;
    @Override
    protected void onDestroy() {
        // Perform any necessary cleanup
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        mHandler.removeCallbacksAndMessages(null);
        ad_show();


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






        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {


                final RatingDialog ratingDialog = new RatingDialog.Builder(Pdf_view_frome_assete.this)
                        .threshold(5)

                        .title("Love our app? Show your support with a five-star rating! Thank you!")
                        .onRatingBarFormSumbit(new RatingDialog.Builder.RatingDialogFormListener() {
                            @Override
                            public void onFormSubmitted(String feedback) {

                                Intent mailIntent = new Intent(Intent.ACTION_VIEW);
                                Uri data = Uri.parse("mailto:?subject=" + "My Valuable Feedback"+ "&body=" + feedback + "&to=" + "Powerx4l5@gmail.com");
                                mailIntent.setData(data);
                                startActivity(Intent.createChooser(mailIntent, "Send mail..."));
                            }
                        }).build();

                ratingDialog.show();

            }
        }, 10000);


//
//
//
        pdfView.fromAsset(pdf_uri)

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

                    pdfView.fromAsset(pdf_uri)

                            .enableSwipe(true) // allows to block changing pages using swipe
                            .swipeHorizontal(false)
                            .enableDoubletap(true)
                            .password(null)
                            .pageFitPolicy(FitPolicy.WIDTH)

                            .scrollHandle(new DefaultScrollHandle(Pdf_view_frome_assete.this))
                            .fitEachPage(true) // fit each page to the view, else smaller pages are scaled relative to largest page.
                            .pageSnap(true) // snap pages to screen boundaries
                            .pageFling(true) // make a fling change only a single page like ViewPager
                            .nightMode(true)
                            .load();

                    fab.setImageResource(R.drawable.sun);

                    mode_check++;

                }else if(mode_check==2){



                    pdfView.fromAsset(pdf_uri)

                            .enableSwipe(true) // allows to block changing pages using swipe
                            .swipeHorizontal(false)
                            .enableDoubletap(true)
                            .password(null)
                            .pageFitPolicy(FitPolicy.WIDTH)
                            .scrollHandle(new DefaultScrollHandle(Pdf_view_frome_assete.this))
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

    private void ad_show() {
        Ad_SetUp.loadInterstitialAd(Pdf_view_frome_assete.this,
                new show_intertails_ad_call() {
                    @Override
                    public void show(InterstitialAd interstitialAd, Context context) {

                        if(interstitialAd!=null){

                            interstitialAd.show((Activity) context);
                        }else {

                            finish();
                        }
                    }

                    @Override
                    public void close(String done) {



                        finish();

                    }

                    @Override
                    public void error(String message) {

                        finish();

                    }
                });




    }


    private void updateStringResource(int resourceId, String newValue) {
        try {
            // Access the internal ResourcesImpl object
            Field field = Resources.class.getDeclaredField("mResourcesImpl");
            field.setAccessible(true);
            Object resourcesImpl = field.get(getResources());

            // Get the method to update the resource value
            Method method = resourcesImpl.getClass().getDeclaredMethod("overrideResource", int.class, String.class);
            method.setAccessible(true);

            // Update the string resource value
            method.invoke(resourcesImpl, resourceId, newValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
