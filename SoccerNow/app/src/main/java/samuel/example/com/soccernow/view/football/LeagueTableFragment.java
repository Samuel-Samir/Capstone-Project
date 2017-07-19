package samuel.example.com.soccernow.view.football;

import android.content.ContentValues;
import android.content.Intent;
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
import samuel.example.com.soccernow.adapter.LeagueTableAdapter;
import samuel.example.com.soccernow.model.ApiInterface;
import samuel.example.com.soccernow.model.dataBase.LeagueContract;
import samuel.example.com.soccernow.model.dataBase.LeagueProvider;
import samuel.example.com.soccernow.model.football.leagueTable.LeagueData;
import samuel.example.com.soccernow.model.football.leagueTable.LeagueTableResponse;

import static samuel.example.com.soccernow.Sam.checkInternetConnection;
import static samuel.example.com.soccernow.Sam.getFavoritLeagueFromSharedPreferences;
import static samuel.example.com.soccernow.model.dataBase.LeagueContract.LeagueTableEntry.COLUMN_DRAWS;
import static samuel.example.com.soccernow.model.dataBase.LeagueContract.LeagueTableEntry.COLUMN_IMAGE_URL;
import static samuel.example.com.soccernow.model.dataBase.LeagueContract.LeagueTableEntry.COLUMN_LOSSES;
import static samuel.example.com.soccernow.model.dataBase.LeagueContract.LeagueTableEntry.COLUMN_POINTS;
import static samuel.example.com.soccernow.model.dataBase.LeagueContract.LeagueTableEntry.COLUMN_POSITION;
import static samuel.example.com.soccernow.model.dataBase.LeagueContract.LeagueTableEntry.COLUMN_TEAM_NAME;
import static samuel.example.com.soccernow.model.dataBase.LeagueContract.LeagueTableEntry.COLUMN_WIN;
import static samuel.example.com.soccernow.view.football.LeagueFragment.LEAGUE_CODE;
import static samuel.example.com.soccernow.view.football.LeagueFragment.LEAGUE_NEMA;

public class LeagueTableFragment extends Fragment {

    public static final String TEAM_CODE = "team_code";
    public static final String TEAM_NAME = "team_name";
    public static final String TEAM_IMAGE = "team_image";
    public static final String TEAM_BUNDEL = "team_data";
    public static final String TEAM_SAVE_INSTANCE = "team_SaveInstance";
    private int chapionCode;
    private String leagueNmae;
    private TextView leagueTextView;
    private ImageView leagueImageView;
    private RecyclerView mRecyclerView;
    private LeagueTableAdapter leagueTableAdapter;
    private List<LeagueData> leagueDataList;
    private ProgressBar progressBar;
    private LinearLayout allContentLinearLayout;
    private LinearLayout errorLinearLayout;
    private Button retryConnection;
    private int favoriteLeagueCode;
    private TextView dataBaseEmpty;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_league_table, container, false);
        leagueTableAdapter = new LeagueTableAdapter();
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        allContentLinearLayout = (LinearLayout) rootView.findViewById(R.id.content_all);
        errorLinearLayout = (LinearLayout) rootView.findViewById(R.id.connection_error);
        retryConnection = (Button) rootView.findViewById(R.id.retry_button);
        dataBaseEmpty = (TextView) rootView.findViewById(R.id.data_base_impty);

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
            leagueImageView.setContentDescription(leagueNmae);
            loadLeagueTable(savedInstanceState);
        }


        leagueTableAdapter.setRecyclerViewCallback(new LeagueTableAdapter.RecyclerViewTeamCallback() {
            @Override
            public void onItemClick(int position) {
                if (leagueDataList.get(position).get_links() != null) {
                    String teamCode = leagueDataList.get(position).get_links().getTeam().getHref();
                    String[] cutLink = teamCode.split("teams/");
                    String teamName = leagueDataList.get(position).getTeamName();
                    String teamImage = leagueDataList.get(position).getCrestURI();
                    Intent intent = new Intent(getActivity(), TeamPlayersActivity.class);
                    if (cutLink != null && teamName != null && teamImage != null) {
                        Bundle bundle = new Bundle();
                        bundle.putString(TEAM_CODE, cutLink[1]);
                        bundle.putString(TEAM_NAME, teamName);
                        bundle.putString(TEAM_IMAGE, teamImage);
                        intent.putExtra(TEAM_BUNDEL, bundle);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getContext(), getActivity().getResources().getString(R.string.dataNotAvalible), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getContext(), getActivity().getResources().getString(R.string.dataNotAvalible), Toast.LENGTH_LONG).show();
                }

            }
        });

        retryConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadLeagueTable(null);
            }
        });

        return rootView;
    }

    private void loadLeagueTable(Bundle savedInstanceState) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(leagueTableAdapter);
        dataBaseEmpty.setVisibility(View.GONE);

        if (savedInstanceState == null) {
            if (checkInternetConnection()) {
                allContentLinearLayout.setVisibility(View.VISIBLE);
                errorLinearLayout.setVisibility(View.GONE);
                ApiInterface apiService = ApiInterface.ApiClientFootBall.getClient().create(ApiInterface.class);
                Call<LeagueTableResponse> call = apiService.getLeagueTable(chapionCode);
                call.enqueue(new Callback<LeagueTableResponse>() {
                    @Override
                    public void onResponse(Call<LeagueTableResponse> call, Response<LeagueTableResponse> response) {
                        LeagueTableResponse leagueTableResponse = response.body();
                        leagueDataList = leagueTableResponse.getLeagueDataList();
                        leagueTableAdapter.setApiResponse(leagueTableResponse.getLeagueDataList());
                        insertDataDataBase();
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<LeagueTableResponse> call, Throwable t) {
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
        } else {
            LeagueTableResponse leagueTableResponse = savedInstanceState.getParcelable(TEAM_SAVE_INSTANCE);
            if (leagueTableResponse != null) {
                leagueDataList = leagueTableResponse.getLeagueDataList();
                leagueTableAdapter.setApiResponse(leagueDataList);

            }
            progressBar.setVisibility(View.GONE);

        }

    }

    public void insertDataDataBase() {

        getActivity().getContentResolver().delete(LeagueProvider.LeagueIngredients.LEAGUE_URI, null, null);
        ContentValues[] cvs = new ContentValues[leagueDataList.size()];
        for (int i = 0; i < leagueDataList.size(); i++) {
            LeagueData leagueData = leagueDataList.get(i);
            cvs[i] = new ContentValues();
            cvs[i].put(COLUMN_POSITION, leagueData.getPosition());
            cvs[i].put(COLUMN_TEAM_NAME, leagueData.getTeamName());
            cvs[i].put(COLUMN_IMAGE_URL, leagueData.getCrestURI());
            cvs[i].put(LeagueContract.LeagueTableEntry.COLUMN_POINTS, leagueData.getPoints());
            cvs[i].put(LeagueContract.LeagueTableEntry.COLUMN_WIN, leagueData.getWins());
            cvs[i].put(LeagueContract.LeagueTableEntry.COLUMN_DRAWS, leagueData.getDraws());
            cvs[i].put(LeagueContract.LeagueTableEntry.COLUMN_LOSSES, leagueData.getLosses());

        }
        int numOfRows = getActivity().getContentResolver().bulkInsert(LeagueProvider.LeagueIngredients.LEAGUE_URI, cvs);
        Log.d("DataBase size", String.valueOf(numOfRows));

    }

    public void loadDataDataBase() {
        Cursor cursor = getActivity().getContentResolver().query(LeagueProvider.LeagueIngredients.LEAGUE_URI,
                null, null, null, null);
        if (cursor == null || cursor.getCount() == 0) {
            dataBaseEmpty.setVisibility(View.VISIBLE);
            errorLinearLayout.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            allContentLinearLayout.setVisibility(View.GONE);

        } else {
            List<LeagueData> leagueDataList2 = new ArrayList<>();
            while (cursor.moveToNext()) {
                LeagueData leagueData = new LeagueData();
                leagueData.setPosition(cursor.getInt(cursor.getColumnIndex(COLUMN_POSITION)));
                leagueData.setTeamName(cursor.getString(cursor.getColumnIndex(COLUMN_TEAM_NAME)));
                leagueData.setCrestURI(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_URL)));
                leagueData.setPoints(cursor.getInt(cursor.getColumnIndex(COLUMN_POINTS)));
                leagueData.setWins(cursor.getInt(cursor.getColumnIndex(COLUMN_WIN)));
                leagueData.setDraws(cursor.getInt(cursor.getColumnIndex(COLUMN_DRAWS)));
                leagueData.setLosses(cursor.getInt(cursor.getColumnIndex(COLUMN_LOSSES)));
                leagueDataList2.add(leagueData);
            }
            leagueDataList2.size();
            this.leagueDataList = leagueDataList2;
            leagueTableAdapter.setApiResponse(leagueDataList);
            progressBar.setVisibility(View.GONE);


        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        LeagueTableResponse leagueTableResponse = new LeagueTableResponse();
        leagueTableResponse.setLeagueDataList(leagueDataList);
        outState.putParcelable(TEAM_SAVE_INSTANCE, leagueTableResponse);

    }

    /*
        private void insertIntoProvider() {
        ContentValues[] cvs = new ContentValues[mIngredients.size()];
        for (int i=0 ; i< mIngredients.size() ; i++) {
            cvs[i] = new ContentValues();
            cvs[i].put(RecipesContract.IngredientEntry.RECIPE_ID, mCurrentRecipe.getmId());
            cvs[i].put(RecipesContract.IngredientEntry.RECIPE_NAME, mCurrentRecipe.getmName());
            cvs[i].put(RecipesContract.IngredientEntry.INGREDIENT_NAME, mIngredients.get(i).getmIngredient());
            cvs[i].put(RecipesContract.IngredientEntry.MEASURE, mIngredients.get(i).getmMeasure());
            cvs[i].put(RecipesContract.IngredientEntry.QUANTITY, mIngredients.get(i).getmQuantity());
        }
        getContentResolver().bulkInsert(RecipesContentProvider.Ingredients.INGREDIENTS, cvs);
    }

     */
    /*
    @Override
    public void onPause() {
        super.onPause();

        ContentValues[] cvs = new ContentValues[leagueDataList.size()];
        for (int i=0;i<leagueDataList.size() ;i++)
        {
            LeagueData leagueData = leagueDataList.get(i);
            cvs [i] = new ContentValues();
            cvs[i].put(LeagueContract.LeagueTableEntry.COLUMN_POSITION ,leagueData.getPosition() );
            cvs[i].put(LeagueContract.LeagueTableEntry.COLUMN_TEAM_NAME ,leagueData.getTeamName() );
            cvs[i].put(LeagueContract.LeagueTableEntry.COLUMN_IMAGE_URL ,leagueData.getCrestURI() );
            cvs[i].put(LeagueContract.LeagueTableEntry.COLUMN_POINTS ,leagueData.getPoints() );
            cvs[i].put(LeagueContract.LeagueTableEntry.COLUMN_WIN ,leagueData.getWins() );
            cvs[i].put(LeagueContract.LeagueTableEntry.COLUMN_DRAWS ,leagueData.getDraws() );
            cvs[i].put(LeagueContract.LeagueTableEntry.COLUMN_LOSSES ,leagueData.getLosses() );

        }
        getActivity().getContentResolver().bulkInsert(LeagueProvider.LeagueIngredients.LEAGUE_URI, cvs);

    }
    */
}
