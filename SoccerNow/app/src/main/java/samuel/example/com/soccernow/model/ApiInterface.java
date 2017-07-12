package samuel.example.com.soccernow.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import samuel.example.com.soccernow.model.articleModel.NewsResponse;
import samuel.example.com.soccernow.model.football.LeagueTableResponse;

/**
 * Created by samuel on 6/16/2017.
 */

public interface ApiInterface {





    @GET("articles/")
    Call<NewsResponse> getTopNews(@Query("source") String source, @Query("sortBy") String sortby, @Query("apiKey") String apiKey);
/*
    @Headers("X-Auth-Token: 8899bb9e61d04e20b2de5ec3d26e5ecf")
    @GET("competitions")
    Call<List<Competition>> getf();
*/

    @Headers("X-Auth-Token: 8899bb9e61d04e20b2de5ec3d26e5ecf")
    @GET("competitions/{champion_id}/leagueTable")
    Call<LeagueTableResponse>  getLeagueTable (@Path("champion_id") int championId);

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

    class ApiClientFootBall {

        public static final String BASE_URL_FOOT_BALL = "http://api.football-data.org/v1/";
        private static Retrofit retrofit = null;


        public static Retrofit getClient() {
            if (retrofit==null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL_FOOT_BALL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return retrofit;
        }
    }
}
