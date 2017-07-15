package samuel.example.com.soccernow.view.football;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import samuel.example.com.soccernow.R;
import samuel.example.com.soccernow.adapter.TeamPlayersAdapter;
import samuel.example.com.soccernow.model.ApiInterface;
import samuel.example.com.soccernow.model.football.PlayerData;
import samuel.example.com.soccernow.model.football.PlayerResponse;
import samuel.example.com.soccernow.model.football.leagueTable.LeagueTableResponse;

import static samuel.example.com.soccernow.view.football.LeagueTableFragment.TEAM_BUNDEL;
import static samuel.example.com.soccernow.view.football.LeagueTableFragment.TEAM_CODE;
import static samuel.example.com.soccernow.view.football.LeagueTableFragment.TEAM_IMAGE;
import static samuel.example.com.soccernow.view.football.LeagueTableFragment.TEAM_NAME;

public class TeamPlayersActivity extends AppCompatActivity {
    private int teamId;
    private List<PlayerData> playerDataList;
    private TeamPlayersAdapter teamPlayersAdapter ;
    private RecyclerView mRecyclerView;
    private TextView teamName;
    private ImageView teamImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_players);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        teamPlayersAdapter = new TeamPlayersAdapter();
        if (getIntent()!=null && getIntent().getBundleExtra(TEAM_BUNDEL)!=null)
        {
            Bundle bundle = getIntent().getBundleExtra(TEAM_BUNDEL);
            if(bundle.getString(TEAM_CODE)!=null && bundle.getString(TEAM_NAME)!=null && bundle.getString(TEAM_IMAGE)!=null )
            {
                setTitle(getResources().getString(R.string.teamsplayers));
                teamId = Integer.valueOf(bundle.getString(TEAM_CODE));
                String name = bundle.getString(TEAM_NAME);
                String teamUri = bundle.getString(TEAM_IMAGE);
                teamName = (TextView) findViewById(R.id.teamTextView);
                teamImage = (ImageView) findViewById(R.id.teamImageView) ;
                teamName.setText(name);
                teamImage.setImageDrawable(getResources().getDrawable(R.drawable.p436));
                loadLeagueTable ();
            }
        }
    }



    private void loadLeagueTable ()
    {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(TeamPlayersActivity.this));
        mRecyclerView.setAdapter(teamPlayersAdapter);

        ApiInterface apiService = ApiInterface.ApiClientFootBall.getClient().create(ApiInterface.class);
        Call<PlayerResponse> call = apiService.getTeamPlayers(teamId);
        call.enqueue(new Callback<PlayerResponse>() {
            @Override
            public void onResponse(Call<PlayerResponse> call, Response<PlayerResponse> response) {
                playerDataList = response.body().getPlayerDataList();
                teamPlayersAdapter.setApiResponse(playerDataList);
            }

            @Override
            public void onFailure(Call<PlayerResponse> call, Throwable t) {
                Toast.makeText(getBaseContext(), " error", Toast.LENGTH_SHORT).show();

            }
        });


    }
}
