package samuel.example.com.soccernow.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by samuel on 6/16/2017.
 */

public class NewsResponse {

    @SerializedName("status")
    private String status ;
    @SerializedName("source")
    private String source ;
    @SerializedName("sortBy")
    private String sortBy ;
    @SerializedName("articles")
    private List <Article> articles ;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }


}

/*
    "status": "ok",
    "source": "the-sport-bible",
    "sortBy": "top",
 */