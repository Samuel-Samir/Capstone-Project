package samuel.example.com.soccernow.view.news;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import samuel.example.com.soccernow.R;
import samuel.example.com.soccernow.adapter.ViewPagerAdapter;

import static samuel.example.com.soccernow.Utilities.currentTabChoose;

public class NewsFragment extends Fragment {

    public static String TAG_NEWS_FRAGMENT = "newsFragments";
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);


        viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);

        tabLayout = (TabLayout) rootView.findViewById(R.id.tabLayout);

        setFragments();
        onOrientationChange(getResources().getConfiguration().orientation);

        return rootView;
    }


    private void setFragments() {
        tabLayout.setupWithViewPager(viewPager);
        ViewPagerAdapter customAdapter = new ViewPagerAdapter(getChildFragmentManager());

        ArrayList<String> fragmentsNames = new ArrayList<>();
        fragmentsNames.add(getResources().getString(R.string.top_news));
        fragmentsNames.add(getResources().getString(R.string.latest_news));

        ArrayList<Fragment> fragmentsList = new ArrayList<>();
        fragmentsList.add(new TopNewsFragment());
        fragmentsList.add(new LatestNewsFragment());
        customAdapter.setData(fragmentsNames, fragmentsList);
        viewPager.setCurrentItem(currentTabChoose);
        viewPager.setAdapter(customAdapter);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                currentTabChoose = tab.getPosition();
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
    public void onPause() {
        super.onPause();
        currentTabChoose = 0;

    }

    public void onOrientationChange(int orientation) {
        viewPager.setCurrentItem(currentTabChoose);

    }

}
