package com.mradking.mylibrary.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mradking.mylibrary.R;
import com.mradking.mylibrary.adapter.SaveFileAdapter;
import com.mradking.mylibrary.database.DatabaseHelper;
import com.mradking.mylibrary.database.DatabaseHeper_Chapter;
import com.mradking.mylibrary.interf.get_data_call;
import com.mradking.mylibrary.modal.Modal;
import com.mradking.mylibrary.other.Ad_SetUp;
import com.mradking.mylibrary.other.TimerUtility;
import com.mradking.mylibrary.other.XUtils;

import java.util.List;

public class chapter_list extends Activity {

    private RecyclerView cart_recycler_view;
    public SaveFileAdapter saveFileAdapter;
    LinearLayout progress_bar;
    TextView tv_status;
    String url;
    XUtils utils;
    DatabaseHeper_Chapter db;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapter_list);
        progress_bar=findViewById(R.id.progess_bar);

        url=getIntent().getExtras().getString("key");
        toolbar=findViewById(R.id.toolbar);
        tv_status=findViewById(R.id.tv_status);
         db = new DatabaseHeper_Chapter(this);
        cart_recycler_view =findViewById(R.id.list);



         utils =new XUtils();

        book_data_set_up(getIntent().getExtras().getString("book"));

        timer_set();





        LinearLayout adView= findViewById(R.id.adView);
        Ad_SetUp.load_banner_ad(chapter_list.this,adView);


    }

    private void timer_set() {

        TimerUtility.TimerListener listener = new TimerUtility.TimerListener() {
            @Override
            public void onTimerFinish() {

            }
        };
        TimerUtility timerUtility = new TimerUtility(60000, 1000, tv_status, listener);
        timerUtility.startTimer();

    }

    private void book_data_set_up(String book) {
        String book_name=getIntent().getExtras().getString("book_name");
        if(book.contentEquals("book")){

            toolbar.setTitle(book_name);
            utils.book_download_data( url
                    , this, new get_data_call() {
                        @Override
                        public void onsusess(List<Modal> list) {
                            progress_bar.setVisibility(View.GONE);

                            List<Modal> contacts = db.getAllContacts();

                            saveFileAdapter = new SaveFileAdapter( chapter_list.this,contacts,"3");

                            LinearLayoutManager lm1 = new LinearLayoutManager(chapter_list.this, LinearLayoutManager.VERTICAL, false);


                            cart_recycler_view.setVisibility(View.VISIBLE);
                            cart_recycler_view.setHasFixedSize(true);
                            cart_recycler_view.setLayoutManager(lm1);
                            cart_recycler_view.setAdapter(saveFileAdapter);


                        }

                        @Override
                        public void failed(String message) {

                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                        }

                    });


        }
        else if(book.contentEquals("note")){
            toolbar.setTitle(book_name+" Notes");
            utils.book_data( url
                    , this, new get_data_call() {
                        @Override
                        public void onsusess(List<Modal> list) {
                            progress_bar.setVisibility(View.GONE);

                            List<Modal> contacts = db.getAllContacts();

                            saveFileAdapter = new SaveFileAdapter( chapter_list.this,contacts,"2");

                            LinearLayoutManager lm1 = new LinearLayoutManager(chapter_list.this, LinearLayoutManager.VERTICAL, false);


                            cart_recycler_view.setVisibility(View.VISIBLE);
                            cart_recycler_view.setHasFixedSize(true);
                            cart_recycler_view.setLayoutManager(lm1);
                            cart_recycler_view.setAdapter(saveFileAdapter);


                        }

                        @Override
                        public void failed(String message) {

                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                        }

                    });
        }
        else if(book.contentEquals("solution")){
            toolbar.setTitle(book_name+" Solutions");
            utils.book_data( url
                    , this, new get_data_call() {
                        @Override
                        public void onsusess(List<Modal> list) {
                            progress_bar.setVisibility(View.GONE);

                            List<Modal> contacts = db.getAllContacts();

                            saveFileAdapter = new SaveFileAdapter( chapter_list.this,contacts,"1");

                            LinearLayoutManager lm1 = new LinearLayoutManager(chapter_list.this, LinearLayoutManager.VERTICAL, false);


                            cart_recycler_view.setVisibility(View.VISIBLE);
                            cart_recycler_view.setHasFixedSize(true);
                            cart_recycler_view.setLayoutManager(lm1);
                            cart_recycler_view.setAdapter(saveFileAdapter);


                        }

                        @Override
                        public void failed(String message) {

                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                        }

                    });
        }
    }
}
