package samuel.example.com.soccernow.view.football;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import samuel.example.com.soccernow.R;
import samuel.example.com.soccernow.adapter.LeagueTableAdapter;
import samuel.example.com.soccernow.model.ApiInterface;
import samuel.example.com.soccernow.model.articleModel.NewsResponse;
import samuel.example.com.soccernow.model.football.leagueTable.LeagueData;
import samuel.example.com.soccernow.model.football.leagueTable.LeagueTableResponse;
import samuel.example.com.soccernow.view.ContentActivity;

import static samuel.example.com.soccernow.utilities.checkInternetConnection;
import static samuel.example.com.soccernow.utilities.getFavoritLeagueFromSharedPreferences;
import static samuel.example.com.soccernow.view.football.LeagueFragment.LEAGUE_CODE;
import static samuel.example.com.soccernow.view.football.LeagueFragment.LEAGUE_NEMA;

public class LeagueTableFragment extends Fragment {

    private int chapionCode;
    private String leagueNmae;
    private TextView leagueTextView;
    private ImageView leagueImageView;
    private RecyclerView mRecyclerView;
    private LeagueTableAdapter leagueTableAdapter;
    private List <LeagueData> leagueDataList ;
    private ProgressBar progressBar;
    private LinearLayout allContentLinearLayout;
    private LinearLayout errorLinearLayout ;
    private Button retryConnection;
    private int favoriteLeagueCode ;

    public static final String TEAM_CODE = "team_code";
    public static final String TEAM_NAME = "team_name";
    public static final String TEAM_IMAGE = "team_image";
    public static final String TEAM_BUNDEL = "team_data";
    public static final String TEAM_SAVE_INSTANCE = "team_SaveInstance";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.fragment_league_table, container, false);
        leagueTableAdapter = new LeagueTableAdapter();
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        allContentLinearLayout=(LinearLayout) rootView.findViewById(R.id.content_all);
        errorLinearLayout = (LinearLayout) rootView.findViewById(R.id.connection_error);
        retryConnection =(Button) rootView.findViewById(R.id.retry_button);
        favoriteLeagueCode =getFavoritLeagueFromSharedPreferences(getContext());

        if (getArguments()!= null && getArguments().getInt(LEAGUE_CODE)!=0 )
        {
            progressBar.setVisibility(View.VISIBLE);

            chapionCode = getArguments().getInt(LEAGUE_CODE);
            leagueNmae = getArguments().getString(LEAGUE_NEMA);
            leagueTextView = (TextView) rootView.findViewById(R.id.leagueTextView);
            leagueTextView.setText(leagueNmae);
            leagueImageView = (ImageView) rootView.findViewById(R.id.leagueImageView);
            leagueImageView.setImageDrawable(getActivity().getResources().getDrawable(getActivity().getResources()
                    .getIdentifier("p"+chapionCode ,"drawable" , getActivity().getPackageName())));

            loadLeagueTable (savedInstanceState);
        }


        leagueTableAdapter.setRecyclerViewCallback(new LeagueTableAdapter.RecyclerViewTeamCallback() {
            @Override
            public void onItemClick(int position) {
                String teamCode = leagueDataList.get(position).get_links().getTeam().getHref();
                String []cutLink =teamCode.split("teams/");
                String teamName = leagueDataList.get(position).getTeamName();
                String teamImage = leagueDataList.get(position).getCrestURI();
                Intent intent =new Intent(getActivity() , TeamPlayersActivity.class);
                if (cutLink!=null && teamName!=null && teamImage!=null) {
                    Bundle bundle = new Bundle();
                    bundle.putString(TEAM_CODE, cutLink[1]);
                    bundle.putString(TEAM_NAME, teamName);
                    bundle.putString(TEAM_IMAGE, teamImage);
                    intent.putExtra(TEAM_BUNDEL, bundle);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getContext(),getActivity().getResources().getString(R.string.dataNotAvalible) ,Toast.LENGTH_LONG).show();
                }

            }
        });

        retryConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadLeagueTable(null);
            }
        });

        return  rootView;
    }

     private void loadLeagueTable (Bundle savedInstanceState)
     {
         mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
         mRecyclerView.setAdapter(leagueTableAdapter);

         if(savedInstanceState==null) {
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
                         progressBar.setVisibility(View.GONE);


                     }

                     @Override
                     public void onFailure(Call<LeagueTableResponse> call, Throwable t) {
                         Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.something_wrong), Toast.LENGTH_SHORT).show();
                         progressBar.setVisibility(View.GONE);


                     }
                 });
             }

             else if (favoriteLeagueCode==chapionCode)
             {
                 Toast.makeText(getContext(),getActivity().getResources().getString(R.string.dataNotAvalible) ,Toast.LENGTH_LONG).show();
             }
             else {

                 allContentLinearLayout.setVisibility(View.GONE);
                 errorLinearLayout.setVisibility(View.VISIBLE);
             }
         }
         else {
             LeagueTableResponse leagueTableResponse = savedInstanceState.getParcelable(TEAM_SAVE_INSTANCE);
             if(leagueTableResponse!=null)
             {
                 leagueDataList = leagueTableResponse.getLeagueDataList();
                 leagueTableAdapter.setApiResponse(leagueDataList);

             }
             progressBar.setVisibility(View.GONE);

         }

     }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        LeagueTableResponse leagueTableResponse = new LeagueTableResponse();
        leagueTableResponse.setLeagueDataList(leagueDataList);
        outState.putParcelable(TEAM_SAVE_INSTANCE ,leagueTableResponse);

    }

}
