package samuel.example.com.soccernow.view.news;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.BinderThread;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
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

import static samuel.example.com.soccernow.utilities.checkInternetConnection;
import static samuel.example.com.soccernow.utilities.isTablet;


public class TopNewsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private NewsAdapter newsAdapter;
    private List<Article> topNewsArticles ;
    private ProgressBar progressBar;
    private LinearLayout errorLinearLayout ;
    private Button retryConnection;
    private String TOP_SAVE_INSTANCESTATE= "savedInstances2";
    public static String BUNDLE_TOP_NEWS ="topNews";
    public static String TOP_NEWS_TAG ="topNewsTag";





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_top_news, container, false);

        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        newsAdapter = new NewsAdapter();
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        errorLinearLayout = (LinearLayout) rootView.findViewById(R.id.connection_error);
        retryConnection =(Button) rootView.findViewById(R.id.retry_button);
        progressBar.setVisibility(View.VISIBLE);
        onOrientationChange(getResources().getConfiguration().orientation , savedInstanceState);

        retryConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadNewsResponse(null);
            }
        });

        return  rootView ;
    }


    public void onOrientationChange(int orientation ,  Bundle savedInstanceState){
        int landScape=2;
        int portrait= 1;
        if (isTablet(getContext()))
        {
            landScape=2;
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

            if (checkInternetConnection()) {

                mRecyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                errorLinearLayout.setVisibility(View.GONE);
                ApiInterface apiService = ApiInterface.ApiClient.getClient().create(ApiInterface.class);

                Call<NewsResponse> call = apiService.getTopNews(getResources().getString(R.string.news_source), getResources().getString(R.string.top_Order), getResources().getString(R.string.news_api_key));
                call.enqueue(new Callback<NewsResponse>() {
                    @Override
                    public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {

                        topNewsArticles = response.body().getArticles();

                        newsAdapter.setApiResponse(topNewsArticles);
                        progressBar.setVisibility(View.GONE);

                    }

                    @Override
                    public void onFailure(Call<NewsResponse> call, Throwable t) {

                        Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.something_wrong), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);

                    }
                });
            }
            else {
                mRecyclerView.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                errorLinearLayout.setVisibility(View.VISIBLE);



            }
        }

        else if (savedInstanceState!=null)
        {

            NewsResponse newsResponse =   savedInstanceState.getParcelable(TOP_SAVE_INSTANCESTATE);

            if(newsResponse.getArticles()!=null)
            {
                topNewsArticles = newsResponse.getArticles();
                newsAdapter.setApiResponse(topNewsArticles);
            }
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        NewsResponse newsResponse = new NewsResponse() ;
        newsResponse.setArticles(topNewsArticles);
        outState.putParcelable(  TOP_SAVE_INSTANCESTATE , newsResponse);
    }


}
