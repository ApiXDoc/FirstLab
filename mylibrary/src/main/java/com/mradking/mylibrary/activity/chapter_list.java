package com.mradking.mylibrary.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mradking.mylibrary.R;
import com.mradking.mylibrary.adapter.SaveFileAdapter;
import com.mradking.mylibrary.database.DatabaseHelper;
import com.mradking.mylibrary.modal.Modal;
import com.mradking.mylibrary.other.Ad_SetUp;

import java.util.List;

public class chapter_list extends Activity {

    private RecyclerView cart_recycler_view;
    public SaveFileAdapter saveFileAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapter_list);

        DatabaseHelper db = new DatabaseHelper(this);
        cart_recycler_view =findViewById(R.id.list);

        List<Modal> contacts = db.getAllContacts();

        saveFileAdapter = new SaveFileAdapter( chapter_list.this,contacts,"1");

        LinearLayoutManager lm1 = new LinearLayoutManager(chapter_list.this, LinearLayoutManager.VERTICAL, false);



        cart_recycler_view.setHasFixedSize(true);
        cart_recycler_view.setLayoutManager(lm1);
        cart_recycler_view.setAdapter(saveFileAdapter);

        LinearLayout adView= findViewById(R.id.adView);
        Ad_SetUp.load_banner_ad(chapter_list.this,adView);


    }
}
