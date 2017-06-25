package samuel.example.com.soccernow.model.articleModel;


import com.google.gson.annotations.SerializedName;

/**
 * Created by samuel on 6/16/2017.
 */

public class Article {

    @SerializedName("author")
    private String author ;
    @SerializedName("title")
    private String title ;
    @SerializedName("description")
    private String description ;
    @SerializedName("url")
    private String url;
    @SerializedName("urlToImage")
    private String urlToImage ;
    @SerializedName("publishedAt")
    private String publishedAt ;



    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }


}

