package samuel.example.com.soccernow.view.football;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import samuel.example.com.soccernow.R;
import samuel.example.com.soccernow.adapter.LeagueMatchAdapter;
import samuel.example.com.soccernow.model.ApiInterface;
import samuel.example.com.soccernow.model.football.leagueMatches.LeagueMatchesResponse;

import static samuel.example.com.soccernow.view.football.LeagueFragment.LEAGUE_CODE;
import static samuel.example.com.soccernow.view.football.LeagueFragment.LEAGUE_NEMA;

public class MatchesFragment extends Fragment {

    private int chapionCode;
    private String leagueNmae;
    private TextView leagueTextView;
    private ImageView leagueImageView;
    private RecyclerView mRecyclerView;
    private LeagueMatchAdapter leagueMatchAdapter ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_matches, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        leagueMatchAdapter = new LeagueMatchAdapter();

        if (getArguments()!= null && getArguments().getInt(LEAGUE_CODE)!=0 )
        {
            chapionCode = getArguments().getInt(LEAGUE_CODE);
            leagueNmae = getArguments().getString(LEAGUE_NEMA);
            leagueTextView = (TextView) rootView.findViewById(R.id.leagueTextView);
            leagueTextView.setText(leagueNmae);
            leagueImageView = (ImageView) rootView.findViewById(R.id.leagueImageView);
            leagueImageView.setImageDrawable(getActivity().getResources().getDrawable(getActivity().getResources()
                    .getIdentifier("p"+chapionCode ,"drawable" , getActivity().getPackageName())));


            getLeagueMatches ();
        }
        return rootView;
    }

    private  void getLeagueMatches ()
    {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(leagueMatchAdapter);

        ApiInterface apiService = ApiInterface.ApiClientFootBall.getClient().create(ApiInterface.class);
        Call <LeagueMatchesResponse> call = apiService.getLeagueMatches(chapionCode);
        call.enqueue(new Callback<LeagueMatchesResponse>() {
            @Override
            public void onResponse(Call<LeagueMatchesResponse> call, Response<LeagueMatchesResponse> response) {
                LeagueMatchesResponse leagueMatchesResponse =response.body();
                leagueMatchAdapter.setApiResponse(leagueMatchesResponse.getMatcheDataList());
            }

            @Override
            public void onFailure(Call<LeagueMatchesResponse> call, Throwable t) {
                Toast.makeText(getActivity(), " error", Toast.LENGTH_SHORT).show();

            }
        });
    }


}
