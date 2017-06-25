package samuel.example.com.soccernow.model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import samuel.example.com.soccernow.SoccerNowApp;

/**
 * Created by samuel on 6/25/2017.
 */

public class ConnectivityReceiver
        extends BroadcastReceiver {

    public static ConnectivityReceiverListener mConnectivityReceiverListener;

    public ConnectivityReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent arg1) {
        ConnectivityManager con_manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        boolean isConnected = con_manager.getActiveNetworkInfo() != null
                && con_manager.getActiveNetworkInfo().isAvailable()
                && con_manager.getActiveNetworkInfo().isConnected();


        if (mConnectivityReceiverListener != null) {
            mConnectivityReceiverListener.onNetworkConnectionChanged(isConnected);
        }
    }

    public static boolean isConnected() {

        ConnectivityManager con_manager = (ConnectivityManager) SoccerNowApp.getInstance().getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        return con_manager.getActiveNetworkInfo() != null
                && con_manager.getActiveNetworkInfo().isAvailable()
                && con_manager.getActiveNetworkInfo().isConnected();

    }


    public interface ConnectivityReceiverListener {
        void onNetworkConnectionChanged(boolean isConnected);
    }
}