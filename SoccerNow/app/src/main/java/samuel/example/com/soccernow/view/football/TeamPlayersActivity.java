package samuel.example.com.soccernow.view.football;

import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.caverock.androidsvg.SVG;

import java.io.InputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import samuel.example.com.soccernow.R;
import samuel.example.com.soccernow.SoccerNowApp;
import samuel.example.com.soccernow.adapter.TeamPlayersAdapter;
import samuel.example.com.soccernow.model.ApiInterface;
import samuel.example.com.soccernow.model.ConnectivityReceiver;
import samuel.example.com.soccernow.model.football.PlayerData;
import samuel.example.com.soccernow.model.football.PlayerResponse;
import samuel.example.com.soccernow.svg.SvgDecoder;
import samuel.example.com.soccernow.svg.SvgDrawableTranscoder;
import samuel.example.com.soccernow.svg.SvgSoftwareLayerSetter;

import static samuel.example.com.soccernow.Sam.checkInternetConnection;
import static samuel.example.com.soccernow.Sam.showSnackbar;
import static samuel.example.com.soccernow.Sam.showSnackbarDisconnected;
import static samuel.example.com.soccernow.view.football.LeagueTableFragment.TEAM_BUNDEL;
import static samuel.example.com.soccernow.view.football.LeagueTableFragment.TEAM_CODE;
import static samuel.example.com.soccernow.view.football.LeagueTableFragment.TEAM_IMAGE;
import static samuel.example.com.soccernow.view.football.LeagueTableFragment.TEAM_NAME;

public class TeamPlayersActivity extends AppCompatActivity
        implements ConnectivityReceiver.ConnectivityReceiverListener {
    String name;
    private int teamId;
    private List<PlayerData> playerDataList;
    private TeamPlayersAdapter teamPlayersAdapter;
    private RecyclerView mRecyclerView;
    private TextView teamName;
    private ImageView teamImage;
    private ProgressBar progressBar;
    private LinearLayout allContentLinearLayout;
    private LinearLayout errorLinearLayout;
    private Button retryConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_players);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        allContentLinearLayout = (LinearLayout) findViewById(R.id.content_all);
        errorLinearLayout = (LinearLayout) findViewById(R.id.connection_error);
        retryConnection = (Button) findViewById(R.id.retry_button);
        teamPlayersAdapter = new TeamPlayersAdapter();

        if (!checkInternetConnection()) {
            showSnackbarDisconnected(findViewById(android.R.id.content), this);
        }
        if (getIntent() != null && getIntent().getBundleExtra(TEAM_BUNDEL) != null) {
            Bundle bundle = getIntent().getBundleExtra(TEAM_BUNDEL);
            if (bundle.getString(TEAM_CODE) != null && bundle.getString(TEAM_NAME) != null && bundle.getString(TEAM_IMAGE) != null) {

                setTitle(getResources().getString(R.string.teamsplayers));
                teamId = Integer.valueOf(bundle.getString(TEAM_CODE));
                name = bundle.getString(TEAM_NAME);
                String teamUri = bundle.getString(TEAM_IMAGE);
                teamName = (TextView) findViewById(R.id.teamTextView);
                teamImage = (ImageView) findViewById(R.id.teamImageView);
                teamName.setText(name);
                teamImage.setImageDrawable(getResources().getDrawable(R.drawable.p436));
                GenericRequestBuilder requestBuilder;

                Uri uri = Uri.parse(teamUri);
                requestBuilder = Glide.with(this)
                        .using(Glide.buildStreamModelLoader(Uri.class, this), InputStream.class)
                        .from(Uri.class)
                        .as(SVG.class)
                        .transcode(new SvgDrawableTranscoder(), PictureDrawable.class)
                        .sourceEncoder(new StreamEncoder())
                        .cacheDecoder(new FileToStreamDecoder<SVG>(new SvgDecoder()))
                        .decoder(new SvgDecoder())
                        .animate(android.R.anim.fade_in)
                        .listener(new SvgSoftwareLayerSetter<Uri>());
                requestBuilder
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .load(uri)
                        .into(teamImage);

                loadLeagueTable();
            }
        }
        teamImage.setContentDescription(name);
        retryConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadLeagueTable();
            }
        });
    }


    private void loadLeagueTable() {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(TeamPlayersActivity.this));
        mRecyclerView.setAdapter(teamPlayersAdapter);

        if (checkInternetConnection()) {
            allContentLinearLayout.setVisibility(View.VISIBLE);
            errorLinearLayout.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            ApiInterface apiService = ApiInterface.ApiClientFootBall.getClient().create(ApiInterface.class);
            Call<PlayerResponse> call = apiService.getTeamPlayers(teamId);
            call.enqueue(new Callback<PlayerResponse>() {
                @Override
                public void onResponse(Call<PlayerResponse> call, Response<PlayerResponse> response) {
                    playerDataList = response.body().getPlayerDataList();
                    teamPlayersAdapter.setApiResponse(playerDataList);
                    progressBar.setVisibility(View.GONE);

                }

                @Override
                public void onFailure(Call<PlayerResponse> call, Throwable t) {
                    Toast.makeText(getBaseContext(), getResources().getString(R.string.something_wrong), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);


                }
            });
        } else {
            allContentLinearLayout.setVisibility(View.GONE);
            errorLinearLayout.setVisibility(View.VISIBLE);
        }


    }

    @Override
    protected void onResume() {
        super.onResume();

        SoccerNowApp.getInstance().setConnectivityListener(TeamPlayersActivity.this);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnackbar(isConnected, findViewById(android.R.id.content), this);
    }

}
