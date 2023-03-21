package com.mradking.mylibrary.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.mradking.mylibrary.R;
import com.mradking.mylibrary.frg.Ncrt_Sollution_book_1_Frg;
import com.mradking.mylibrary.frg.Ncrt_Sollution_book_2_Frg;
import com.mradking.mylibrary.frg.Ncrt_Sollution_book_3_Frg;

import java.util.ArrayList;
import java.util.List;

public class list extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    TabLayout tabLayout;

    private InterstitialAd interstitialAd;
    String inter_ad_id_st,banner_ad_id_st;
    String intertaila_ad_unit ;







    private ViewPager2 viewPager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_main);


        viewPager = (ViewPager2) findViewById(R.id.viewpager);

        tabLayout = (TabLayout)findViewById(R.id.tabs);

        setupViewPager(viewPager);










        if(getIntent().getExtras().getString("number_book").contentEquals("1")){


    tabLayout.setVisibility(View.GONE);
}

    }


    private void setupViewPager(ViewPager2 viewPager) {

        if(getIntent().getExtras().getString("number_book").contentEquals("1")){






            ViewPagerAdapter1 adapter = new ViewPagerAdapter1(this);

            adapter.addFragment(new Ncrt_Sollution_book_1_Frg(),getIntent().getExtras().getString("book1_name"));


            viewPager.setAdapter(adapter);

            new TabLayoutMediator(tabLayout, viewPager, ((tab, position) ->
                    tab.setText(adapter.mFragmentTitleList.get(position)))).attach();






        }else if(getIntent().getExtras().getString("number_book").contentEquals("2")){




            ViewPagerAdapter1 adapter = new ViewPagerAdapter1(this);

            adapter.addFragment(new Ncrt_Sollution_book_1_Frg(), getIntent().getExtras().getString("book1_name"));
            adapter.addFragment(new Ncrt_Sollution_book_2_Frg(), getIntent().getExtras().getString("book2_name"));


            viewPager.setAdapter(adapter);

            new TabLayoutMediator(tabLayout, viewPager, ((tab, position) ->
                    tab.setText(adapter.mFragmentTitleList.get(position)))).attach();




        }else if(getIntent().getExtras().getString("number_book").contentEquals("3")){



            ViewPagerAdapter1 adapter = new ViewPagerAdapter1(this);

            adapter.addFragment(new Ncrt_Sollution_book_1_Frg(), getIntent().getExtras().getString("book1_name"));
            adapter.addFragment(new Ncrt_Sollution_book_2_Frg(), getIntent().getExtras().getString("book2_name"));
            adapter.addFragment(new Ncrt_Sollution_book_3_Frg(),getIntent().getExtras().getString("book3_name"));


            viewPager.setAdapter(adapter);

            new TabLayoutMediator(tabLayout, viewPager, ((tab, position) ->
                    tab.setText(adapter.mFragmentTitleList.get(position)))).attach();




        }





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

