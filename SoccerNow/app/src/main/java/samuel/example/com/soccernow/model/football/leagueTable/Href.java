package samuel.example.com.soccernow.model.football.leagueTable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by samuel on 7/15/2017.
 */

public class Href {
    @SerializedName("href")
    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
