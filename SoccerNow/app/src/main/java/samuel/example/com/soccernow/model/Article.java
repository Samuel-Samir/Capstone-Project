package samuel.example.com.soccernow.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by samuel on 6/16/2017.
 */

public class Article implements Parcelable{

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


    protected Article(Parcel in) {
        author = in.readString();
        title = in.readString();
        description = in.readString();
        url = in.readString();
        urlToImage = in.readString();
        publishedAt = in.readString();
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(author);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(url);
        dest.writeString(urlToImage);
        dest.writeString(publishedAt);
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