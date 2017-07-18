package samuel.example.com.soccernow;

import android.app.Application;

import samuel.example.com.soccernow.model.ConnectivityReceiver;

/**
 * Created by samuel on 6/25/2017.
 */

public class SoccerNowApp extends Application {

    private static SoccerNowApp mInstance;

    public static synchronized SoccerNowApp getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        if (listener != null)
            ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}