package samuel.example.com.soccernow.model.articleModel;


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

    public  NewsResponse () {}
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

    public List<Article> getArticles() {
        return articles;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeString(source);
        dest.writeString(sortBy);

        dest.writeTypedList(articles);
    }
}

