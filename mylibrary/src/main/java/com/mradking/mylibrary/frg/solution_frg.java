package com.mradking.mylibrary.frg;

import android.graphics.drawable.Drawable;
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
import com.mradking.mylibrary.adapter.SaveFileAdapter;
import com.mradking.mylibrary.adapter.main_adapter;
import com.mradking.mylibrary.database.DatabaseHelper;
import com.mradking.mylibrary.modal.Modal;
import com.mradking.mylibrary.other.Ad_SetUp;

import java.util.List;

public class solution_frg extends Fragment {
    private RecyclerView cart_recycler_view;
    public main_adapter Adapter;
    ImageView imageView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.books_list, container, false);
        DatabaseHelper db = new DatabaseHelper(getActivity());
        cart_recycler_view =view.findViewById(R.id.list);

        List<Modal> contacts = db.getAllContacts();

        imageView=view.findViewById(R.id.image);
        Drawable drawable = getResources().getDrawable(R.drawable.solution); // Replace "my_image" with the actual name of your drawable resource
        imageView.setImageDrawable(drawable);


        Adapter = new main_adapter( getActivity(),contacts,"1");

        LinearLayoutManager lm1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        Toolbar toolbar=view.findViewById(R.id.toolbar);

        toolbar.setVisibility(View.GONE);

        cart_recycler_view.setHasFixedSize(true);
        cart_recycler_view.setLayoutManager(lm1);
        cart_recycler_view.setAdapter(Adapter);

        LinearLayout adView= view.findViewById(R.id.adView);
        Ad_SetUp.load_banner_ad(getActivity(),adView);



        return view;
    }
}
