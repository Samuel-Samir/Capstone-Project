package samuel.example.com.soccernow.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import samuel.example.com.soccernow.R;

public class SplashScreenActivity extends AppCompatActivity implements Runnable {
    private static final Integer SPLASH_SCREEN_DELAY = 1500;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new Handler().postDelayed(this, SPLASH_SCREEN_DELAY);
    }

    @Override
    public void run() {
        Intent intent = new Intent(SplashScreenActivity.this , FavoriteleagueActivity.class);
        startActivity(intent);
        finish();
    }
}
