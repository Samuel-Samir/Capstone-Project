package samuel.example.com.soccernow.view.football;


import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import samuel.example.com.soccernow.R;
import samuel.example.com.soccernow.adapter.LeagueMatchAdapter;
import samuel.example.com.soccernow.model.ApiInterface;
import samuel.example.com.soccernow.model.dataBase.LeagueProvider;
import samuel.example.com.soccernow.model.football.leagueMatches.LeagueMatchesResponse;
import samuel.example.com.soccernow.model.football.leagueMatches.MatchResult;
import samuel.example.com.soccernow.model.football.leagueMatches.MatcheData;

import static samuel.example.com.soccernow.Utilities.checkInternetConnection;
import static samuel.example.com.soccernow.Utilities.getFavoritLeagueFromSharedPreferences;
import static samuel.example.com.soccernow.model.dataBase.LeagueContract.MatchesEntry.COLUMN_AWAY_GOALS;
import static samuel.example.com.soccernow.model.dataBase.LeagueContract.MatchesEntry.COLUMN_AWAY_TEAM;
import static samuel.example.com.soccernow.model.dataBase.LeagueContract.MatchesEntry.COLUMN_DATA;
import static samuel.example.com.soccernow.model.dataBase.LeagueContract.MatchesEntry.COLUMN_HOME_GOALS;
import static samuel.example.com.soccernow.model.dataBase.LeagueContract.MatchesEntry.COLUMN_HOME_TEAM;
import static samuel.example.com.soccernow.model.dataBase.LeagueContract.MatchesEntry.COLUMN_STATUS;
import static samuel.example.com.soccernow.view.football.LeagueFragment.LEAGUE_CODE;
import static samuel.example.com.soccernow.view.football.LeagueFragment.LEAGUE_NEMA;

public class MatchesFragment extends Fragment {

    public static final String TEAM_SAVE_INSTANCE = "team_SaveInstance";
    private int chapionCode;
    private String leagueNmae;
    private TextView leagueTextView;
    private ImageView leagueImageView;
    private RecyclerView mRecyclerView;
    private LeagueMatchAdapter leagueMatchAdapter;
    private ProgressBar progressBar;
    private List<MatcheData> matcheDataList;
    private LinearLayout allContentLinearLayout;
    private LinearLayout errorLinearLayout;
    private Button retryConnection;
    private int favoriteLeagueCode;
    private TextView dataBaseEmpty;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_matches, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        allContentLinearLayout = (LinearLayout) rootView.findViewById(R.id.content_all);
        errorLinearLayout = (LinearLayout) rootView.findViewById(R.id.connection_error);
        retryConnection = (Button) rootView.findViewById(R.id.retry_button);
        dataBaseEmpty = (TextView) rootView.findViewById(R.id.data_base_impty);
        leagueMatchAdapter = new LeagueMatchAdapter();
        favoriteLeagueCode = getFavoritLeagueFromSharedPreferences(getContext());

        if (getArguments() != null && getArguments().getInt(LEAGUE_CODE) != 0) {
            progressBar.setVisibility(View.VISIBLE);

            chapionCode = getArguments().getInt(LEAGUE_CODE);
            leagueNmae = getArguments().getString(LEAGUE_NEMA);
            leagueTextView = (TextView) rootView.findViewById(R.id.leagueTextView);
            leagueTextView.setText(leagueNmae);
            leagueImageView = (ImageView) rootView.findViewById(R.id.leagueImageView);
            leagueImageView.setImageDrawable(getActivity().getResources().getDrawable(getActivity().getResources()
                    .getIdentifier("p" + chapionCode, "drawable", getActivity().getPackageName())));


            getLeagueMatches(savedInstanceState);
        }

        retryConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLeagueMatches(null);
            }
        });

        return rootView;
    }

    private void getLeagueMatches(Bundle savedInstanceState) {


        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(leagueMatchAdapter);
        dataBaseEmpty.setVisibility(View.GONE);

        if (savedInstanceState == null) {
            if (checkInternetConnection()) {
                allContentLinearLayout.setVisibility(View.VISIBLE);
                errorLinearLayout.setVisibility(View.GONE);
                ApiInterface apiService = ApiInterface.ApiClientFootBall.getClient().create(ApiInterface.class);
                Call<LeagueMatchesResponse> call = apiService.getLeagueMatches(chapionCode);
                call.enqueue(new Callback<LeagueMatchesResponse>() {
                    @Override
                    public void onResponse(Call<LeagueMatchesResponse> call, Response<LeagueMatchesResponse> response) {
                        LeagueMatchesResponse leagueMatchesResponse = response.body();
                        leagueMatchAdapter.setApiResponse(leagueMatchesResponse.getMatcheDataList());
                        matcheDataList = leagueMatchesResponse.getMatcheDataList();
                        insertDataDataBase();
                        progressBar.setVisibility(View.GONE);


                    }

                    @Override
                    public void onFailure(Call<LeagueMatchesResponse> call, Throwable t) {
                        Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.something_wrong), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);


                    }
                });
            } else if (favoriteLeagueCode == chapionCode) {

                loadDataDataBase();
            } else {

                allContentLinearLayout.setVisibility(View.GONE);
                errorLinearLayout.setVisibility(View.VISIBLE);
            }
        } else if (savedInstanceState != null) {
            LeagueMatchesResponse leagueMatchesResponse = savedInstanceState.getParcelable(TEAM_SAVE_INSTANCE);
            if (leagueMatchesResponse != null) {
                matcheDataList = leagueMatchesResponse.getMatcheDataList();
                leagueMatchAdapter.setApiResponse(matcheDataList);
            }
            progressBar.setVisibility(View.GONE);

        }
    }


    public void insertDataDataBase() {

        getActivity().getContentResolver().delete(LeagueProvider.MatchesIngredients.MATCHEST_URI, null, null);
        ContentValues[] cvs = new ContentValues[matcheDataList.size()];
        for (int i = 0; i < matcheDataList.size(); i++) {
            MatcheData matcheData = matcheDataList.get(i);

            cvs[i] = new ContentValues();
            cvs[i].put(COLUMN_DATA, matcheData.getDate());
            cvs[i].put(COLUMN_STATUS, matcheData.getStatus());
            cvs[i].put(COLUMN_HOME_TEAM, matcheData.getHomeTeamName());
            cvs[i].put(COLUMN_AWAY_TEAM, matcheData.getAwayTeamName());
            cvs[i].put(COLUMN_HOME_GOALS, matcheData.getResult().getGoalsHomeTeam());
            cvs[i].put(COLUMN_AWAY_GOALS, matcheData.getResult().getGoalsAwayTeam());

        }
        int numOfRows = getActivity().getContentResolver().bulkInsert(LeagueProvider.MatchesIngredients.MATCHEST_URI, cvs);
        Log.d("DataBase size", String.valueOf(numOfRows));

    }

    public void loadDataDataBase() {
        Cursor cursor = getActivity().getContentResolver().query(LeagueProvider.MatchesIngredients.MATCHEST_URI,
                null, null, null, null);
        if (cursor == null || cursor.getCount() == 0) {
            //Toast.makeText(getContext(),getActivity().getResources().getString(R.string.dataNotAvalible) ,Toast.LENGTH_LONG).show();
            dataBaseEmpty.setVisibility(View.VISIBLE);
            errorLinearLayout.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            allContentLinearLayout.setVisibility(View.GONE);


        } else {
            List<MatcheData> matcheDatas = new ArrayList<>();
            while (cursor.moveToNext()) {
                MatcheData matcheData = new MatcheData();
                matcheData.setDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATA)));
                matcheData.setStatus(cursor.getString(cursor.getColumnIndex(COLUMN_STATUS)));
                matcheData.setHomeTeamName(cursor.getString(cursor.getColumnIndex(COLUMN_HOME_TEAM)));
                matcheData.setAwayTeamName(cursor.getString(cursor.getColumnIndex(COLUMN_AWAY_TEAM)));
                MatchResult matchResult = new MatchResult();
                matchResult.setGoalsHomeTeam(cursor.getInt(cursor.getColumnIndex(COLUMN_HOME_GOALS)));
                matchResult.setGoalsAwayTeam(cursor.getInt(cursor.getColumnIndex(COLUMN_AWAY_GOALS)));
                matcheData.setResult(matchResult);
                matcheDatas.add(matcheData);
            }
            this.matcheDataList = matcheDatas;
            leagueMatchAdapter.setApiResponse(matcheDataList);
            progressBar.setVisibility(View.GONE);


        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        LeagueMatchesResponse leagueMatchesResponse = new LeagueMatchesResponse();
        leagueMatchesResponse.setMatcheDataList(matcheDataList);
        outState.putParcelable(TEAM_SAVE_INSTANCE, leagueMatchesResponse);

    }

}
