package samuel.example.com.soccernow;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import samuel.example.com.soccernow.model.NewsResponse;

/**
 * Created by samuel on 6/16/2017.
 */

public interface ApiInterface {


    @GET("/")
    Call<NewsResponse> getTopNews ();
    /*


       @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
    */


    class ApiClient {

        public static final String BASE_URL = "https://newsapi.org/v1/articles";
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
