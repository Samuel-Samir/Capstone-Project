package samuel.example.com.soccernow.view.news;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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


public class TopNewsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private NewsAdapter newsAdapter;
    private List<Article> topNewsArticles ;
    private ProgressBar progressBar;
    public static String BUNDLE_TOP_NEWS ="topNews";
    public static String TOP_NEWS_TAG ="topNewsTag";




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_top_news, container, false);


        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        newsAdapter = new NewsAdapter();
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(newsAdapter);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);

        loadNewsResponse ();

        newsAdapter.setRecyclerViewCallback(new NewsAdapter.RecyclerViewCallback() {
            @Override
            public void onItemClick(int position) {

                Uri webpage = Uri.parse(topNewsArticles.get(position).getUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        return  rootView ;
    }


    public void loadNewsResponse ()
    {
        ApiInterface apiService = ApiInterface.ApiClient.getClient().create(ApiInterface.class);
        Call<NewsResponse> call =apiService.getTopNews("talksport" , "top" , "27819ced7daf46d5ac106af434a7c7db");
        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {

                topNewsArticles = response.body().getArticles();

                newsAdapter.setApiResponse(topNewsArticles);
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {

                Toast.makeText(getContext() , " error" , Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);

            }
        });
    }


}
