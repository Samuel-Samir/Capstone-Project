package samuel.example.com.soccernow.view.news;


import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import samuel.example.com.soccernow.model.ApiInterface;
import samuel.example.com.soccernow.adapter.NewsAdapter;
import samuel.example.com.soccernow.R;
import samuel.example.com.soccernow.model.articleModel.Article;
import samuel.example.com.soccernow.model.articleModel.NewsResponse;

/**
 * A simple {@link Fragment} subclass.
 */
public class LatestNewsFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private NewsAdapter newsAdapter;
    private List<Article> latestNewsArticles ;
    private ProgressBar progressBar;
    private String LATEST_SAVE_INSTANCESTATE= "savedInstances";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_latest_news, container, false);

        newsAdapter = new NewsAdapter();
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);

        onOrientationChange(getResources().getConfiguration().orientation , savedInstanceState);

        newsAdapter.setRecyclerViewCallback(new NewsAdapter.RecyclerViewCallback() {
            @Override
            public void onItemClick(int position) {
                Uri webpage = Uri.parse("geo:"+latestNewsArticles.get(position).getUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        return  rootView ;
    }


    public void onOrientationChange(int orientation ,  Bundle savedInstanceState){
        int landScape=2;
        int portrait= 1;
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int widthPixels = metrics.widthPixels;
        int heightPixels = metrics.heightPixels;
        if (widthPixels>=1023 || heightPixels>=1023)
        {
            landScape=3;
            portrait=2;
        }

        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(portrait, StaggeredGridLayoutManager.VERTICAL ));
            mRecyclerView.setAdapter(newsAdapter);

        }
        else if(orientation == Configuration.ORIENTATION_LANDSCAPE){

            mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(landScape, StaggeredGridLayoutManager.VERTICAL ));
            mRecyclerView.setAdapter(newsAdapter);

        }
        loadNewsResponse (savedInstanceState);

    }


    public void loadNewsResponse ( Bundle savedInstanceState)
    {
        if (savedInstanceState==null) {
            ApiInterface apiService = ApiInterface.ApiClient.getClient().create(ApiInterface.class);

            Call<NewsResponse> call = apiService.getTopNews(getResources().getString(R.string.news_source), getResources().getString(R.string.latest_Order), getResources().getString(R.string.news_api_key));
            call.enqueue(new Callback<NewsResponse>() {
                @Override
                public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {

                    latestNewsArticles = response.body().getArticles();

                    newsAdapter.setApiResponse(latestNewsArticles);
                    progressBar.setVisibility(View.GONE);

                }

                @Override
                public void onFailure(Call<NewsResponse> call, Throwable t) {

                    Toast.makeText(getContext(), " error", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);

                }
            });
        }

        else if (savedInstanceState!=null)
        {

            NewsResponse newsResponse =   savedInstanceState.getParcelable(LATEST_SAVE_INSTANCESTATE);

            if(newsResponse.getArticles()!=null)
            {
                latestNewsArticles = newsResponse.getArticles();
                newsAdapter.setApiResponse(latestNewsArticles);
            }
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        NewsResponse newsResponse = new NewsResponse() ;
        newsResponse.setArticles(latestNewsArticles);
        outState.putParcelable(  LATEST_SAVE_INSTANCESTATE , newsResponse);
    }
}
