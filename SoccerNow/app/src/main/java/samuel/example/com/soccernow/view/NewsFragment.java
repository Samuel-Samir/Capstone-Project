package samuel.example.com.soccernow.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import samuel.example.com.soccernow.ApiInterface;
import samuel.example.com.soccernow.NewsAdapter;
import samuel.example.com.soccernow.R;
import samuel.example.com.soccernow.model.Article;
import samuel.example.com.soccernow.model.NewsResponse;

public class NewsFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_top_news, container, false);

        return  rootView ;
    }



}
