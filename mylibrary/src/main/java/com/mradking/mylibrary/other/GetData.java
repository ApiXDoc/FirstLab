package com.mradking.mylibrary.other;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mradking.mylibrary.activity.chapter_list;
import com.mradking.mylibrary.activity.list;
import com.mradking.mylibrary.database.DatabaseHelper;
import com.mradking.mylibrary.database.DatabaseHelper_Book2;
import com.mradking.mylibrary.database.DatabaseHelper_Book3;
import com.mradking.mylibrary.database.DatabaseHeper_Chapter;
import com.mradking.mylibrary.interf.get_data_call;
import com.mradking.mylibrary.modal.Modal;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetData extends Activity {
    private static final String DOWNLOAD_URL = "https://docs.google.com/uc?export=download&id=0BxyMs1jY42NLZ3Y0YUlPV21ZYTA";
    private ProgressDialog progressDialog;
    TextView progress_tv,download_button_txt;
    RelativeLayout btnShowProgress;
    String path;
    ProgressBar progressBar;

    DownloadManager dm;
    private WebView webView;
    int i=0;
    int i2=0;
    int i3=0;



    public void getData(String link, ArrayList<String>links_list, Context context){

      if(links_list.size()-1<=i){


            String url="https://shoppingzin.com/test/example/example_basic_selector.php?url="+links_list.get(i);

            RequestQueue requestQueue= Volley.newRequestQueue(context);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String db_link_st = response.getString("db_link");
                        String chapter_name_st = response.getString("name");

                        String inputString = chapter_name_st;
                        int startIndex = inputString.indexOf(">") + 1;
                        int endIndex = inputString.indexOf("<", startIndex);
                        String result = inputString.substring(startIndex, endIndex);
                        String donwloading_link=db_link_st.replace("amp;","");


                        DatabaseHelper databaseHelper = new DatabaseHelper(context);
                        databaseHelper.insertData(new Modal(result, donwloading_link,"no"));

                        Intent intent=new Intent(context, chapter_list.class);
                        context.startActivity(intent);



                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                }
            }, new Response.ErrorListener() {
                // this is the error listener method which
                // we will call if we get any error from API.
                @Override
                public void onErrorResponse(VolleyError error) {
                    // below line is use to display a toast message along with our error.
                    Toast.makeText(context, "Fail to get data..", Toast.LENGTH_SHORT).show();
                }
            });
            // at last we are adding our json
            // object request to our request
            // queue to fetch all the json data.
            requestQueue.add(jsonObjectRequest);






        }else {


                String url="https://shoppingzin.com/test/example/example_basic_selector.php?url="+link;

                RequestQueue requestQueue= Volley.newRequestQueue(context);

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String db_link_st = response.getString("db_link");
                            String chapter_name_st = response.getString("name");

                            String inputString = chapter_name_st;
                            int startIndex = inputString.indexOf(">") + 1;
                            int endIndex = inputString.indexOf("<", startIndex);
                            String result = inputString.substring(startIndex, endIndex);

                            String donwloading_link=db_link_st.replace("amp;","");



                            DatabaseHelper databaseHelper = new DatabaseHelper(context);
                            databaseHelper.insertData(new Modal(result, donwloading_link,"no"));


                            getData(links_list.get(++i),links_list,context);

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                }, new Response.ErrorListener() {
                    // this is the error listener method which
                    // we will call if we get any error from API.
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // below line is use to display a toast message along with our error.
                        Toast.makeText(context, "Fail to get data..", Toast.LENGTH_SHORT).show();
                    }
                });
                // at last we are adding our json
                // object request to our request
                // queue to fetch all the json data.
                requestQueue.add(jsonObjectRequest);









        }




    }


    public void two_book_get_data(String book_1_chapter_link, ArrayList<String> book1_array,
                                  ArrayList<String> book2_array,ArrayList<String> book3_array,String book_name_1,String book_name_2,String book_name_3, Context context,String key){






        if(book1_array.size()-1<=i){


            String url="https://shoppingzin.com/test/example/example_basic_selector.php?url="+book1_array.get(i);

            RequestQueue requestQueue= Volley.newRequestQueue(context);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String db_link_st = response.getString("db_link");
                        String chapter_name_st = response.getString("name");

                        String inputString = chapter_name_st;
                        int startIndex = inputString.indexOf(">") + 1;
                        int endIndex = inputString.indexOf("<", startIndex);
                        String result = inputString.substring(startIndex, endIndex);
                        String donwloading_link=db_link_st.replace("amp;","");


                        DatabaseHelper databaseHelper = new DatabaseHelper(context);
                        databaseHelper.insertData(new Modal(result, donwloading_link,"no"));

                        one_book_get_data(book2_array.get(0),book1_array,book2_array,book3_array,book_name_1,book_name_2,book_name_3,context,key);



                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                }
            }, new Response.ErrorListener() {
                // this is the error listener method which
                // we will call if we get any error from API.
                @Override
                public void onErrorResponse(VolleyError error) {
                    // below line is use to display a toast message along with our error.
                    Toast.makeText(context, "Fail to get data..", Toast.LENGTH_SHORT).show();
                }
            });
            // at last we are adding our json
            // object request to our request
            // queue to fetch all the json data.
            requestQueue.add(jsonObjectRequest);






        }else {


            String url="https://shoppingzin.com/test/example/example_basic_selector.php?url="+book_1_chapter_link;

            RequestQueue requestQueue= Volley.newRequestQueue(context);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String db_link_st = response.getString("db_link");
                        String chapter_name_st = response.getString("name");

                        String inputString = chapter_name_st;
                        int startIndex = inputString.indexOf(">") + 1;
                        int endIndex = inputString.indexOf("<", startIndex);
                        String result = inputString.substring(startIndex, endIndex);

                        String donwloading_link=db_link_st.replace("amp;","");



                        DatabaseHelper databaseHelper = new DatabaseHelper(context);
                        databaseHelper.insertData(new Modal(result, donwloading_link,"no"));


                        two_book_get_data(book1_array.get(++i),book1_array,book2_array,book3_array,book_name_1,book_name_2,book_name_3,context,key);

                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                }
            }, new Response.ErrorListener() {
                // this is the error listener method which
                // we will call if we get any error from API.
                @Override
                public void onErrorResponse(VolleyError error) {
                    // below line is use to display a toast message along with our error.
                    Toast.makeText(context, "Fail to get data..", Toast.LENGTH_SHORT).show();
                }
            });
            // at last we are adding our json
            // object request to our request
            // queue to fetch all the json data.
            requestQueue.add(jsonObjectRequest);









        }





    }

    private void one_book_get_data(String s, ArrayList<String> book1_array, ArrayList<String> book2_array, ArrayList<String> book3_array, String book_name_1, String book_name_2,String book_name_3, Context context,String key) {




        if(book2_array.size()-1<=i2){


            String url="https://shoppingzin.com/test/example/example_basic_selector.php?url="+book2_array.get(i2);

            RequestQueue requestQueue= Volley.newRequestQueue(context);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String db_link_st = response.getString("db_link");
                        String chapter_name_st = response.getString("name");

                        String inputString = chapter_name_st;
                        int startIndex = inputString.indexOf(">") + 1;
                        int endIndex = inputString.indexOf("<", startIndex);
                        String result = inputString.substring(startIndex, endIndex);
                        String donwloading_link=db_link_st.replace("amp;","");


                        DatabaseHelper_Book2 databaseHelper = new DatabaseHelper_Book2(context);
                        databaseHelper.insertData(new Modal(result, donwloading_link,"no"));


                        if(key.contentEquals("2")){

                            Intent intent=new Intent(context, list.class);
                            intent.putExtra("book1_name",book_name_1);
                            intent.putExtra("book2_name",book_name_2);
                            intent.putExtra("number_book","2");
                            context.startActivity(intent);

                        }else {

                          third_book(book3_array.get(0),book3_array,book_name_1,book_name_2,book_name_3,context);

                        }




                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                }
            }, new Response.ErrorListener() {
                // this is the error listener method which
                // we will call if we get any error from API.
                @Override
                public void onErrorResponse(VolleyError error) {
                    // below line is use to display a toast message along with our error.
                    Toast.makeText(context, "Fail to get data..", Toast.LENGTH_SHORT).show();
                }
            });
            // at last we are adding our json
            // object request to our request
            // queue to fetch all the json data.
            requestQueue.add(jsonObjectRequest);






        }else {


            String url="https://shoppingzin.com/test/example/example_basic_selector.php?url="+s;

            RequestQueue requestQueue= Volley.newRequestQueue(context);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String db_link_st = response.getString("db_link");
                        String chapter_name_st = response.getString("name");

                        String inputString = chapter_name_st;
                        int startIndex = inputString.indexOf(">") + 1;
                        int endIndex = inputString.indexOf("<", startIndex);
                        String result = inputString.substring(startIndex, endIndex);

                        String donwloading_link=db_link_st.replace("amp;","");



                        DatabaseHelper_Book2 databaseHelper = new DatabaseHelper_Book2(context);
                        databaseHelper.insertData(new Modal(result, donwloading_link,"no"));


                        one_book_get_data(book2_array.get(++i2),book1_array,book2_array,book3_array,book_name_1,book_name_2,book_name_3,context,key);

                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                }
            }, new Response.ErrorListener() {
                // this is the error listener method which
                // we will call if we get any error from API.
                @Override
                public void onErrorResponse(VolleyError error) {
                    // below line is use to display a toast message along with our error.
                    Toast.makeText(context, "Fail to get data..", Toast.LENGTH_SHORT).show();
                }
            });
            // at last we are adding our json
            // object request to our request
            // queue to fetch all the json data.
            requestQueue.add(jsonObjectRequest);









        }




    }




    public void three_book_get_data(String link, ArrayList<String> book1_array,
                                    ArrayList<String> book2_array, ArrayList<String> book3_array, String book_name_1, String book_name_2, String book_name_3, Context context){


     two_book_get_data(book1_array.get(0),book1_array,book2_array,book3_array,
                book_name_1,book_name_2,book_name_3,context,"3");



    }



    public void third_book(String link, ArrayList<String>links_list,String book_name_1, String book_name_2, String book_name_3, Context context){




        if(links_list.size()-1<=i3){


            String url="https://shoppingzin.com/test/example/example_basic_selector.php?url="+links_list.get(i3);

            RequestQueue requestQueue= Volley.newRequestQueue(context);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String db_link_st = response.getString("db_link");
                        String chapter_name_st = response.getString("name");

                        String inputString = chapter_name_st;
                        int startIndex = inputString.indexOf(">") + 1;
                        int endIndex = inputString.indexOf("<", startIndex);
                        String result = inputString.substring(startIndex, endIndex);
                        String donwloading_link=db_link_st.replace("amp;","");


                        DatabaseHelper_Book3 databaseHelper = new DatabaseHelper_Book3(context);
                        databaseHelper.insertData(new Modal(result, donwloading_link,"no"));


                        Intent intent=new Intent(context, list.class);
                        intent.putExtra("book1_name",book_name_1);
                        intent.putExtra("book2_name",book_name_2);
                        intent.putExtra("book2_name",book_name_3);
                        intent.putExtra("number_book","3");
                        context.startActivity(intent);



                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                }
            }, new Response.ErrorListener() {
                // this is the error listener method which
                // we will call if we get any error from API.
                @Override
                public void onErrorResponse(VolleyError error) {
                    // below line is use to display a toast message along with our error.
                    Toast.makeText(context, "Fail to get data..", Toast.LENGTH_SHORT).show();
                }
            });
            // at last we are adding our json
            // object request to our request
            // queue to fetch all the json data.
            requestQueue.add(jsonObjectRequest);






        }else {


            String url="https://shoppingzin.com/test/example/example_basic_selector.php?url="+link;

            RequestQueue requestQueue= Volley.newRequestQueue(context);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String db_link_st = response.getString("db_link");
                        String chapter_name_st = response.getString("name");

                        String inputString = chapter_name_st;
                        int startIndex = inputString.indexOf(">") + 1;
                        int endIndex = inputString.indexOf("<", startIndex);
                        String result = inputString.substring(startIndex, endIndex);

                        String donwloading_link=db_link_st.replace("amp;","");



                        DatabaseHelper_Book3 databaseHelper = new DatabaseHelper_Book3(context);
                        databaseHelper.insertData(new Modal(result, donwloading_link,"no"));


                        third_book(links_list.get(++i3),links_list, book_name_1,  book_name_2,  book_name_3,context);

                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                }
            }, new Response.ErrorListener() {
                // this is the error listener method which
                // we will call if we get any error from API.
                @Override
                public void onErrorResponse(VolleyError error) {
                    // below line is use to display a toast message along with our error.
                    Toast.makeText(context, "Fail to get data..", Toast.LENGTH_SHORT).show();
                }
            });
            // at last we are adding our json
            // object request to our request
            // queue to fetch all the json data.
            requestQueue.add(jsonObjectRequest);









        }




    }


    public void data_for_book(String link, ArrayList<String>links_list, Context context,String database_name, get_data_call call){
        List<Modal>list = new ArrayList<>();


        if(links_list.size()-1<=i){


            String url="https://shoppingzin.com/test/example/example_basic_selector.php?url="+links_list.get(i);

            RequestQueue requestQueue= Volley.newRequestQueue(context);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String db_link_st = response.getString("db_link");
                        String chapter_name_st = response.getString("name");

                        String inputString = chapter_name_st;
                        int startIndex = inputString.indexOf(">") + 1;
                        int endIndex = inputString.indexOf("<", startIndex);
                        String result = inputString.substring(startIndex, endIndex);
                        String donwloading_link=db_link_st.replace("amp;","");

                        DatabaseHeper_Chapter databaseHelper = new DatabaseHeper_Chapter(context);
                        databaseHelper.insertData(new Modal(result, donwloading_link,"no"));



                        call.onsusess(list);



                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                }
            }, new Response.ErrorListener() {
                // this is the error listener method which
                // we will call if we get any error from API.
                @Override
                public void onErrorResponse(VolleyError error) {
                    // below line is use to display a toast message along with our error.
                    call.failed(error.toString());

                }
            });
            // at last we are adding our json
            // object request to our request
            // queue to fetch all the json data.
            requestQueue.add(jsonObjectRequest);






        }else {


            String url="https://shoppingzin.com/test/example/example_basic_selector.php?url="+link;

            RequestQueue requestQueue= Volley.newRequestQueue(context);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String db_link_st = response.getString("db_link");
                        String chapter_name_st = response.getString("name");

                        String inputString = chapter_name_st;
                        int startIndex = inputString.indexOf(">") + 1;
                        int endIndex = inputString.indexOf("<", startIndex);
                        String result = inputString.substring(startIndex, endIndex);

                        String donwloading_link=db_link_st.replace("amp;","");

                        DatabaseHeper_Chapter databaseHelper = new DatabaseHeper_Chapter(context);
                        databaseHelper.insertData(new Modal(result, donwloading_link,"no"));


                        data_for_book(links_list.get(++i),links_list,context,database_name,call);

                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                }
            }, new Response.ErrorListener() {
                // this is the error listener method which
                // we will call if we get any error from API.
                @Override
                public void onErrorResponse(VolleyError error) {
                    // below line is use to display a toast message along with our error.
                    call.failed(error.toString());
                }
            });
            // at last we are adding our json
            // object request to our request
            // queue to fetch all the json data.
            requestQueue.add(jsonObjectRequest);









        }




    }




}
