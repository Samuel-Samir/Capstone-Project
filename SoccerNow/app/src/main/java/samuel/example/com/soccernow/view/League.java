package samuel.example.com.soccernow.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import samuel.example.com.soccernow.R;
import samuel.example.com.soccernow.model.ApiInterface;
import samuel.example.com.soccernow.model.articleModel.NewsResponse;
import samuel.example.com.soccernow.model.football.Competition;
import samuel.example.com.soccernow.utilities;

/**
 * A simple {@link Fragment} subclass.
 */
public class League extends Fragment {

    public League() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View rootView =inflater.inflate(R.layout.fragment_league, container, false);
         int x  = utilities.competitions.size();
        Log.e("a7a" , String.valueOf(x));
        //oadNewsResponse ( );
        return rootView ;
    }




}
