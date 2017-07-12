package samuel.example.com.soccernow.view.football;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import samuel.example.com.soccernow.R;
import samuel.example.com.soccernow.adapter.ViewPagerAdapter;
import samuel.example.com.soccernow.model.ApiInterface;
import samuel.example.com.soccernow.model.articleModel.NewsResponse;
import samuel.example.com.soccernow.model.football.Competition;
import samuel.example.com.soccernow.utilities;
import samuel.example.com.soccernow.view.news.LatestNewsFragment;
import samuel.example.com.soccernow.view.news.TopNewsFragment;

import static samuel.example.com.soccernow.view.ContentActivity.CHAMPIONTYPE;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeagueFragment extends Fragment {

    private int chapionCode;
    TabLayout tabLayout;
    ViewPager viewPager;
    public static String LEAGUE_CODE ="code";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View rootView =inflater.inflate(R.layout.fragment_league, container, false);

        if (getArguments()!= null && getArguments().getInt(CHAMPIONTYPE)!=0)
        {
            chapionCode = getArguments().getInt(CHAMPIONTYPE);
        }

        viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
        tabLayout = (TabLayout) rootView.findViewById(R.id.tabLayout);
        setFragments () ;

        return rootView ;
    }

    private  void setFragments ()
    {
        tabLayout.setupWithViewPager(viewPager);
        ViewPagerAdapter customAdapter = new ViewPagerAdapter( getChildFragmentManager());

        ArrayList<String> fragmentsNames = new ArrayList<>();
        fragmentsNames.add(getResources().getString(R.string.leagueTable));
        fragmentsNames.add(getResources().getString(R.string.matches));

        ArrayList<Fragment> fragmentsList =new ArrayList<>();
        LeagueTableFragment leagueTableFragment = new LeagueTableFragment();
        MatchesFragment matchesFragment =new MatchesFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(LEAGUE_CODE , chapionCode);
        leagueTableFragment.setArguments(bundle);
        matchesFragment.setArguments(bundle);

        fragmentsList.add(leagueTableFragment);
        fragmentsList.add(matchesFragment);
        customAdapter.setData(fragmentsNames ,fragmentsList);

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




}
