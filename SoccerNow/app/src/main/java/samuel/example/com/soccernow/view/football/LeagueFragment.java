package samuel.example.com.soccernow.view.football;


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

import static samuel.example.com.soccernow.view.ContentActivity.CHAMPIONTYPE;
import static samuel.example.com.soccernow.view.ContentActivity.CHAMPION_NAME;


public class LeagueFragment extends Fragment {

    private int chapionCode;
    private String leagueName;
    TabLayout tabLayout;
    ViewPager viewPager;
    public static String LEAGUE_CODE ="code";
    public static String LEAGUE_NEMA ="name";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.fragment_league, container, false);

        if (getArguments()!= null && getArguments().getInt(CHAMPIONTYPE)!=0 &&  getArguments().getString(CHAMPION_NAME)!=null)
        {
            chapionCode = getArguments().getInt(CHAMPIONTYPE);
            leagueName=getArguments().getString(CHAMPION_NAME);
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
        bundle.putString(LEAGUE_NEMA , leagueName);
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
