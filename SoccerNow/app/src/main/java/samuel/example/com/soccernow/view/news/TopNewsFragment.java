package samuel.example.com.soccernow.view.news;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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


public class TopNewsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private NewsAdapter newsAdapter;
    private List<Article> topNewsArticles ;
    public static String BUNDLE_TOP_NEWS ="topNews";
    public static String TOP_NEWS_TAG ="topNewsTag";




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_top_news, container, false);

       // newsOrder = "top";
        //newsOrder = "latest";

        newsAdapter = new NewsAdapter();
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(newsAdapter);
        loadNewsResponse ();

        newsAdapter.setRecyclerViewCallback(new NewsAdapter.RecyclerViewCallback() {
            @Override
            public void onItemClick(int position) {

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
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {

                Toast.makeText(getContext() , " error" , Toast.LENGTH_SHORT).show();
            }
        });
    }


}
