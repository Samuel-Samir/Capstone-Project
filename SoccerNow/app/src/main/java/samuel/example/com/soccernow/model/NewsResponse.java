package samuel.example.com.soccernow.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by samuel on 6/16/2017.
 */

public class NewsResponse implements Parcelable {

    @SerializedName("status")
    private String status ;
    @SerializedName("source")
    private String source ;
    @SerializedName("sortBy")
    private String sortBy ;
    @SerializedName("articles")
    private List <Article> articles ;


    protected NewsResponse(Parcel in) {
        status = in.readString();
        source = in.readString();
        sortBy = in.readString();
        articles = in.createTypedArrayList(Article.CREATOR);
    }

    public static final Creator<NewsResponse> CREATOR = new Creator<NewsResponse>() {
        @Override
        public NewsResponse createFromParcel(Parcel in) {
            return new NewsResponse(in);
        }

        @Override
        public NewsResponse[] newArray(int size) {
            return new NewsResponse[size];
        }
    };

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeString(source);
        dest.writeString(sortBy);
        dest.writeTypedList(articles);
    }
}

/*
    "status": "ok",
    "source": "the-sport-bible",
    "sortBy": "top",
 */