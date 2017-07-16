package samuel.example.com.soccernow;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;

import java.util.List;

import samuel.example.com.soccernow.model.ConnectivityReceiver;

/**
 * Created by samuel on 6/25/2017.
 */

public class utilities {

    public static int currentChoose=1;
    public static boolean checkInternetConnection() {
        return ConnectivityReceiver.isConnected();
    }

    public static void showSnackbar(boolean isConnected, View view, Context context) {
        if (isConnected) {
            showSnackbarConnected(view, context);
        } else {
            showSnackbarDisconnected(view, context);
        }
    }

    private static void showSnackbarConnected(View view, Context context) {
        Snackbar snackbar = Snackbar.make(view, R.string.snackbar_message_connected, Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        snackbar.show();
    }

    public static void showSnackbarDisconnected(View view, Context context) {
        Snackbar snackbar = Snackbar.make(view, R.string.snackbar_message_disconnected, Snackbar.LENGTH_INDEFINITE);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(context, R.color.red));
        snackbar.show();
    }

    public static boolean isTablet(Context context) {
        boolean xlarge = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE);
        boolean large = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
        return (xlarge || large);
    }

    public static void saveFavoritLeagueSharedPreferences(int leagueCode, Context context) {

        SharedPreferences.Editor editor = context.getSharedPreferences(context.getString(R.string.fav_league), Context.MODE_PRIVATE).edit();
        editor.putString(context.getString(R.string.league_code), String.valueOf(leagueCode));
        editor.commit();


    }


    public static int getFavoritLeagueFromSharedPreferences (Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.fav_league), Context.MODE_PRIVATE);
        String code = (sharedPref.getString(context.getString(R.string.league_code), "-1"));

        return Integer.valueOf(code);
    }
}
