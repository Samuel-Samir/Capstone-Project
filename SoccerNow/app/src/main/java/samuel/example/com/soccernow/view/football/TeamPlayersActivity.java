package samuel.example.com.soccernow.view.football;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import samuel.example.com.soccernow.R;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_players);
        if (getIntent()!=null && getIntent().getBundleExtra(TEAM_BUNDEL)!=null)
        {
            Bundle bundle = getIntent().getBundleExtra(TEAM_BUNDEL);
            if(bundle.getString(TEAM_CODE)!=null && bundle.getString(TEAM_NAME)!=null && bundle.getString(TEAM_IMAGE)!=null )
            {
                teamId = Integer.valueOf(bundle.getString(TEAM_CODE));
                loadLeagueTable ();
            }
        }
    }



    private void loadLeagueTable ()
    {
        /*
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(leagueTableAdapter);*/

        ApiInterface apiService = ApiInterface.ApiClientFootBall.getClient().create(ApiInterface.class);
        Call<PlayerResponse> call = apiService.getTeamPlayers(teamId);
        call.enqueue(new Callback<PlayerResponse>() {
            @Override
            public void onResponse(Call<PlayerResponse> call, Response<PlayerResponse> response) {
                playerDataList = response.body().getPlayerDataList();
                int si =playerDataList.size();
            }

            @Override
            public void onFailure(Call<PlayerResponse> call, Throwable t) {
                Toast.makeText(getBaseContext(), " error", Toast.LENGTH_SHORT).show();

            }
        });


    }
}
