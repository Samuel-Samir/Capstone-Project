package samuel.example.com.soccernow.view.news;


import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import samuel.example.com.soccernow.R;

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

        setFragents () ;
        return  rootView ;
    }



    private  void setFragents ()
    {
        tabLayout.setupWithViewPager(viewPager);
        CustomAdapter  customAdapter = new CustomAdapter( getActivity().getSupportFragmentManager(), getActivity().getApplicationContext());
        viewPager.setAdapter(customAdapter);

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
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getActivity().setContentView(R.layout.fragment_news);
        setFragents ();
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
                    Toast.makeText(getContext() , "hello" ,Toast.LENGTH_SHORT).show();

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
