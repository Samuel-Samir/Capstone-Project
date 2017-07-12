package samuel.example.com.soccernow.view.football;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import samuel.example.com.soccernow.R;

import static samuel.example.com.soccernow.view.ContentActivity.CHAMPIONTYPE;
import static samuel.example.com.soccernow.view.football.LeagueFragment.LEAGUE_CODE;

public class LeagueTableFragment extends Fragment {
    private int chapionCode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View rootView =inflater.inflate(R.layout.fragment_league_table, container, false);
        if (getArguments()!= null && getArguments().getInt(LEAGUE_CODE)!=0)
        {
            chapionCode = getArguments().getInt(LEAGUE_CODE);
            TextView textView = (TextView) rootView.findViewById(R.id.sasa);
            textView.setText("LeagueTableFragment = "+String.valueOf(chapionCode));
        }
        return  rootView;
    }



}
