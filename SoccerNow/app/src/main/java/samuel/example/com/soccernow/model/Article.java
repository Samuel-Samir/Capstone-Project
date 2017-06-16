package samuel.example.com.soccernow.model;

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

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
}

/*
            "author": "Josh Lawless",
            "title": "BREAKING: Everton Confirm £23.6 Million Signing | SPORTbible",
            "description": "The Toffees aren't messing about...",
            "url": "http://www.sportbible.com/football/news-breaking-everton-confirm-263-million-signing-20170615",
            "urlToImage": "http://20.theladbiblegroup.com/s3/content/808x455/056c3b9359426752a11d5b59e53d77be.png",
            "publishedAt": "2017-06-15T21:11:56"
 */