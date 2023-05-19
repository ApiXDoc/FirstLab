package com.mradking.mylibrary.interf;

import android.content.Context;

import com.google.android.gms.ads.interstitial.InterstitialAd;

public interface show_intertails_ad_call {

    public void show(InterstitialAd interstitialAd, Context context);
    public void close(String done);
    public void error(String message);
}
