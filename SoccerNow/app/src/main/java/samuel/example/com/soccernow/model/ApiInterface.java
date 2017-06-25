package samuel.example.com.soccernow.model;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import samuel.example.com.soccernow.model.articleModel.NewsResponse;

/**
 * Created by samuel on 6/16/2017.
 */

public interface ApiInterface {




    @GET("articles/")
    Call<NewsResponse> getTopNews(@Query("source") String source, @Query("sortBy") String sortby, @Query("apiKey") String apiKey);


    class ApiClient {

        public static final String BASE_URL = "https://newsapi.org/v1/";
        private static Retrofit retrofit = null;


        public static Retrofit getClient() {
            if (retrofit==null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return retrofit;
        }
    }
}
