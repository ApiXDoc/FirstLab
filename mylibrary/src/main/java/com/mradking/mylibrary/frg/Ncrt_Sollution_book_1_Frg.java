package com.mradking.mylibrary.frg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mradking.mylibrary.R;
import com.mradking.mylibrary.activity.chapter_list;
import com.mradking.mylibrary.adapter.SaveFileAdapter;
import com.mradking.mylibrary.database.DatabaseHelper;
import com.mradking.mylibrary.modal.Modal;
import com.mradking.mylibrary.other.Ad_SetUp;

import java.util.List;

public class Ncrt_Sollution_book_1_Frg extends Fragment {
    private RecyclerView cart_recycler_view;
    public SaveFileAdapter saveFileAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.chapter_list, container, false);
        DatabaseHelper db = new DatabaseHelper(getActivity());
        cart_recycler_view =view.findViewById(R.id.list);

        List<Modal> contacts = db.getAllContacts();


        saveFileAdapter = new SaveFileAdapter( getActivity(),contacts,"1");

        LinearLayoutManager lm1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        Toolbar toolbar=view.findViewById(R.id.toolbar);

        toolbar.setVisibility(View.GONE);

        cart_recycler_view.setHasFixedSize(true);
        cart_recycler_view.setLayoutManager(lm1);
        cart_recycler_view.setAdapter(saveFileAdapter);

        LinearLayout adView= view.findViewById(R.id.adView);
        Ad_SetUp.load_banner_ad(getActivity(),adView);



        return view;
    }
}