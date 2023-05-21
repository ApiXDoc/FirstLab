package com.mradking.mylibrary.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.codemybrainsout.ratingdialog.RatingDialog;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.mradking.mylibrary.R;
import com.mradking.mylibrary.frg.Ncrt_Sollution_book_1_Frg;
import com.mradking.mylibrary.frg.Ncrt_Sollution_book_2_Frg;
import com.mradking.mylibrary.frg.Ncrt_Sollution_book_3_Frg;
import com.mradking.mylibrary.frg.books_frg;
import com.mradking.mylibrary.frg.home_page_frg;
import com.mradking.mylibrary.frg.notes_frg;
import com.mradking.mylibrary.frg.solution_frg;
import com.mradking.mylibrary.other.sharePrefX;

import org.apache.commons.text.StringEscapeUtils;

import java.util.ArrayList;
import java.util.List;

public class main_act extends AppCompatActivity {
    TabLayout tabLayout;
    private ViewPager2 viewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_main);


        viewPager = (ViewPager2) findViewById(R.id.viewpager);

        tabLayout = (TabLayout)findViewById(R.id.tabs);

        setupViewPager(viewPager);






    }


    private void setupViewPager(ViewPager2 viewPager) {

            String home_st=sharePrefX.getString(getApplicationContext(),"app_name","home");
//
            ViewPagerAdapter1 adapter = new ViewPagerAdapter1(main_act.this);
            adapter.addFragment(new home_page_frg(),home_st );
            adapter.addFragment(new solution_frg(), "Solutions");
            adapter.addFragment(new notes_frg(), "Notes");
            adapter.addFragment(new books_frg(), "Books");



        viewPager.setAdapter(adapter);

            new TabLayoutMediator(tabLayout, viewPager, ((tab, position) ->
                    tab.setText(adapter.mFragmentTitleList.get(position)))).attach();








    }


    public class ViewPagerAdapter1 extends FragmentStateAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<Fragment>();
        private final List<String> mFragmentTitleList = new ArrayList<String>();

        public ViewPagerAdapter1(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }


        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getItemCount() {
            return mFragmentTitleList.size();
        }
    }

}