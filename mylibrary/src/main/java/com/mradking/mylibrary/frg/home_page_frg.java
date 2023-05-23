package com.mradking.mylibrary.frg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mradking.mylibrary.R;
import com.mradking.mylibrary.adapter.main_adapter;
import com.mradking.mylibrary.database.DatabaseHelper_Home;
import com.mradking.mylibrary.modal.Modal;
import com.mradking.mylibrary.other.Ad_SetUp;

import java.util.List;

public class home_page_frg  extends Fragment {
    private RecyclerView cart_recycler_view;
    public main_adapter Adapter;
    ImageView imageView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.books_list, container, false);
        DatabaseHelper_Home db = new DatabaseHelper_Home(getActivity());

        cart_recycler_view =view.findViewById(R.id.list);

        LinearLayout adView= view.findViewById(R.id.adView);
        Ad_SetUp.load_banner_ad(getActivity(),adView);


        List<Modal> contacts = db.getAllContacts();

        imageView=view.findViewById(R.id.image);
        imageView.setVisibility(View.GONE);


        Adapter = new main_adapter( getActivity(),contacts,"4");

        LinearLayoutManager lm1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        Toolbar toolbar=view.findViewById(R.id.toolbar);

        toolbar.setVisibility(View.GONE);

        cart_recycler_view.setHasFixedSize(true);
        cart_recycler_view.setLayoutManager(lm1);
        cart_recycler_view.setAdapter(Adapter);




        return view;
    }
}