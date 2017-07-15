package samuel.example.com.soccernow.view;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import samuel.example.com.soccernow.R;
import samuel.example.com.soccernow.SoccerNowApp;
import samuel.example.com.soccernow.model.ConnectivityReceiver;
import samuel.example.com.soccernow.view.football.LeagueFragment;
import samuel.example.com.soccernow.view.news.NewsFragment;

import static samuel.example.com.soccernow.utilities.checkInternetConnection;
import static samuel.example.com.soccernow.utilities.currentChoose;
import static samuel.example.com.soccernow.utilities.showSnackbar;
import static samuel.example.com.soccernow.utilities.showSnackbarDisconnected;
import static samuel.example.com.soccernow.view.news.NewsFragment.TAG_NEWS_FRAGMENT;

public class ContentActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,  ConnectivityReceiver.ConnectivityReceiverListener {



    FragmentManager mFragmentManager ;
    FragmentTransaction mFragmentTransaction ;
    public static String CHAMPIONTYPE="chapiontype";
    public static String CHAMPION_NAME="chapionName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_news);
        navigationView.setNavigationItemSelectedListener(this);

        if (!checkInternetConnection()) {
            showSnackbarDisconnected(findViewById(android.R.id.content), this);
        }

        setCurrentChoose (currentChoose);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.content, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int chapionCode=0;
        int id = item.getItemId();
        Bundle bundle ;

        if (id == R.id.nav_news) {

            currentChoose=1;
            mFragmentManager = getSupportFragmentManager();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.content_main,new NewsFragment()).commit();
        }
        else if (id ==R.id.nav_primera_division)  {
            currentChoose=2;
            chapionCode= 436;
            bundle=new Bundle();
            bundle.putInt(CHAMPIONTYPE , chapionCode);
            bundle.putString(CHAMPION_NAME,getResources().getString(R.string.primera_division));
            LeagueFragment leagueFragment =new LeagueFragment();
            leagueFragment.setArguments(bundle);
            mFragmentManager = getSupportFragmentManager();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.content_main,leagueFragment).commit();

        }
        else if (id ==R.id.nav_primera_liga)  {

            currentChoose=3;
            chapionCode= 445;
            bundle=new Bundle();
            bundle.putInt(CHAMPIONTYPE , chapionCode);
            bundle.putString(CHAMPION_NAME,getResources().getString(R.string.primera_liga));
            LeagueFragment leagueFragment =new LeagueFragment();
            leagueFragment.setArguments(bundle);
            mFragmentManager = getSupportFragmentManager();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.content_main,leagueFragment).commit();

        }
        else if (id ==R.id.nav_bundesliga)  {

            currentChoose=4;
            chapionCode= 430;
            bundle=new Bundle();
            bundle.putInt(CHAMPIONTYPE , chapionCode);
            bundle.putString(CHAMPION_NAME,getResources().getString(R.string.bundesliga));
            LeagueFragment leagueFragment =new LeagueFragment();
            leagueFragment.setArguments(bundle);
            mFragmentManager = getSupportFragmentManager();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.content_main,leagueFragment).commit();
        }
        else if (id ==R.id.nav_ligue_1)  {

            currentChoose=5;
            chapionCode= 450;
            bundle=new Bundle();
            bundle.putInt(CHAMPIONTYPE , chapionCode);
            bundle.putString(CHAMPION_NAME,getResources().getString(R.string.ligue_1));
            LeagueFragment leagueFragment =new LeagueFragment();
            leagueFragment.setArguments(bundle);
            mFragmentManager = getSupportFragmentManager();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.content_main,leagueFragment).commit();

        }
        else if (id ==R.id.nav_serie_A)  {

            currentChoose=6;
            chapionCode= 444;
            bundle=new Bundle();
            bundle.putInt(CHAMPIONTYPE , chapionCode);
            bundle.putString(CHAMPION_NAME,getResources().getString(R.string.serie_A));
            LeagueFragment leagueFragment =new LeagueFragment();
            leagueFragment.setArguments(bundle);
            mFragmentManager = getSupportFragmentManager();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.content_main,leagueFragment).commit();

        }
        else if (id ==R.id.nav_champions_league)  {

            currentChoose=7;
            chapionCode= 446;
            bundle=new Bundle();
            bundle.putInt(CHAMPIONTYPE , chapionCode);
            bundle.putString(CHAMPION_NAME,getResources().getString(R.string.champions_league));
            LeagueFragment leagueFragment =new LeagueFragment();
            leagueFragment.setArguments(bundle);
            mFragmentManager = getSupportFragmentManager();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.content_main,leagueFragment).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setCurrentChoose (int choose)
    {
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        int chapionCode=0;
        Bundle bundle ;


        if (choose==1)
            mFragmentTransaction.replace(R.id.content_main,new NewsFragment() , TAG_NEWS_FRAGMENT).commit();
        else if (choose==2) {
            chapionCode= 436;
            bundle=new Bundle();
            bundle.putInt(CHAMPIONTYPE , chapionCode);
            bundle.putString(CHAMPION_NAME,getResources().getString(R.string.primera_division));
            LeagueFragment leagueFragment =new LeagueFragment();
            leagueFragment.setArguments(bundle);
            mFragmentManager = getSupportFragmentManager();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.content_main, leagueFragment, TAG_NEWS_FRAGMENT).commit();
        }

        else if (choose==3) {
            chapionCode= 445;
            bundle=new Bundle();
            bundle.putInt(CHAMPIONTYPE , chapionCode);
            bundle.putString(CHAMPION_NAME,getResources().getString(R.string.primera_liga));
            LeagueFragment leagueFragment =new LeagueFragment();
            leagueFragment.setArguments(bundle);
            mFragmentManager = getSupportFragmentManager();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.content_main, leagueFragment, TAG_NEWS_FRAGMENT).commit();
        }
        else if (choose==4) {
            chapionCode= 430;
            bundle=new Bundle();
            bundle.putInt(CHAMPIONTYPE , chapionCode);
            bundle.putString(CHAMPION_NAME,getResources().getString(R.string.bundesliga));
            LeagueFragment leagueFragment =new LeagueFragment();
            leagueFragment.setArguments(bundle);
            mFragmentManager = getSupportFragmentManager();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.content_main, leagueFragment, TAG_NEWS_FRAGMENT).commit();
        }
        else if (choose==5) {
            chapionCode= 450;
            bundle=new Bundle();
            bundle.putInt(CHAMPIONTYPE , chapionCode);
            bundle.putString(CHAMPION_NAME,getResources().getString(R.string.ligue_1));
            LeagueFragment leagueFragment =new LeagueFragment();
            leagueFragment.setArguments(bundle);
            mFragmentManager = getSupportFragmentManager();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.content_main, leagueFragment, TAG_NEWS_FRAGMENT).commit();
        }
        else if (choose==6) {
            chapionCode= 444;
            bundle=new Bundle();
            bundle.putInt(CHAMPIONTYPE , chapionCode);
            bundle.putString(CHAMPION_NAME,getResources().getString(R.string.serie_A));
            LeagueFragment leagueFragment =new LeagueFragment();
            leagueFragment.setArguments(bundle);
            mFragmentManager = getSupportFragmentManager();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.content_main, leagueFragment, TAG_NEWS_FRAGMENT).commit();
        }
        else if (choose==7) {
            chapionCode= 446;
            bundle=new Bundle();
            bundle.putInt(CHAMPIONTYPE , chapionCode);
            bundle.putString(CHAMPION_NAME,getResources().getString(R.string.champions_league));
            LeagueFragment leagueFragment =new LeagueFragment();
            leagueFragment.setArguments(bundle);
            mFragmentManager = getSupportFragmentManager();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.content_main, leagueFragment, TAG_NEWS_FRAGMENT).commit();
        }




    }

    @Override
    protected void onResume() {
        super.onResume();

        SoccerNowApp.getInstance().setConnectivityListener(ContentActivity.this);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnackbar(isConnected, findViewById(android.R.id.content), this);
    }

}
