package com.mradking.mylibrary.other;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.mradking.mylibrary.R;
import com.mradking.mylibrary.activity.Pdf_view_act;
import com.mradking.mylibrary.activity.chapter_list;
import com.mradking.mylibrary.database.DatabaseHelper;
import com.mradking.mylibrary.database.DatabaseHelper_Book2;
import com.mradking.mylibrary.database.DatabaseHelper_Book3;
import com.mradking.mylibrary.interf.show_intertails_ad_call;
import com.mradking.mylibrary.modal.Modal;


public class Download_file extends Activity {
    String url;
    private static final String DOWNLOAD_URL = "https://docs.google.com/uc?export=download&id=0BxyMs1jY42NLZ3Y0YUlPV21ZYTA";
    private ProgressDialog progressDialog;
    TextView progress_tv,download_button_txt;
    RelativeLayout btnShowProgress;
    String path;
    ProgressBar progressBar;

    DownloadManager dm;
    private WebView webView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download_layout);

        XUtils utility=new XUtils();

        utility.requestStoragePermission(Download_file.this);


        dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);

        progress_tv=findViewById(R.id.tv_status);
        url=getIntent().getExtras().getString("key");

        shareApp(url);

        LinearLayout adView= findViewById(R.id.adView);
        Ad_SetUp.load__big_banner_ad(Download_file.this,adView);



    }

    public  void shareApp(String url){


        webView = findViewById(R.id.web);
        webView.loadUrl(url);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });


        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {




                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                request.setTitle(URLUtil.guessFileName(url, contentDisposition, mimetype));
                request.setDescription("Downloading file...");
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN);
                   request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(url, contentDisposition, mimetype));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    request.setRequiresCharging(false);
                }
                request.setAllowedOverMetered(true);
                request.setVisibleInDownloadsUi(false);

                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setMimeType(mimetype);
                request.allowScanningByMediaScanner();


                long downloadId = dm.enqueue(request);
                new DownStatus().execute(String.valueOf(downloadId));


                registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));




            }


            BroadcastReceiver onComplete = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {

                    long downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                    DownloadManager.Query query = new DownloadManager.Query();
                    query.setFilterById(downloadId);
                    Cursor cursor = dm.query(query);
                    if (cursor.moveToFirst()) {
                        String title = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_TITLE));
                        String uriString = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                        Uri uri = Uri.parse(uriString);
                        String path = uri.getPath();





                        Ad_SetUp.loadInterstitialAd(Download_file.this,
                                new show_intertails_ad_call() {
                            @Override
                            public void show(InterstitialAd interstitialAd, Context context) {

                                if(interstitialAd!=null){

                                    interstitialAd.show((Activity) context);
                                }else {
                                    sharePrefX.saveString(getApplicationContext(),url,path);

                                    DatabaseHelper databaseHelper = new DatabaseHelper(context);
                                    databaseHelper.updateData(getIntent().getExtras().getString("id")
                                            ,path,"yes");

                                    Intent newActivityIntent = new Intent(context, Pdf_view_act.class);
                                    newActivityIntent.putExtra("key", path);
                                    newActivityIntent.putExtra("back_key","back");
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    finish();
                                    context.startActivity(newActivityIntent);
                                  }
                            }

                            @Override
                            public void close(String done) {

                                sharePrefX.saveString(getApplicationContext(),url,path);

                                DatabaseHelper databaseHelper = new DatabaseHelper(context);
                                databaseHelper.updateData(getIntent().getExtras().getString("id")
                                        ,path,"yes");

                                Intent newActivityIntent = new Intent(context, Pdf_view_act.class);
                                newActivityIntent.putExtra("key", path);
                                newActivityIntent.putExtra("back_key","back");
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                finish();
                                context.startActivity(newActivityIntent);


                            }

                            @Override
                            public void error(String message) {
                                sharePrefX.saveString(getApplicationContext(),url,path);

                                DatabaseHelper databaseHelper = new DatabaseHelper(context);
                                databaseHelper.updateData(getIntent().getExtras().getString("id")
                                        ,path,"yes");

                                Intent newActivityIntent = new Intent(context, Pdf_view_act.class);
                                newActivityIntent.putExtra("key", path);
                                newActivityIntent.putExtra("back_key","back");
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                finish();
                                context.startActivity(newActivityIntent);
                            }
                        });




                    }

                }
            };
        });

    }




    private String bytesIntoHumanReadable(long bytes) {
        long kilobyte = 1024;
        long megabyte = kilobyte * 1024;
        long gigabyte = megabyte * 1024;
        long terabyte = gigabyte * 1024;

        if ((bytes >= 0) && (bytes < kilobyte)) {
            return bytes + " B";

        } else if ((bytes >= kilobyte) && (bytes < megabyte)) {
            return (bytes / kilobyte) + " KB";

        } else if ((bytes >= megabyte) && (bytes < gigabyte)) {
            return (bytes / megabyte) + " MB";

        } else if ((bytes >= gigabyte) && (bytes < terabyte)) {
            return (bytes / gigabyte) + " GB";

        } else if (bytes >= terabyte) {
            return (bytes / terabyte) + " TB";

        } else {
            return bytes + " Bytes";
        }
    }






    public  class DownStatus extends AsyncTask<String,String,String> {




        @Override
        protected String doInBackground(String... strings) {
            downloadFileProcess(strings[0]);
            return null;
        }

        private void downloadFileProcess(String downloadId) {
            DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
            boolean downloading = true;
            while (downloading) {
                DownloadManager.Query query = new DownloadManager.Query();
                query.setFilterById(Long.parseLong(downloadId));
                Cursor cursor = downloadManager.query(query);
                cursor.moveToFirst();

                int bytes_downloaded = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                int total_size = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));

                if (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_SUCCESSFUL) {
                    downloading = false;
                }


                int progress = (int) ((bytes_downloaded * 100L) / total_size);
                String status = getStatusMessage(cursor);
                publishProgress(new String[]{String.valueOf(progress), String.valueOf(bytes_downloaded), status});
                cursor.close();
            }

        }

        @Override
        protected void onProgressUpdate(final String... values) {
            super.onProgressUpdate(values);

            //  textView.setText(values[0]);
            String byte_st=bytesIntoHumanReadable(Long.parseLong(values[1]));

            progress_tv.setText(values[0]+"%");

            if(Integer.parseInt(values[0])==100) {


            }


        }



    }

    private String getStatusMessage(Cursor cursor) {
        String msg="-";
        switch (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))){
            case DownloadManager.STATUS_FAILED:
                msg="Failed";
                break;
            case DownloadManager.STATUS_PAUSED:
                msg= "Paused";
                break;
            case DownloadManager.STATUS_RUNNING:
                msg= "Running";
                break;
            case DownloadManager.STATUS_SUCCESSFUL:
                msg= "Completed";
                break;
            case DownloadManager.STATUS_PENDING:
                msg= "Pending";
                break;
            default:
                msg="Unknown";
                break;
        }
        return msg;
    }


}
