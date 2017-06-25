package samuel.example.com.soccernow.model.articleModel;


import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by samuel on 6/16/2017.
 */

public class NewsResponse  {

    @SerializedName("status")
    private String status ;
    @SerializedName("source")
    private String source ;
    @SerializedName("sortBy")
    private String sortBy ;
    @SerializedName("articles")
    private List <Article> articles ;


    public List<Article> getArticles() {
        return articles;
    }

}

