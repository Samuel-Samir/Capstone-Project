package samuel.example.com.soccernow.view;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.ProgressBar;

import samuel.example.com.soccernow.R;
import samuel.example.com.soccernow.adapter.FavoriteleagueAdapter;

import static samuel.example.com.soccernow.Sam.getFavoritLeagueFromSharedPreferences;
import static samuel.example.com.soccernow.Sam.isTablet;

public class FavoriteleagueActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private FavoriteleagueAdapter favoriteleagueAdapter;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoriteleague);
        if (getFavoritLeagueFromSharedPreferences(getBaseContext()) != -1) {
            Intent intent = new Intent(FavoriteleagueActivity.this, ContentActivity.class);
            startActivity(intent);
            finish();
        }
        favoriteleagueAdapter = new FavoriteleagueAdapter(FavoriteleagueActivity.this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        onOrientationChange(getResources().getConfiguration().orientation);

    }


    public void onOrientationChange(int orientation) {
        int landScape = 2;
        int portrait = 1;

        if (isTablet(FavoriteleagueActivity.this)) {
            landScape = 2;
            portrait = 2;
        }

        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        /*
        float density  = getResources().getDisplayMetrics().density;
        float dpHeight = outMetrics.heightPixels / density;
        float dpWidth  = outMetrics.widthPixels / density;
        if (dpHeight > 400 &&dpHeight <580 )
            portrait=1;*/


        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(portrait, StaggeredGridLayoutManager.VERTICAL));
            mRecyclerView.setAdapter(favoriteleagueAdapter);

        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {

            mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(landScape, StaggeredGridLayoutManager.VERTICAL));
            mRecyclerView.setAdapter(favoriteleagueAdapter);

        }
        progressBar.setVisibility(View.GONE);

    }
}
