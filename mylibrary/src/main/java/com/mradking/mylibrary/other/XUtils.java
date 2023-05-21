package com.mradking.mylibrary.other;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.mradking.mylibrary.activity.Splash;
import com.mradking.mylibrary.activity.chapter_list;
import com.mradking.mylibrary.activity.list;
import com.mradking.mylibrary.activity.main_act;
import com.mradking.mylibrary.database.DatabaseHelper;
import com.mradking.mylibrary.database.DatabaseHelper_Book2;
import com.mradking.mylibrary.database.DatabaseHelper_Book3;
import com.mradking.mylibrary.database.DatabaseHelper_Home;
import com.mradking.mylibrary.database.DatabaseHeper_Chapter;
import com.mradking.mylibrary.interf.get_data_call;
import com.mradking.mylibrary.modal.Modal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class XUtils extends Activity {

    private static Context context;






    public String getRandomString(int length) {
        String allowedChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = (int) (Math.random() * allowedChars.length());
            sb.append(allowedChars.charAt(randomIndex));
        }

        return sb.toString();
    }


    public void requestStoragePermission(Context context) {
        Dexter.withActivity((Activity) context)
                .withPermissions(
                        Manifest.permission.INTERNET,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE

                )
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {




                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            showSettingsDialog(context);
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(context, "Error occurred! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }


    public void showSettingsDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Need Permissions");
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                openSettings(context);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }


    public void openSettings(Context context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);

    }



    public static void shareApp(Context context) {
        final String appPackageName = context.getPackageName();
        try {
            String appLink = "https://play.google.com/store/apps/details?id=" + appPackageName;

            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, " 10th class Science Notes ");
            String shareMessage="PDF Spliter is one of best App" +
                    "\uD83D\uDC49\uD83C\uDFFBApp Link:-"+appLink +
                    "\n";
            context.startActivity(Intent.createChooser(shareIntent, "choose one"));

            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
        } catch(Exception e) {

            String appLink = "https://play.google.com/store/apps/details?id=" + appPackageName;

            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, " 10th class Science Notes ");
            String shareMessage="PDF Spliter is one of best App" +
                    "\uD83D\uDC49\uD83C\uDFFBApp Link:-"+appLink +
                    "\n";
            context.startActivity(Intent.createChooser(shareIntent, "choose one"));

            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
        }

    }

    public static void rateApp(Context context) {
        final String appPackageName = context.getPackageName();
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }



    public static void MoreApp(Context context) {
        final String appName = context.getPackageName();
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appName)));
        } catch (ActivityNotFoundException anfe) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appName)));
        }
    }


    public void one_book( String url, Context context){
        SharedPreferences sharedPref = context.getSharedPreferences("myPrefs", context.MODE_PRIVATE);


        if (sharedPref.getBoolean("isFirstRun", true)) {
            // This is the first time the app is run, so run your code here
            // ...

            // Save a boolean value in preferences to indicate that the app has been run once
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean("isFirstRun", false);
            editor.apply();
            boolean isSuccess = true; // Set isSuccess to true if the task was successful, false otherwise
           
                getArray(url,context);
        //    getData.getData(getArray(url).get(0),getArray(url),context);


        }else {

            Intent intent=new Intent(context, chapter_list.class);
            context.startActivity(intent);


        }


    }

    public void book_data(String url, Context context, get_data_call call) {
        List<Modal>list_test = new ArrayList<>();
        ArrayList<String> list = new ArrayList<>();
        GetData getData = new GetData();
        RequestQueue queue = Volley.newRequestQueue(context);


        String url_1 = "https://shoppingzin.com/test/example/chapter_list_cbse.php?url="+url;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url_1, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String link = jsonObject.getString("link");

                                String chapter_name_st = jsonObject.getString("chapter_name");

                                DatabaseHeper_Chapter databaseHelper = new DatabaseHeper_Chapter(context);
                                databaseHelper.insertData(new Modal(chapter_name_st, link,"no"));


                            }

                            call.onsusess(list_test);


                        } catch (JSONException e) {
                            e.printStackTrace();
                            call.failed("JSON parsing error");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        call.failed("Network request error");
                    }
                });

        queue.add(request);
    }


    public void book_download_data(String url,Context context,get_data_call call){
       List<Modal>list_test = new ArrayList<>();
       ArrayList<String> list = new ArrayList<String>();
       GetData getData=new GetData();
       RequestQueue queue = Volley.newRequestQueue(context);

// Create a new JSON request to the server
       String url_1 = "https://shoppingzin.com/test/example/cbse_books_download.php?url="+url;
       JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url_1, null,
               new Response.Listener<JSONArray>() {
                   @Override
                   public void onResponse(JSONArray response) {
                       try {
                           // Loop through each JSON object in the array
                           for (int i = 0; i < response.length(); i++) {
                               // Extract the data you need from each JSON object
                               JSONObject jsonObject = response.getJSONObject(i);
                               String link = jsonObject.getString("link");
                               String chapter_name_st = jsonObject.getString("chapter_name");
                               String donwloading_link=link.replace("amp;","");

                               DatabaseHeper_Chapter databaseHelper = new DatabaseHeper_Chapter(context);
                               databaseHelper.insertData(new Modal(chapter_name_st, donwloading_link,"no"));



                               list.add(link);


                               // Do something with the extracted data

                           }
                       } catch (JSONException e) {
                           e.printStackTrace();
                       }

                        call.onsusess(list_test);

                   }
               },
               new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {
                       error.printStackTrace();
                       call.failed(error.getMessage().toString());
                   }
               });

// Add the JSON request to the request queue
       queue.add(request);


   }



    public void two_book( ArrayList<String> book1_array,
                         ArrayList<String> book2_array,String book_name_1,String book_name_2, Context context){
        SharedPreferences sharedPref = context.getSharedPreferences("myPrefs", context.MODE_PRIVATE);
        GetData getData=new GetData();

        if (sharedPref.getBoolean("isFirstRun", true)) {
            // This is the first time the app is run, so run your code here
            // ...

            // Save a boolean value in preferences to indicate that the app has been run once
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean("isFirstRun", false);
            editor.apply();
            boolean isSuccess = true; // Set isSuccess to true if the task was successful, false otherwise



            getData.two_book_get_data(book1_array.get(0),book1_array,book2_array,book2_array,
                    book_name_1,book_name_2,book_name_2,context,"2");


        }else {

            Intent intent=new Intent(context, list.class);
            intent.putExtra("book1_name",book_name_1);
            intent.putExtra("book2_name",book_name_2);
            intent.putExtra("number_book","2");

            context.startActivity(intent);


        }


    }


    public void three_book( ArrayList<String> book1_array,
                          ArrayList<String> book2_array, ArrayList<String> book3_array,String book_name_1,String book_name_2,String book_name_3, Context context){
        SharedPreferences sharedPref = context.getSharedPreferences("myPrefs", context.MODE_PRIVATE);
        GetData getData=new GetData();

        if (sharedPref.getBoolean("isFirstRun", true)) {
            // This is the first time the app is run, so run your code here
            // ...

            // Save a boolean value in preferences to indicate that the app has been run once
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean("isFirstRun", false);
            editor.apply();
            boolean isSuccess = true; // Set isSuccess to true if the task was successful, false otherwise


            getData.three_book_get_data(book1_array.get(0),book1_array,book2_array,book3_array,book_name_1,book_name_2,book_name_3,context);


        }else {

            Intent intent=new Intent(context, list.class);
            intent.putExtra("book1_name",book_name_1);
            intent.putExtra("book2_name",book_name_2);
            intent.putExtra("book3_name",book_name_3);
            intent.putExtra("number_book","3");

            context.startActivity(intent);


        }


    }

    public ArrayList<String> get_two_book_array(String book1_url,String book2_url,String book_name1 ,String book_name2,Context context){
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> list1 = new ArrayList<String>();
        GetData getData=new GetData();
        RequestQueue queue = Volley.newRequestQueue(context);

        book1_url = "https://shoppingzin.com/test/example/chapter_list_cbse.php?url="+book1_url;
         JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, book1_url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // Loop through each JSON object in the array
                            for (int i = 0; i < response.length(); i++) {
                                // Extract the data you need from each JSON object
                                JSONObject jsonObject = response.getJSONObject(i);
                                String link = jsonObject.getString("link");

                                list.add(link);


                                // Do something with the extracted data

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

               /////////book 2

                       String book2_url_st="https://shoppingzin.com/test/example/chapter_list_cbse.php?url="+book2_url;

                        JsonArrayRequest request1 = new JsonArrayRequest(Request.Method.GET, book2_url_st, null, new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {




                                try {
                                    // Loop through each JSON object in the array
                                    for (int i = 0; i < response.length(); i++) {
                                        // Extract the data you need from each JSON object
                                        JSONObject jsonObject = response.getJSONObject(i);
                                        String link = jsonObject.getString("link");

                                        list1.add(link);


                                        // Do something with the extracted data

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }





                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                            }
                        });


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

// Add the JSON request to the request queue
        queue.add(request);
        return list;
    }

    public ArrayList<String> getArray(String url,Context context) {
        ArrayList<String> list = new ArrayList<String>();
        GetData getData=new GetData();
        RequestQueue queue = Volley.newRequestQueue(context);

// Create a new JSON request to the server
         url = "https://shoppingzin.com/test/example/chapter_list_cbse.php?url="+url;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // Loop through each JSON object in the array
                            for (int i = 0; i < response.length(); i++) {
                                // Extract the data you need from each JSON object
                                JSONObject jsonObject = response.getJSONObject(i);
                                String link = jsonObject.getString("link");

                                list.add(link);


                                 // Do something with the extracted data

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        getData.getData(list.get(0),list,context);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

// Add the JSON request to the request queue
        queue.add(request);


        return list;
    }


    public void get_book_name(String url, Context context){


        DatabaseHelper db = new DatabaseHelper(context);
        List<Modal> contacts = db.getAllContacts();

        if(contacts.size()==0){

            RequestQueue queue = Volley.newRequestQueue(context);

// Create a new JSON request to the server
            String url_1 = "https://shoppingzin.com/test/example/cbse_books_type.php?url="+url+"&type=solution";
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url_1, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                // Loop through each JSON object in the array
                                for (int i = 0; i < response.length(); i++) {
                                    // Extract the data you need from each JSON object
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    String book_name = jsonObject.getString("book_name");
                                    String link = jsonObject.getString("link");

                                    if(book_name.toLowerCase().contains("hindi")){

                                    }else {
                                        DatabaseHelper databaseHelper = new DatabaseHelper(context);
                                        databaseHelper.insertData(new Modal(book_name, link,"no"));

                                    }


                                    // Do something with the extracted data

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            get_book_data_notes(url,context);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });

// Add the JSON request to the request queue
            queue.add(request);

        }else {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent intent=new Intent(context, main_act.class);
                    context.startActivity(intent);
                    finish();
                }
            }, 2000);



        }




    }

    private void get_book_data_notes(String url, Context context) {

        List<Modal>list = null;
        GetData getData=new GetData();
        RequestQueue queue = Volley.newRequestQueue(context);

// Create a new JSON request to the server
        String url_1 = "https://shoppingzin.com/test/example/cbse_books_type.php?url="+url+"&type=note";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url_1, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // Loop through each JSON object in the array
                            for (int i = 0; i < response.length(); i++) {
                                // Extract the data you need from each JSON object
                                JSONObject jsonObject = response.getJSONObject(i);
                                String book_name = jsonObject.getString("book_name");
                                String link = jsonObject.getString("link");

                                DatabaseHelper_Book2 databaseHelper = new DatabaseHelper_Book2(context);
                                databaseHelper.insertData(new Modal(book_name, link,"no"));

                                // Do something with the extracted data

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

//
                        get_book_data_book(url,context);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

// Add the JSON request to the request queue
        queue.add(request);

    }

    private void get_book_data_book(String url, Context context) {

        List<Modal>list = null;
        GetData getData=new GetData();
        RequestQueue queue = Volley.newRequestQueue(context);

// Create a new JSON request to the server
        String url_1 = "https://shoppingzin.com/test/example/cbse_books_type.php?url="+url+"&type=book";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url_1, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // Loop through each JSON object in the array
                            for (int i = 0; i < response.length(); i++) {
                                // Extract the data you need from each JSON object
                                JSONObject jsonObject = response.getJSONObject(i);
                                String book_name = jsonObject.getString("book_name");
                                String link = jsonObject.getString("link");

                                DatabaseHelper_Book3 databaseHelper = new DatabaseHelper_Book3(context);
                                databaseHelper.insertData(new Modal(book_name, link,"no"));

                                // Do something with the extracted data

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();

                        }
                        Intent intent=new Intent(context, main_act.class);
                        context.startActivity(intent);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

// Add the JSON request to the request queue
        queue.add(request);

    }


    public void get_book_sol_cbse(String lng, String clas, String home_page_url,
                                  String app_name, Context context){


        DatabaseHelper db = new DatabaseHelper(context);
        List<Modal> contacts = db.getAllContacts();

        if(contacts.size()==0){

            RequestQueue queue = Volley.newRequestQueue(context);

// Create a new JSON request to the server
            String url_1 = "https://shoppingzin.com/test/example/books_list.php?url="+"https://www.selfstudys.com/books/ncert-solution/"+lng+"/"+clas;
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url_1, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                // Loop through each JSON object in the array
                                for (int i = 0; i < response.length(); i++) {
                                    // Extract the data you need from each JSON object
                                    JSONObject jsonObject = response.getJSONObject(i);

                                    String link = jsonObject.getString("link");
                                    String book_name = jsonObject.getString("chapter_name");
//                                    String decodedString = StringEscapeUtils.unescapeJava(book_name);


                                    DatabaseHelper databaseHelper = new DatabaseHelper(context);
                                    databaseHelper.insertData(new Modal(book_name, link,"no"));

                                    // Do something with the extracted data

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            get_book_data_notes_cbse(lng,clas,home_page_url,app_name,context);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });

// Add the JSON request to the request queue
            queue.add(request);

        }else {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent intent=new Intent(context, main_act.class);
                    context.startActivity(intent);
                    ((Activity) context).finish();
                }
            }, 2000);



        }




    }
    private void get_book_data_notes_cbse(String lng, String clas, String home_page_url, String app_name, Context context) {

        List<Modal>list = null;
        GetData getData=new GetData();
        RequestQueue queue = Volley.newRequestQueue(context);

// Create a new JSON request to the server
        String url_1 = "https://shoppingzin.com/test/example/books_list.php?url="+"https://www.selfstudys.com/books/ncert-notes/"+lng+"/class-"+clas;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url_1, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // Loop through each JSON object in the array
                            for (int i = 0; i < response.length(); i++) {
                                // Extract the data you need from each JSON object
                                JSONObject jsonObject = response.getJSONObject(i);

                                String link = jsonObject.getString("link");

                                String book_name = jsonObject.getString("chapter_name");
//                                String decodedString = StringEscapeUtils.unescapeJava(book_name);

                                DatabaseHelper_Book2 databaseHelper = new DatabaseHelper_Book2(context);
                                databaseHelper.insertData(new Modal(book_name, link,"no"));

                                // Do something with the extracted data

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

//
                        get_book_data_book_cbse(lng,clas,home_page_url,app_name,context);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

// Add the JSON request to the request queue
        queue.add(request);

    }

    private void get_book_data_book_cbse(String lng, String clas, String home_page_url, String app_name, Context context) {

        List<Modal>list = null;
        GetData getData=new GetData();
        RequestQueue queue = Volley.newRequestQueue(context);
        String newclass=clas.replace("th","");


// Create a new JSON request to the server
        String url_1 = "https://shoppingzin.com/test/example/books_list.php?url="+"https://www.selfstudys.com/books/ncert-books-pdf/"+lng+"/class-"+newclass;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url_1, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // Loop through each JSON object in the array
                            for (int i = 0; i < response.length(); i++) {
                                // Extract the data you need from each JSON object
                                JSONObject jsonObject = response.getJSONObject(i);

                                String link = jsonObject.getString("link");
                                String book_name = jsonObject.getString("chapter_name");

                                DatabaseHelper_Book3 databaseHelper = new DatabaseHelper_Book3(context);
                                databaseHelper.insertData(new Modal(book_name, link,"no"));

                                // Do something with the extracted data

                            }
                            home_page_book_data_cbse(home_page_url,app_name,context);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();

                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

// Add the JSON request to the request queue
        queue.add(request);
    }

    public void book_data_cbse(String url, Context context, get_data_call call) {
        List<Modal>list_test = new ArrayList<>();
        ArrayList<String> list = new ArrayList<>();
        GetData getData = new GetData();
        RequestQueue queue = Volley.newRequestQueue(context);


        String url_1 = "https://shoppingzin.com/test/example/books_chapter_list.php?url="+"https://www.selfstudys.com"+url;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url_1, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String link = jsonObject.getString("link");

                                String chapter_name_st = jsonObject.getString("chapter_name");

                                DatabaseHeper_Chapter databaseHelper = new DatabaseHeper_Chapter(context);
                                databaseHelper.insertData(new Modal(chapter_name_st, link,"no"));


                            }

                            call.onsusess(list_test);


                        } catch (JSONException e) {
                            e.printStackTrace();
                            call.failed("JSON parsing error");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        call.failed("Network request error");
                    }
                });

        queue.add(request);
    }
    public void home_page_book_data_cbse(String url, String main_app_subject, Context context) {

        RequestQueue queue = Volley.newRequestQueue(context);


        String url_1 = "https://shoppingzin.com/test/example/books_chapter_list.php?url="+"https://www.selfstudys.com/"+url;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url_1, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String link = jsonObject.getString("link");

                                String chapter_name_st = jsonObject.getString("chapter_name");

                                DatabaseHelper_Home databaseHelper = new DatabaseHelper_Home(context);
                                databaseHelper.insertData(new Modal(chapter_name_st, link,"no"));
                                sharePrefX.saveString(context,"app_name",main_app_subject);

                            }

                            Intent intent=new Intent(context, main_act.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            context.startActivity(intent);
                            ((Activity) context).finish();





                        } catch (JSONException e) {
                            e.printStackTrace();

                       
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();

                    }
                });

        queue.add(request);
    }


    public void app_set_up(String lanuga, String clas,String home_page_name ,String home_page_url, String app_name, String TagLine, Drawable drawable,Context context){


        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

        Intent intent=new Intent(context, Splash.class);
        intent.putExtra("bitmap", bitmap);
        intent.putExtra("lanuga",lanuga);
        intent.putExtra("class",clas);
        intent.putExtra("home_page_url",home_page_url);
        intent.putExtra("app_name",app_name);
        intent.putExtra("tag_line",TagLine);
        intent.putExtra("home_page_name",home_page_name);
        context.startActivity(intent);
        ((Activity) context).finish();

    }


}
