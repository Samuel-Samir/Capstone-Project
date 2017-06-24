package samuel.example.com.soccernow.view;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import samuel.example.com.soccernow.ApiInterface;
import samuel.example.com.soccernow.NewsAdapter;
import samuel.example.com.soccernow.R;
import samuel.example.com.soccernow.model.Article;
import samuel.example.com.soccernow.model.NewsResponse;

public class NewsFragment extends Fragment {


    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);

        //LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.LinearLayout);

        viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);

        tabLayout = (TabLayout) rootView.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(new NewsFragment.CustomAdapter(getActivity().getSupportFragmentManager(), getActivity().getApplicationContext()));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
        });

        return  rootView ;
    }



    private class CustomAdapter extends FragmentPagerAdapter {

        private String fragments[] = {getResources().getString(R.string.top_news) , getResources().getString(R.string.latest_news) };

        public CustomAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: {

                    return new TopNewsFragment();
                }
                case 1: {

                    return new LatestNewsFragment();
                }
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return fragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragments[position];
        }
    }



}
